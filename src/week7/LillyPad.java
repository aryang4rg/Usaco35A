package week7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
Bronze Lilypad Pond
===================

Farmer John has installed a beautiful pond for his cows' esthetic
enjoyment and exercise. The rectangular pond has been partitioned
into square cells of M rows and N columns (1 <= M <= 30; 1 <= N <=
30). Some of the cells have astonishingly sturdy lilypads; others
have rocks; the remainder are just beautiful, cool, blue water.

Bessie is practising her ballet moves by jumping from one lilypad
to another and is currently located at one of the lilypads (see the
input data for the location's specifier). She wants to travel to
another lilypad in the pond by jumping from one lilypad to another.
She can jump neither into the water nor onto a rock.

Surprising only to the uninitiated, Bessie's jumps between lilypads
always appear as a sort of chess-knight's move: move M1 (1 <= M1
<= 30) 'squares' in one direction and then M2 (1 <= M2 <= 30; M1
!= M2) more in an orthogonal direction (or perhaps M2 in one direction
and then M1 in an orthogonal direction). Bessie sometimes might
have as many as eight choices for her jump.

Given the pond layout and the format of Bessie's jumps, determine
the smallest number of leaps that Bessie must make to move from her
starting location to her final destination, a feat which is always
possible for the given test data.

PROBLEM NAME: bronlily

INPUT FORMAT:

* Line 1: Four space-separated integers: M, N, M1, and M2

* Lines 2..M+1: Line i+1 describes row i of the pond using N
        space-separated integers with these values: 0 indicates empty
        water; 1 indicates a lilypad; 2 indicates a rock; 3 indicates
        the lilypad Bessie upon which she starts; 4 indicates the
        lilypad that is Bessie's destination.

SAMPLE INPUT:

4 5 1 2
1 0 1 0 1
3 0 2 0 4
0 1 2 0 0
0 0 0 1 0

INPUT DETAILS:

Bessie starts on the left in row 2; her destination is on the right in row
2. Several lilypads and rocks occupy the pond.

OUTPUT FORMAT:

* Line 1: A single integer that is the minimum number of jumps between
        lilypads that Bessie must make to travel from her starting
        place to her destination.

SAMPLE OUTPUT:

2

OUTPUT DETAILS:

Bessie cleverly hops onto the pad at row 1, column 3 on her way to the
right hand side.
 */
public class LillyPad
{
    public static final int WATER = 0;
    public static final int LILLYPAD = 1;
    public static final int START = 3;
    public static final int END = 4;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();
        int m1 = s.nextInt();
        int m2 = s.nextInt();
        int[] dx = {m1, m1, m2, -m2, -m1, -m1, -m2, m2 };
        int[] dy = {m2, -m2, m1, m1,  m2, -m2, -m1, -m1};

        int[][] matrix = new int[n][m];
        boolean[][] seen = new boolean[n][m];

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int num = s.nextInt();
                if (num == 0 || num == 2)
                {
                    matrix[j][i] = WATER;
                }
                else
                {
                    matrix[j][i] = num;
                }

                if (num == START)
                {
                    queue.add(new Point(j,i,0));
                    seen[j][i] = true;
                }
            }
        }

        while (!queue.isEmpty())
        {
            Point currPoint = queue.poll();
            if (matrix[currPoint.x][currPoint.y] == END)
            {
                System.out.println(currPoint.size);
                return;
            }
            for (int i = 0; i < dx.length; i++)
            {
                Point p = new Point(currPoint.x + dx[i], currPoint.y + dy[i], currPoint.size+1);
                if  (p.x >= 0 && p.y >= 0 && p.x < n && p.y < m && !seen[p.x][p.y] && matrix[p.x][p.y] != WATER )
                {
                    queue.add(p);
                    seen[p.x][p.y] = true;
                }
            }
        }



        System.out.println(0);

    }
}

class Point
{
    int x, y, size;

    public Point(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
}
