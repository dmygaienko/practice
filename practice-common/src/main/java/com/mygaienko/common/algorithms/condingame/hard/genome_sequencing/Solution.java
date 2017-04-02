package com.mygaienko.common.algorithms.condingame.hard.genome_sequencing;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        ArrayList<String> strings = initStrings(in);

        System.out.println(getShortestSequenceLength(strings));
    }

    /**
     * case 1: abc def => abcdef        (concat)
     * case 2: abc cde  => abcde        (concat)
     * case 3: abcdef cde => abcdef     (no concat)
     * case 4: cdef abc => abcdef
     * case 5: cde abcdef => abcdef
     * case 6: abcdef abk => abcedfabk
     * case 7: abk abcdef => abcedfabk
     * @param strings
     * @return
     */
    private static int getShortestSequenceLength(ArrayList<String> strings) {
        String result = "";

        for (String string : strings) {
            Set<SubstringResult> substringResults = getCommonSubString(result, string);

            if (!substringResults.isEmpty()) {
                for (SubstringResult substringResult : substringResults) {
                    if (resultFullyContainsSubstr(substringResult)) {
                        //do nothing
                        break;
                    } else if (stringFullyContainsResult(substringResult)) {
                        result = string;
                        break;
                    } else if (substrIsAtEndOfResult(substringResult)) {
                        result = result + string.substring(substringResult.substring.length());
                        break;
                    } else if (substrIsAtStartOfResult(substringResult)) {
                        result = string + result.substring(substringResult.substring.length());
                        break;
                    }
                }
            } else {
                result = result + string;
            }
        }

        return result.length();
    }

    //case 1: bcd abcde => abcde        (bc)
    private static boolean stringFullyContainsResult(SubstringResult res) {
        return res.stringJ.equals(res.substring);
    }

    //case 1: abcde bcd => abcde        (bc)
    private static boolean resultFullyContainsSubstr(SubstringResult res) {
        return res.stringI.equals(res.substring);
    }

    //case 1: bcde abc => abcde        (bc)
    private static boolean substrIsAtStartOfResult(SubstringResult res) {
        return res.lastIndexI == res.substring.length() - 1 &&
                res.lastIndexJ == res.stringJ.length() - 1;
    }

    //case 1: abcde def => abcdef        (de)
    private static boolean substrIsAtEndOfResult(SubstringResult res) {
        return res.lastIndexI == res.stringI.length() - 1 &&
                res.lastIndexJ == res.substring.length() - 1;
    }

    public static Set<SubstringResult> getCommonSubString(String stringI, String stringJ) {
        int[][] lengths = new int[stringI.length()][stringJ.length()];
        int longestLength = 0;
        Set<SubstringResult> subStrings = new HashSet<>();

        for (int i = 0; i < stringI.length(); i++) {
            for (int j = 0; j < stringJ.length(); j++) {
                if (stringI.charAt(i) == stringJ.charAt(j)) {

                    int currentLength;
                    if (i == 0 || j == 0) {
                        currentLength = 1;
                    } else {
                        currentLength =  lengths[i - 1][j - 1] + 1;
                    }
                    lengths[i][j] = currentLength;

                    String substring = stringI.substring(i + 1 - currentLength, i + 1);
                    SubstringResult result = new SubstringResult(substring, i, j, stringI, stringJ);
                    if (isLastPartAndStartOfAny(result)) {
                        if (lengths[i][j] > longestLength) {
                            longestLength = lengths[i][j];
                        }
                        subStrings.add(result);
                    }

                } else {
                    lengths[i][j] = 0;
                }
            }
        }
        return subStrings;
    }

    private static boolean isLastPartAndStartOfAny(SubstringResult result) {
        return substrIsAtEndOfResult(result) || substrIsAtStartOfResult(result);
    }


    private static ArrayList<String> initStrings(Scanner in) {
        int N = in.nextInt();
        ArrayList<String> strings = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            strings.add(in.next());
        }
        return strings;
    }



    private static class SubstringResult {
        private final String substring;
        private final int lastIndexI;
        private final int lastIndexJ;
        private final String stringI;
        private final String stringJ;

        public SubstringResult(String substring, int lastIndexI, int lastIndexJ, String stringI, String stringJ) {
            this.substring = substring;
            this.lastIndexI = lastIndexI;
            this.lastIndexJ = lastIndexJ;
            this.stringI = stringI;
            this.stringJ = stringJ;
        }

        @Override
        public String toString() {
            return "SubstringResult{" +
                    "substring='" + substring + '\'' +
                    ", lastIndexI=" + lastIndexI +
                    ", lastIndexJ=" + lastIndexJ +
                    ", stringI='" + stringI + '\'' +
                    ", stringJ='" + stringJ + '\'' +
                    '}';
        }
    }
}