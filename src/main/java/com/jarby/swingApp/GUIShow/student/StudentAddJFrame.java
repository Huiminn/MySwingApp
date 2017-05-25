package com.jarby.swingApp.GUIShow.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.jarby.swingApp.GUIShow.StudentManagementJFrame;
import com.jarby.swingApp.entity.Student;
import com.jarby.swingApp.service.StudentManagementService;
import com.jarby.swingApp.service.impl.StudentManagementServiceImpl;

import javax.swing.JButton;

public class StudentAddJFrame extends JFrame implements ActionListener  {
	private JTextField textStudentName;
	private JTextField textStudentNo;
	private JTextField textTel;
	private JTextField textEmail;
	private JTextField textAddress;
	private JButton btnSave;
	private JButton btnBack;
	private StudentManagementService studentService;
	private Student student;
	private JOptionPane jOptionPane;
	public StudentAddJFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student name:");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentName.setBounds(76, 121, 93, 14);
		getContentPane().add(lblStudentName);
		
		textStudentName = new JTextField();
		textStudentName.setBounds(179, 112, 123, 30);
		getContentPane().add(textStudentName);
		textStudentName.setColumns(10);
		
		JLabel lblStudentNo = new JLabel("Student No:");
		lblStudentNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentNo.setBounds(76, 56, 93, 14);
		getContentPane().add(lblStudentNo);
		
		textStudentNo = new JTextField();
		textStudentNo.setBounds(179, 54, 123, 30);
		getContentPane().add(textStudentNo);
		textStudentNo.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelephone.setBounds(76, 179, 93, 14);
		getContentPane().add(lblTelephone);
		
		textTel = new JTextField();
		textTel.setBounds(179, 170, 123, 30);
		getContentPane().add(textTel);
		textTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(76, 239, 93, 14);
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(179, 230, 123, 30);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(76, 296, 93, 14);
		getContentPane().add(lblAddress);
		
		textAddress = new JTextField();
		textAddress.setBounds(179, 287, 190, 30);
		getContentPane().add(textAddress);
		textAddress.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(76, 355, 69, 30);
		btnSave.addActionListener(this);
		getContentPane().add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(179, 355, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		
		studentService = new StudentManagementServiceImpl();
		
		this.setBounds(200, 150, 500, 500);
		this.setTitle("Student Registration");
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		student = new Student();
		int confirm = 0;
		if(obj == btnSave){
			confirm = jOptionPane.showConfirmDialog(btnSave, "Save?");
				if(confirm == 0){
					student.setAddress(textAddress.getText());
					student.setCreatetime(new Timestamp(System.currentTimeMillis()));
					student.setEmail(textEmail.getText());
					student.setStudentName(textStudentName.getText());
					student.setStudentNo(textStudentNo.getText());
					student.setTelephone(textTel.getText());
					student.setUpdatetime(new Timestamp(System.currentTimeMillis()));			
					studentService.addStudent(student);
				}
			
		}else if(obj == btnBack){
			this.setVisible(false);
			StudentManagementJFrame studentJFrame = new StudentManagementJFrame();
			studentJFrame.setVisible(true);
		}
		
	}
	
	public static void main(String[] args){
		StudentAddJFrame studentAddJFrame = new StudentAddJFrame();
		studentAddJFrame.setVisible(true);
	}
}
