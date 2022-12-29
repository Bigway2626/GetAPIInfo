package com.apitest.apitest.dbConnect;

import java.sql.*;

/**
 * @author bigway2626.huang
 */
public class H2Utils {
    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:todolist";
    //http://localhost:9100/h2-console/網址
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection conn = getConnection();

    public static void main(String[] args) throws SQLException {

        System.out.println(conn);
    }

    private static Connection getConnection() {
        ResultSet resultSet = null;
        if (null != conn) {
            return conn;
        }
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = conn.createStatement();
            //String selectSql = "SELECT * FROM TODO ";
            // resultSet = statement.executeQuery(selectSql);
            if (null != conn) {
                System.out.println("H2数据库连接成功！");
            }
            /*while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }*/
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;

    }
}
