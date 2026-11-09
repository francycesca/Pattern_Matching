public class FiniteAutomatonAlgorithm {
    public static int FiniteAutomaton(char[] T, int[][] delta, int m) {
        int n = T.length;
        int q = 0;
        int confronti = 0;

        for (int i = 0; i < n; i++) {
            // ulteriore controllo nel caso in cui il carattere incontrato non rientrasse
            // nei 256 ASCII
            if (T[i] < 256) {
                q = delta[q][T[i]];
                confronti++;
            } else
                q = 0; // in caso ricomincia da capo

            if (q == m) {
                // System.out.println("FA numero di confronti: " + confronti);
                // return i - m + 1; //indice a cui ho trovato la parola
                return confronti;
            }
        }

        // System.out.println("FA numero di confronti: " + confronti);
        // return -1;
        return confronti;
    }

    private static int[][] TransitionFunction(char[] P, char[] alphabet) {
        int m = P.length;
        // matrice delle transizioni con m+1 stati e 256 colonne (caratteri ASCII)
        int[][] delta = new int[m + 1][256];

        for (int q = 0; q <= m; q++) {
            for (char c : alphabet) {

                int k = Math.min(m, q + 1);

                String Pqa = new String(P, 0, q) + c;

                while (k > 0) {

                    String pk = new String(P, 0, k);

                    // verifica se Pk è suffisso di Pq + c
                    if (Pqa.endsWith(pk)) {
                        break;
                    }

                    k--;
                }

                delta[q][c] = k;
            }
        }

        return delta;
    }

    // metodo di creazione della funzione di transizione mandando in ingresso
    // l'intero alfabeto
    public static int[][] CreateTransitionFunction(char[] P) {
        // generazione dell'alfabeto
        char[] alphabet = new char[256];
        for (int i = 0; i < 256; i++) {
            alphabet[i] = (char) i;
        }

        int[][] delta = TransitionFunction(P, alphabet);
        return delta;
    }
}
