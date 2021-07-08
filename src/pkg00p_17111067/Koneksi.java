package pkg00p_17111067;

import java.sql.*;

public class Koneksi {

    String url, usr, pwd, db;

    public void Jdbc_1() {
        db = "db_17111067";
        url = "jdbc:mysql://localhost:3306/" + db;
        usr = "root";
        pwd = "";
    }

    public Connection getConnection() {
        Connection con = null;
        Jdbc_1();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Ok : Driver Ditemukan");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver Tidak Ditemukan \n" + e + "\n");
        } catch (SQLException e) {
            System.out.println("Error:Tidak Bisa Koneksi Ke Database\n"
                    + e + "\n");
        }
        return con;
    }

    private void PembuatDialog() {
        System.out.println("Koneksi database berhasil");
        System.exit(0);
    }

    public static void main(String args[]) {
        Koneksi kon = new Koneksi();
        kon.getConnection();
        kon.PembuatDialog();
    }
}
