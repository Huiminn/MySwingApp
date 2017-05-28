package com.huimin.swingApp.GUIShow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.huimin.swingApp.ConstantVariable.ConstantVar;
import com.huimin.swingApp.GUIShow.student.StudentUpdateJFrame;
import com.huimin.swingApp.GUIShow.userAccount.UserAccountAddJFrame;
import com.huimin.swingApp.GUIShow.userAccount.UserAccouontUpdateJFrame;
import com.huimin.swingApp.entity.Student;
import com.huimin.swingApp.entity.User;
import com.huimin.swingApp.service.UserAccountManagementService;
import com.huimin.swingApp.service.UserLoginService;
import com.huimin.swingApp.service.impl.UserAccountManagementServiceImpl;
import com.huimin.swingApp.service.impl.UserLoginServiceImpl;

@SuppressWarnings("serial")
public class UserAccountManagementJFrame extends JFrame implements ActionListener {

	private Object[] head={"Id","Username","Password","Role Name","Delete","Update"};
	private Object[][] data = null;
	private Object[] row = null;
	private JTable table;
	private DefaultTableModel dtm;
	private JButton btnAddUser;
	private JScrollPane scrollPane;
	private UserAccountManagementService userAccountManagementService;
	private JOptionPane jOptionPane;
	private int confirm;
	private JButton btnBack;
	public UserAccountManagementJFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblAddAccount = new JLabel("Add User");
		lblAddAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddAccount.setBounds(37, 46, 96, 20);
		getContentPane().add(lblAddAccount);
		
		btnAddUser = new JButton("Add");
		btnAddUser.setBounds(134, 40, 76, 30);
		btnAddUser.addActionListener(this);
		getContentPane().add(btnAddUser);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 91, 470, 180);
		getContentPane().add(scrollPane);
		
		userAccountManagementService = new UserAccountManagementServiceImpl();
		data = queryData(userAccountManagementService.selectAllUsers());
		dtm = new DefaultTableModel(data, head);
		
		table = new JTable();
		table.setModel(dtm);
		setTableCellForeColor(table);
		table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			public void mouseClicked(MouseEvent eve){
				 int row = table.rowAtPoint(eve.getPoint());
			        int col = table.columnAtPoint(eve.getPoint());
			        if(col==4){
			        	confirm = jOptionPane.showConfirmDialog(table, "Delete?");
			        	if(confirm==0){
			        		userAccountManagementService.deleteUser(Integer.valueOf(table.getValueAt(row, 0).toString()));
			        		removeTableModelData();
			        		data = queryData(userAccountManagementService.selectAllUsers());
			        		dtm = new DefaultTableModel(data,head);
			        		table.setModel(dtm);
			 
			        	}			        	
			        }else if(col==5){
			        	ConstantVar.userid = Integer.valueOf(table.getValueAt(row, 0).toString());
			        	//call function to close current JFrame and implement the bookUpdate
			        	UserUpdateImpl();
			        }
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(438, 43, 69, 30);
		btnBack.addActionListener(this);
		getContentPane().add(btnBack);
		
		this.setBounds(0, 0, 554, 321);
		this.setTitle("User Account Management System");
		
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
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnAddUser){
			this.setVisible(false);
			UserAccountAddJFrame userAccountAddJFrame = new UserAccountAddJFrame();
			userAccountAddJFrame.setVisible(true);
		}else if(obj == btnBack){
			this.setVisible(false);
			Main main = new Main();
			main.setVisible(true);
		}
		
	}
	
	//before setting new data to TableModel, clearing all data from TableModel
			private void removeTableModelData(){
				while(dtm.getRowCount()>0){
					dtm.removeRow(0);
				}
			}
		
		 public Object[][] queryData(List<User> list){
			 
		        data=new Object[list.size()][head.length];

		        for(int i=0;i<list.size();i++){
		            for(int j=0;j<head.length;j++){
		                data[i][0]=list.get(i).getUserid();
		                data[i][1]=list.get(i).getUserName();
		                data[i][2]=list.get(i).getPassword();
		                data[i][3]=list.get(i).getRoleName();
		                data[i][4]="Delete";	
		                data[i][5]="Update";	
		            }
		        }
		        return data;
		    }
	
		 private void UserUpdateImpl(){
				this.setVisible(false);
				UserAccouontUpdateJFrame uauj = new UserAccouontUpdateJFrame();
				uauj.setVisible(true);				
			}
		 
	public static void main(String[] args){
		UserAccountManagementJFrame uamJ = new UserAccountManagementJFrame();
		uamJ.setVisible(true);
	}
	
}
