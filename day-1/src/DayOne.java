import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayOne {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    /**
     * Count how many time we stop at 0 point.
     * @throws FileNotFoundException
     */
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-1/aoc-day-1.txt"));
        int start = 50;
        int cnt = 0;
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String direction = s.substring(0,1);
            Integer directionVal = Integer.parseInt(s.substring(1));
            if (direction.equals("L")) {
                start = start - directionVal;
            } else {
                start = start + directionVal;
            }
            start %= 100; // It is a circle, so keep it has idempotence. -46 and 54 will have the same result
            if (start == 0) cnt++;
        }
        System.out.println("Number of ZERO: " + cnt);
    }

    /**
     * Count how many time we pass 0 point.
     * @throws FileNotFoundException
     */
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-1/aoc-day-1.txt"));
        int start = 50;
        int before = 0;
        int cnt = 0;
        while(sc.hasNextLine()) {
            String s = sc.nextLine();

            String direction = s.substring(0,1);
            Integer directionVal = Integer.parseInt(s.substring(1));

            if (direction.equals("L")) {
                start = start - directionVal;
            } else {
                start = start + directionVal;
            }

            // |start| is garanteed to be less than 100, count effect of direction value
            cnt += Math.abs(start / 100);

            // edge case zero point, either we are exactly 0, or we passed the 0 point (sign flip)
            if (start == 0 || before * start < 0) cnt++;

            start %= 100;
            before = start;
        }
        System.out.println("Number of ZERO: " + cnt);
    }
}
