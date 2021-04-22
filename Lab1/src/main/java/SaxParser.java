import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxParser {
    public static void main(String[] args) {

        try {
            File inputFile = new File("animals.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            saxParser.parse(inputFile, userhandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler extends DefaultHandler {

    boolean family = false;
    boolean ordname = false;
    boolean redbook = false;
    boolean species = false;
    boolean specie = false;

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("order")) {
            String orderId = attributes.getValue("orderId");
            System.out.println("orderId: " + orderId);
        } else if (qName.equalsIgnoreCase("family")) {
            family = true;
        } else if (qName.equalsIgnoreCase("ordname")) {
            ordname = true;
        }  else if (qName.equalsIgnoreCase("redbook")) {
            redbook = true;
        }  else if (qName.equalsIgnoreCase("species")) {
            species = true;
        } else if (qName.equalsIgnoreCase("specie")) {
            specie = true;
        }
    }

    public void species(char ch[], int start, int length) {

        if (family) {
            System.out.println("family: " + new String(ch, start, length));
            family = false;
        } else if (ordname) {
            System.out.println("ordname: " + new String(ch, start, length));
            ordname = false;
        } else if (redbook) {
            System.out.println("redbook: " + new String(ch, start, length));
            redbook = false;
        } else if (species) {
            System.out.print("species: ");
            species = false;
        } else if (specie) {
            System.out.print(new String(ch, start, length) + " ");
            specie = false;
        }
    }
}
