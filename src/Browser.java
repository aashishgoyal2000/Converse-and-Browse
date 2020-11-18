import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.DropMode;
import java.awt.Color;

public class Browser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField addressbar;
	private JEditorPane display = new JEditorPane(), editorPane = new JEditorPane();
	private JButton books = new JButton("Books"), images = new JButton("Images"), shopping = new JButton("Shopping"),
			news = new JButton("News");
	private JScrollPane jsp = new JScrollPane(display);
	private String bookss = null, imagess = null, newss = null, shoppingg = null;
	private HTMLEditorKit kit = new HTMLEditorKit();
	private Document doc = kit.createDefaultDocument();
	private HtmlEditorKitTest hekt = new HtmlEditorKitTest();
	private GoogleSearch googleSearch = new GoogleSearch();

	public Browser() {
		super("Converse and Browse");
		addressbar = new JTextField("Enter a URL address");
		addressbar.setToolTipText("Enter a URL");
		addressbar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				loadCrap(event.getActionCommand());
			}
		});
		display.setBackground(Color.GRAY);
		display.setDropMode(DropMode.INSERT);
		display.setEditable(false);
		display.setDocument(doc);

		Icon ic = new ImageIcon("D:\\College Java Specillization\\GUI Practise\\Converse And Browse\\src\\google.png");
		Image ii = ((ImageIcon) ic).getImage();
		Image newImg = ii.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(newImg)); // NOI18N

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(editorPane,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(shopping, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(books, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(images, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(news, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(addressbar, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
						.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(addressbar)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(books).addGap(18)
								.addComponent(images).addGap(18).addComponent(news).addGap(18).addComponent(shopping)
								.addGap(18)
								.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE))
						.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 555, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		display.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					loadCrap(e.getURL().toString());
				}
			}
		});
		getContentPane().setLayout(groupLayout);

		setSize(700, 700);
		setVisible(true);
	}

	protected void loadCrap(String actionCommand) {
		try {
			if (!isValidURL(actionCommand)) {
				HashMap<String, String> res = googleSearch.googleSearch(actionCommand);
				System.out.println(res.toString() + "Nothing here");
				setButtons(res);
				display.setEditorKit(kit);
				StyleSheet styleSheet = kit.getStyleSheet();
				styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
				styleSheet.addRule("h1 {color: blue;}");
				styleSheet.addRule("p {color: #ff0000;}");
				styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
				String stringOfHTML = hekt.getString(res);
				display.setText(stringOfHTML);
			} else {
				display.setContentType("text/html");
				display.setPage(actionCommand);
				addressbar.setText(actionCommand);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void setButtons(HashMap<String, String> ll) {
		books.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bookss == null) {
					bookss = (String) ll.get("Books");
					ll.remove("Books");
				}
				loadCrap(bookss);
			}
		});
		images.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imagess == null) {
					imagess = (String) ll.get("Images");
					ll.remove("Images");
				}
				loadCrap(imagess);
			}
		});
		news.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (newss == null) {
					newss = (String) ll.get("News");
					ll.remove("News");
				}
				loadCrap(newss);
			}
		});
		shopping.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (shoppingg == null) {
					shoppingg = (String) ll.get("Shopping");
					ll.remove("Shopping");
				}
				loadCrap(shoppingg);
			}
		});

	}

	public static void main(String[] args) {
		new Browser().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}