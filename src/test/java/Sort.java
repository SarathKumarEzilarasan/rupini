import java.util.*;

public class Sort {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("john");
        list.add("anand");
        list.add("zebra");
        System.out.println(list);
        Collections.sort(list,Collections.reverseOrder());
        System.out.println(list);
    }
}
