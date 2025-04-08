package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;

public class TelaCadastro extends JFrame implements ActionListener {
    private final JButton btnRegistrar = new JButton();
    private final JButton btnJaCadastrado = new JButton();
    private final JTextField campoEmail = new JTextField();
    private final JTextField campoUsuario = new JTextField();
    private final JTextField campoSenha = new JTextField();
    private final JLabel lblErroCadastro = new JLabel();
    private final JLabel lblErroSenhaCurta = new JLabel();
    private final JLabel lblErroCamposVazios = new JLabel();
    private final JLabel lblTituloCadastro = new JLabel();
    private final JLabel lblEmail = new JLabel();
    private final JLabel lblUsuario = new JLabel();
    private final JLabel lblSenha = new JLabel();
    private final JFrame janelaCadastro = new JFrame();

    public TelaCadastro() {

        lblTituloCadastro.setForeground(Color.WHITE);
        lblTituloCadastro.setFont(new Font("Inter", Font.BOLD, 30));
        lblTituloCadastro.setText("CADASTRO");
        lblTituloCadastro.setBounds(107, 60, 170, 25);

        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Inter", Font.BOLD, 12));
        lblEmail.setText("EMAIL");
        lblEmail.setBounds(41, 110, 100, 25);

        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Inter", Font.BOLD, 12));
        lblUsuario.setText("USERNAME");
        lblUsuario.setBounds(41, 190, 100, 25);

        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Inter", Font.BOLD, 12));
        lblSenha.setText("SENHA");
        lblSenha.setBounds(41, 270, 100, 25);

        campoEmail.setFont(new Font("Inter", Font.PLAIN, 17));
        campoEmail.setForeground(Color.WHITE);
        campoEmail.setBackground(new Color(29, 29, 29));
        campoEmail.setBounds(39, 135, 300, 35);
        campoEmail.setCaretColor(Color.WHITE);
        campoEmail.setBorder(null);

        campoUsuario.setFont(new Font("Inter", Font.PLAIN, 17));
        campoUsuario.setForeground(Color.WHITE);
        campoUsuario.setBackground(new Color(29, 29, 29));
        campoUsuario.setBounds(39, 215, 300, 35);
        campoUsuario.setCaretColor(Color.WHITE);
        campoUsuario.setBorder(null);

        campoSenha.setFont(new Font("Inter", Font.PLAIN, 17));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setBackground(new Color(29, 29, 29));
        campoSenha.setBounds(39, 295, 300, 35);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(null);

        campoEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    campoUsuario.requestFocusInWindow();
                }
            }
        });

        campoUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    campoSenha.requestFocusInWindow();
                }
            }
        });

        btnRegistrar.setText("LOGIN");
        btnRegistrar.setFont(new Font("Inter", Font.BOLD, 15));
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setCursor(new Cursor(HAND_CURSOR));
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setBounds(39, 355, 300, 35);
        btnRegistrar.addActionListener(this);

        campoSenha.addActionListener(e -> btnRegistrar.doClick());

        btnJaCadastrado.setText("JÁ TENHO LOGIN");
        btnJaCadastrado.setFont(new Font("Inter", Font.BOLD, 15));
        btnJaCadastrado.setBackground(Color.BLUE);
        btnJaCadastrado.setForeground(Color.WHITE);
        btnJaCadastrado.setCursor(new Cursor(HAND_CURSOR));
        btnJaCadastrado.setBorderPainted(false);
        btnJaCadastrado.setBounds(39, 411, 300, 35);
        btnJaCadastrado.addActionListener(this);

        janelaCadastro.add(btnJaCadastrado);
        janelaCadastro.add(btnRegistrar);
        janelaCadastro.add(lblSenha);
        janelaCadastro.add(campoSenha);
        janelaCadastro.add(lblUsuario);
        janelaCadastro.add(lblEmail);
        janelaCadastro.add(campoUsuario);
        janelaCadastro.add(campoEmail);
        janelaCadastro.add(lblTituloCadastro);

        janelaCadastro.setSize(400, 620);
        janelaCadastro.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janelaCadastro.getContentPane().setBackground(new Color(22, 22, 22));
        janelaCadastro.setLocationRelativeTo(null);
        janelaCadastro.setLayout(null);
        janelaCadastro.setResizable(false);
        janelaCadastro.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(btnJaCadastrado)) {
            janelaCadastro.dispose();
        } else if (campoSenha.getText().equals("") || campoUsuario.getText().equals("") || campoEmail.getText().equals("")) {

            campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED));
            campoUsuario.setBorder(BorderFactory.createLineBorder(Color.RED));
            campoEmail.setBorder(BorderFactory.createLineBorder(Color.RED));

            lblErroCamposVazios.setText("Por favor preencha todos os campos");
            lblErroCamposVazios.setFont(new Font("Inter", Font.PLAIN, 12));
            lblErroCamposVazios.setForeground(Color.red);
            lblErroCamposVazios.setBounds(39, 324, 310, 35);
            lblErroSenhaCurta.setText("");

            janelaCadastro.add(lblErroCamposVazios);
            janelaCadastro.revalidate();
            janelaCadastro.repaint();

        } else if (e.getSource().equals(btnRegistrar)) {

            if (campoSenha.getText().length() <= 8) {
                lblErroCamposVazios.setText("");
                campoSenha.setBorder(BorderFactory.createLineBorder(Color.RED));
                lblErroSenhaCurta.setText("Senha deve ter mais de 8 caracteres");
                lblErroSenhaCurta.setFont(new Font("Inter", Font.PLAIN, 12));
                lblErroSenhaCurta.setForeground(Color.red);
                lblErroSenhaCurta.setBounds(39, 324, 310, 35);
                janelaCadastro.add(lblErroSenhaCurta);
                janelaCadastro.revalidate();
                janelaCadastro.repaint();
                return;
            }

         
        
            UsuarioDTO usuario = new UsuarioDTO();
            UsuarioDAO usuarioDAO = new UsuarioDAO(); 
            usuario.setUsername(campoUsuario.getText());
            usuario.setEmail(campoEmail.getText().toLowerCase());

            if(usuarioDAO.verificarEmail(usuario.getEmail()))
            {
                JOptionPane.showMessageDialog(null, "Email " + usuario.getEmail() + ", já possui cadastro", "Verificar email", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            usuario.setPassword(campoSenha.getText());
            usuarioDAO.inserirUser(usuario);
        
            janelaCadastro.dispose();
        }
    }
}

