import java.util.Scanner;

public class ChangeGreedy {

    private static int getChange(int m) {
        int count = m / 10;
        m = m % 10;
        count += m / 5;
        m = m % 5;
        count += m / 1;
        return count;
    }

    private static int getChangeGreedy(int m) {
        int changes = 0, count = 0;
        int[] denominations = {1, 5, 10};
        int max = denominations.length - 1;

        while (changes < m) {
            while (changes <= m && (m - changes) >= denominations[max]) {
                changes += denominations[max];
                count++;
            }
            max = max - 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Sample 1: m = 2 -> " + getChangeGreedy(2));
        System.out.println("Sample 2: m = 28 -> " + getChangeGreedy(28));

        for (int m = 1; m <= 1000; m++) {
            if (getChangeGreedy(m) != getChange(m)) {
                System.out.println("Diferencia en m = " + m);
                return;
            }
        }
        System.out.println("Enfoque 1 y Enfoque 2 coinciden para m de 1 a 1000");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese m (1 a 1000): ");
        int m = scanner.nextInt();
        System.out.println(getChangeGreedy(m));
        scanner.close();
    }
}
