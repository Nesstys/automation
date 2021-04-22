import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SaxQuery {
    public static void main(String[] args) {

        try {
            File inputFile = new File("animals.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler2 userHandler = new UserHandler2();
            saxParser.parse(inputFile, userHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler2 extends DefaultHandler {

    boolean family = false;
    boolean ordname = false;
    boolean redbook = false;
    boolean species = false;
    boolean specie = false;
    String orderId = null;
    String orderToFind = "02";

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("order")) {
            orderId = attributes.getValue("orderId");
        }
        if (orderToFind.equals(orderId)) {
            if (qName.equalsIgnoreCase("family")) {
                family = true;
            } else if (qName.equalsIgnoreCase("ordname")) {
                ordname = true;
            } else if (qName.equalsIgnoreCase("redbook")) {
                redbook = true;
            } else if (qName.equalsIgnoreCase("specie")) {
                specie = true;
            } else if (qName.equalsIgnoreCase("species")) {
                species = true;
            }
        }
    }

    public void species(char ch[], int start, int length) {
        if (orderToFind.equals(orderId)) {
            if (family) {
                System.out.println("family: " + new String(ch, start, length));
                family = false;
            } else if (ordname) {
                System.out.println("ordname: " + new String(ch, start, length));
                ordname = false;
            }  else if (redbook) {
                System.out.println("redbook: " + new String(ch, start, length));
                redbook = false;
            } else if (species) {
                System.out.print("species: ");
                species = false;
            } else if (specie) {
                System.out.print(new String(ch, start, length) + ", ");
                specie = false;
            }
        }
    }
}
