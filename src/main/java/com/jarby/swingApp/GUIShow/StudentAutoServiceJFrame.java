package com.jarby.swingApp.GUIShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jarby.swingApp.ConstantVariable.ConstantVar;
import com.jarby.swingApp.entity.Book;
import com.jarby.swingApp.entity.Student;
import com.jarby.swingApp.entity.User;
import com.jarby.swingApp.service.BookManagementService;
import com.jarby.swingApp.service.BorrowRecordService;
import com.jarby.swingApp.service.StudentManagementService;
import com.jarby.swingApp.service.UserAccountManagementService;
import com.jarby.swingApp.service.impl.BookManagementServiceImpl;
import com.jarby.swingApp.service.impl.BorrowRecordServiceImpl;
import com.jarby.swingApp.service.impl.StudentManagementServiceImpl;
import com.jarby.swingApp.service.impl.UserAccountManagementServiceImpl;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Choice;
import java.awt.Color;

@SuppressWarnings("serial")
public class StudentAutoServiceJFrame extends JFrame implements ActionListener  {
	private JTextField textBookName;
	private JTextField textAuthor;
	private Choice choiceBookName;
	private Choice choiceAuthor;
	private JButton btnSearch ;
	private JButton btnExit;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel dtm;
	private List<Book> booklist;
	private BookManagementService bookService;
	private JOptionPane jOptionPane;
	private StudentManagementService studentManagementService;
	private UserAccountManagementService userAccountManagementService;
	private BorrowRecordService borrowRecordService ;
	Object[] head = {"Id","Book Name","Author","Create Time","Update Time","Status","Operation"};
	Object[][] data = null;
	Object[] row = null;
	public StudentAutoServiceJFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBookName.setBounds(25, 67, 73, 14);
		getContentPane().add(lblBookName);
		
		textBookName = new JTextField();
		textBookName.setBounds(108, 55, 163, 30);
		getContentPane().add(textBookName);
		textBookName.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthor.setBounds(25, 138, 73, 14);
		getContentPane().add(lblAuthor);
		
		textAuthor = new JTextField();
		textAuthor.setBounds(108, 127, 163, 30);
		getContentPane().add(textAuthor);
		textAuthor.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSearch.setBounds(329, 127, 75, 30);
		btnSearch.addActionListener(this);
		getContentPane().add(btnSearch);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(491, 24, 70, 30);
		btnExit.addActionListener(this);
		getContentPane().add(btnExit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 181, 536, 178);
		getContentPane().add(scrollPane);
		
		choiceBookName = new Choice();
		choiceBookName.setBounds(108, 24, 70, 20);
		choiceBookName.add("All");
		choiceBookName.add("Book Name");
		getContentPane().add(choiceBookName);
		
		choiceAuthor = new Choice();
		choiceAuthor.setBounds(108, 101, 70, 20);
		choiceAuthor.add("All");
		choiceAuthor.add("Author");
		getContentPane().add(choiceAuthor);
		
		/*******instantiate bookService*******/
		bookService = new BookManagementServiceImpl();
		studentManagementService = new StudentManagementServiceImpl();
		userAccountManagementService = new UserAccountManagementServiceImpl();
		borrowRecordService = new BorrowRecordServiceImpl();
		
		/******instantiate defaultTableModel********/
		booklist = bookService.findAllBooks();
		data = queryData(booklist);
		dtm = new DefaultTableModel(data,head);
		
		/**********Define Table*************/
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(10, 11, 417, 218); 
		setTableCellForeColor(table);
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			public void mouseClicked(MouseEvent evt){
				 int row = table.rowAtPoint(evt.getPoint());
			     int col = table.columnAtPoint(evt.getPoint());
			     int confirm = 0;
			     Student student = null;
			     User user = null;
			     if(col == 6){
			    	 /*****borrow book ****/
			    	 /*System.out.println("id: "+table.getValueAt(row, 0));*/
			    	 confirm = jOptionPane.showConfirmDialog(table, "borrow this book?");
			    	 if(confirm == 0){
			    		 user = userAccountManagementService.selectUserByUserid(ConstantVar.sessionUserid);
			    		 System.out.println(user.getStudentNo());
			    		 student = studentManagementService.findStudentByStudentNo(user.getStudentNo());
			    		 System.out.println(ConstantVar.bookid);
			    		 System.out.println(student.getStudentid());
			    		 /**insert borrow record**/
			    		 borrowRecordService.insertBorrowRecord(Integer.valueOf(table.getValueAt(row, 0).toString()), student.getStudentid());
			    		 /**update book status to "Out" according to bookid**/
			    		 bookService.updateBookStatus(Integer.valueOf(table.getValueAt(row, 0).toString()), "Out");
			    		 /****refresh student auto service***/
			    		 refreshStudentAutoService();
			    	 }
			     }
			}
		});
		scrollPane.setViewportView(table);
		
		this.setBounds(0,0,596,420);
		this.setTitle("Student Auto Service From Library System");
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String sql = null;
		if(obj == btnSearch){
			sql = "select * from book where 1=1 ";
			if(choiceBookName.getSelectedIndex()==1)
				sql = sql + " and book_name='"+textBookName.getText()+"'";
			if(choiceAuthor.getSelectedIndex()==1)
				sql = sql + " and author='"+textAuthor.getText()+"'";
			booklist = bookService.findBooksBySmartWay(sql);
			removeTableModelData();
			for(Book book:booklist){
				row = new Object[head.length];
				row[0] = book.getBookid();
				row[1] = book.getBookName();
				row[2] = book.getAuthor();
				row[3] = book.getCreateTime();
				row[4] = book.getUpdateTime();
				row[5] = book.getStatus();
				row[6] = "borrow";
				dtm.addRow(row);
				table.setModel(dtm);			
			}
		}else if(obj == btnExit){
			/***close current JFrame**/
			this.setVisible(false);
			
			/****Set new JFrame of UserLogin***/
			UserLogin userLogin = new UserLogin();
			userLogin.setVisible(true);
		}
		
	}
	
	//before setting new data to TableModel, clearing all data from TableModel
	private void removeTableModelData(){
		while(dtm.getRowCount()>0){
			dtm.removeRow(0);
		}
	}

	private void setTableCellForeColor(JTable tb){
		DefaultTableCellRenderer backgroundColor = new DefaultTableCellRenderer();		
		
		TableColumn tableColumnDelete = tb.getColumn("Operation");
		backgroundColor.setForeground(Color.red);
		tableColumnDelete.setCellRenderer(backgroundColor);		
		
	}
	
	private void refreshStudentAutoService(){
		List<Book> booklist = null;
		booklist = bookService.findAllBooks();
		data = queryData(booklist);
		dtm.setDataVector(data, head);
		table.setModel(dtm);
	}
	 public Object[][] queryData(List<Book> list){
		 
	        data=new Object[list.size()][head.length];

	        for(int i=0;i<list.size();i++){
	            for(int j=0;j<head.length;j++){
	                data[i][0]=list.get(i).getBookid();
	                data[i][1]=list.get(i).getBookName();
	                data[i][2]=list.get(i).getAuthor();
	                data[i][3]=list.get(i).getCreateTime();
	                data[i][4]=list.get(i).getUpdateTime();
	                data[i][5]=list.get(i).getStatus();
	                data[i][6]="Borrow";	             
	            }
	        }
	        return data;
	    }
	
	public static void main(String[] args){
		StudentAutoServiceJFrame studentServiceJFrame = new StudentAutoServiceJFrame();
		studentServiceJFrame.setVisible(true);
	}
}
