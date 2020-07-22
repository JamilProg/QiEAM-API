import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class stores actual API methods which do features.
 * The database connection is done when Actions constructor is called.
 */
class Actions {

    private Connection c = null;
    /**
     *
     * @param dtbasename: Name of the database
     * @param login: PostGreSQL login
     * @param password: PostGreSQL password
     */
    Actions(String dtbasename, String login, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/" + dtbasename,
                            login, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }


    String addLibGames() {
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO LIBGAME "
                    + "VALUES (1, 'Call Of Duty');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO LIBGAME "
                    + "VALUES (2, 'BattleField');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO LIBGAME "
                    + "VALUES (3, 'Homeworld');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO LIBGAME "
                    + "VALUES (4, 'Max Payne');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Library games added");
        return "Library games added";
    }


    String addStoreGames() {
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO STOREGAME "
                    + "VALUES (1, 'Zelda');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO STOREGAME "
                    + "VALUES (2, 'Spiderman');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO STOREGAME "
                    + "VALUES (3, 'Anno 1800');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO STOREGAME "
                    + "VALUES (4, 'Frostpunk');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Store games added");
        return "Store games added";
    }


    String addFriends() {
        Statement stmt;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO FRIEND "
                    + "VALUES (1, 'Kratos');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO FRIEND "
                    + "VALUES (2, 'Atreus');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO FRIEND "
                    + "VALUES (3, 'Thanos');";
            stmt.executeUpdate(sql);

            stmt = c.createStatement();
            sql = "INSERT INTO FRIEND "
                    + "VALUES (4, 'Minerva');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Friends added");
        return "Friends added";
    }


    List<LibGame> getAllLibGames() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qieam",
                "postgres", "root");
             PreparedStatement pst = con.prepareStatement("SELECT * FROM LIBGAME");
             ResultSet rs = pst.executeQuery()) {
            ArrayList<LibGame> res = new ArrayList<>();
            while (rs.next()) {
                LibGame lg = new LibGame(rs.getInt(1), rs.getString(2));
                res.add(lg);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    List<StoreGame> getAllStoreGames() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qieam",
                "postgres", "root");
             PreparedStatement pst = con.prepareStatement("SELECT * FROM STOREGAME");
             ResultSet rs = pst.executeQuery()) {
            ArrayList<StoreGame> res = new ArrayList<>();
            while (rs.next()) {
                StoreGame sg = new StoreGame(rs.getInt(1), rs.getString(2));
                res.add(sg);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    List<Friend> getAllFriends() {
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qieam",
                "postgres", "root");
             PreparedStatement pst = con.prepareStatement("SELECT * FROM FRIEND");
             ResultSet rs = pst.executeQuery()) {
            ArrayList<Friend> res = new ArrayList<>();
            while (rs.next()) {
                Friend f = new Friend(rs.getInt(1), rs.getString(2));
                res.add(f);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}