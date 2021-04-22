import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class DomQuery {
    public static void main(String argv[]) {

        try {
            File inputFile = new File("animals.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("phylum");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList bookNameList = eElement.getElementsByTagName("order");

                    for (int count = 0; count < bookNameList.getLength(); count++) {
                        Node node1 = bookNameList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element book = (Element) node1;
                            String m = book.getAttribute("orderId");
                            if (m.equals("01")) {
                                System.out.println("\nCurrent Element: ");
                                System.out.print("\tOrder id: ");
                                System.out.println(book.getAttribute("orderId"));
                                System.out.print("\tClass: ");
                                System.out.println(eElement.getAttribute("className"));
                                System.out.println("\tOrder: "
                                        + book.getElementsByTagName("ordname")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tFamily: "
                                        + book.getElementsByTagName("family")
                                        .item(0)
                                        .getTextContent());
                                System.out.println("\tRed book: "
                                        + eElement
                                        .getElementsByTagName("redbook")
                                        .item(0)
                                        .getTextContent());
                                System.out.print("\tSpecies: ");
                                NodeList cList = doc.getElementsByTagName("species");
                                for (int i = 0; i < cList.getLength(); i++) {
                                    System.out.print(eElement
                                            .getElementsByTagName("specie")
                                            .item(i)
                                            .getTextContent() + ", ");
                                    if (i == cList.getLength() - 1) {
                                        System.out.print("\b\b.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
