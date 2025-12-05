import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class DayFIve {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-5/aoc-day-5.txt"));
        ArrayList<Range> list = new ArrayList<>();
        while (true) {

            String s = sc.nextLine();
            if (s.isEmpty()) break;
            String[] split = s.split("-");
            list.add(new Range(Long.parseLong(split[0]), Long.parseLong(split[1])));
        }
        ArrayList<Range> merged = mergeBoundaries(list);
        long ans = 0;
        for (Range r : merged) {
            System.out.println(r.toString());
            ans += r.r - r.l + 1;
        }
        System.out.println(ans);

    }
    public static ArrayList<Range> mergeBoundaries(ArrayList<Range> ranges) {
        boolean[] isMerged = new boolean[ranges.size()];
        for (int i = 0; i < ranges.size() - 1; i++) {
            if (isMerged[i]) continue;
            Range r1 = ranges.get(i);
            for (int j = i+1; j < ranges.size(); j++) {
                if (isMerged[j]) continue;
                Range r2 = ranges.get(j);
                if (r2.l <= r1.r && r2.r >= r1.r) {
                    // overlap from right side
                    System.out.println("From right: " + r1 + " " + r2);
                    r1 = new Range(Math.min(r1.l, r2.l), Math.max(r1.r, r2.r));
                    ranges.set(i, r1);
                    System.out.println("Merged: " + r1);
                    isMerged[j] = true;
                    i--; // we updated r1, so need to retraversal
                    break;
                } else if (r2.r >= r1.l && r2.l <= r1.l) {
                    // overlap from left side
                    System.out.println("From left: " + r1 + " " + r2);
                    r1 = new Range(Math.min(r1.l, r2.l), Math.max(r1.r, r2.r));
                    ranges.set(i, r1);
                    System.out.println("Merged: " + r1);
                    isMerged[j] = true;
                    i--;
                    break;
                } else if (r2.l >= r1.l && r2.r <= r1.r) {
                    // r2 is contained by r1
                    isMerged[j] = true;
                }
            }
        }
        ArrayList<Range> merged = new ArrayList<>();
        for (int i = 0; i < ranges.size() - 1; i++) {
//            System.out.println("After merge: " + ranges.get(i));
            if (isMerged[i]) continue;
            merged.add(ranges.get(i));
        }
        return merged;
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-5/aoc-day-5.txt"));
        ArrayList<Range> list = new ArrayList<>();
        while (true) {

            String s = sc.nextLine();
            if (s.isEmpty()) break;
            String[] split = s.split("-");

            list.add(new Range(Long.parseLong(split[0]), Long.parseLong(split[1])));
        }
        int cnt = 0;
        while (sc.hasNextLine()) {
            long cur = Long.parseLong(sc.nextLine());
            for (Range r : list) {
                if (cur >= r.l && cur <= r.r) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
class Range {
    long l;
    long r;
    public Range(long l, long r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public String toString() {
        return "Range{" +
                "l=" + l +
                ", r=" + r +
                '}';
    }
}