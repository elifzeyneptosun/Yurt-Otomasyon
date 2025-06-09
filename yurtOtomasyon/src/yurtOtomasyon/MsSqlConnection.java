package swingdeneme;

import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MsSqlConnection {

    private Connection con;

    public Connection connect() {

        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dsn = "jdbc:sqlserver://localhost:1433;databaseName=yurtOtomasyon;encrypt=true;trustServerCertificate=true;";
        String username = "localhost";
        String password = "123456789";

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(dsn, username, password);
            System.out.println("Connected");

        } catch (Exception ex) {

            System.out.println("not Connected");
            ex.printStackTrace();
        }

        return con;
    }

    public void Close(Connection con) {

        try {

            this.con = con;
            con.close();
        } catch (Exception ex) {

            ex.getMessage();
        }

    }

    public static void main(String args[]) {

        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;

        try {

            con = new MsSqlConnection().connect();
            System.out.println("this will be written to the text file after connection");
            pstmt = con.prepareStatement("select*from  PersonelCalisma");
            rs = pstmt.executeQuery();

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }
            con.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
