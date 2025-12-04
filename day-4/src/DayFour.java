import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DayFour {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day-4/aoc-day-4.txt"));
        ArrayList<char[]> list = new ArrayList<char[]>();

        while (sc.hasNextLine()) {
            list.add(sc.nextLine().toCharArray());
        }

        int x = list.size();
        int y = list.getFirst().length;

        char[][] grid = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                grid[i][j] = list.get(i)[j];
            }
        }

        // start
        int cnt = 0;
        int prev = -1;

        // From problem statement I assume we only remove after the whole traversal of the grid
        while (cnt != prev) {
            prev = cnt;
            ArrayList<Index> indices = new ArrayList<>();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (grid[i][j] == '@') {
                        // check [i, j-1], [i, j+1]
                        // check [i-1, j] [i-1, j-1] [i-1, j+1]
                        // check [i+1, j] [i+1, j-1] [i+1, j+1]
                        if (check(grid, i, j)) {
                            indices.add(new Index(i, j));
                            cnt++;
                        }
                    }
                }
            }
            updateGrid(grid, indices);
        }
        System.out.println(cnt);
        System.out.println(Arrays.deepToString(grid));
    }
    public static void updateGrid(char[][] grid, ArrayList<Index> indices) {
        for (Index index : indices) {
            grid[index.x][index.y] = '.';
        }
    }
    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day-4/aoc-day-4.txt"));
        ArrayList<char[]> list = new ArrayList<char[]>();

        while (sc.hasNextLine()) {
            list.add(sc.nextLine().toCharArray());
        }

        int x = list.size();
        int y = list.getFirst().length;

        char[][] grid = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                grid[i][j] = list.get(i)[j];
            }
        }

        // start
        int cnt = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == '@') {
                    // check [i, j-1], [i, j+1]
                    // check [i-1, j] [i-1, j-1] [i-1, j+1]
                    // check [i+1, j] [i+1, j-1] [i+1, j+1]
                    if (check(grid, i, j)) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static boolean check(char[][] arr, int i, int j) {
        int cnt = 0;
        int yBound = arr.length;
        int xBound = arr[0].length;
        // same row
        if (j - 1 >= 0 && arr[i][j-1] == '@') cnt++;
        if (j + 1 < yBound && arr[i][j+1] == '@') cnt++;
        // a row above
        if (i - 1 >= 0 && arr[i-1][j] == '@') cnt++;
        if (i - 1 >= 0 && j-1 >= 0 && arr[i-1][j-1] == '@') cnt++;
        if (i - 1 >= 0 && j+1 < yBound && arr[i-1][j+1] == '@') cnt++;
        // a row down
        if (i + 1 < xBound && arr[i+1][j] == '@') cnt++;
        if (i + 1 < xBound && j+1 < yBound && arr[i+1][j+1] == '@') cnt++;
        if (i + 1 < xBound && j-1 >= 0 && arr[i+1][j-1] == '@') cnt++;
        return cnt < 4;
    }
}

class Index {
    int x;
    int y;
    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

