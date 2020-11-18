import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class CreateHTMLfromSearchResults extends JFrame {
	private static final long serialVersionUID = 1L;
	String htmpPage = "<html>\r\n" + "	<body>\r\n"
			+ "	<object classid=\"htmlButton\" value=\"Button\">This is Button</object>\r\n" + "	</body>\r\n"
			+ "	</html>";

	public CreateHTMLfromSearchResults() {

		JEditorPane pan = new JEditorPane();
//		pan.setContentType("text/html");
		URL url = CreateHTMLfromSearchResults.class.getResource("a.html");
		try {
			pan.setPage(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		add(pan);
		setSize(700, 700);
		setVisible(true);
	}

	class htmlButton extends JButton {
		private static final long serialVersionUID = 1L;

		htmlButton() {
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent a) {
					System.out.println("Hello");// new File("a.html"));
				}
			});
		}
	}

	public static void main(String[] args) {
		new CreateHTMLfromSearchResults().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
