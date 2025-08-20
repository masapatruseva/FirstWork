import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;



public class Main {
    public static void main(String[] args) {

        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("фрукт", "груша");
        multimap.put("фрукт", "яблоко");
        multimap.put("овощ", "помидор");
        multimap.put("фрукт", "апельсин");
        multimap.put("овощ", "лук");

        System.out.println(multimap.get("фрукт"));
        System.out.println(multimap.get("овощ"));




    }


}
