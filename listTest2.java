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
        for (int i=1;i<100;i++){
            list.add("aaa"+i);
        }
    List<String> list1 = new ArrayList<String>();
        for (int i=1;i<30;i++){
            list1.add("bbb"+i);
        }
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
        for (Integer h=1;h<1000;h++){
            System.out.print(h.hashCode()+",");
        }
}
}
