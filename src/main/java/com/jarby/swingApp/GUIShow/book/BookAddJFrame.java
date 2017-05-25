package com.jarby.swingApp.GUIShow.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.jarby.swingApp.GUIShow.BookManagementJFrame;
import com.jarby.swingApp.entity.Book;
import com.jarby.swingApp.service.BookManagementService;
import com.jarby.swingApp.service.impl.BookManagementServiceImpl;

import javax.swing.JButton;

public class BookAddJFrame extends JFrame implements ActionListener {
	private JTextField textBookName;
	private JTextField textAuthor;

	private JButton btnSave;
	private JButton btnBack;
	private JOptionPane jOptionPane;
	public BookAddJFrame() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 11, 577, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(41, 34, 87, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Author:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(41, 78, 65, 14);
		panel.add(lblNewLabel_1);
		
		textBookName = new JTextField();
		textBookName.setBounds(135, 33, 189, 20);
		panel.add(textBookName);
		textBookName.setColumns(10);
		
		textAuthor = new JTextField();
		textAuthor.setBounds(136, 77, 111, 20);
		panel.add(textAuthor);
		textAuthor.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(41, 123, 89, 23);
		btnSave.addActionListener(this);
		panel.add(btnSave);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(158, 123, 89, 23);
		btnBack.addActionListener(this);
		panel.add(btnBack);
		// TODO Auto-generated constructor stub
		this.setBounds(200, 150, 600, 250);
	}
	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		BookManagementService bookService = new BookManagementServiceImpl();
		Book book = new Book();
		int confirm = 0;
		if(obj == btnSave){
			confirm = jOptionPane.showConfirmDialog(btnSave, "Save?");
			if(confirm == 0){
				book.setAuthor(textAuthor.getText());
				book.setBookName(textBookName.getText());
				book.setCreateTime(new Timestamp(System.currentTimeMillis()));
				
				book.setStatus("In");
				book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				bookService.addBook(book);
				System.out.println(new Timestamp(System.currentTimeMillis()));
				System.out.println("executing adding book!!!");
			}
			else {
				
			}
			
		}
		else if(obj == btnBack){
			this.setVisible(false);
			BookManagementJFrame bookJFrame = new BookManagementJFrame();
			bookJFrame.setVisible(true);
		}
	}
	
	public static void main(String[] args){
		BookAddJFrame bookAddJFrame = new BookAddJFrame();
		bookAddJFrame.setVisible(true);
	}
}
