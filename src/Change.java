import java.util.Arrays;
/**
 *  Finds the number of ways to make a dollar.  (dynamic programming,
 *  counting sort, DFS, recursion)
 *
 */

public class Change {
    static int[] coinVal = {1, 5, 10, 25, 50};
    static long[][] dp;

    public static void main(String[] args) {
        int target = 1000;
        //We are including 0 and target as possible spit values
        dp = new long[target + 1][coinVal.length];
        for (long[] arr:dp) {
            Arrays.fill(arr, -1);
        }
        System.out.println(split(target, 0));
    }

    public static long split(int cents, int idx) {
        //Was this a valid breakdown?
        if (idx == coinVal.length) {
            //No more remainders
            return cents == 0 ? 1 : 0;
        }
        else if (dp[cents][idx] != -1) {
            //If it's been filled in
            return dp[cents][idx];
        }
        long output = 0;
        //Consider case of not using coin at all, so start at 0
        for(int cV = 0; cV <= cents; cV += coinVal[idx]) {
            output += split(cents - cV, idx + 1);
        }
        dp[cents][idx] = output;
        return output;
    }
}