package com.huimin.swingApp.GUIShow.userAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.FormatFlagsConversionMismatchException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Choice;
import javax.swing.JTextField;

import com.huimin.swingApp.GUIShow.UserAccountManagementJFrame;
import com.huimin.swingApp.encrypteUtils.MD5Algorithm;
import com.huimin.swingApp.entity.RoleUser;
import com.huimin.swingApp.entity.Student;
import com.huimin.swingApp.entity.User;
import com.huimin.swingApp.service.RoleService;
import com.huimin.swingApp.service.RoleUserService;
import com.huimin.swingApp.service.StudentManagementService;
import com.huimin.swingApp.service.UserAccountManagementService;
import com.huimin.swingApp.service.UserLoginService;
import com.huimin.swingApp.service.impl.RoleServiceImpl;
import com.huimin.swingApp.service.impl.RoleUserServiceImpl;
import com.huimin.swingApp.service.impl.StudentManagementServiceImpl;
import com.huimin.swingApp.service.impl.UserAccountManagementServiceImpl;
import com.huimin.swingApp.service.impl.UserLoginServiceImpl;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class UserAccountAddJFrame extends JFrame implements ActionListener {
	private JTextField textUserName;
	private JPasswordField passwordField;
	private JButton btnSave;
	private JButton btnBack;
	private Choice choiceRole;
	private UserAccountManagementService userAccountManagementService;
	private MD5Algorithm md5Algorithm;
	private JOptionPane jOptionPane;
	private JLabel lblStudentno;
	private JTextField textStudentNo;
	private StudentManagementService studentManagementService;
	private JLabel labelStudentNo;
	private Student student = null;
	private RoleService roleService;
	private User user;
	private RoleUserService roleUserService;
	private RoleUser roleUser;
	private   Runnable runnable;
	final long timeInterval = 100;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private boolean flag = false;
	public UserAccountAddJFrame() {
		
		getContentPane().setLayout(null);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRole.setBounds(56, 50, 46, 14);
		getContentPane().add(lblRole);
		
		choiceRole = new Choice();
		choiceRole.setBounds(146, 50, 66, 26);
		choiceRole.add("Student");
		choiceRole.add("Admin");
		getContentPane().add(choiceRole);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(56, 149, 78, 14);
		getContentPane().add(lblUsername);
		
		textUserName = new JTextField();
		textUserName.setBounds(146, 145, 113, 26);
		getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 205, 113, 26);
		getContentPane().add(passwordField);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(56, 211, 78, 14);
		getContentPane().add(lblPassword);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(56, 259, 69, 30);
		btnSave.addActionListener(this);
		getContentPane().add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(146, 259, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		
		lblStudentno = new JLabel("StudentNo:");
		lblStudentno.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentno.setBounds(56, 100, 78, 14);
		getContentPane().add(lblStudentno);
		
		textStudentNo = new JTextField();
		textStudentNo.setBounds(146, 93, 113, 26);
		getContentPane().add(textStudentNo);
		textStudentNo.setColumns(10);
		
		labelStudentNo = new JLabel("");
		labelStudentNo.setBounds(288, 85, 199, 36);
		getContentPane().add(labelStudentNo);
		
		userAccountManagementService = new UserAccountManagementServiceImpl();
		md5Algorithm = new MD5Algorithm();
		studentManagementService = new StudentManagementServiceImpl();
		roleService = new RoleServiceImpl();
		roleUserService = new RoleUserServiceImpl();
		
		/*******create a thread to check the choice, student or admin*******/
		createThread();
		
		this.setBounds(0, 0, 550, 354);
		
	}
	
	private void createThread(){
		runnable = new Runnable() {  
            public void run() {  
                while (true) {  
                    //------- code for task to run  
                     if(choiceRole.getSelectedItem().equals("Admin")){
                    /*	 System.out.println("your admin");*/
	                	 getContentPane().remove(lblStudentno);
	                	 getContentPane().remove(textStudentNo);
	                	 lblUsername.setBounds(56, 100, 78, 14);
	                	 textUserName.setBounds(146, 93, 113, 26);
	                	 passwordField.setBounds(146, 145, 113, 26);
	                	 lblPassword.setBounds(56, 149, 78, 14);
	                	 btnBack.setBounds(146, 205, 69, 30);
	                	 btnSave.setBounds(146, 205, 69, 30);
	                	 getContentPane().repaint();
                      	 flag = false;
                     }else if(choiceRole.getSelectedItem().equals("Student")){
                    	 if(!flag){
                    		getContentPane().add(lblStudentno);
                     		getContentPane().add(textStudentNo);
                     		lblUsername.setBounds(56, 149, 78, 14);
                     		textUserName.setBounds(146, 145, 113, 26);
                     		passwordField.setBounds(146, 205, 113, 26);
                     		lblPassword.setBounds(56, 211, 78, 14);
                     		btnBack.setBounds(146, 259, 69, 30);
     	                	btnSave.setBounds(146, 259, 69, 30);
                     		getContentPane().repaint();
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
	
	@SuppressWarnings({ "deprecation", "static-access" })
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		int confirm = 0;
		if(obj == btnSave){
			confirm = jOptionPane.showConfirmDialog(btnSave, "save?");
			if(confirm == 0){
				student = studentManagementService.findStudentByStudentNo(textStudentNo.getText());
				
				if(choiceRole.getSelectedItem().equals("Student")){
					/***creating student login account,student has to show studentNo****/
					addStudent(1);		
				}else if(choiceRole.getSelectedItem().equals("Admin")){
					addAdmin(2);
				}
				
			}			
		}else if(obj == btnBack){
			this.setVisible(false);
			UserAccountManagementJFrame uamj = new UserAccountManagementJFrame();
			uamj.setVisible(true);
		}
		
	}
	
	public void addStudent(int roleId){
		if(!(student == null)){
			insertUserRecord();
			insertRoleUserRecord(roleId);
			labelStudentNo.setText("");
		}else labelStudentNo.setText("This studentNo doesn't exist!");
	}
	
	public void addAdmin(int roleId){
		insertUserRecord();
		insertRoleUserRecord(roleId);
	}
	
	@SuppressWarnings("deprecation")
	private void insertUserRecord(){
		String password = null;
		user = new User();
		user.setUserName(textUserName.getText());
		//encrypt original password with MD5 algorithm  
		password = md5Algorithm.md5Encryption(passwordField.getText().getBytes());
		System.out.println("password:"+password);
		user.setPassword(password);
		user.setRoleName(choiceRole.getSelectedItem());
		System.out.println("student no:"+student.getStudentNo());
		user.setStudentNo(student.getStudentNo());
		userAccountManagementService.insertUser(user);
	}
	
	private void insertRoleUserRecord(int roleId){
		user = userAccountManagementService.selectUserByUsernameAndRoleName(textUserName.getText(), choiceRole.getSelectedItem());
		roleUser = new RoleUser();
		roleUser.setRoleid(roleId);
		roleUser.setUserid(user.getUserid());
		roleUserService.insertRoleUserRecord(roleUser);
	}
	
	public static void main(String[] args){
		UserAccountAddJFrame userAccountAddJFrame = new UserAccountAddJFrame();
		userAccountAddJFrame.setVisible(true);
	}
}
