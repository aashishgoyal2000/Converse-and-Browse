package Browser;
import java.util.HashMap;
import java.util.Map;

public class HtmlEditorKitTest {

	public String getString(HashMap<String, String> hm) {

		String htmlString = "<html>\n" + "<body>\n" + "<h1>Welcome!</h1>\n";

		for (Map.Entry<String, String> hp : hm.entrySet()) {
			htmlString += "<h1>" + hp.getKey() + "</h1>\n";
			htmlString += "<p><a href='" + hp.getValue() + "'>" + hp.getValue() + "</p>\n";
		}
		htmlString += "</body>\n";
		return htmlString;
	}
}