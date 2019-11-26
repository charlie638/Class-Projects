import java.util.*;

public class Euler26 {
    public static int longDivision(int d) {
        int divisor = d;
        int dividend = 10;
        int quotient = 0;
        int steps = 0;
        List<Integer> quotArr = new ArrayList<>(10);
        List<Integer> divdArr = new ArrayList<>(10);
        while (dividend != 0) {
            if (divisor < dividend) {
                quotient = dividend / divisor;
                dividend = dividend % divisor;
            }
            dividend *= 10;

            // when the quotient and dividend are the same as before, then it will repeat
            if (quotArr.indexOf(quotient) != -1) {
                // test if the dividend is the same as when the first quotient was the same
                if (divdArr.get(quotArr.indexOf(quotient)) == dividend)
                    return steps;
                // test if the dividend is the same as when the last quotient was the same
                if (divdArr.get(quotArr.lastIndexOf(quotient)) == dividend)
                    return steps;
            }

            quotArr.add(quotient);
            divdArr.add(dividend);
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
        int max = 0;
        int maxNum = 0;
        for (int i = 2; i < 1000; i++) {
            int len = longDivision(i);
            System.out.printf("%d: %d\n",i, len);
            if (len > max) {
                max = len;
                maxNum = i;
            }
        }
        System.out.printf("max: %d", maxNum);

    }
}