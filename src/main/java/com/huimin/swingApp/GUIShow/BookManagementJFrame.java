package com.huimin.swingApp.GUIShow;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.huimin.swingApp.ConstantVariable.ConstantVar;
import com.huimin.swingApp.GUIShow.book.BookAddJFrame;
import com.huimin.swingApp.GUIShow.book.BookUpdateJFrame;
import com.huimin.swingApp.entity.Book;
import com.huimin.swingApp.entity.BorrowRecord;
import com.huimin.swingApp.entity.Student;
import com.huimin.swingApp.service.BookManagementService;
import com.huimin.swingApp.service.BorrowRecordService;
import com.huimin.swingApp.service.StudentManagementService;
import com.huimin.swingApp.service.impl.BookManagementServiceImpl;
import com.huimin.swingApp.service.impl.BorrowRecordServiceImpl;
import com.huimin.swingApp.service.impl.StudentManagementServiceImpl;
import com.mysql.jdbc.Constants;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.Color;

import javax.swing.JTextField;

public class BookManagementJFrame extends JFrame implements ActionListener{
	Object[] head = {"id","Book Name","Author","Create Time","Update Time","Status","Delete","Update"};
	Object[][] data = null;
	Object[] row = null;
	Object[] head1 = {"id","Book Name","Author","Borrow Time","Return Time","Student Name","StudentNo","Status","Operation"};
	Object[][] data1 = null;
	Object[] row1 = null;
	private JTable table;
	private JTableHeader jTableHeader;
	private JScrollPane scrollPane;
	private JButton btnAdd;
	private JLabel lblNewLabel_1;
	private JLabel lblAuthor;
	private Choice choice_1;
	private JLabel lblNewLabel_2;
	private Choice choice_2;
	private JButton btnSearch;
	private JTextField textAuthor;
	private JTextField textBookName;
	private Choice choice;
	/**********book and student***********/
	private   BookManagementService bookService;
	private StudentManagementService studentService;
	private BorrowRecordService borrowService;
	private Book book;
	private Student student;
	
