package com.jarby.swingApp.GUIShow;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.jarby.swingApp.ConstantVariable.ConstantVar;
import com.jarby.swingApp.encrypteUtils.MD5Algorithm;
import com.jarby.swingApp.entity.User;
import com.jarby.swingApp.service.UserLoginService;
import com.jarby.swingApp.service.impl.UserLoginServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;

@SuppressWarnings("serial")
public class UserLogin extends JFrame implements ActionListener {
	private JTextField textUserName;
	private JPasswordField textPassword;
	private Choice choiceRole;
	private JButton btnLogin;
	private UserLoginService userLoginService;
	private MD5Algorithm md5Algorithm;
	private JLabel lblLoginNotice;
	public  UserLogin() {
		setBackground(Color.PINK);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(448, 137, 280, 189);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Password:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(20, 104, 82, 14);
		panel.add(lblUsername);
		
		JLabel label = new JLabel("Username:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(20, 60, 82, 14);
		panel.add(label);
		
		textUserName = new JTextField();
		textUserName.setBounds(121, 59, 132, 20);
		panel.add(textUserName);
		textUserName.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(121, 103, 132, 20);
		panel.add(textPassword);
		
		btnLogin = new JButton("Login");
		
		//Register button action event to button of btnLogin
		btnLogin.addActionListener(this);
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(20, 153, 89, 23);
		panel.add(btnLogin);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRole.setBounds(20, 23, 46, 14);
		panel.add(lblRole);
		
		choiceRole = new Choice();
		choiceRole.setBounds(121, 23, 89, 20);
		choiceRole.add("Student");
		choiceRole.add("Admin");
		panel.add(choiceRole);
		
		JLabel lblTrainTicketOrdering = new JLabel("User Login");
		lblTrainTicketOrdering.setBounds(569, 48, 97, 29);
		getContentPane().add(lblTrainTicketOrdering);
		lblTrainTicketOrdering.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(22, 26, 416, 420);
		getContentPane().add(lblBackground);
		
		ImageIcon imageIcon = new ImageIcon("./src/main/images/loginBackground1.jpg");
		lblBackground.setIcon(imageIcon);
		getContentPane().add(lblBackground);
		
		lblLoginNotice = new JLabel("");
		lblLoginNotice.setBounds(473, 96, 214, 30);
		getContentPane().add(lblLoginNotice);
		
		userLoginService = new UserLoginServiceImpl();
		md5Algorithm = new MD5Algorithm();
		this.setBounds(200, 150, 817, 499);
		this.setTitle("User Login");
	}
	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String password = null;
		User user = null;
		if(obj == btnLogin){
			/***select user record according user name and user role****/
			user = userLoginService.selectUserByUsernameAndRoleName(textUserName.getText(), choiceRole.getSelectedItem());
			if(!(user == null)){
				password = md5Algorithm.md5Encryption(textPassword.getText().toString().getBytes());
				if(password.equals(user.getPassword())){
					ConstantVar.userName = user.getUserName();
					ConstantVar.sessionUserid = user.getUserid();
					if(user.getRoleName().equals("Student")){
						this.setVisible(false);
						StudentAutoServiceJFrame studentAutoServiceJFrame = new StudentAutoServiceJFrame();
						studentAutoServiceJFrame.setVisible(true);
					}
					else if(user.getRoleName().equals("Admin")){
						this.setVisible(false);
						Main main = new Main();
						main.setVisible(true);
					}
				}
			}else{
				lblLoginNotice.setText("user name or password incorrect");
				this.setVisible(true);
			} 
				
				
					
			/*	Main main = new Main();
			this.setVisible(false);
			main.setVisible(true);*/
		}
	}
	
	public static void main(String[] args){
		UserLogin userLogin = new UserLogin();
		userLogin.setVisible(true);
		userLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
