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

            Z
            |
            |
            |
           / \
         /    \
       X       Y
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
        } else if (n > 0 && matches == 0) {
            matches = 4 * 3;
        }

        int potentialN = (xyz[0] + 1) * (xyz[1] + 1) * (xyz[2] + 1);
        if (potentialN <= n) {
            matches += collectGreaterCub(xyz);
        } else {
            return matches + increasePanels(xyz, n - current);
        }
        return collect(xyz, n, potentialN, matches);
    }

    private static int increasePanels(int[] xyz, int delta) {
        int matches = 0;

        int potentialPanel = xyz[0] * 1 * xyz[2];
        if (potentialPanel == delta) {
            //build and glue
            matches +=  buildPanelByY(xyz);
        } else if (potentialPanel > delta)  {
            int side = new Double(Math.pow(delta, 1 / 2)).intValue();
            matches += buildSidedPanelWithRemaining(side, new Double(delta - Math.pow(side, 2)).intValue());
        } else if (potentialPanel < delta) {

            // нарастить панельки до упора
            matches +=  buildPanelByY(xyz);
        }
        return matches;
    }

    public static int collectGreaterCub(int[] xyz) {
        int byY = buildPanelByY(xyz);
        ++xyz[1];

        int byX = buildPanelByX(xyz);
        ++xyz[2];

        int byZ = buildPanelByZ(xyz);
        ++xyz[0];

        return byY + byX + byZ;
    }

    private static int buildPanelByZ(int[] xyz) {
        int gluesBetweenByZ = (xyz[1] == 1 && xyz[2]== 1 ? 0 : ((xyz[1]-1) * xyz[2]) + (xyz[1] * (xyz[2]-1)));
        int pivotsZ = (xyz[1] > 1 && xyz[2] > 1) ? (xyz[1]-1) * (xyz[2]-1) : 0;
        int buildByZ = 1 * xyz[1] * xyz[2] * 4 * 3 - gluesBetweenByZ * 4 + pivotsZ; // - glues between them ?? как высчитать что клеим последний кубик, на который требуется не 4, а 5 спичек
        int gluesFacesByZ = xyz[1] * xyz[2] * 4 - gluesBetweenByZ;
        return buildByZ - gluesFacesByZ;
    }

    private static int buildPanelByX(int[] xyz) {
        int gluesBetweenByX = (xyz[0] == 1 && xyz[1]== 1 ? 0 : ((xyz[0]-1) * xyz[1]) + (xyz[0] * (xyz[1]-1)));
        int pivotsX = (xyz[0] > 1 && xyz[1] > 1) ? (xyz[0]-1) * (xyz[1]-1) : 0;
        int buildByX = xyz[0] * xyz[1] * 1 * 4 * 3 - gluesBetweenByX * 4 + pivotsX; // - glues between them
        int gluesFacesByX = xyz[0] * xyz[1] * 4 - gluesBetweenByX;
        return buildByX - gluesFacesByX;
    }

    private static int buildPanelByY(int[] xyz) {
        int gluesBetweenByY = (xyz[0] == 1 && xyz[2]== 1 ? 0 : ((xyz[0]-1) * xyz[2]) + (xyz[0] * (xyz[2]-1)));
        int pivotsY = (xyz[0] > 1 && xyz[2] > 1) ? (xyz[0]-1) * (xyz[2]-1) : 0;
        int buildByY = xyz[0] * 1 * xyz[2] * 4 * 3 - gluesBetweenByY * 4 + pivotsY; // - glues between them
        int gluesFacesByY = xyz[0] * xyz[2] * 4 - gluesBetweenByY;
        return buildByY - gluesFacesByY;
    }

    private static int buildSidedPanelWithRemaining(int side, int remaining) {
        int gluesBetween = (side == 1 ? 0 : ((side-1) * side) + (side * (side-1)));
        int pivots = (side > 1 && side > 1) ? (side-1) * (side-1) : 0;
        int build = side * 1 * side * 4 * 3 - gluesBetween * 4 + pivots; // - glues between them
        int gluesFaces = side * side * 4 - gluesBetween;
        return build - gluesFaces + buildRemaining(side, remaining);
    }

    private static int buildRemaining(int side, int remaining) {
        if (remaining == 0) return 0;
        else if (remaining - side > 0) {
            return remaining * 3 + 2 * 2;
        } else {
            return remaining * 3 + 2;
        }
    }
}
