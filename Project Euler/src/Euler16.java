import java.util.ArrayList;
import java.util.List;

public class Euler16 {
    public static void main(String[] args) {
        // create list staring with 2
        List<Integer> thousand = new ArrayList<>(1);
        thousand.add(2);
        // iterate 1 through 1000
        for (int i = 1; i < 1000; i++) {
            int len = thousand.size()-1;
            // start at highest power
            for (int j = len; j >= 0; j--) {
                int two = thousand.get(j) * 2;
                // check if double digit number
                if (two / 10 == 1) {
                    // check if there is already a number in the digit spot above
                    if (j+1 < thousand.size()) { thousand.set(j+1, thousand.get(j+1) + 1); }
                    // otherwise put 1 in digit above
                    else { thousand.add(1); }
                }
                // always set the current digit to double
                thousand.set(j, two % 10);
            }
        }
        long sum = 0;
        for (int i = 0; i < thousand.size(); i++) { sum += thousand.get(i); }
        System.out.println(sum);
    }
}
