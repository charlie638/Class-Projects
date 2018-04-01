// terwi045

public class Random {
    private int prime1;
    private int prime2;
    private int max;
    private int rold;

    public Random(int p1, int p2, int m) {
        prime1 = p1;
        prime2 = p2;
        max = m;
        setSeed(0);
    }

    public void setSeed(int seed) {
        rold = seed;
    }

    public int getMaximum() {
        return max;
    }

    public int random() {
        rold = ((prime1 * rold) + prime2) % getMaximum();
        return Math.abs(rold);
    }

    public int randomInteger(int lower, int upper) {
        if (lower > upper) {
            int temp = lower;
            lower = upper;
            upper = temp;
        }
        int number = random();
        // this while loop changes the random number to a scale that makes more sense
        while (number < (int) Math.pow(10, Math.log10(upper) + 1)) {
            int mul = random()%10;
            // dont want to multiply by 1 or 0
            while (mul < 2)
                mul = random()%10;
            number *= mul;
        }
        while (number < lower || number > upper) {
            // adjust is a random number of the length of upper
            // so that when it adjust the original random number it does it on a scale that makes sense
            int adjust = random()% (int) Math.pow(10, Math.log10(upper) + 1);
            if (number > upper) {   // this part ^ returns 10 to the power of the length of upper
                number -= adjust;
            }
            else number += adjust;
        }
        return number;
    }

    public boolean randomBoolean() {
        return (random() % 2 == 0);
    }

    public double randomDouble(double lower, double upper) {
        if (lower > upper) {
            upper = lower;
        }
        double result = 0;
        while (result > upper || result < lower || result == 0) {
            double leftOfDec = randomInteger((int) lower, (int) upper);
            double rightOfDec = 0;
            for (int i = 1; i < 20; i++) {
                double num = random() % 10 * Math.pow(10, -i);
                rightOfDec += num;
            }
            result = leftOfDec + rightOfDec;
        }
        return result;
    }

    public static void main(String[] args) {
        Random random1 = new Random(Prime.prime(1900), Prime.prime(2600),Prime.prime(5000));

        System.out.println("Testing getMaximum Method...");
        System.out.print("(Should print 'true'): ");
        System.out.println(random1.getMaximum() == Prime.prime(5000));
        System.out.println();

        System.out.println("Testing Random Integer...");
        System.out.println("Test 1: 1 - 100");
        int ones = 0;
        int tens = 0;
        int twenties = 0;
        int thirties = 0;
        int forties = 0;
        int fifties = 0;
        int sixties = 0;
        int seventies = 0;
        int eighties = 0;
        int nineties = 0;
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            int a = random1.randomInteger(0, 100);
            sum += a;
            if      (a<=10)  {ones += 1;}
            else if (a<=20)  {tens += 1;}
            else if (a<=30)  {twenties += 1;}
            else if (a<=40)  {thirties += 1;}
            else if (a<=50)  {forties += 1;}
            else if (a<=60)  {fifties += 1;}
            else if (a<=70)  {sixties += 1;}
            else if (a<=80)  {seventies += 1;}
            else if (a<=90)  {eighties += 1;}
            else if (a<=100) {nineties += 1;}
        }

        System.out.println("Average = " + "\t" + sum/1000 + " (should be near 50)");
        System.out.println();
        System.out.println("-Distribution-   (each should be near 100)");
        System.out.println("ones: " + "\t\t" + ones);
        System.out.println("tens: " + "\t\t" + tens);
        System.out.println("twenties: " + "\t" + twenties);
        System.out.println("thirties: " + "\t" + thirties);
        System.out.println("forties: " + "\t" + forties);
        System.out.println("fifties: " + "\t" + fifties);
        System.out.println("sixties: " + "\t" + sixties);
        System.out.println("seventies: " + "\t" + seventies);
        System.out.println("eighties: " + "\t" + eighties);
        System.out.println("nineties: " + "\t" + nineties);

        System.out.println();
        System.out.println("Test 2: 0 - 1000");
        int sum2 = 0;
        for (int i = 0; i < 1000; i++) {
            int a = random1.randomInteger(0, 1000);
            sum2 += a;
        }
        System.out.println("Average: " + "\t" + sum2/1000 + " (should be near 500)");

        System.out.println();
        System.out.println("Test 3: 0 - 10");
        int sum3 = 0;
        for (int i = 0; i < 100; i++) {
            int a = random1.randomInteger(0, 10);
            sum3 += a;

        }
        System.out.println("Average: " + "\t" + sum3/100 + " (should be near 5)");
        System.out.println();

        System.out.println("Testing Random Number Generator...");
        int randnum = random1.random();
        int randnum2 = random1.random();
        int tries = 0;
        while (randnum != randnum2) {
            randnum = random1.random();
            tries += 1;
        }
        System.out.println("It took " + tries + " tries to guess a random number twice");
        System.out.println();

        System.out.println("Testing Random Boolean...");
        double boolsum = 0;
        for (int i = 0; i < 100000; i++) {
            if (random1.randomBoolean())
                boolsum += 1;
        }
        System.out.println("After 100,000 tests Random Boolean was true " + boolsum/100000 + "% of the time; (should be near .5)");
        System.out.println();

        System.out.println("Testing Random Double...");
        double dubsum = 0;
        int onePointFour = 0;
        int onePointSix = 0;
        int onePointEight = 0;
        int twoPointZero = 0;
        int twoPointTwo = 0;
        int twoPointFour = 0;
        int twoPointSix = 0;
        int twoPointEight = 0;
        int threePointZero = 0;
        int threePointTwo = 0;
        int threePointFour = 0;
        int threePointSix = 0;
        for (int i = 0; i < 1000; i++) {
            double a = random1.randomDouble(1.2,3.6);
            dubsum += a;
            if      (a<=1.4)  {onePointFour += 1;}
            else if (a<=1.6)  {onePointSix += 1;}
            else if (a<=1.8)  {onePointEight += 1;}
            else if (a<=2.0)  {twoPointZero += 1;}
            else if (a<=2.2)  {twoPointTwo += 1;}
            else if (a<=2.4)  {twoPointFour += 1;}
            else if (a<=2.6)  {twoPointSix += 1;}
            else if (a<=2.8)  {twoPointEight += 1;}
            else if (a<=3.0)  {threePointZero += 1;}
            else if (a<=3.2)  {threePointTwo += 1;}
            else if (a<=3.4)  {threePointFour += 1;}
            else if (a<=3.6)  {threePointSix += 1;}
        }
        System.out.println("Average = " + dubsum/1000 + " (should be near 2.4)");
        System.out.println();
        System.out.println("-Distribution-   (each should be near 83)");
        System.out.println("1.4: " + "\t" + onePointFour);
        System.out.println("1.6: " + "\t" + onePointSix);
        System.out.println("1.8: " + "\t" + onePointEight);
        System.out.println("2.0: " + "\t" + twoPointZero);
        System.out.println("2.2: " + "\t" + twoPointTwo);
        System.out.println("2.4: " + "\t" + twoPointFour);
        System.out.println("2.6: " + "\t" + twoPointSix);
        System.out.println("2.8: " + "\t" + twoPointEight);
        System.out.println("3.0: " + "\t" + threePointZero);
        System.out.println("3.2: " + "\t" + threePointTwo);
        System.out.println("3.4: " + "\t" + threePointFour);
        System.out.println("3.6: " + "\t" + threePointSix);

        //asdfasdf
    }
}