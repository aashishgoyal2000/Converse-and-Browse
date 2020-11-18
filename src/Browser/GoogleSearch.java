package Browser;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {

	public static void main(String[] args) {
		GoogleSearch gs = new GoogleSearch();
		try {
			System.out.println(gs.googleSearch("journaldev").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, String> googleSearch(String search) throws IOException {
		String google = "http://www.google.com/search?q=";
		String charset = "UTF-8";
		String userAgent = "Mozilla/5.0";

		Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get()
				.select("a[href]");
		HashMap<String, String> ll = new HashMap<String, String>();
		for (Element link : links) {
			String linkedString = link.attr("href");
			if (linkedString.contains("/url?q=")) {
				linkedString = linkedString.substring(7);
			}
			if (isValidURL(linkedString)) {
				ll.put(link.text(), linkedString + "\n");
			} else {
				ll.put(link.text(), google + linkedString + "\n");
			}
		}
		return ll;
	}

	boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
