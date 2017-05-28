package com.huimin.swingApp.GUIShow.student;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.huimin.swingApp.ConstantVariable.ConstantVar;
import com.huimin.swingApp.GUIShow.StudentManagementJFrame;
import com.huimin.swingApp.entity.Student;
import com.huimin.swingApp.service.StudentManagementService;
import com.huimin.swingApp.service.impl.StudentManagementServiceImpl;

public class StudentUpdateJFrame extends JFrame implements ActionListener {

	
	private JTextField textStudentName;
	private JTextField textStudentNo;
	private JTextField textTel;
	private JTextField textEmail;
	private JTextField textAddress;
	private JButton btnUpdate;
	private JButton btnBack;
	private JLabel lblId;
	private JTextField textStudentId;
	private StudentManagementService studentService;
	private Student student;
	private JOptionPane jOptionPane;
	public StudentUpdateJFrame() {
	getContentPane().setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student name:");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentName.setBounds(76, 165, 93, 14);
		getContentPane().add(lblStudentName);
		
		textStudentName = new JTextField();
		textStudentName.setBounds(179, 156, 123, 30);
		getContentPane().add(textStudentName);
		textStudentName.setColumns(10);
		
		JLabel lblStudentNo = new JLabel("Student No:");
		lblStudentNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentNo.setBounds(76, 100, 93, 14);
		getContentPane().add(lblStudentNo);
		
		textStudentNo = new JTextField();
		textStudentNo.setBounds(179, 98, 123, 30);
		getContentPane().add(textStudentNo);
		textStudentNo.setColumns(10);
		
		JLabel lblTelephone = new JLabel("Telephone:");
		lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelephone.setBounds(76, 223, 93, 14);
		getContentPane().add(lblTelephone);
		
		textTel = new JTextField();
		textTel.setBounds(179, 214, 123, 30);
		getContentPane().add(textTel);
		textTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(76, 283, 93, 14);
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(179, 274, 123, 30);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddress.setBounds(76, 340, 93, 14);
		getContentPane().add(lblAddress);
		
		textAddress = new JTextField();
		textAddress.setBounds(179, 331, 190, 30);
		getContentPane().add(textAddress);
		textAddress.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(76, 397, 69, 30);
		btnUpdate.addActionListener(this);
		getContentPane().add(btnUpdate);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(179, 397, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		
		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(76, 45, 46, 14);
		getContentPane().add(lblId);
		
		textStudentId = new JTextField();
		textStudentId.setBounds(179, 40, 66, 30);
		getContentPane().add(textStudentId);
		textStudentId.setColumns(10);
		
		studentService = new StudentManagementServiceImpl();
		/*******Accoring to studentid to find student record from DB*********/
		student = studentService.findStudentById(ConstantVar.studentid);
		
		/*********set student info to each text*************/
		textStudentId.setText(String.valueOf(student.getStudentid()));
		textStudentNo.setText(student.getStudentNo());
		textStudentName.setText(student.getStudentName());
		textTel.setText(student.getTelephone());
		textEmail.setText(student.getEmail());
		textAddress.setText(student.getAddress());
		textStudentId.setEnabled(false);
		
		this.setBounds(200, 150, 500, 500);
		this.setTitle("Update Student Infomation");
	}
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		int confirm = 0;
		if(obj == btnUpdate){
			confirm = jOptionPane.showConfirmDialog(btnUpdate, "update?");
			if(confirm == 0){
				student = new Student();
				student.setStudentid(Integer.parseInt(textStudentId.getText().toString()));
				student.setStudentNo(textStudentNo.getText());
				student.setStudentName(textStudentName.getText());
				student.setTelephone(textTel.getText());
				student.setEmail(textEmail.getText());
				student.setAddress(textAddress.getText());
				student.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				studentService.updateStudentById(student);
			}			
		}else if(obj == btnBack){
			this.setVisible(false);
			StudentManagementJFrame studentJFrame = new StudentManagementJFrame();
			studentJFrame.setVisible(true);
		}
		
	}
	
	
}
