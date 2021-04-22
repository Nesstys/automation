import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.LinkedHashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class JSONWrite {
    public static void main(String[] args) {
        Map m = new LinkedHashMap();
        JSONObject obj = new JSONObject();
        m.put("family", "Felidae");
        m.put("order name", "Carnivora");
        m.put("red book", "true");
        JSONArray list = new JSONArray();
        list.add("Lion");
        list.add("Tiger");
        list.add("Raccoon");
        m.put("species", list);
        obj.put("order1", m);


        Map m1 = new LinkedHashMap();
        m1.put("family", "Equidae");
        m1.put("order name", "Perissodactyla");
        m1.put("red book", "true");
        JSONArray list1 = new JSONArray();
        list1.add("Zebra");
        list1.add("Tapirs");
        list1.add("Rhinoceros");
        m1.put("species", list1);
        obj.put("order2", m1);


        Map m2 = new LinkedHashMap();
        m2.put("family", "Plethodontidae");
        m2.put("order name", "Urodela");
        m2.put("red book", "true");
        JSONArray list2 = new JSONArray();
        list2.add("Tiger salamander");
        list2.add("Sirens");
        list2.add("Fire salamander");
        m2.put("species", list2);
        obj.put("order3", m2);

        try (FileWriter file = new FileWriter("animals.json")) {
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }
}
