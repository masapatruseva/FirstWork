package Demonstration;

import cattery.Cat;
import cattery.Cattery;
import com.google.gson.Gson;
import myJson.JsonObjectWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>(Arrays.asList(
                new Cat("Мурка", "Тайская", 3),
                new Cat("Барсик", "Бенгальская", 5),
                new Cat("Пушок", "Мейн-кун", 1),
                new Cat("Симба", "Персидская", 2),
                new Cat("Тишка", "Бирманская", 8)
        ));

        Cattery cattery = new Cattery("Кошкин дом", cats);
        Cat cat = new Cat("Мурка", "Тайская", 3);

        System.out.println("Из объекта в Json");
        JsonObjectWriter jsonObjectWriter = new JsonObjectWriter();
        String catSer = jsonObjectWriter.toJson(cat);
        String catterySer = jsonObjectWriter.toJson(cattery);
        System.out.println(catSer);
        System.out.println(catterySer);

        System.out.println("Из Json в объект");
        Gson gson = new Gson();
        Cat catDes = gson.fromJson(catSer, Cat.class);
        System.out.println(catDes.getName());
        Cattery catteryDes = gson.fromJson(catterySer, Cattery.class);
        System.out.println(catteryDes.getCatteryName());



    }
}
