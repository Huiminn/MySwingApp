package com.jarby.swingApp.GUIShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jarby.swingApp.ConstantVariable.ConstantVar;
import com.jarby.swingApp.GUIShow.book.BookUpdateJFrame;
import com.jarby.swingApp.GUIShow.student.StudentAddJFrame;
import com.jarby.swingApp.GUIShow.student.StudentUpdateJFrame;
import com.jarby.swingApp.entity.Book;
import com.jarby.swingApp.entity.Student;
import com.jarby.swingApp.service.StudentManagementService;
import com.jarby.swingApp.service.impl.StudentManagementServiceImpl;

import javax.swing.JScrollPane;
import javax.swing.JTable;
   
@SuppressWarnings("serial")
public class StudentManagementJFrame extends JFrame implements ActionListener {
	private JTextField textStudentName;
	private JTextField textStudentID;
	private JButton btnAdd;
	private Choice choiceStudentName;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private Choice choiceStudentID;
	private JTable table;
	private DefaultTableModel dtm ;
	private StudentManagementService studentManagementService;
	private String sql;
	private int confirm;
	private JOptionPane jOptionPane;

	Object[] head = {"id","Student NO","Student name","Telephone","E-mail","Address","Createtime","Updatetime","Delete","Update"};
	Object[] row = null;
	Object[][] data = null;
	private JButton btnBack;
	public StudentManagementJFrame() {
		
		studentManagementService = new StudentManagementServiceImpl();
		getContentPane().setLayout(null);
		
		JLabel lblStudentName = new JLabel("Student name:");
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentName.setBounds(28, 150, 88, 14);
		getContentPane().add(lblStudentName);
		
		JLabel lblAddStudent = new JLabel("Register student:");
		lblAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAddStudent.setBounds(28, 56, 104, 14);
		getContentPane().add(lblAddStudent);
		
		btnAdd = new JButton("Register");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdd.setBounds(132, 48, 88, 30);
		btnAdd.addActionListener(this);
		getContentPane().add(btnAdd);
		
		choiceStudentName = new Choice();
		choiceStudentName.setBounds(132, 100, 88, 20);
		choiceStudentName.add("All");
		choiceStudentName.add("Student name");
		getContentPane().add(choiceStudentName);
		
		textStudentName = new JTextField();
		textStudentName.setBounds(132, 138, 155, 30);
		getContentPane().add(textStudentName);
		textStudentName.setColumns(10);
		
		JLabel lblStudentid = new JLabel("StudentID:");
		lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStudentid.setBounds(307, 151, 72, 14);
		getContentPane().add(lblStudentid);
		
		textStudentID = new JTextField();
		textStudentID.setBounds(375, 138, 104, 30);
		getContentPane().add(textStudentID);
		textStudentID.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(509, 138, 80, 30);
		btnSearch.addActionListener(this);
		getContentPane().add(btnSearch);
		data = queryData(studentManagementService.findAllStudents());
		dtm = new DefaultTableModel(data, head);
		
		/**********Define Table*************/
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(10, 11, 417, 218); 
		 /* 
         * set JTable auto resize closed
         */  
		setTableCellForeColor(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);  
        table.addMouseListener(new MouseAdapter() {
        	@SuppressWarnings("static-access")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
        		 int row = table.rowAtPoint(evt.getPoint());
			        int col = table.columnAtPoint(evt.getPoint());
			        if(col==8){
			        	confirm = jOptionPane.showConfirmDialog(table, "Delete?");
			        	if(confirm==0){
			        		studentManagementService.deleteStudent(Integer.valueOf((table.getValueAt(row, 0)).toString()));
			        		data = queryData(studentManagementService.findAllStudents());
			        		removeTableModelData();
			        		dtm = new DefaultTableModel(data,head);
			        		table.setModel(dtm);
			 
			        	}			        	
			        }else if(col==9){
			        	ConstantVar.studentid = Integer.valueOf(table.getValueAt(row, 0).toString());
			        	//call function to close current JFrame and implement the bookUpdate
			        	studentUpdateImpl();
			        }
        	}
		});
       
        
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 186, 741, 214);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		choiceStudentID = new Choice();
		choiceStudentID.setBounds(375, 100, 70, 25);
		choiceStudentID.add("All");
		choiceStudentID.add("StudentID");
		getContentPane().add(choiceStudentID);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(700, 49, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		this.setBounds(50, 100, 814, 450);
		this.setTitle("Student Management System");
	}

	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		List<Student> studentlist = null;
		if(obj == btnAdd){
			this.setVisible(false);
			StudentAddJFrame studentAddJFrame = new StudentAddJFrame();
			studentAddJFrame.setVisible(true);
		}else if(obj == btnSearch){
			sql = "select * from student where 1=1";
			if(choiceStudentName.getSelectedIndex()==1)
				sql = sql + " and student_name='"+textStudentName.getText()+"'";
			if(choiceStudentID.getSelectedIndex()==1)
				sql = sql + " and studentNo='"+textStudentID.getText()+"'";
			studentlist = studentManagementService.findStudentsBySmartWay(sql);
			removeTableModelData();
			for(Student student: studentlist){
				row = new Object[head.length];
				row[0] = student.getStudentid();
				row[1] = student.getStudentNo();
				row[2] = student.getStudentName();
				row[3] = student.getTelephone();
				row[4] = student.getEmail();
				row[5] = student.getAddress();
				row[6] = student.getCreatetime();
				row[7] = student.getUpdatetime();
				row[8] = "Delete";
				row[9] = "Update";
				dtm.addRow(row);
			}
			table.setModel(dtm);	
			this.setVisible(true);
		}else if(btnBack == obj){
			this.setVisible(false);
			Main main = new Main();
			main.setVisible(true);
		}
	}
	
	private void studentUpdateImpl(){
		this.setVisible(false);
		StudentUpdateJFrame  studentUpdateJFrame = new StudentUpdateJFrame();
		studentUpdateJFrame.setVisible(true);
		
	}
	
	//before setting new data to TableModel, clearing all data from TableModel
		private void removeTableModelData(){
			while(dtm.getRowCount()>0){
				dtm.removeRow(0);
			}
		}
	
		private void setTableCellForeColor(JTable tb){
			DefaultTableCellRenderer backgroundColor = new DefaultTableCellRenderer();		
			
			TableColumn tableColumnDelete = tb.getColumn("Delete");
			backgroundColor.setForeground(Color.blue);
			tableColumnDelete.setCellRenderer(backgroundColor);
			
			TableColumn tableColumnUpdate = tb.getColumn("Update");
			backgroundColor.setForeground(Color.red);
			tableColumnUpdate.setCellRenderer(backgroundColor);			
		}
		
	 public Object[][] queryData(List<Student> list){
		 
	        data=new Object[list.size()][head.length];

	        for(int i=0;i<list.size();i++){
	            for(int j=0;j<head.length;j++){
	                data[i][0]=list.get(i).getStudentid();
	                data[i][1]=list.get(i).getStudentNo();
	                data[i][2]=list.get(i).getStudentName();
	                data[i][3]=list.get(i).getTelephone();
	                data[i][4]=list.get(i).getEmail();
	                data[i][5]=list.get(i).getAddress();
	                data[i][6]=list.get(i).getCreatetime();
	                data[i][7]=list.get(i).getUpdatetime();
	                data[i][8]="Delete";
	                data[i][9]="Update";
	            }
	        }
	        return data;
	    }
	
	public static void main(String[] args){
		StudentManagementJFrame studentJFrame = new StudentManagementJFrame();
		studentJFrame.setVisible(true);
	}
}
