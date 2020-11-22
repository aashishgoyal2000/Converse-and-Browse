package Chatting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class MyCanvas extends JComponent { 
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g) {  
    	Color bg = new Color(192,192,192); 
    	g.setColor(bg);
        g.drawLine(175,0,175,440);
        g.drawLine(0,68,500,68);
    } 
} 

public class chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel=new JPanel();  
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private final String path = "D:\\\\College Java Specillization\\\\GUI Practise\\\\Converse And Browse\\\\src\\\\Chatting\\\\";
	
	JButton b, b2, b3, b4;
	private DefaultListModel<String> l2 = new DefaultListModel<>();
	private JLabel l3=new JLabel("Contacts"), l1=new JLabel();
	private Color bg3 = new Color(0,204,0);
	private Font font = new Font("Franklin Gothic Heavy", Font.ITALIC, 20), font2 = new Font("Franklin Gothic Heavy", Font.PLAIN, 13), font3 = new Font("System", Font.PLAIN, 15);
	private JTextArea area=new JTextArea();
	private JTextField tf2=new JTextField();
	final JList<String> list2 = new JList<>(l2);

	public chat() {
		
		Icon icon = new ImageIcon(getClass().getResource("rsz_green_arrow.jpeg"));
		Icon icon2 = new ImageIcon(getClass().getResource("rsz_add_icon.jpeg"));
		Icon icon3 = new ImageIcon(getClass().getResource("rsz_delete.jpeg"));
		b=new JButton(icon);
		b2=new JButton("Start a new chat");
		b3=new JButton(icon2);
		b4=new JButton(icon3);
        setBounds(0, 0, 520, 700);     
		setLayout(null);
	      
	    l3.setBounds(50,30,100,30);
	    add(l3);
	    l3.setFont(font); 
	    l3.setForeground(bg3);
	      
	    l1.setBounds(354,42, 172,18); 
	    
	    l1.setFont(font);
	    l1.setForeground(bg3);
	    add(l1);
	     
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
        
	    list2.setBounds(0,70,190,465);
        list2.setFont(font3);
        b.setBounds(441,504,70,30); 
		b.setFont(font2);
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setBackground(bg3);  
		b2.setBounds(341,293,141,30);
		b2.setFont(font2);
		b4.setBounds(0,30,28,38);
		b4.setFont(font2); 
		b4.setBorder(BorderFactory.createEmptyBorder());
		tf2.setBounds(196,504,233,31); 
		area.setBounds(196,70,312,421);
		area.setFont(font2);
		b3.setBounds(162,30,28,38);
		b3.setFont(font2);
		b3.setBorder(BorderFactory.createEmptyBorder());
		addActionListener();
		
		add(area);
		add(tf2);    
	    add(list2);
		add(b);
		add(b3);
		add(b2);
		add(b4);
		add(new MyCanvas());
		area.setVisible(false);
        b.setVisible(false);
        b2.setVisible(false);
        tf2.setVisible(false);
        l1.setVisible(false);  
	}
	
	private void addActionListener() {
		list2.addListSelectionListener(new ListSelectionListener()  {	
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				area.setVisible(false);
				tf2.setVisible(false);
				b.setVisible(false);
		        l1.setVisible(false);
		        b2.setVisible(false);
		        try {
					pstmt=con.prepareStatement("select * from chatstable where name=?");
					pstmt.setString(1,list2.getSelectedValue());
					ResultSet table=pstmt.executeQuery();
					if(table.next()) {
						if(table.getString("chat").equals("")) {
							area.setText("");
							b2.setVisible(true);
					    }
					    else {
							area.setVisible(true);
							tf2.setVisible(true);
							b.setVisible(true);
						    l1.setVisible(true);
					        l1.setText(list2.getSelectedValue());
							area.setText(table.getString("chat"));
						
					    }
					}
		    	}
		    	catch (SQLException e) {e.printStackTrace();}  
			}
		});
		b.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){ 
				area.append("\n");
				area.append(tf2.getText());
				try 
				{
					pstmt=con.prepareStatement("update chatstable set chat=? where name=?");
		   			pstmt.setString(1,area.getText());
		   			pstmt.setString(2,list2.getSelectedValue());
		   			pstmt.executeUpdate();
		   		} 
		        catch (SQLException ex) {
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
				String newname=JOptionPane.showInputDialog("Enter Name");
				l2.addElement(newname); 
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
				int a=JOptionPane.showConfirmDialog(panel, "Are you sure?");  
				if(a==JOptionPane.YES_OPTION){  
				    try {
						pstmt=con.prepareStatement("delete from chatstable where name=?");
						pstmt.setString(1,list2.getSelectedValue());
						pstmt.executeUpdate();	
				    } 
				    catch (SQLException e2) {
						e2.printStackTrace();
					}  
					int ind=list2.getSelectedIndex();
					l2.remove(ind);
					b2.setVisible(false);
				}     
			 } 
		});

	}
	
	public static void main(String[] args) {
		new chat();
	}
}


