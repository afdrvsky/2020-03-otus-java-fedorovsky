import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class HelloOtus {
    public static void main(String[] args) {
        System.out.println(CreateTable());
    }

    public static Table CreateTable(){
        Table<Integer, String, Integer> biggestCitiesPopulation
                = HashBasedTable.create();
        biggestCitiesPopulation.put(1, "Shanghai", 24256800);
        biggestCitiesPopulation.put(2, "Karachi", 23500000);
        biggestCitiesPopulation.put(3, "Bejing", 21516000);
        return biggestCitiesPopulation;
    }
}
