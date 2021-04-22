import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class DomXMLCreate {
    public static void main(String argv[]) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element animalsElement = doc.createElement("animals");
            doc.appendChild(animalsElement);

            Element phylum1 = doc.createElement("phylum");
            animalsElement.appendChild(phylum1);

            Attr classId = doc.createAttribute("classId");
            classId.setValue("01");
            phylum1.setAttributeNode(classId);

            Attr className = doc.createAttribute("className");
            className.setValue("mammalia");
            phylum1.setAttributeNode(className);

            Element order1 = doc.createElement("order");
            phylum1.appendChild(order1);

            Attr orderId = doc.createAttribute("orderId");
            orderId.setValue("01");
            order1.setAttributeNode(orderId);

            Element family = doc.createElement("family");
            family.appendChild(doc.createTextNode("felidae"));
            order1.appendChild(family);

            Element orderName = doc.createElement("ordname");
            orderName.appendChild(doc.createTextNode("Carnivora"));
            order1.appendChild(orderName);

            Element red_book = doc.createElement("redbook");
            red_book.appendChild(doc.createTextNode("true"));
            order1.appendChild(red_book);

            Element species = doc.createElement("species");
            order1.appendChild(species);

            Element specie1 = doc.createElement("specie");
            specie1.appendChild(doc.createTextNode("Lion"));
            species.appendChild(specie1);

            Element specie2 = doc.createElement("specie");
            specie2.appendChild(doc.createTextNode("Tiger"));
            species.appendChild(specie2);

            Element specie3 = doc.createElement("specie");
            specie3.appendChild(doc.createTextNode("Raccoon"));
            species.appendChild(specie3);

            Element order2 = doc.createElement("order");
            phylum1.appendChild(order2);

            Attr order2Id = doc.createAttribute("orderId");
            order2Id.setValue("02");
            order2.setAttributeNode(order2Id);

            Element family2 = doc.createElement("family");
            family2.appendChild(doc.createTextNode("equidae"));
            order2.appendChild(family2);

            Element order2Name = doc.createElement("ordname");
            order2Name.appendChild(doc.createTextNode("perissodactyla"));
            order2.appendChild(order2Name);

            Element red_book2 = doc.createElement("redbook");
            red_book2.appendChild(doc.createTextNode("true"));
            order2.appendChild(red_book2);

            Element species2 = doc.createElement("species");
            order2.appendChild(species2);

            Element specie12 = doc.createElement("specie");
            specie12.appendChild(doc.createTextNode("Zebra"));
            species2.appendChild(specie12);

            Element specie22 = doc.createElement("specie");
            specie22.appendChild(doc.createTextNode("Tapirs"));
            species2.appendChild(specie22);

            Element specie32 = doc.createElement("specie");
            specie32.appendChild(doc.createTextNode("Rhinoceros"));
            species2.appendChild(specie32);

            Element phylum2 = doc.createElement("phylum");
            animalsElement.appendChild(phylum2);

            Attr phylumId = doc.createAttribute("classId");
            phylumId.setValue("02");
            phylum2.setAttributeNode(phylumId);

            Attr phylum2Name = doc.createAttribute("className");
            phylum2Name.setValue("amphibia");
            phylum2.setAttributeNode(phylum2Name);

            Element order3 = doc.createElement("order");
            phylum2.appendChild(order3);

            Attr order3Id = doc.createAttribute("orderId");
            order3Id.setValue("03");
            order3.setAttributeNode(order3Id);

            Element family3 = doc.createElement("family");
            family3.appendChild(doc.createTextNode("plethodontidae"));
            order3.appendChild(family3);

            Element order3Name = doc.createElement("ordname");
            order3Name.appendChild(doc.createTextNode("urodela"));
            order3.appendChild(order3Name);

            Element red_book3 = doc.createElement("redbook");
            red_book3.appendChild(doc.createTextNode("true"));
            order3.appendChild(red_book3);

            Element species3 = doc.createElement("species");
            order3.appendChild(species3);

            Element specie13 = doc.createElement("specie");
            specie13.appendChild(doc.createTextNode("Tiger salamander"));
            species3.appendChild(specie13);

            Element specie23 = doc.createElement("specie");
            specie23.appendChild(doc.createTextNode("Sirens"));
            species3.appendChild(specie23);

            Element specie33 = doc.createElement("specie");
            specie33.appendChild(doc.createTextNode("Fire salamander"));
            species3.appendChild(specie33);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StreamResult result = new StreamResult(new File("animals.xml"));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
