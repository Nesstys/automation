import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.*;

public class APOI {
    public static void main(String[] argv) {

        ArrayList<String> familys = new ArrayList<>();
        ArrayList<String> order_names = new ArrayList<>();
        ArrayList<String> species = new ArrayList<>();
        ArrayList<String> redbook = new ArrayList<>();
        ArrayList<String> ordersId = new ArrayList<>();

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("animals.xml"));
            doc.getDocumentElement().normalize();

            NodeList listOfOrders = doc.getElementsByTagName("order");

            for (int s = 0; s < listOfOrders.getLength(); s++) {
                Node nNode = listOfOrders.item(s);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element idElement = (Element) nNode;
                    ordersId.add(idElement.getAttribute("orderId"));

                    Element eElement = (Element) nNode;
                    NodeList AuthorsList = eElement.getElementsByTagName("family");
                    Element firstNameElement = (Element) AuthorsList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    familys.add(textFNList.item(0).getNodeValue());

                    NodeList lastNameList = eElement.getElementsByTagName("ordname");
                    Element lastNameElement = (Element) lastNameList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    order_names.add(textLNList.item(0).getNodeValue());

                    NodeList nredbook = eElement.getElementsByTagName("redbook");
                    Element eredbook = (Element) nredbook.item(0);
                    NodeList nlredbook = eredbook.getChildNodes();
                    redbook.add(nlredbook.item(0).getNodeValue());

                    NodeList listOfSpecies = doc.getElementsByTagName("species");

                    for (int tmp = 0; tmp < listOfSpecies.getLength(); tmp++) {
                        Node firstspecieNode = listOfSpecies.item(tmp);
                        if (firstspecieNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element firstspecieElement = (Element) firstspecieNode;
                            NodeList cast1 = firstspecieElement.getElementsByTagName("specie");
                            for (int i = 0; i < listOfSpecies.getLength(); i++) {
                                Element casts2 = (Element) cast1.item(i);
                                NodeList textcastList1 = casts2.getChildNodes();
                                species.add(textcastList1.item(0).getNodeValue());
                            }
                        }
                    }
                }
            }
        } catch (SAXParseException err) {
            System.out.println("Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("01 mammalia");
        Sheet sheet1 = wb.createSheet("02 amphibia");
        Map<String, Object[]> data = new HashMap<>();

        HSSFCellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("orderId");
        row0.createCell(1).setCellValue("family");
        row0.createCell(2).setCellValue("ordname");
        row0.createCell(3).setCellValue("redbook");
        row0.createCell(4).setCellValue("species");
        for (int temp = 0; temp < row0.getLastCellNum(); temp++) {
            row0.getCell(temp).setCellStyle(style);
        }

        Row row01 = sheet1.createRow(0);
        row01.createCell(0).setCellValue("orderId");
        row01.createCell(1).setCellValue("family");
        row01.createCell(2).setCellValue("ordname");
        row01.createCell(3).setCellValue("redbook");
        row01.createCell(4).setCellValue("species");
        for (int temp = 0; temp < row01.getLastCellNum(); temp++) {
            row01.getCell(temp).setCellStyle(style);
        }

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 8));
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 6, 8));

        for (int i = 0; i < familys.size(); i++) {
            data.put(i + "", new Object[]{ordersId.get(0), familys.get(0), order_names.get(0), redbook.get(0), species.get(0), species.get(1), species.get(2)});
        }

        Set<String> keyset = data.keySet();
        int rownum = 1;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }
        Map<String, Object[]> data1 = new HashMap<>();
        for (int i = 0; i < familys.size(); i++) {
            data1.put(i + "", new Object[]{ordersId.get(1), familys.get(1), order_names.get(1),
                    redbook.get(1), species.get(3), species.get(4), species.get(5)});
        }

        Set<String> keyset1 = data1.keySet();
        int rownum1 = 2;
        for (String key : keyset1) {
            Row row1 = sheet.createRow(rownum1);
            Object[] objArr1 = data1.get(key);
            int cellnum = 0;
            for (Object obj : objArr1) {
                Cell cell = row1.createCell(cellnum++);
                for (int i = 0; i < row1.getLastCellNum(); i++) {
                    sheet.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        Map<String, Object[]> data2 = new HashMap<>();
        for (int i = 0; i < familys.size(); i++) {
            data2.put(i + "", new Object[]{ordersId.get(2), familys.get(2), order_names.get(2),
                    redbook.get(2), species.get(6), species.get(7), species.get(8)});
        }

        Set<String> keyset2 = data2.keySet();
        int rownum2 = 1;
        for (String key : keyset2) {
            Row row2 = sheet1.createRow(rownum2);
            Object[] objArr = data2.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row2.createCell(cellnum++);
                for (int i = 0; i < row2.getLastCellNum(); i++) {
                    sheet1.autoSizeColumn(i);
                }
                if (obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            FileOutputStream out = new FileOutputStream("animals.xls");
            wb.write(out);
            out.close();
            System.out.println("Excel written successfully...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
