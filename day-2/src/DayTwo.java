import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) throws FileNotFoundException {
//        checkInvalid("3", 1);
        part2();
    }

    /**
     * @throws FileNotFoundException
     */
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-2/aoc-day-2.txt"));
        String[] s =  sc.nextLine().split(",");
        long ans = 0;
        for (int i = 0; i < s.length; i++) {
            String sl = s[i].split("-")[0];
            String su = s[i].split("-")[1];
            long lower = Long.parseLong(sl);
            long upper = Long.parseLong(su);
            System.out.println("Lower: " + lower + " Upper: " + upper);
            for (long j = lower; j <= upper; j++) {
                String curS = String.valueOf(j);
                // String with Size 1 can never be correct
                if (curS.length() == 1) {continue;}
                // Check every substring for current number
                for (int k = 1; k <= su.length() / 2; k++) {
                    if (checkInvalid(curS, k)) {
                        ans += j;
                        System.out.println("Invalid: " + curS + " K:" + k);
                        break;
                    }
                }
            }
        }
        System.out.println(ans);
    }

    /**
     * Check substring size 1, 2, 3 etc
     * @param size
     * @return
     */
    public static boolean checkInvalid(String s, int size) {
        String sub = s.substring(0, size);

        for  (int i = size; i < s.length(); i+=size) {
            if (i+size > s.length()) return false;
            if (!sub.equals(s.substring(i, i+size))) return false;
        }
        return true;
    }


    /**
     * So, invalid ID must be even digits.
     * It is n * 10^x + n
     * n is then in the range [1, 10^x - 1]
     * @throws FileNotFoundException
     */
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-2/aoc-day-2.txt"));
        String[] s =  sc.nextLine().split(",");
        long cnt = 0;
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
            long lower =  Long.parseLong(s[i].split("-")[0]);
            long upper =  Long.parseLong(s[i].split("-")[1]);
            // long has the range 10^18, so max x is 9 and start with 11, so min x is 1
            for (int j = 1; j < 10; j++) {
                long startVal = (long) Math.pow(10, j-1);
                if (startVal >= upper) break;
                cnt += calc(j,lower, upper);
            }
            System.out.println();
        }
        System.out.println(cnt);
    }

    /**
     *
     * @param x
     * @return the first n that works
     */
    public static long calc(int x,long lower, long upper) {
        long sum = 0;
        long startVal = (long) Math.pow(10, x-1);
        for (long i = startVal; i < Math.pow(10, x); i++) {
            long cur =  (long) (i * Math.pow(10, x) + i);
            if (cur > upper) return sum;
            if (cur >= lower && cur <= upper) {
//                System.out.println(lower + " " + upper + " " + cur);
                sum += cur;
            }
        }
        return sum;
    }
}