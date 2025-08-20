import java.util.Collections;



public class Main {
    public static void main(String[] args) {
        DIYarrayList<Integer> list1 = new DIYarrayList<>();
        DIYarrayList<Integer> list2 = new DIYarrayList<>();

        for (int i = 1; i <= 25; i++) {
            list1.add(i);
            list2.add(100 + i);
        }

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

        Collections.addAll(list1, 26, 27, 28, 29, 31);
        System.out.println("После addAll: ");
        System.out.println(list1);

        Collections.copy(list1, list2);
        System.out.println("После copy: ");
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);


        Collections.sort(list1, (i1, i2) -> i2 - i1);
        System.out.println("После sort: ");
        System.out.println(list1);

    }
}
