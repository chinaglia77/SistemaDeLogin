package DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {
     private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static java.sql.Connection conn;

    public static java.sql.Connection getConexaoDao() {

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                return conn;
            } else {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERRO AO CONETAR AO BANCO DE DADOS", "BANCO DE DADOS",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }
}
