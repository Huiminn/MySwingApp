package com.huimin.swingApp.GUIShow;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.huimin.swingApp.ConstantVariable.ConstantVar;
import com.huimin.swingApp.encrypteUtils.MD5Algorithm;
import com.huimin.swingApp.entity.User;
import com.huimin.swingApp.service.UserLoginService;
import com.huimin.swingApp.service.impl.UserLoginServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.FormatFlagsConversionMismatchException;
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
	private JPanel panel;
	final long timeInterval = 100;
	private   Runnable runnable;
	private JTextField textStudentNo;
	private JLabel lblStudentNo ;
	private JLabel lblPassword;
	private boolean flag = false;
	public  UserLogin() {
		setBackground(Color.PINK);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(449, 113, 280, 247);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(20, 153, 82, 14);
		panel.add(lblPassword);
	
		
		JLabel label = new JLabel("Username:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(20, 60, 82, 14);
		panel.add(label);
		
		textUserName = new JTextField();
		textUserName.setBounds(121, 59, 132, 20);
		panel.add(textUserName);
		textUserName.setColumns(10);
		
		textStudentNo = new JTextField();
		textStudentNo.setBounds(121, 103, 132, 20);
		panel.add(textStudentNo);
		textStudentNo.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(121, 148, 132, 20);
		panel.add(textPassword);
		
		btnLogin = new JButton("Login");
		
		//Register button action event to button of btnLogin
		btnLogin.addActionListener(this);
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setBounds(20, 202, 89, 23);
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
		
		lblStudentNo = new JLabel("StudentNo:");
		lblStudentNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudentNo.setBounds(20, 104, 82, 14);
		panel.add(lblStudentNo);
		
		
		
		JLabel lblTrainTicketOrdering = new JLabel("User Login");
		lblTrainTicketOrdering.setBounds(470, 73, 97, 29);
		getContentPane().add(lblTrainTicketOrdering);
		lblTrainTicketOrdering.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(22, 26, 416, 420);
		getContentPane().add(lblBackground);
		
		ImageIcon imageIcon = new ImageIcon("./src/main/images/loginBackground1.jpg");
		lblBackground.setIcon(imageIcon);
		getContentPane().add(lblBackground);
		
		lblLoginNotice = new JLabel("");
		lblLoginNotice.setBounds(558, 73, 233, 30);
		getContentPane().add(lblLoginNotice);
		
		userLoginService = new UserLoginServiceImpl();
		md5Algorithm = new MD5Algorithm();
		
		/*******create a thread to check the choice, student or admin*******/
		createThread();
		
		this.setBounds(200, 150, 817, 499);
		this.setTitle("User Login");
		
		
	}
	
	private void createThread(){
		runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    //------- code for task to run  
                     if(choiceRole.getSelectedItem().equals("Admin")){
                    /*	 System.out.println("your admin");*/
                     	panel.remove(textStudentNo);
                      	panel.remove(lblStudentNo);
                      	lblPassword.setBounds(20, 104, 82, 14);
                      	textPassword.setBounds(121, 103, 132, 20);
                      	btnLogin.setBounds(20, 153, 89, 23);
                      	panel.repaint();
                      	flag = false;
                     }else if(choiceRole.getSelectedItem().equals("Student")){
                    	 lblPassword.setBounds(20, 153, 82, 14);
                    	 textPassword.setBounds(121, 148, 132, 20);
                    	 btnLogin.setBounds(20, 202, 89, 23);                    	
                    	 if(!flag){
                    		 panel.add(lblStudentNo);
                        	 panel.add(textStudentNo);
                    		 panel.repaint();
                    		 flag = true;
                    	 }                  	
                     }               
                	 try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  
                }  
            }  
	     };  
	     Thread thread = new Thread(runnable);  
	     thread.start(); 
		
	}
	@SuppressWarnings({ "deprecation", "unused" })
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String password = null;
		User user = null;
		if(obj == btnLogin){			
			
			switch(choiceRole.getSelectedIndex()){
			case 0:
					/***select user record according to studentNo****/
					user = userLoginService.selectUserByStudentNo(textStudentNo.getText().toString());
					if(user == null ||!user.getUserName().equals(textUserName.getText()))
					{
						lblLoginNotice.setText("The studentNo incorrect");
						break;
					}
					if(checkLogin(user,password)){
						this.setVisible(false);
						StudentAutoServiceJFrame sasj = new StudentAutoServiceJFrame();
						sasj.setVisible(true);
					}else{
						System.out.println("1111");
						lblLoginNotice.setText("user name or password incorrect");	
					} 
					
					break;
			case 1: 
					/***select user record according user name and user role****/
					user = userLoginService.selectUserByUsernameAndRoleName(textUserName.getText(), choiceRole.getSelectedItem());
					if(checkLogin(user, password)){
						this.setVisible(false);
						Main main = new Main();
						main.setVisible(true);
					}else lblLoginNotice.setText("user name or password incorrect");					
					break;
			default:
		}
		}
	}
	
	private boolean checkLogin(User user,String password){
		if(user == null)
			return false;
		System.out.println("userid: "+user.getUserid());
		password = md5Algorithm.md5Encryption(textPassword.getText().toString().getBytes());
		if(password.equals(user.getPassword())&&user.getUserName().equals(textUserName.getText())){
			ConstantVar.userName = user.getUserName();
			ConstantVar.sessionUserid = user.getUserid();
			return true;
		}else return false;
	}
	public static void main(String[] args){
		UserLogin userLogin = new UserLogin();
		userLogin.setVisible(true);
		userLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
