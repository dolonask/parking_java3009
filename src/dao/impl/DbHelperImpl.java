package dao.impl;

import dao.DbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelperImpl implements DbHelper {

    @Override
    public void initDatabase() {
        Connection connection = getConnection();

        String[] ddl = {
                "CREATE TABLE 'cars' (\n" +
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "'car_number' TEXT NOT NULL \n" +
                        ");",
                "CREATE TABLE 'entries' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "'start_date' TEXT NOT NULL ,\n" +
                        "'end_date' TEXT DEFAULT NULL,\n" +
                        "'status' TEXT NOT NULL ,\n" +
                        "'id_cars' INTEGER REFERENCES 'cars' ('id')\n" +
                        ");"
        };

        try {
            Statement statement = connection.createStatement();
            for (String query: ddl)
                statement.addBatch(query);

            statement.executeBatch();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Connection getConnection() {
        String url = "jdbc:sqlite:parking.db";
        try {
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
