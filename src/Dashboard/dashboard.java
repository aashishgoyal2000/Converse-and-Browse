package Dashboard;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTabbedPane;
import Browser.Browser;
import Notepad.*;
import Chatting.*;
//@SuppressWarnings("unused")
public class dashboard extends JFrame{

	private static final long serialVersionUID = 1L;
	
	dashboard() {
		setSize(620, 700);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		Browser browser = new Browser();
		chat chatting = new chat();
		TextEditor notepd = new TextEditor();
		
		tabbedPane.add(browser);
		tabbedPane.setToolTipTextAt(0, "Browser\r\n");
		tabbedPane.add(chatting);
		tabbedPane.add(notepd);
		
		tabbedPane.setTitleAt(0, "Browser");
		tabbedPane.setTitleAt(1, "Converse");
		tabbedPane.setTitleAt(2,  "Notepad");

		setVisible(true);
	}

	public static void main(String[] args) {

		new dashboard().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
