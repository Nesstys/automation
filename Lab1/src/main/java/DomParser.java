import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomParser {
    public static void main(String[] args) {
        try {
            File inputFile = new File("animals.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("order");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Order ID: "
                            + eElement.getAttribute("orderId"));
                    System.out.println("Order name: "
                            + eElement.getElementsByTagName("ordname")
                            .item(0)
                            .getTextContent());
                    System.out.println("Family : "
                            + eElement
                            .getElementsByTagName("family")
                            .item(0)
                            .getTextContent());
                    System.out.println("Red Book: "
                            + eElement
                            .getElementsByTagName("redbook")
                            .item(0)
                            .getTextContent());
                    System.out.print("Species: ");
                    NodeList cList = doc.getElementsByTagName("species");
                    for (int i = 0; i < cList.getLength(); i++) {
                        System.out.print(eElement
                                .getElementsByTagName("specie")
                                .item(i)
                                .getTextContent() + " ");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
