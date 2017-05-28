package com.huimin.swingApp.GUIShow;


import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Choice;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.huimin.swingApp.ConstantVariable.ConstantVar;

import javax.swing.SwingConstants;
import javax.swing.JCheckBoxMenuItem;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private JButton btnLoanSystem;
	private  JButton btnStudentRegis;
	private JButton btnUserAccountManager;
	private JButton btnExit;
	public Main() {
		getContentPane().setLayout(new BorderLayout(0, 0));
       /* menuFile.setMnemonic('F'); 
        menuEdit.setMnemonic('E'); 
        menuView.setMnemonic('V'); */
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JMenuBar jmb1 = new JMenuBar();
		JMenu jm1 = new JMenu("Books");
		JMenuItem jmi1 = new JMenuItem("add");
		jmb1.add(jm1);
		jm1.add(jmi1);
		panel.add(jmb1);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(590, 31, 66, 14);
		panel.add(lblNewLabel);
		
		JLabel lblUser = new JLabel("someone");
		lblUser.setBounds(666, 31, 66, 14);
		lblUser.setText(ConstantVar.userName);
		panel.add(lblUser);
		
		btnExit = new JButton("Exit");
		btnExit.setForeground(SystemColor.windowBorder);
		btnExit.setBackground(UIManager.getColor("Button.darkShadow"));
		btnExit.setBounds(758, 28, 60, 23);
		btnExit.addActionListener(this);
		panel.add(btnExit);
        
        btnLoanSystem = new JButton("Library Loan System");
        btnLoanSystem.setBackground(Color.ORANGE);
        btnLoanSystem.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnLoanSystem.setBounds(280, 108, 275, 65);
        btnLoanSystem.addActionListener(this);
        panel.add(btnLoanSystem);
        
        btnStudentRegis = new JButton("Student Registration System");
        btnStudentRegis.setBackground(Color.GREEN);
        btnStudentRegis.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnStudentRegis.setBounds(280, 254, 275, 65);
        btnStudentRegis.addActionListener(this);
        panel.add(btnStudentRegis);
        
        btnUserAccountManager = new JButton("User Account Management System");
        btnUserAccountManager.setBackground(Color.MAGENTA);
        btnUserAccountManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnUserAccountManager.setBounds(280, 408, 274, 65);
        btnUserAccountManager.addActionListener(this);
        panel.add(btnUserAccountManager);
        
        JTextArea textAreaLoanSystem = new JTextArea();
        textAreaLoanSystem.setBackground(Color.LIGHT_GRAY);
        textAreaLoanSystem.setBounds(21, 108, 234, 79);
        //set to auto jump to next line
        textAreaLoanSystem.setLineWrap(true); 
        textAreaLoanSystem.setText("Desc: Admin can manage loan system, CRUD operations,"
        		+ "including adding new book,updating book,searching books borrowed by students "
        		+ "and as well as returning books for students");
        panel.add(textAreaLoanSystem);
        
        JTextArea textAreaStudentRegistrationSystem = new JTextArea();
        textAreaStudentRegistrationSystem.setBackground(Color.LIGHT_GRAY);
        textAreaStudentRegistrationSystem.setBounds(21, 254, 234, 81);
        //set to auto jump to next line
        textAreaStudentRegistrationSystem.setLineWrap(true); 
        textAreaStudentRegistrationSystem.setText("Desc: Admin manages the student info registration,including CRUD operations,"
        		+ "register,update,delete student info from student registration system");
        panel.add(textAreaStudentRegistrationSystem);
        
        JTextArea textAreaUserAccountSystem = new JTextArea();
        textAreaUserAccountSystem.setBackground(Color.LIGHT_GRAY);
        textAreaUserAccountSystem.setBounds(21, 408, 234, 82);
        //set to auto jump to next line
        textAreaUserAccountSystem.setLineWrap(true); 
        textAreaUserAccountSystem.setText("Desc: This is a User Login System. Admin empowered to "
        		+ " create login users for two roles of student and admin, as well as including CRUD operations "
        		+ " for existing user accounts ");
        panel.add(textAreaUserAccountSystem);
	
        
        
        
		this.setBounds(50, 50, 860, 650);
		this.setTitle("Library Management System");
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(btnLoanSystem == obj){
			BookManagementJFrame book = new BookManagementJFrame();
			this.setVisible(false);
			book.setVisible(true);
		}else if(btnStudentRegis == obj){
			StudentManagementJFrame studentManagementJFrame = new StudentManagementJFrame();
			this.setVisible(false);
			studentManagementJFrame.setVisible(true);
		}else if(btnUserAccountManager == obj){			
			UserAccountManagementJFrame userAccountManagementJFrame = new UserAccountManagementJFrame();
			this.setVisible(false);
			userAccountManagementJFrame.setVisible(true);
		}else if(btnExit == obj){
			this.setVisible(false);
			UserLogin userLogin = new UserLogin();
			userLogin.setVisible(true);
		}
	}
	
	public static void main(String[] args){
		Main main = new Main();
		main.setVisible(true);
		main.setTitle("Welcome to student management system");
	}
}
