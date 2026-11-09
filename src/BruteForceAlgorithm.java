public class BruteForceAlgorithm {
    public static int BruteForce(char[] T, char[] P) {
        int n = T.length;
        int m = P.length;
        int confronti = 0;

        for (int s = 0; s <= n - m; s++) {
            int i = 0;
            while (i < m) {
                confronti++;
                if (T[s + i] == P[i])
                    i = i + 1;
                else
                    break;
            }

            if (i == m) {
                // System.out.println("Brute Force: numero confronti: " + confronti);
                return confronti;
            }
        }
        // System.out.println("Brute Force: numero confronti: " + confronti);
        // return -1;
        return confronti;
    }
}