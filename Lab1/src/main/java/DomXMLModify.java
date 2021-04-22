import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;

public class DomXMLModify {
    public static void main(String[] argv) {
        String phylumToCopy = "02";

        try {
            File inputFile = new File("animals.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            DocumentBuilderFactory dbrFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder drBuilder = dbrFactory.newDocumentBuilder();
            Document rdoc = drBuilder.newDocument();

            NodeList phylumList = doc.getElementsByTagName("phylum");

            for (int temp = 0; temp < phylumList.getLength(); temp++) {
                Node phylum = phylumList.item(temp);
                if (phylum.getNodeType() == Node.ELEMENT_NODE) {
                    Element orderAtt = (Element) phylum;
                    if (orderAtt.getAttribute("classId").equals(phylumToCopy)) {
                        Element animalsElement = rdoc.createElement("animals");
                        rdoc.appendChild(animalsElement);

                        Element phylumElement = rdoc.createElement("phylum");
                        animalsElement.appendChild(phylumElement);

                        Attr classId = rdoc.createAttribute("classId");
                        classId.setValue(orderAtt.getAttribute("classId"));
                        phylumElement.setAttributeNode(classId);

                        Attr className = rdoc.createAttribute("className");
                        className.setValue(orderAtt.getAttribute("className"));
                        phylumElement.setAttributeNode(className);

                        NodeList orderNameList = orderAtt.getElementsByTagName("order");

                        for (int count = 0; count < orderNameList.getLength(); count++) {
                            Node books = orderNameList.item(count);
                            if (books.getNodeType() == books.ELEMENT_NODE) {
                                Element bookInfo = (Element) books;

                                Element book = rdoc.createElement("order");
                                phylumElement.appendChild(book);

                                Attr orderId = rdoc.createAttribute("orderId");
                                orderId.setValue(bookInfo.getAttribute("orderId"));
                                book.setAttributeNode(orderId);

                                Element family = rdoc.createElement("family");
                                family.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("family").item(0).getTextContent()));
                                book.appendChild(family);

                                Element orderName = rdoc.createElement("ordname");
                                orderName.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("ordname").item(0).getTextContent()));
                                book.appendChild(orderName);

                                Element redbook = rdoc.createElement("redbook");
                                redbook.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("redbook").item(0).getTextContent()));
                                book.appendChild(redbook);

                                Element species = rdoc.createElement("species");
                                book.appendChild(species);

                                NodeList charactersList = doc.getElementsByTagName("species");
                                for (int i = 0; i < charactersList.getLength(); i++){
                                    Element character = rdoc.createElement("specie");
                                    character.appendChild(rdoc.createTextNode(bookInfo.getElementsByTagName("specie").item(i).getTextContent()));
                                    species.appendChild(character);
                                }
                            }
                        }
                    }
                }
            }
            // write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(new File("animals_mod.xml"));
            DOMSource source = new DOMSource(rdoc);
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
