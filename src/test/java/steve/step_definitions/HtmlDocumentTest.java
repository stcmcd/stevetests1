package steve.step_definitions;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.XmlPath.CompatibilityMode;

public class HtmlDocumentTest {

    @Test
    public void titleShouldBeHelloWorld() {
        XmlPath doc = new XmlPath(
                CompatibilityMode.HTML,
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
                        + "<head><title>Hello world</title></head>"
                        + "<body>some body"
                        + "<div class=\"content\">wrapped</div>"
                        + "<div class=\"content\">wrapped2</div>"
                        + "</body></html>");

        String title = doc.getString("html.head.title");
        String content = doc.getString("html.body.div.find { it.@class == 'content' }");
        String content2 = doc.getString("**.findAll { it.@class == 'content' }[1]");

        assertEquals("Hello world", title);
        assertEquals("wrapped", content);
        assertEquals("wrapped2", content2);
    }
}