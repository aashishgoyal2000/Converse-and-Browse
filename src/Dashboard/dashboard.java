package Dashboard;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import Browser.Browser;
import Notepad.*;
import Chatting.*;

public class dashboard {
	JFrame f = new JFrame("Converse And Browse");
	JPanel panel=new JPanel();   
	public static JLabel lblNewLabel;
	String path = "D:\\\\College Java Specillization\\\\GUI Practise\\\\Converse And Browse\\\\src\\\\Dashboard\\\\";
	Browser p1=new Browser();   
    TextEditor p2=new TextEditor();  
    chat p3=new chat();  
    JLabel l2=new JLabel("<html>Spending too much<br>time on switching<br>Applications?<br>We can fix that.<br>Now Browse,Converse and use an Editor within the same window.</html>");
    JTabbedPane tp=new JTabbedPane();
    JLabel lblNewLabel_1 = new JLabel("Contact Us:- (+91)9876543210");
    
    GroupLayout gl_panel = new GroupLayout(panel);
    
    
    
    dashboard()
	{
		f.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel.setBackground(Color.WHITE);
     
		Icon icon = new ImageIcon(getClass().getResource("logo.jpeg"));
        
		JLabel l=new JLabel(icon);
        
        l.setFont(new Font("Tahoma", Font.PLAIN, 10));
        l.setText("");
        l.setHorizontalAlignment(SwingConstants.LEFT);
	      
	    l2.setForeground(Color.ORANGE);
	    l2.setFont(new Font("Tahoma", Font.BOLD, 20));    
            
        tp.setFont(new Font("Tahoma", Font.PLAIN, 22));
        tp.setTabPlacement(JTabbedPane.LEFT);
        tp.add("Home",panel);  
        lblNewLabel = new JLabel("");
        
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(l2, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        				.addComponent(l, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGap(88)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
        			.addContainerGap())
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addGap(50)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(l, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(l2, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        tp.add("Browse",p1);  
        tp.add("Chat",p3);   
        tp.add("Editor",p2);
        
        GroupLayout groupLayout = new GroupLayout(f.getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addComponent(tp, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(103, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addComponent(tp)
        );
        f.getContentPane().setLayout(groupLayout);
        f.setSize(626,626);
	    f.setVisible(true);
	}
	public static void main(String[] args) {
	     new dashboard();
	    new Slideshow();
}
}