	private DefaultTableModel dtm ;
	private DefaultTableModel dtm1;
	private List<Book> booklist;
	private int confirm;
	private JOptionPane jOptionPane;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textStudentID;
	private JTextField textStudentName;
	private JScrollPane scrollPaneBookRecord;
	private JTable table_1;
	private  Choice choiceStudentID;
	private  Choice choiceStudentName;
	private Choice choiceStatus;
	private JButton btnBrwSearch;
	private JButton btnBack;
	private JLabel lblResultsSearched;
	public BookManagementJFrame() {
		
		/**************Define button add********************/
		btnAdd = new JButton("Add");
		btnAdd.setBounds(100, 50, 70, 23);
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 14));
		/*btnNewButton.setContentAreaFilled(false);*/
		btnAdd.setBorder(BorderFactory.createRaisedBevelBorder());
		btnAdd.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(btnAdd);
		
		/****initializing bookService and studentService****/
		bookService = new BookManagementServiceImpl();
		studentService = new StudentManagementServiceImpl();
		borrowService = new BorrowRecordServiceImpl();
		
		data = queryData(bookService.findAllBooks());
		dtm = new DefaultTableModel(data,head);
		
		/****************define JTable****************/
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(10, 11, 417, 218); 
		/********set table fore background*********/
		setTableCellForeColor(table);
		table.addMouseListener(new MouseAdapter(){
			@SuppressWarnings("static-access")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = table.rowAtPoint(evt.getPoint());
			        int col = table.columnAtPoint(evt.getPoint());
			        if(col==6){
			        	confirm = jOptionPane.showConfirmDialog(table, "Delete?");
			        	if(confirm==0){
			        		bookService.deleteBook(Integer.valueOf((table.getValueAt(row, 0)).toString()));
			        		data = queryData(bookService.findAllBooks());
			        		removeTableModelData(dtm);
			        		dtm = new DefaultTableModel(data,head);
			        		table.setModel(dtm);
			        		System.out.println(table.getValueAt(row, 0));
			        	}			        	
			        }else if(col==7){
			        	ConstantVar.bookid = Integer.valueOf(table.getValueAt(row, 0).toString());
			        	//call function to close current JFrame and implement the bookUpdate
			        	bookUpdateImpl();
			        }
			       /* System.out.println("Current row: "+row);
			        System.out.println("Current col: "+col);*/
			    }
		});
        /* 
         * set JTable auto resize closed
         */  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);  
		
        /****************define JScrollPane****************/
		scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 55, 770, 192);
		scrollPane.setViewportView(table);
	    this.getContentPane().add(scrollPane);
	   
	    JLabel lblNewLabel = new JLabel("Add book:");
	    lblNewLabel.setBounds(10, 55, 58, 14);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblNewLabel);
	    
	    /**************Define choices********************/
	    choice = new Choice();
	    choice.setBounds(100, 90, 70, 20);
	    choice.add("All");
	    choice.add("Book name");
	    getContentPane().add(choice);
	    
	    /**************Define label Book name********************/
	    lblNewLabel_1 = new JLabel("Book name:");
	    lblNewLabel_1.setBounds(10, 120, 81, 27);
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblNewLabel_1);
	    
	    /**************Define label Author********************/
	    lblAuthor = new JLabel("Author:");
	    lblAuthor.setBounds(10, 200, 51, 27);
	    lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblAuthor);
	    
	    choice_1 = new Choice();
	    choice_1.setBounds(100, 170, 70, 20);
	    choice_1.add("All");
	    choice_1.add("Author");
	    getContentPane().add(choice_1);
	    
	    /**************Define label Status********************/
	    lblNewLabel_2 = new JLabel("Status:");
	    lblNewLabel_2.setBounds(250, 122, 46, 23);
	    lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblNewLabel_2);
	    
	    choice_2 = new Choice();
	    choice_2.setBounds(300, 120, 70, 20);
	    choice_2.add("All");
	    choice_2.add("In");
	    choice_2.add("Out");
	    getContentPane().add(choice_2);
	    
	    /**************Define button Search********************/
	    btnSearch = new JButton("Search");
	    btnSearch.setBounds(250, 200, 80, 27);
	    btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    btnSearch.addActionListener(this);
	    getContentPane().add(btnSearch);
	    
	    /**************Define textField Author********************/
	    textAuthor = new JTextField();
	    textAuthor.setBounds(100, 200, 115, 25);
	    getContentPane().add(textAuthor);
	    textAuthor.setColumns(10);
	    
	    /**************Define textField Book name********************/
	    textBookName = new JTextField();
	    textBookName.setBounds(100, 120, 140, 25);
	    getContentPane().add(textBookName);
	    textBookName.setColumns(10);
	    
	    lblNewLabel_3 = new JLabel("Borrow books records");
	    lblNewLabel_3.setBounds(10, 300, 157, 23);
	    lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    getContentPane().add(lblNewLabel_3);
	    
	    lblNewLabel_4 = new JLabel("Book Management:");
	    lblNewLabel_4.setBounds(10, 11, 140, 28);
	    lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
	    getContentPane().add(lblNewLabel_4);
	    
	    JLabel lblStudentid = new JLabel("StudentID:");
	    lblStudentid.setBounds(10, 385, 70, 14);
	    lblStudentid.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblStudentid);
	    
	    textStudentID = new JTextField();
	    textStudentID.setBounds(97, 378, 140, 25);
	    getContentPane().add(textStudentID);
	    textStudentID.setColumns(10);
	    
	    /**************Define choice studentID********************/
	    choiceStudentID = new Choice();
	    choiceStudentID.setBounds(97, 342, 70, 20);
	    choiceStudentID.add("All");
	    choiceStudentID.add("StudentID");
	    getContentPane().add(choiceStudentID);
	    
	    choiceStudentName = new Choice();
	    choiceStudentName.setBounds(97, 425, 70, 20);
	    choiceStudentName.add("All");
	    choiceStudentName.add("Student name");
	    getContentPane().add(choiceStudentName);
	    
	    textStudentName = new JTextField();
	    textStudentName.setBounds(97, 461, 140, 25);
	    getContentPane().add(textStudentName);
	    textStudentName.setColumns(10);
	    
	    JLabel lblStudentname = new JLabel("StudentName:");
	    lblStudentname.setBounds(10, 466, 91, 14);
	    lblStudentname.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblStudentname);
	    
	    choiceStatus = new Choice();
	    choiceStatus.setBounds(321, 379, 70, 20);
	    choiceStatus.add("All");
	    choiceStatus.add("borrowed");
	    choiceStatus.add("returned");
	    getContentPane().add(choiceStatus);
	    
	    JLabel lblStatus = new JLabel("Status:");
	    lblStatus.setBounds(269, 385, 46, 14);
	    lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    getContentPane().add(lblStatus);
	    
	    scrollPaneBookRecord = new JScrollPane();
	    scrollPaneBookRecord.setBounds(478, 305, 770, 186);
	    getContentPane().add(scrollPaneBookRecord);
	    
	    
	    /***********borrow book record***************/
	    data1 = queryData1(borrowService.findAllBooksInitialStage());
	    dtm1 = new DefaultTableModel(data1, head1);
	    
	    table_1 = new JTable();
	    table_1.setBounds(686, 362, 50, 50); 
	    table_1.setModel(dtm1);
	    
	    /*************set table_1 cell background color*****************/
	    setTableCellForeColor(table_1);
	    
		/**********************************/
	    table_1.addMouseListener(new MouseAdapter() {
	    	@SuppressWarnings("static-access")
			public void mouseClicked(MouseEvent eve){
	    		int row = table_1.rowAtPoint(eve.getPoint());
	    		int col = table_1.columnAtPoint(eve.getPoint());
	    		BorrowRecord borrowRecord = null;
	    		if(col == 8){
	    			confirm = jOptionPane.showConfirmDialog(table_1, "return this book?");
	    			if(confirm == 0){
	    				
	    				/***update table borrow_record status to 'returned'**/
	    				borrowService.updateBookStatus(Integer.valueOf(table_1.getValueAt(row, 0).toString()),"returned");
	    				
	    				/**search for borrow book record by borrowid***/
	    				borrowRecord = borrowService.selectBorrowRecordById(Integer.valueOf(table_1.getValueAt(row, 0).toString()));
	    				
	    				/****update table book status to "In",which means this book has been returned and the book is available for anyone else*******/
	    				bookService.updateBookStatus(borrowRecord.getBookid(), "In");
	    				refreshBookManagementRendering();
	    			}
	    		}
	    	}
		});
	    /*scrollPaneBookRecord.setColumnHeaderView(table_1);*/
	    scrollPaneBookRecord.setViewportView(table_1);
	    
	    btnBrwSearch = new JButton("Search");
	    btnBrwSearch.setBounds(321, 458, 89, 30);
	    btnBrwSearch.addActionListener(this);
	    getContentPane().add(btnBrwSearch);
	    
	    btnBack = new JButton("Back");
	    btnBack.setBounds(1179, 16, 69, 28);
	    btnBack.addActionListener(this);
	    getContentPane().add(btnBack);
	    
	    lblResultsSearched = new JLabel("");
	    lblResultsSearched.setBounds(478, 258, 296, 36);
	    getContentPane().add(lblResultsSearched);
		/*panel.add(jTableHeader,BorderLayout.NORTH);*/
			    
		this.setBounds(50, 50, 1299, 562);
	}
	
	private void refreshBookManagementRendering(){
		data = queryData(bookService.findAllBooks());
		/***refresh table**/
		removeTableModelData(dtm);
		dtm.setDataVector(data, head);
		table.setModel(dtm);
		setTableCellForeColor(table);
		/*****refresh table_1******/
		removeTableModelData(dtm1);
		data = queryData1(borrowService.findAllBookRecords());
		dtm1.setDataVector(data, head1);
		table_1.setModel(dtm1);
		setTableCellForeColor(table_1);
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
	                data[i][6]="Delete";
	                data[i][7]="Update";
	            }
	        }
	        return data;
	    }
	
	 public Object[][] queryData1(List<Map<String, Object>> list){
		
	        data=new Object[list.size()][head1.length];
	        for(int i=0;i<list.size();i++){
	            for(int j=0;j<head1.length;j++){
	                data[i][0]=list.get(i).get("borrowid");	             
	                data[i][1]=list.get(i).get("book_name");	   
	                data[i][2]=list.get(i).get("author");	   
	                data[i][3]=list.get(i).get("borrow_time");	   
	                data[i][4]=list.get(i).get("return_time");	   
	               
	                data[i][5]=list.get(i).get("student_name");	   
	                data[i][6]=list.get(i).get("studentNo");	   
	              
	                data[i][7]=list.get(i).get("status");	   
	               /* if(book.getStatus().equals("In"))
	                	data[i][7]="returned";
	                else if(book.getStatus().equals("Out"))
	                	data[i][7]="borrowed";*/
	                data[i][8]="return";
	                			
	            }
	        }
	        return data;
	    }
	 
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		String sql = null;
		Student studentPerformed = null;
		if(btnAdd == obj){
			bookService.findAllBooks();
			this.setVisible(false);
			BookAddJFrame bookAddJFrame = new BookAddJFrame();
			bookAddJFrame.setVisible(true);
		}else if(btnSearch == obj){
			sql = "select * from book where 1=1";
			if(choice.getSelectedIndex()==1)
				sql = sql + " and book_name='"+textBookName.getText()+"'";
			if(choice_1.getSelectedIndex()==1)
				sql = sql + " and author='"+textAuthor.getText()+"'";
			if(choice_2.getSelectedIndex()==1)
				sql = sql + " and status='"+"In"+"'";
			if(choice_2.getSelectedIndex()==2)
				sql = sql + " and status='"+"Out"+"'";
			System.out.println(sql);
			booklist = bookService.findBooksBySmartWay(sql);
			removeTableModelData(dtm);
			for(Book book: booklist){
				row = new Object[head.length];
				row[0] = book.getBookid();
				row[1] = book.getBookName();
				row[2] = book.getAuthor();
				row[3] = book.getCreateTime();
				row[4] = book.getUpdateTime();
				row[5] = book.getStatus();
				row[6] = "Delete";
				row[7] = "Update";
				dtm.addRow(row);
			}
			table.setModel(dtm);	
			this.setVisible(true);
		}else if(btnBrwSearch == obj){
			
			/*sql = "select br.borrowid  from borrow_record br LEFT JOIN book b on "
					+ "br.borrowid = b.bookId LEFT JOIN student s on "
					+ "br.studentid = s.studentid  where 1=1";*/
			sql = "select br.borrowid,b.book_name,b.author,br.borrow_time,br.return_time,s.student_name,s.studentNo,br.status from borrow_record br LEFT JOIN book b "+
					"on br.bookid = b.bookId LEFT JOIN student s "+
							"on br.studentid = s.studentid  where 1=1";
		
			if(!textStudentID.getText().toString().equals("")){
				studentPerformed = studentService.findStudentByStudentNo(textStudentID.getText().toString());				
			}
				
			List<Map<String, Object>> borrowRecordlist = null;
			borrowRecordsSearchInSmartWay(sql,studentPerformed,borrowRecordlist);
			/*if(!(studentPerformed == null)){
				sql = sql + " and studentid='"+studentPerformed.getStudentid()+"'";
				
			}else lblResultsSearched.setText("No results searched");*/
			/*if(!(textStudentName.getText().toString().equals(""))){
				sql = sql + " and studentName='";
			}*/
		}		
		else if(btnBack == obj){
			this.setVisible(false);
			Main main = new Main();
			main.setVisible(true);
		}
	}
	
	private void borrowRecordsSearchInSmartWay(String sql,Student studentPerformed,List<Map<String, Object>> borrowRecordlist){
		
		sql = assembleSqlScript(sql,studentPerformed);
		if(!(studentPerformed==null)){
			System.out.println("student Name: "+ studentPerformed.getStudentName());			
		}
		System.out.println(sql);
		/*borrowRecordlist =  borrowService.findBorrowRecordsByStudentId(student.getStudentid());*/
		borrowRecordlist = borrowService.findBorrowRecordsBySmartWay(sql);
		System.out.println("size: "+borrowRecordlist.size());
		data = queryData1(borrowRecordlist);
		removeTableModelData(dtm1);
		dtm1.setDataVector(data, head1);
		table_1.setModel(dtm1);
		setTableCellForeColor(table_1);
		lblResultsSearched.setText("");
	}
	
	private String assembleSqlScript(String sql,Student student){
		if(choiceStatus.getSelectedIndex() == 1)
			sql = sql + " and br.status='"+"borrowed"+"'";
		if(choiceStatus.getSelectedIndex() == 2)
			sql = sql + " and br.status='"+"returned"+"'";
		if(choiceStudentID.getSelectedIndex() == 1)
			sql = sql + " and br.studentid='"+student.getStudentid()+"'";

		return sql;
	}
	
	private void bookUpdateImpl(){
		this.setVisible(false);
		BookUpdateJFrame bookUpdateJFrame = new BookUpdateJFrame();
		bookUpdateJFrame.setVisible(true);
		
	}
	//before setting new data to TableModel, clearing all data from TableModel
	private void removeTableModelData(DefaultTableModel dtm){
		while(dtm.getRowCount()>0){
			dtm.removeRow(0);
		}
	}
	
	private void setTableCellForeColor(JTable tb){
		DefaultTableCellRenderer backgroundColor = new DefaultTableCellRenderer();		
		
		if(tb == table){
			TableColumn tableColumnDelete = tb.getColumn("Delete");
			backgroundColor.setForeground(Color.blue);
			tableColumnDelete.setCellRenderer(backgroundColor);
			
			TableColumn tableColumnUpdate = tb.getColumn("Update");
			backgroundColor.setForeground(Color.red);
			tableColumnUpdate.setCellRenderer(backgroundColor);
		}else if(tb == table_1){			
			TableColumn tableColumn = table_1.getColumn("Operation");
			backgroundColor.setForeground(Color.red);
			tableColumn.setCellRenderer(backgroundColor);
		}
		
	}
	
	/*private void setTable_1CellForeColor(){
		DefaultTableCellRenderer backgroundColor = new DefaultTableCellRenderer();
		backgroundColor.setForeground(Color.red);
		
		TableColumn tableColumn = table_1.getColumn("Operation");
		tableColumn.setCellRenderer(backgroundColor);
	}*/
	
	public static void main(String[] args){
		BookManagementJFrame bookJFrame = new BookManagementJFrame();
		bookJFrame.setVisible(true);
	}
}
