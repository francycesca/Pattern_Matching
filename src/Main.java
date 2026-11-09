import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        int test = 6;
        int[] confrontiBF = new int[test];
        int[] confrontiFA = new int[test];
        double[] tempiBF = new double[test];
        double[] tempiFA = new double[test];

        // TEST 1: TESTO REALE

        // CASO 1: Consideriamo solo i primi 300.000 caratteri del testo
        char[] pattern1 = "smartphone".toCharArray();
        char[] testo1 = caricaTestoDaFile("testi_prova/promessi_sposi1.txt");

        // CASO 2: Consideriamo solo i primi 600.000 caratteri del testo
        char[] testo2 = caricaTestoDaFile("testi_prova/promessi_sposi2.txt");

        // CASO 3: Consideriamo tutto il testo
        char[] testo3 = caricaTestoDaFile("testi_prova/promessi_sposi3.txt");

        // TEST 2: METTERE ALLA PROVA L'ALGORITMO

        // CASO 1: Consideriamo solo i primi 300.000 caratteri del testo
        char[] pattern2 = "aaaaab".toCharArray();
        char[] testo4 = caricaTestoDaFile("testi_prova/testo_difficile1.txt");

        // CASO 2: Consideriamo solo i primi 600.000 caratteri del testo
        char[] testo5 = caricaTestoDaFile("testi_prova/testo_difficile2.txt");

        // CASO 3: Consideriamo tutto il testo
        char[] testo6 = caricaTestoDaFile("testi_prova/testo_difficile3.txt");

        // ----------------------------------------------
        // BRUTE FORCE
        // ----------------------------------------------

        // TEST 1
        long startBF = System.nanoTime();
        confrontiBF[0] = BruteForceAlgorithm.BruteForce(testo1, pattern1);
        long endBF = System.nanoTime();

        tempiBF[0] = calcolaTempi(startBF, endBF);

        // TEST 2
        startBF = System.nanoTime();
        confrontiBF[1] = BruteForceAlgorithm.BruteForce(testo2, pattern1);
        endBF = System.nanoTime();

        tempiBF[1] = calcolaTempi(startBF, endBF);

        // TEST 3
        startBF = System.nanoTime();
        confrontiBF[2] = BruteForceAlgorithm.BruteForce(testo3, pattern1);
        endBF = System.nanoTime();

        tempiBF[2] = calcolaTempi(startBF, endBF);

        // TEST 4
        startBF = System.nanoTime();
        confrontiBF[3] = BruteForceAlgorithm.BruteForce(testo4, pattern2);
        endBF = System.nanoTime();

        tempiBF[3] = calcolaTempi(startBF, endBF);

        // TEST 5
        startBF = System.nanoTime();
        confrontiBF[4] = BruteForceAlgorithm.BruteForce(testo5, pattern2);
        endBF = System.nanoTime();

        tempiBF[4] = calcolaTempi(startBF, endBF);

        // TEST 6
        startBF = System.nanoTime();
        confrontiBF[5] = BruteForceAlgorithm.BruteForce(testo6, pattern2);
        endBF = System.nanoTime();

        tempiBF[5] = calcolaTempi(startBF, endBF);

        // ----------------------------------------------
        // FINITE STATE AUTOMATON
        // ----------------------------------------------

        // TEST 1
        int[][] tFunction = FiniteAutomatonAlgorithm.CreateTransitionFunction(pattern1);

        long startFA = System.nanoTime();
        confrontiFA[0] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo1, tFunction, pattern1.length);
        long endFA = System.nanoTime();
        tempiFA[0] = calcolaTempi(startFA, endFA);

        // TEST 2
        startFA = System.nanoTime();
        confrontiFA[1] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo2, tFunction, pattern1.length);
        endFA = System.nanoTime();
        tempiFA[1] = calcolaTempi(startFA, endFA);

        // TEST 3
        startFA = System.nanoTime();
        confrontiFA[2] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo3, tFunction, pattern1.length);
        endFA = System.nanoTime();
        tempiFA[2] = calcolaTempi(startFA, endFA);

        // TEST 4

        tFunction = FiniteAutomatonAlgorithm.CreateTransitionFunction(pattern2);

        startFA = System.nanoTime();
        confrontiFA[3] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo4, tFunction, pattern2.length);
        endFA = System.nanoTime();
        tempiFA[3] = calcolaTempi(startFA, endFA);

        // TEST 5
        startFA = System.nanoTime();
        confrontiFA[4] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo5, tFunction, pattern2.length);
        endFA = System.nanoTime();
        tempiFA[4] = calcolaTempi(startFA, endFA);

        // TEST 6
        startFA = System.nanoTime();
        confrontiFA[5] = FiniteAutomatonAlgorithm.FiniteAutomaton(testo6, tFunction, pattern2.length);
        endFA = System.nanoTime();
        tempiFA[5] = calcolaTempi(startFA, endFA);

        // ----------------------------------------------
        // STAMPA TABELLA RISULTATI
        // ----------------------------------------------
        System.out.println("=====================================================================================");
        System.out.println("\t\t\t\tRISULTATI TEST");
        System.out.println("=====================================================================================");
        System.out.println("Test\t\t\tTempo BF\tTempo FA\tConfronti BF\tConfronti FA");
        System.out.println("-------------------------------------------------------------------------------------");

        String[] nomiTest = {
                "Promessi Sposi 300.000",
                "Promessi Sposi 600.000",
                "Promessi Sposi Completo",
                "Caso Peggiore 300.000",
                "Caso Peggiore 600.000",
                "Caso Peggiore completo",
        };

        for (int i = 0; i < nomiTest.length; i++) {
            // Usiamo System.out.printf per incolonnare perfettamente i dati senza far
            // saltare i tabulatori
            System.out.printf("%-23s\t%-13.4f\t%-13.4f\t%-12d\t%-12d\n",
                    nomiTest[i],
                    tempiBF[i],
                    tempiFA[i],
                    confrontiBF[i],
                    confrontiFA[i]);
        }
        System.out.println("=====================================================================================");
    }

    // Legge da un file di testo e trasforma in un array di caratteri
    public static char[] caricaTestoDaFile(String percorsoFile) {
        try {
            String contenuto = Files.readString(Paths.get(percorsoFile));
            return contenuto.toCharArray();
        } catch (IOException e) {
            System.err.println("Errore");
            e.printStackTrace();
            return null;
        }
    }

    public static double calcolaTempi(long start, long end) {
        return (end - start) / 1000000.0;
    }
}