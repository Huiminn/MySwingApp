package com.jarby.swingApp.GUIShow.userAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.jarby.swingApp.ConstantVariable.ConstantVar;
import com.jarby.swingApp.GUIShow.UserAccountManagementJFrame;
import com.jarby.swingApp.entity.User;
import com.jarby.swingApp.service.UserAccountManagementService;
import com.jarby.swingApp.service.UserLoginService;
import com.jarby.swingApp.service.impl.UserAccountManagementServiceImpl;

import java.awt.Choice;
import javax.swing.JButton;

public class UserAccouontUpdateJFrame extends JFrame implements ActionListener{
	private JTextField textId;
	private JTextField textUsername;
	private JTextField textPassword;
	private Choice choiceRoleName;
	private JButton btnUpdate;
	private JButton btnBack;
	private UserAccountManagementService userAccountManagementService;
	private User user;
	private JOptionPane jOptionPane;
	public UserAccouontUpdateJFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(65, 57, 46, 14);
		getContentPane().add(lblId);
		
		textId = new JTextField();
		textId.setBounds(152, 50, 86, 25);
		getContentPane().add(textId);
		textId.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserName.setBounds(65, 111, 86, 14);
		getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(65, 179, 86, 14);
		getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(152, 105, 117, 25);
		getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(152, 173, 117, 25);
		getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JLabel lblRoleName = new JLabel("Role Name:");
		lblRoleName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoleName.setBounds(65, 244, 86, 14);
		getContentPane().add(lblRoleName);
		
		choiceRoleName = new Choice();
		choiceRoleName.setBounds(152, 240, 86, 20);
		choiceRoleName.add("Student");
		choiceRoleName.add("Admin");
		getContentPane().add(choiceRoleName);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(65, 303, 86, 30);
		btnUpdate.addActionListener(this);
		getContentPane().add(btnUpdate);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(242, 303, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		
		
		userAccountManagementService = new UserAccountManagementServiceImpl();
		user = userAccountManagementService.selectUserByUserid(ConstantVar.userid);
		System.out.println("userid: "+ConstantVar.userid);
		textId.setText(String.valueOf(user.getUserid()));
		textId.setEnabled(false);
		textUsername.setText(user.getUserName());
		textUsername.setEnabled(false);
		textPassword.setText(user.getPassword());
		textPassword.setEnabled(false);
		
		this.setBounds(0, 0, 521, 409);
		this.setTitle("Update User Account Role");
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		int confirm = 0;
		if(obj == btnUpdate){
			confirm = jOptionPane.showConfirmDialog(btnUpdate, "update?");
			if(confirm == 0){
				userAccountManagementService.updateRoleNameByUserid(choiceRoleName.getSelectedItem(),ConstantVar.userid);
			}
		}else if(obj == btnBack){
			this.setVisible(false);
			UserAccountManagementJFrame uam = new UserAccountManagementJFrame();
			uam.setVisible(true);
		}
		
	}
	
	public static void main(String[] args){
		UserAccouontUpdateJFrame userAccouontUpdateJFrame = new UserAccouontUpdateJFrame();
		userAccouontUpdateJFrame.setVisible(true);
	}
	
}
