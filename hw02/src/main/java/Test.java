import java.util.*;

public class Test {

    public static void main(String[] args) {

        DIYarrayList<Integer> diYarrayList1 = new DIYarrayList<>();
        DIYarrayList<Integer> diYarrayList2 = new DIYarrayList<>();

        for (int i = 0; i < 100; i++) {
            diYarrayList1.add(i);
        }

        for (int i = 0; i <= 50; i++) {
            diYarrayList2.add(i * 10);
        }

        System.out.println("First list: " + diYarrayList1);
        System.out.println("Second list: " + diYarrayList2);

        Collections.addAll(diYarrayList1, diYarrayList2.toArray(new Integer[]{}));
        System.out.println("First list after adding second list: " + diYarrayList1);

        Collections.copy(diYarrayList1, diYarrayList2);
        System.out.println("First list with copied second list: " + diYarrayList1);

        Collections.sort(diYarrayList1);
        System.out.println("Sorted first list: " + diYarrayList1);
    }

}