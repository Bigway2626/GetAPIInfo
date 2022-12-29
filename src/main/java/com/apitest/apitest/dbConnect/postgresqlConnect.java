package com.apitest.apitest.dbConnect;

import java.sql.*;

import static com.apitest.apitest.entity.Talk.getCoinMessage;

/**
 * @author bigway2626.huang
 */
public class postgresqlConnect {
    //jdbc:postgresql://<database_host>:<port>/<database_name>
    private ResultSet resultSet = null;
    private final String url = "jdbc:postgresql://localhost/postgres";
    private final String user = "postgres";
    private final String password = "1234";

    String[] CoinData = getCoinMessage("USD");

    public Connection SelectData() {
        Connection conn = null;
        String selectSql = "SELECT * FROM coinvalue ";
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(selectSql);
            System.out.println("Connected to the PostgreSQL server successfully.");
            System.out.println(conn);
            // Print results from select statement getString為取得的欄位值
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public Connection InsertData() {
        String InserSql = "INSERT INTO coinvalue(code,Time,symbol,rate,description,rate_float) VALUES" + "(" + "'" + CoinData[0] + "'" + "," + "'" + CoinData[5] + "'" + "," + "'" + CoinData[1] + "'" + "," + "'" + CoinData[2] + "'" + "," + "'" + CoinData[3] + "'" + "," + "'" + CoinData[4] + "'" + ")";
        //System.out.println(InserSql);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(InserSql);
            System.out.println("Insert to the PostgreSQL server successfully.");
            //System.out.println(conn);
            // Print results from select statement getString為取得的欄位值
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        postgresqlConnect PgTest = new postgresqlConnect();
        PgTest.InsertData();
        PgTest.SelectData();
    }
}
