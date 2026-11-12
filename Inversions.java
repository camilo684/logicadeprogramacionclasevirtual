import java.util.*;
import java.io.*;

public class Inversions {

    private static long count(int[] a, int[] aux, int left, int mid, int right) {
        long numberOfInversions = 0;

        for (int k = left; k <= right; k++)
            aux[k] = a[k];

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if      (i > mid)            a[k] = aux[j++];
            else if (j > right)          a[k] = aux[i++];
            else if (aux[i] <= aux[j])   a[k] = aux[i++];
            else {
                a[k] = aux[j++];
                numberOfInversions += (mid - i + 1);
            }
        }
        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a, int[] aux, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left) {
            return numberOfInversions;
        }
        int mid = left + (right - left) / 2;
        numberOfInversions += getNumberOfInversions(a, aux, left, mid);
        numberOfInversions += getNumberOfInversions(a, aux, mid + 1, right);
        numberOfInversions += count(a, aux, left, mid, right);
        return numberOfInversions;
    }

    private static long getNumberOfInversions(int[] a) {
        int[] b = a.clone();
        int[] aux = new int[b.length];
        return getNumberOfInversions(b, aux, 0, b.length - 1);
    }

    private static long getNumberOfInversionsNaive(int[] a) {
        long numberOfInversions = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }

    private static void stressTesting() {
        Random r = new Random();
        int[] a = new int[10];
        for (int prueba = 0; prueba < 1000; prueba++) {
            for (int i = 0; i < a.length; i++) {
                a[i] = r.nextInt(100);
            }
            long number1 = getNumberOfInversionsNaive(a);
            long number2 = getNumberOfInversions(a);
            if (number1 != number2) {
                System.out.println(number1 + " " + number2);
                for (int i = 0; i < a.length; i++) {
                    System.out.printf("%d ", a[i]);
                }
                System.out.println();
                return;
            }
        }
        System.out.println("Stress testing: 1000 pruebas OK");
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a));
        stressTesting();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
