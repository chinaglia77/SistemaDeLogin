package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DTO.UsuarioDTO;

public class UsuarioDAO {
    public void inserirUser(UsuarioDTO user) {

        String sql = "INSERT INTO USUARIOS(USERNAME, PASSWORD, EMAIL) VALUE(?, ?, ?)";
        PreparedStatement ps;
        
        try {

            ps = ConexaoDAO.getConexaoDao().prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            ps.execute();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public UsuarioDTO buscarUser(String email, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        try {
            conn = ConexaoDAO.getConexaoDao();
            ps = conn.prepareStatement(
                    "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USUARIOS WHERE EMAIL = ? AND PASSWORD = ?");
            ps.setString(1, email);
            ps.setString(2, password);
    
            rs = ps.executeQuery();
    
            if (rs.next()) {
                UsuarioDTO usuario = new UsuarioDTO();
                usuario.setPassword(rs.getString("PASSWORD"));
                usuario.setEmail(rs.getString("EMAIL"));
                JOptionPane.showMessageDialog(null, "ACESSADO COM SUCESSO");
                return usuario;
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha inválidos", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public boolean verificarEmail(String email)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sqlVerificarEmail = "SELECT * FROM usuarios WHERE email = ?";

            new ConexaoDAO();
            conn = ConexaoDAO.getConexaoDao();
            ps = conn.prepareStatement(sqlVerificarEmail);
            ps.setString(1, email);
            rs = ps.executeQuery();

            return rs.next();
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao buscar email no banco de dados", "Verificar email", JOptionPane.ERROR_MESSAGE);
        }
        return false;


    }
}
