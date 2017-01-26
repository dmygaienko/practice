package com.mygaienko.common.algorithms.e_olimp.ex3;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 12											 ( 1 )

 12 + 12 - 4 = 20 							 ( 2 )	каждый + 1 кубик требует + 8 спичек

 12 + 12 + 12 - 4 - 4 = 28

 12 + 12 + 12 + 12 - 4 - 4 - 4 - 4 = 32       ( 2 na 2 )

 ??(2 на 2 на 2)
 ??(3 на 3 на 3)

 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        PrintWriter out = new PrintWriter(System.out);
        out.println(execute(a));
        out.flush();
    }

    public static int execute(int a) {
        int[] xyz = new int[] {1, 1, 1};
        return collect(xyz, a, 0, 0);
    }

    private static int collect(int[] xyz, int n, int current, int matches) {
        if (current == n || n == 0) return matches;

        if (n == 1) {
            return 4 * 3;
        }

        int potentialN = (xyz[0] + 1) * (xyz[1] + 1) * (xyz[2] + 1);
        if (potentialN <= n) {
            matches += collectGreaterCub(xyz, matches);
        } else {
            return matches + increasePanels(xyz, n - current);
        }
        return collect(xyz, n, potentialN, matches);
    }

    private static int increasePanels(int[] xyz, int delta) {
        int potentialPanel = xyz[0] * 1 * xyz[2];
        if (potentialPanel == delta) {
            //build and glue
        } else if (potentialPanel > delta)  {
            //бинарно попытаться подобрать
        } else if (potentialPanel < delta) {
            //нарастить с другой стороны
        }
        return 0;
    }

    public static int collectGreaterCub(int[] xyz, int matches) {
        int buildByY = xyz[0] * 1 * xyz[2] * 4 * 3; // - glues between them
        int glueByY = xyz[0] * xyz[2] * 4 - (xyz[0] == 1 && xyz[2]== 1 ? 0 : ((xyz[0]-1) * xyz[2]) + (xyz[0] * (xyz[2]-1)));

        ++xyz[1];

        int buildByZ = xyz[0] * xyz[1] * 1 * 4 * 3; // - glues between them
        int glueByZ = xyz[0] * xyz[1] * 4 - (xyz[0] == 1 && xyz[1]== 1 ? 0 : ((xyz[0]-1) * xyz[1]) + (xyz[0] * (xyz[1]-1)));

        ++xyz[2];

        int buildByX = 1 * xyz[1] * xyz[2] * 4 * 3; // - glues between them
        int glueByX = xyz[1] * xyz[2] * 4 - (xyz[1] == 1 && xyz[2]== 1 ? 0 : ((xyz[1]-1) * xyz[2]) - (xyz[1] * (xyz[2]-1)));

        ++xyz[0];

        return (matches + buildByY + buildByZ + buildByX - glueByY - glueByZ - glueByX);
    }


}
