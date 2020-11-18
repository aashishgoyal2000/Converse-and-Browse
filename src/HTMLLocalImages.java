import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

public class HTMLLocalImages {

	public static String localImageSrc = "http:\\test.jpg";
	public static Image localImage = createImage();
	public static String HTML = "<html>\n" + "<body>\n" + "Local image accessed from HTML<br>\n" + "<img src=\""
			+ localImageSrc + "\">\n" + "</body>\n" + "</html>";

	JTextPane edit = new JTextPane();

	public HTMLLocalImages() {
		JFrame frame = new JFrame("Using local images example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(edit);
		edit.setContentType("text/html");
		edit.setText(HTML);
		try {
			@SuppressWarnings("unchecked")
			Dictionary<URL, Image> cache = (Dictionary<URL, Image>) edit.getDocument().getProperty("imageCache");
			if (cache == null) {
				cache = new Hashtable<URL, Image>();
				edit.getDocument().putProperty("imageCache", cache);
			}
			URL u = new URL(localImageSrc);
			cache.put(u, localImage);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static Image createImage() {
		BufferedImage img = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 100, 50);
		g.setColor(Color.YELLOW);
		g.fillOval(5, 5, 90, 40);
		img.flush();

		return img;
	}

	public static void main(String[] args) throws Exception {
		new HTMLLocalImages();
	}
}
