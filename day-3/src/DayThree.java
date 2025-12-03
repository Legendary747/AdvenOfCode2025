import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayThree {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    /** Search in the range [0, s.length() - 11..0]
     * @throws FileNotFoundException
     */
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-3/aoc-day-3.txt"));
        long sum = 0;
        while (sc.hasNextLine()) {
            char[] s = sc.nextLine().toCharArray();
            int maxIndex = -1;
            long maxVal = -1;
            long cur = 0;
            for (int z = 11 ; z >= 0; z--) {
                maxVal = -1;
                for (int i = maxIndex + 1; i < s.length - z ; i++) {
                    int num = Integer.parseInt(String.valueOf(s[i]));
                    if (num > maxVal) {
                        maxVal = num;
                        maxIndex = i;
                    }
                }
                cur += maxVal * (long) Math.pow(10, z);
            }
            System.out.println(cur);
            sum += cur;
        }
        System.out.println(sum);
    }

    /**
     * Find max digit in the range [0, s.length() - 1]
     * @throws FileNotFoundException
     */
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-3/aoc-day-3.txt"));
        int sum = 0;
        while (sc.hasNextLine()) {
            char[] s = sc.nextLine().toCharArray();
            int maxIndex = -1;
            int maxVal = -1;
            for (int i = 0; i < s.length - 1; i++) {
                int num = Integer.parseInt(String.valueOf(s[i]));
                if (num > maxVal) {
                    maxVal = num;
                    maxIndex = i;
                }
            }
            sum += maxVal * 10;
            maxVal = -1;
            for (int i = maxIndex + 1; i < s.length ; i++) {
                int num = Integer.parseInt(String.valueOf(s[i]));
                if (num > maxVal) {
                    maxVal = num;
                }
            }
            sum += maxVal;
        }
        System.out.println(sum);
    }


}
