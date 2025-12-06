import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DaySix {


    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }


    public static void part2() throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileInputStream("day-6/aoc-day-6.txt"));
        // need to perserve the spaces
        ArrayList<String> in = new ArrayList<>();
        while (sc.hasNextLine()) {
            in.add(sc.nextLine());
        }
        // derive the seperation from the index of + and *
        String signs = in.getLast();
        ArrayList<Integer> starts = new ArrayList<>();
        ArrayList<Chunk> chunks = new ArrayList<>();

        // loop over the sign line, to get the start index of each part, so that we can cut.
        for (int i = 0; i < signs.length(); i++) {
            Chunk c = null;
            if (signs.charAt(i) == '*') {
                c = new Chunk('*', null);
                starts.add(i);
                chunks.add(c);
            } else if (signs.charAt(i) == '+') {
                c = new Chunk('+', null);
                starts.add(i);
                chunks.add(c);
            }
        }
        // useful string is [start_i, start_i+1)
        for (int i = 0; i < starts.size(); i++) {
            int start1 = starts.get(i);
            int start2 = -1;
            if (i == starts.size() - 1) {
                start2 = -1;
            } else {
                start2 = starts.get(i + 1);
            }

            ArrayList<String> nums = new ArrayList<>();
            // Skip the sign row
            for (int j = 0; j < in.size() - 1; j++) {
                String curS = in.get(j);
                System.out.println(curS + "|");
                start2 = start2 == -1 ? curS.length() : start2;
                nums.add(curS.substring(start1, start2));
            }
            chunks.get(i).setNums(nums);
        }

        long total = 0;
        for (Chunk c : chunks) {
            int len = c.nums.getFirst().length();
            long res = c.sign == '+' ? 0 : 1;
            for (int i = 0; i < len; i++) {
                String curN = "";
                for (int j = 0; j < c.nums.size(); j++) {
                    char curChar = c.nums.get(j).charAt(i);
                    if (curChar != ' ') curN += curChar;
                }
                if (curN.isEmpty()) continue;
                if (c.sign == '*') res *= Long.parseLong(curN);
                else res += Long.parseLong(curN);
            }
            total += res;
        }
        System.out.println(total);
    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-6/aoc-day-6.txt"));
        ArrayList<String[]> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine().strip().split("\\s+"));
        }
        String[] sign = list.getLast();
        long total = 0;
        for (int i = 0; i < sign.length; i++) {
            String cur =  sign[i];
            long[] curN = new long[list.size() - 1];
            for (int j = 0; j < list.size() - 1; j++) {
                curN[j] = Long.parseLong(list.get(j)[i]);
            }
            long res = 0;
            if (cur.equals("+")) {
                for (int j = 0; j < curN.length; j++) {
                    res += curN[j];
                }
            } else {
                res = 1;
                for (int j = 0; j < curN.length; j++) {
                    res *= curN[j];
                }
            }
            total += res;
        }
        System.out.println(total);
    }
}

class Chunk {
    char sign;
    ArrayList<String> nums;
    Chunk(char sign, ArrayList<String> nums) {
        this.sign = sign;
        this.nums = nums;
    }

    void setNums(ArrayList<String> nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append("|").append(num).append("|\n");
        }
        sb.append(sign).append("\n");
        return sb.toString();
    }
}
