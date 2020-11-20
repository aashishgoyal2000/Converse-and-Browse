package Browser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JEditorPane;
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
import javax.swing.JPanel;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;

public class Browser extends JPanel{

	private static final long serialVersionUID = 1L;
	private JEditorPane display = new JEditorPane(), editorPane = new JEditorPane();
	private JScrollPane jsp = new JScrollPane(display);
	private JButton books = new JButton("Books"), images = new JButton("Images"), shopping = new JButton("Shopping"), news = new JButton("News");
	private String bookss = null, imagess = null, newss = null, shoppingg = null;
	private HTMLEditorKit kit = new HTMLEditorKit();
    private Document doc = kit.createDefaultDocument();
    private HtmlEditorKitTest hekt = new HtmlEditorKitTest();
    private GoogleSearch googleSearch = new GoogleSearch();
    private JTextField addressBar;
    
	public Browser() {
		editorPane.setEditable(false);
		addressBar = new JTextField("Enter a URL address");
		addressBar.setToolTipText("Enter a URL");
		addressBar.setColumns(10);
		addressBar.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					loadCrap(event.getActionCommand());
				}
			}
		);
		
		JLabel lblNewLabel = new JLabel("");
		Icon ic = new ImageIcon("D:\\College Java Specillization\\GUI Practise\\Converse And Browse\\src\\google.png");
		Image ii = ((ImageIcon) ic).getImage();
		Image newImg = ii.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(newImg));
		
		display.setBackground(Color.GRAY);
		display.setDropMode(DropMode.INSERT);	
		display.setEditable(false);
		display.setDocument(doc);
		
		display.addHyperlinkListener(
				new HyperlinkListener() {	
					@Override
					public void hyperlinkUpdate(HyperlinkEvent e) {
						if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
							loadCrap(e.getURL().toString());
						}
					}
				}
			);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(editorPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(shopping, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(images, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(books, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(news, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(addressBar)
						.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(addressBar, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(books)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(images)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(shopping)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(news)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)))
					.addContainerGap())
		);
		setLayout(groupLayout);
		
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
			}
			else {
				display.setContentType("text/html");
				display.setPage(actionCommand);
				addressBar.setText(actionCommand);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
    boolean isValidURL(String url) {
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
        catch (Exception e) { 
            return false; 
        } 
    }

    public void setButtons(HashMap<String, String> ll) {
    	books.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (bookss == null)
				{
					bookss = (String)ll.get("Books");
					ll.remove("Books");
				}
				loadCrap(bookss);
			}
		});
    	images.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (imagess == null) {
					imagess = (String)ll.get("Images");
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
}