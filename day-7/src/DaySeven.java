import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DaySeven {

    public static void main(String[] args) throws FileNotFoundException {
        part2();
    }

    /* We need to use result from part 1. The idea is that we see the result as a graph.
     * | is edge, and ^ is node. We just need to account the weight for the edges and sum them up.
     */
    public static void part2() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-7/aoc-day-7.txt"));
        ArrayList<String> in = new ArrayList<>();
        while (sc.hasNextLine()) {
            in.add(sc.nextLine());
        }

        char[][] grid = new char[in.size()][in.get(0).length()];
        for (int i = 0; i < in.size(); i++) {
            for (int j = 0; j < in.get(0).length(); j++) {
                grid[i][j] = in.get(i).charAt(j);
            }
        }

        // processed
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.' && i - 1 >= 0 && (grid[i - 1][j] == 'S' || grid[i - 1][j] == '|')) {
                    grid[i][j] = '|';
                } else if (grid[i][j] == '^') {
                    if (grid[i-1][j] != '|') continue;
                    if (j - 1 >= 0 && grid[i][j-1] != '^') grid[i][j-1] = '|';
                    if (j + 1 < grid[j].length && grid[i][j+1] != '^') grid[i][j+1] = '|';
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");

            }
            System.out.println("\n");
        }

        // count edge weight
        long[][] numGrid = new long[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    numGrid[i+1][j] = 1;
                } else if (grid[i][j] == '|') {
                    numGrid[i][j] += numGrid[i-1][j];
                } else if (grid[i][j] == '^') {
                    if (j - 1 >= 0) {
                        numGrid[i][j - 1] += numGrid[i-1][j];
                    }
                    if (j + 1 < grid[j].length) {
                        numGrid[i][j + 1] += numGrid[i-1][j];
                    }
                }
            }
        }
        long cnt = 0;

        for (int i = 0; i < numGrid.length; i++) {
            for (int j = 0; j < numGrid[0].length; j++) {
                System.out.print(numGrid[i][j] + " ");
                if (i == grid.length - 1) cnt += numGrid[i][j];
            }
            System.out.println("\n");
        }

        System.out.println(cnt);

    }

    public static void part1() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("day-7/aoc-day-7.txt"));
        ArrayList<String> in = new ArrayList<>();
        while (sc.hasNextLine()) {
            in.add(sc.nextLine());
        }

        char[][] grid = new char[in.size()][in.get(0).length()];
        for (int i = 0; i < in.size(); i++) {
            for (int j = 0; j < in.get(0).length(); j++) {
                grid[i][j] = in.get(i).charAt(j);
            }
        }
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.' && i - 1 >= 0 && (grid[i - 1][j] == 'S' || grid[i - 1][j] == '|')) {
                    grid[i][j] = '|';
                } else if (grid[i][j] == '^') {
                    if (grid[i-1][j] != '|') continue;
                    if (j - 1 >= 0 && grid[i][j-1] != '^') grid[i][j-1] = '|';
                    if (j + 1 < grid[j].length && grid[i][j+1] != '^') grid[i][j+1] = '|';
                    cnt++;
                }
            }
        }


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println("\n");
        }

        System.out.println(cnt);
    }

}

