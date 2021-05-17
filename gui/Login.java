package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login implements ActionListener {
	
	private JFrame frameLogin;
	private JPanel panel;
	private JButton login;
	private JTextField userText;
	private JTextField passwordText;
	private JLabel success;
	
	public Login() {
		
		frameLogin = new JFrame();
		panel = new JPanel();
		
		frameLogin.setSize(350, 200);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.add(panel);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogin.setTitle("Modern Geek Games");
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		login = new JButton("Login");
		login.setBounds(130, 90, 80, 25);
		login.addActionListener(this);
		panel.add(login);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		frameLogin.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		
		String user = userText.getText();
		String password = passwordText.getText();
		
		if(user.equals("Noah") && password.equals("password")) {
			success.setText("Login successful");
			new GUI();
			frameLogin.dispose();
		} else {
			success.setText("Invalid username or password");
		}
		
	}

	
	
}
