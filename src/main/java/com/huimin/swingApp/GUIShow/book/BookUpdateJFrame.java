package com.huimin.swingApp.GUIShow.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.huimin.swingApp.ConstantVariable.ConstantVar;
import com.huimin.swingApp.GUIShow.BookManagementJFrame;
import com.huimin.swingApp.entity.Book;
import com.huimin.swingApp.service.impl.BookManagementServiceImpl;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BookUpdateJFrame extends JFrame implements ActionListener  {
	private JTextField textBookName;
	private JTextField textAuthor;
	private JTextField textStatus;
	private JButton btnBack;
	private JButton btnUpdate;
	private BookManagementServiceImpl bookServiceImpl;
	private Book book;
	private JLabel lblId;
	private JTextField textId;
	private JOptionPane jOptionPane;
	public BookUpdateJFrame() {
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 490, 327);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book name:");
		lblBookName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookName.setBounds(52, 81, 87, 22);
		panel.add(lblBookName);
		
		textBookName = new JTextField();
		textBookName.setBounds(143, 81, 193, 22);
		panel.add(textBookName);
		textBookName.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAuthor.setBounds(52, 141, 68, 14);
		panel.add(lblAuthor);
		
		textAuthor = new JTextField();
		textAuthor.setBounds(143, 141, 130, 20);
		panel.add(textAuthor);
		textAuthor.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(52, 198, 46, 14);
		panel.add(lblStatus);
		
		textStatus = new JTextField();
		textStatus.setBounds(143, 197, 86, 20);
		panel.add(textStatus);
		textStatus.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(50, 257, 89, 23);
		btnUpdate.addActionListener(this);
		panel.add(btnUpdate);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(173, 257, 89, 23);
		btnBack.addActionListener(this);
		panel.add(btnBack);
		
		
		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(52, 21, 46, 14);
		panel.add(lblId);
		
		textId = new JTextField();
		textId.setBounds(143, 20, 86, 20);
		panel.add(textId);
		textId.setColumns(10);
		
		bookServiceImpl = new BookManagementServiceImpl();
		book = bookServiceImpl.findBookById(ConstantVar.bookid);
		textBookName.setText(book.getBookName());
		textAuthor.setText(book.getAuthor());
		textStatus.setText(book.getStatus());
		textId.setText(String.valueOf(book.getBookid()));
		textId.setEnabled(false);
		
	
		this.setBounds(200, 150, 600, 350);
	}
	
	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		int confirm = 0;
		if(obj == btnBack){
			this.setVisible(false);
			BookManagementJFrame bookJFrame = new BookManagementJFrame();
			bookJFrame.setVisible(true);
		}else if(obj == btnUpdate){
			confirm = jOptionPane.showConfirmDialog(btnUpdate, "Save?");
			if(confirm == 0){
				book.setBookid(Integer.parseInt(textId.getText()));
				book.setAuthor(textAuthor.getText());
				book.setBookName(textBookName.getText());
				book.setStatus(textStatus.getText());
				book.setUpdateTime(new Timestamp(System.currentTimeMillis()));
				bookServiceImpl.updateBookById(book);
			}
			/*System.out.println("bookid: "+BookConstantVar.bookUpdate);*/
		}
	}
	
	public static void main(String[] args){
		BookUpdateJFrame bookUpdateJFrame = new BookUpdateJFrame();
		bookUpdateJFrame.setVisible(true);
	}
}
