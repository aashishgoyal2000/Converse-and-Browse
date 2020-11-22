package Dashboard;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import Dashboard.dashboard;

public class Slideshow
{
	Timer tm;
    int x = 0;
    private String path = "D:\\\\College Java Specillization\\\\GUI Practise\\\\Converse And Browse\\\\src\\\\Dashboard\\\\";
    URL[] list = {
    		getClass().getResource("browse.jpeg"),//0
    		getClass().getResource("notepad.jpeg"),//1
    		getClass().getResource("chat.jpeg")//2
//    		path + "notepad.jpeg",//1
//            path + "chat.jpeg"//2
          };
	public Slideshow()
	{
		SetImageSize(2);
		tm = new Timer(1500,new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				SetImageSize(x);
		        x += 1;
		        if(x >= list.length )
		        	x = 0; 
		    }
		 });
		 tm.start();
	}
	public void SetImageSize(int i) {
	        ImageIcon icon = new ImageIcon(list[i]);
	        Image img = icon.getImage();
	        Image newImg = img.getScaledInstance(dashboard.lblNewLabel.getWidth(), dashboard.lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon newImc = new ImageIcon(newImg);
	        dashboard.lblNewLabel.setIcon(newImc);
	    }
}