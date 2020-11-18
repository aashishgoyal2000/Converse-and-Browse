import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MyCanvas extends JComponent { 
	  
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) 
    {  
    	Color bg = new Color(192,192,192); 
    	g.setColor(bg);
        g.drawLine(175,0,175,440);
        g.drawLine(0,68,500,68);
    } 
} 

public class sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Chat"); 
		Color bg3 = new Color(0,204,0); 
	    JLabel l3=new JLabel("Contacts");  
	    l3.setBounds(40,38,150,30);
	    f.add(l3);
	    Font font = new Font("Franklin Gothic Heavy", Font.ITALIC, 20);
	    l3.setFont(font); 
	    l3.setForeground(bg3);
	    JLabel l1=new JLabel();  
	    l1.setBounds(290,42, 100,18); 
	    Font font1 = new Font("Franklin Gothic Heavy", Font.ITALIC, 20);
	    l1.setFont(font1);
	    l1.setForeground(bg3);
	    f.add(l1);
	    DefaultListModel<String> l2 = new DefaultListModel<>(); 
        PreparedStatement pstmt;   
 	   Connection  con = null;// = ConnectToDatabase.getConnection();
 		    try {
 				Class.forName("com.mysql.jdbc.Driver");
 				  con=DriverManager.getConnection("jdbc:mysql://localhost/swingsproject","root","");
 			} catch (Exception e1) {
 				e1.printStackTrace();
 			} 
 		    try {
 		    	
 				pstmt=con.prepareStatement("select * from chatstable");
 				ResultSet table=pstmt.executeQuery();
 				while(table.next())
 				{
 					String n=table.getString("name");
 					l2.addElement(n);
 				}
 				}
 			 catch (SQLException e2) {
 				e2.printStackTrace();
 			}
        final JList<String> list2 = new JList<>(l2);  
        Font font3 = new Font("System", Font.PLAIN, 15);
        list2.setBounds(0,70,175,370);
        list2.setFont(font3);
        Font font2 = new Font("Franklin Gothic Heavy", Font.PLAIN, 13);
        Icon icon = new ImageIcon("C://Users//Lenovo//eclipse-workspace//SwingsProject//src//chats//rsz_green_arrow.png");
	    JButton b=new JButton(icon);  
		   b.setBounds(427,410,70,30); 
		   b.setFont(font2);
		   b.setBorder(BorderFactory.createEmptyBorder());
		   b.setBackground(bg3);
		   JButton b2=new JButton("Start a new chat");  
		   b2.setBounds(260,200,150,20);
		   b2.setFont(font2);
		   Icon icon2 = new ImageIcon("C://Users//Lenovo//eclipse-workspace//SwingsProject//src//chats//rsz_add_icon.png");
		   JButton b3=new JButton(icon2);  
		   b3.setBounds(155,38,20,30);
		   b3.setFont(font2);
		   b3.setBorder(BorderFactory.createEmptyBorder());
		   Icon icon3 = new ImageIcon("C://Users//Lenovo//eclipse-workspace//SwingsProject//src//chats//rsz_delete.png");
		   JButton b4=new JButton(icon3);  
		   b4.setBounds(0,38,20,30);
		   b4.setFont(font2); 
		   b4.setBorder(BorderFactory.createEmptyBorder());
		   JTextArea area=new JTextArea();
		    area.setBounds(176,70,324,340);
		    area.setFont(font2);
		    f.add(area);
		    JTextField tf2=new JTextField(); 
		    tf2.setBounds(176,410,252,31); 
		    f.add(tf2);
		    
		    list2.addListSelectionListener(new ListSelectionListener() 
			{
				
				@Override
				public void valueChanged(ListSelectionEvent arg0) 
				{
					area.setVisible(false);
					tf2.setVisible(false);
					  b.setVisible(false);
				        l1.setVisible(false);
				        b2.setVisible(false);
							PreparedStatement pstmt;   
					    	   Connection  con = null;
					    		    try {
					    				Class.forName("com.mysql.jdbc.Driver");
					    				  con=DriverManager.getConnection("jdbc:mysql://localhost/swingsproject","root","");
					    			} catch (Exception e1) {
					    				e1.printStackTrace();
					    			}
					        try 
					    	{
								pstmt=con.prepareStatement("select * from chatstable where name=?");
								pstmt.setString(1,list2.getSelectedValue());
								ResultSet table=pstmt.executeQuery();
								if(table.next())
								{	
								 if(table.getString("chat").equals(""))
								  {
									area.setText("");
									b2.setVisible(true);
								  }
								 else
								 {
									area.setVisible(true);
									tf2.setVisible(true);
									  b.setVisible(true);
								        l1.setVisible(true);
								        l1.setText(list2.getSelectedValue());
									area.setText(table.getString("chat"));
								 }
					    	    }
					    	}
					    	catch (SQLException e) 
					    	{
								e.printStackTrace();
							}  
				}
			});
			b.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){ 
					   area.append("\n");
				           area.append(tf2.getText());
				           PreparedStatement pstmt;   
				    	   Connection  con = null;
				    		    try {
				    				Class.forName("com.mysql.jdbc.Driver");
				    				  con=DriverManager.getConnection("jdbc:mysql://localhost/swingsproject","root","");
				    			} catch (Exception e1) {
				    				e1.printStackTrace();
				    			} 
				           try {
				   			pstmt=con.prepareStatement("update chatstable set chat=? where name=?");
				   			pstmt.setString(1,area.getText());
				   			pstmt.setString(2,list2.getSelectedValue());
				   			pstmt.executeUpdate();
				   		} catch (SQLException ex) {
				   			ex.printStackTrace();
				   		}
				        } 
				    });    
			b2.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
					b2.setVisible(false);
					area.setVisible(true);
					tf2.setVisible(true);
					  b.setVisible(true);
				        l1.setVisible(true);
				        l1.setText(list2.getSelectedValue());
				        } 
				    });    
			b3.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e)
				{ 
					String newname=JOptionPane.showInputDialog(f,"Enter Name");
				       l2.addElement(newname); 
				       PreparedStatement pstmt;   
			    	   Connection  con = null;// = ConnectToDatabase.getConnection();
			    		    try {
			    				Class.forName("com.mysql.jdbc.Driver");
			    				  con=DriverManager.getConnection("jdbc:mysql://localhost/swingsproject","root","");
			    			} catch (Exception e1) {
			    				e1.printStackTrace();
			    			} 
			    		    try {
			    				pstmt=con.prepareStatement("insert into chatstable values(?,?)");
			    				pstmt.setString(1,newname);
			    				pstmt.setString(2,"");
			    				pstmt.executeUpdate();
			    			} catch (SQLException e1) {
			    				e1.printStackTrace();
			    			}
		        } 
				    });  
			b4.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e)
				{ 
					int a=JOptionPane.showConfirmDialog(f,"Are you sure?");  
					if(a==JOptionPane.YES_OPTION){  
					    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    PreparedStatement pstmt;   
				    	   Connection  con = null;// = ConnectToDatabase.getConnection();
				    		    try {
				    				Class.forName("com.mysql.jdbc.Driver");
				    				  con=DriverManager.getConnection("jdbc:mysql://localhost/swingsproject","root","");
				    			} catch (Exception e1) {
				    				e1.printStackTrace();
				    			} 
				    		    try {
				    		    	
				    				pstmt=con.prepareStatement("delete from chatstable where name=?");
				    				pstmt.setString(1,list2.getSelectedValue());
				    				pstmt.executeUpdate();
				    			} catch (SQLException e2) {
				    				e2.printStackTrace();
				    			}  
				    		    int ind=list2.getSelectedIndex();
							    l2.remove(ind);
							    b2.setVisible(false);
					}     
		        } 
				    });  
			f.add(list2);
			f.add(b);
			f.add(b2);
			f.add(b3);
			f.add(b4);
			area.setVisible(false);
	        b.setVisible(false);
	        b2.setVisible(false);
	        tf2.setVisible(false);
	        l1.setVisible(false);
	        Image icon4 = Toolkit.getDefaultToolkit().getImage("C://Users//Lenovo//eclipse-workspace//SwingsProject//src//chats//m.jpg");
	        f.setIconImage(icon4);
        f.setSize(500, 500); 
        f.getContentPane().add(new MyCanvas());  
        f.setVisible(true);    
	}
}

