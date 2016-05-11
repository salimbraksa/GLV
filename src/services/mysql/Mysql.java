package services.mysql;

import java.sql.*;

/**
 * Created by Salim on 4/26/16.
 */
public class Mysql {

    private static Mysql sharedInstance = new Mysql();
    public static Mysql sharedInstance() {
        return sharedInstance;
    }
    private Connection connection;

    Statement currentStatement;

    private Mysql() {

    }

    // Connection

    private void connect() {

        try {

            // register the driver
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);

            // Connect
            connection = DriverManager.getConnection("jdbc:sqlite:src/resources/database/production.db");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    // Queries Methods

    public ResultSet executeQuery(String query) {
        try {
            connect();
            currentStatement = connection.createStatement();
            ResultSet result = currentStatement.executeQuery(query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeUpdate(String update) {
        Statement stmt = null;
        try {
            connect();
            stmt = connection.createStatement();
            stmt.executeUpdate(update);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {

        try {
            currentStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
