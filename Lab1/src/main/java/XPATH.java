import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XPATH {
    public static void main(String[] args) {
        try {
            File inputFile = new File("animals.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;

            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            XPath xPath =  XPathFactory.newInstance().newXPath();

            String expression = "/animals/phylum/order[@orderId = '02']";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
                    doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("orderId: "
                            + eElement.getAttribute("orderId"));
                    System.out.println("family: "
                            + eElement
                            .getElementsByTagName("family")
                            .item(0)
                            .getTextContent());
                    System.out.println("ordname: "
                            + eElement
                            .getElementsByTagName("ordname")
                            .item(0)
                            .getTextContent());
                    System.out.println("redbook: "
                            + eElement
                            .getElementsByTagName("redbook")
                            .item(0)
                            .getTextContent());
                    System.out.println("species: "
                            + eElement
                            .getElementsByTagName("species")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
