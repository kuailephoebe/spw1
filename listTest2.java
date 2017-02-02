import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017-02-02.
 */
public class listTest2 {
    public static void main(String args[]) {
    List<String> list = new ArrayList<String>();
    list.add("China");
    list.add("Switzerland");
    list.add("Italy");
    list.add("France");
    list.add("aa");
    list.add("bb");
    list.add("cc");
    list.add("dd");
    list.add("ee");
    List<String> list1 = new ArrayList<String>();
    list1.add("China1");
    list1.add("Switzerland1");
    list1.add("Italy1");
    list1.add("aa1");
    String[] countries = list.toArray(new String[list.size()]);
        Map<Integer,String> listToMap = new HashMap<Integer, String>();
    System.out.println(countries);
        int i=0;
        for (String tmp:countries){
            i++;
            if (i%3==0){
                i++;
            }
            listToMap.put(i,tmp);
        }
        int j=0;
        for (String tmp:list1){
            j+=3;
            listToMap.put(j,tmp);
        }
        System.out.println(listToMap);
        System.out.println(listToMap.values());
}
}
