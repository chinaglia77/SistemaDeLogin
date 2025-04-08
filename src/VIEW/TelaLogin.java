package VIEW;

import java.sql.SQLException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;

public class TelaLogin extends JFrame implements ActionListener {
    
    private final JLabel labelErroLogin = new JLabel();
    private final JButton btnLogin = new JButton();
    private final JButton btnCadastro = new JButton();
    private final JTextField campoEmail = new JTextField();
    private final JPasswordField campoSenha = new JPasswordField();
    private final JLabel labelCriarConta = new JLabel();
    private final JLabel labelTitulo = new JLabel();
    private final JLabel labelEmail = new JLabel();
    private final JLabel labelSenha = new JLabel();
    private final JFrame frame = new JFrame();

    public TelaLogin() {

        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setFont(new Font("Inter", Font.BOLD, 30));
        labelTitulo.setText("LOGIN");
        labelTitulo.setBounds(143, 60, 100, 25);

        labelEmail.setForeground(Color.WHITE);
        labelEmail.setFont(new Font("Inter", Font.BOLD, 12));
        labelEmail.setText("EMAIL");
        labelEmail.setBounds(41, 110, 100, 25);

        labelSenha.setForeground(Color.WHITE);
        labelSenha.setFont(new Font("Inter", Font.BOLD, 12));
        labelSenha.setText("SENHA");
        labelSenha.setBounds(41, 190, 100, 25);

        labelCriarConta.setText("Não tem uma conta? Cadastre-se agora!");
        labelCriarConta.setFont(new Font("Inter", Font.BOLD, 10));
        labelCriarConta.setForeground(Color.WHITE);
        labelCriarConta.setFont(new Font("Inter", Font.BOLD, 12));
        labelCriarConta.setBounds(67, 325, 230, 35);

        campoEmail.setFont(new Font("Inter", Font.PLAIN, 17));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setBackground(new Color(29, 29, 29));
        campoEmail.setBounds(39, 135, 300, 35);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);

        campoSenha.setFont(new Font("Inter", Font.PLAIN, 17));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setBackground(new Color(29, 29, 29));
        campoSenha.setBounds(39, 215, 300, 35);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);

        campoEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    campoSenha.requestFocusInWindow();
                }
            }
        });

        campoSenha.addActionListener(e -> btnLogin.doClick());

        btnLogin.setText("LOGIN");
        btnLogin.setFont(new Font("Inter", Font.BOLD, 15));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setCursor(new Cursor(HAND_CURSOR));
        btnLogin.setBorderPainted(false);
        btnLogin.setBounds(39, 275, 300, 35);
        btnLogin.addActionListener(this);

        btnCadastro.setText("CRIAR CONTA");
        btnCadastro.setFont(new Font("Inter", Font.BOLD, 15));
        btnCadastro.setBackground(Color.BLUE);
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setCursor(new Cursor(HAND_CURSOR));
        btnCadastro.setBorderPainted(false);
        btnCadastro.setBounds(39, 380, 300, 35);
        btnCadastro.addActionListener(this);

        frame.add(labelCriarConta);
        frame.add(btnCadastro);
        frame.add(btnLogin);
        frame.add(labelSenha);
        frame.add(campoSenha);
        frame.add(labelEmail);
        frame.add(labelTitulo);
        frame.add(campoEmail);

        frame.setSize(400, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(22, 22, 22));
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnCadastro)) {
            abrirTelaCadastro();
        } else if (e.getSource().equals(btnLogin)) {
            verificarLogin();
        }
    }

    private void abrirTelaCadastro() {
        new TelaCadastro();
    }

    private void verificarLogin() {
        UsuarioDTO user = new UsuarioDTO();
        user.setEmail(campoEmail.getText().toLowerCase());
        user.setPassword(campoSenha.getText().toLowerCase());

        UsuarioDAO uDao = new UsuarioDAO();

        if (campoEmail.getText().isEmpty() || campoSenha.getText().isEmpty()) {
            campoEmail.setBorder(BorderFactory.createLineBorder(Color.RED));
            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED));

            labelErroLogin.setText("Por favor preencha todos os campos");
            labelErroLogin.setFont(new Font("Inter", Font.PLAIN, 12));
            labelErroLogin.setForeground(Color.RED);
            labelErroLogin.setBounds(39, 244, 300, 35);
            frame.add(labelErroLogin);
            frame.revalidate();
            frame.repaint();
        } 
        else {
            try {
                uDao.buscarUser(user.getEmail(), user.getPassword());
                labelErroLogin.setText("");
                campoEmail.setBorder(null);
                campoEmail.setBorder(null);
                campoSenha.setBorder(null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
