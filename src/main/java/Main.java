import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;

public class Main {
    // Parameter about API security check
    private static String apiPassword = "qieam";
    // Parameters about database connection
    private static String username = "postgres";
    private static String pwd = "root";
    private static String databaseName = "qieam";
    // Database connection launched, and feature methods are ready to be called
    private static Actions actions = new Actions(databaseName, username, pwd);
    /**
     * Function which convert string to SHA256 hashcode
     * @param input string to hash
     * @return hashcode
     */
    private static String toSHA256(String input) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();

            for (byte h : hash) {
                String hexadecim = Integer.toHexString(0xff & h);
                if (hexadecim.length() == 1) result.append('0');
                result.append(hexadecim);
            }

            return result.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    public static void main(String[] args) {
        // Protect the API with a password
        Scanner sc = new Scanner(System.in);
        final String passToFind = toSHA256(apiPassword);
        String pass;
        System.out.println("Enter the password:");
        while(true){
            pass = toSHA256(sc.nextLine());
            if (pass.equals(passToFind))
                break;
            System.out.println("Wrong password! Try again:");
        }
        System.out.println("Password is correct! The API is now running.");


        // API's body
        Gson gson = new Gson();

        // GET /api/friends
        get("/api/friends", (req, res) -> {
            res.type("application/json");
            return actions.getAllFriends();
        }, gson ::toJson);

        // GET /api/store/games
        get("/api/store/games", (req, res) -> {
            res.type("application/json");
            return actions.getAllStoreGames();
        }, gson ::toJson);

        // GET /api/library/games
        get("/api/library/games", (req, res) -> {
            res.type("application/json");
            return actions.getAllLibGames();
        }, gson ::toJson);

        //// The three next POST features
        //// are there to add rows in the tables

        // Table FRIEND
        post("/api/post/addFriends", (req, res) -> {
            res.type("application/json");
            return actions.addFriends();
        }, gson ::toJson);

        // Table LIBGAME
        post("/api/post/addLibGames", (req, res) -> {
            res.type("application/json");
            return actions.addLibGames();
        }, gson ::toJson);

        // Table STOREGAME
        post("/api/post/addStoreGames", (req, res) -> {
            res.type("application/json");
            return actions.addStoreGames();
        }, gson ::toJson);

    }
}
