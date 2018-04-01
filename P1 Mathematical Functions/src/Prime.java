// terwi045

import java.util.Arrays; // so that I can print the array to test it

public class Prime {

    public static boolean isPrime(int number) {
        if (number < 2)
            return false;
        else if (number == 2)
            return true;
        else if (number % 2 == 0)
            return false;
        else {
            for (int i = 3; i < number; i+=2) {
                if (number % i == 0)
                    return false;
            }
            return true;
        }

    }

    public static boolean isMersennePrime(int number) {
        int k = 2;
        for (int i = 0; k < number; i++) {
            if (isPrime(i)) {
                k = 2;
                for (int j = 1; j < i; j++) {
                    k *= 2;
                }
                if (k - 1 == number)
                    return true;
            }
        }
        return false;
    }

    public static int prime(int n) {
        if (n < 1)
            return -1;
        return primeArray(n)[n-1];
    }

    public static int[] primeArray(int howMany) {
        if (howMany < 1)
            return null;
        int[] array = {};
        for (int i = 2; array.length < howMany; i++) {
            if (isPrime(i)) {
                int b[] = {i};
                array = arrayConcatination(array, b);
            }
        }
        return array;
    }

    public static int[] primeFactors(int number) {
        int array[] = {};
        for (int i = 2; i*i < number; i++) {
            while (number % i == 0) {
                number = number / i;
                int b[] = {i};
                array = arrayConcatination(array, b);
            }

        }
        int c[] = {number};
        array = arrayConcatination(array, c);
        return array;
    }
    // works
    public static int[] arrayConcatination(int[] a, int[] b) {
        int newarray[] = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            newarray[i] = a[i];
        }
        for (int k = 0; k < b.length; k++) {
            newarray[k + a.length] = b[k];
        }
        return newarray;
    }

    public static void main(String[] args) {
        System.out.println("Testing isPrime...");
        System.out.println(isPrime(2) + "\t - (2 is prime)");
        System.out.println(isPrime(3) + "\t - (3 is prime)");
        System.out.println(isPrime(4) + "\t - (4 is NOT prime)");
        System.out.println(isPrime(5) + "\t - (5 is prime)");
        System.out.println(isPrime(6) + "\t - (6 is NOT prime");
        System.out.println(isPrime(7) + "\t - (7 is prime)");
        System.out.println(isPrime(8) + "\t - (8 is NOT prime)");
        System.out.println(isPrime(9) + "\t - (9 is NOT prime)");
        System.out.println(isPrime(10) + "\t - (10 is NOT prime)");
        System.out.println();

        System.out.println("Testing Mersenne Prime...");
        for (int i = 0; i < 1000000; i++)
            if (isMersennePrime(i))
                System.out.print(i + ", ");
        System.out.println();
        System.out.println("Mersenne Primes should include... \n3, 7, 31, 127, 2047, 8191, 131071, 524287");
        System.out.println();

        System.out.println("Testing Prime(n)...");
        System.out.println("Printing out first 20 primes...");
        for (int i = 1; i <= 20; i++) {
            System.out.print(prime(i) + ", ");
        }
        System.out.println();
        System.out.println("First 20 primes are... \n2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71");
        System.out.println();

        System.out.println("Testing Prime Array...");
        System.out.println(Arrays.toString(primeArray(20)));
        System.out.println("Again the first 20 primes are... \n2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71");
        System.out.println();

        System.out.println("Testing Prime Factorization...");
        System.out.println(Arrays.toString(primeFactors(630)));
        System.out.println("The prime factorization of 630 should be \n2, 3, 3, 5, 7");
        System.out.println();

        System.out.println(Arrays.toString(primeFactors(1212)));
        System.out.println("The prime factorization of 1212 should be \n2, 2, 3, 101");
    }
}
