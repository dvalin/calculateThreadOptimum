package info.dvalin.calculthreadoptimum.statistique;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author stanislas GRANEL
 * @version 0.1
 *
 * Copyright 2013, Stanislas GRANEL
 *
 * This file is part of CalculThreadOptimum.
 *
 * CalculThreadOptimum is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * CalculThreadOptimum is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * CalculThreadOptimum. If not, see <http://www.gnu.org/licenses/>. 2
 *
 */
public class StatisticThread {

    private static HashMap<Integer, DataThread> hashData = new HashMap();
    private static HashMap<Integer, Integer[]> hashMediantCycle = new HashMap();
    private static HashMap<Integer, Integer[]> hashMediantTemps = new HashMap();
    private static final String separateur = ";";
    private static final String vide = "";
    private static Integer minThread;
    private static Integer maxThread;
    private static int valThread;
    private Integer valeurMedianeCycle = null;
    private int threadMedianeCycle = 0;
    private Integer valeurMedianeTemps = null;
    private int threadMedianeTemps = 0;
    private Double valeurMoyenneCycle = null;
    private int threadMoyenneCycle = 0;
    private Double valeurMoyenneTemps = null;
    private int threadMoyenneTemps = 0;

    /**
     * Cette fonction permet de lire dans un fichier chaque ligne et
     * d'additionner les valeurs par Thread dans une HashMap
     *
     * @throws Exception
     */
    private void readResultFile() throws Exception {
        File file = new File("C:\\tmp\\result.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufReader = new BufferedReader(fileReader);
        for (String line = bufReader.readLine(); line != null; line = bufReader.readLine()) {
            String[] oneLine = line.split(separateur);
            if (oneLine[0] != null && !(vide.equals(oneLine[0]))
                    && oneLine[1] != null && (!vide.equals(oneLine[1]))
                    && oneLine[2] != null && (!vide.equals(oneLine[0]))) {
                valThread = Integer.parseInt(oneLine[0]);

                // on récupère la valeur du plus petit Thread présent dans le fichier
                // et on la stocke dans la variable de classe
                if (minThread == null || minThread > valThread) {
                    minThread = valThread;
                }
                // on récupère la valeur du plus grand Thread présent dans le fichier
                // et on la stocke dans la variable de classe
                if (maxThread == null || maxThread < valThread) {
                    maxThread = valThread;
                }

                // on récupère un objet dataThread grâce à la variable valThread
                // qui correspond au numéro du thread présent de la ligne et qui 
                // est la clef d'entrée de la hashMap
                DataThread dataThread = hashData.get(valThread);
                int nbCycle = Integer.parseInt(oneLine[1]);
                int temps = Integer.parseInt(oneLine[2]);
                int nbTourThread = 1;
                // On vérifie que l'objet DataThread n'est pas null
                if (dataThread != null) {
                    // si l'objet n'est pas null, on récupère ses valeurs
                    nbCycle += dataThread.getNbCycle();
                    nbTourThread += dataThread.getNbTourThread();
                    temps += dataThread.getTemps();
                } else { // sinon on en crée un 
                    dataThread = new DataThread();
                }
                dataThread.setNbCycle(nbCycle);
                dataThread.setNbTourThread(nbTourThread);
                dataThread.setTemps(temps);
                // on met à jour la valeur dans la hashMap
                hashData.put(valThread, dataThread);

                Integer tabInt[];
                if (hashMediantCycle.get(valThread) == null) {
                    tabInt = new Integer[100];
                } else {
                    tabInt = hashMediantCycle.get(valThread);
                }
                tabInt[nbTourThread - 1] = nbCycle;
                hashMediantCycle.put(valThread, tabInt);

                if (hashMediantTemps.get(valThread) == null) {
                    tabInt = new Integer[100];
                } else {
                    tabInt = hashMediantTemps.get(valThread);
                }
                tabInt[nbTourThread - 1] = temps;
                hashMediantTemps.put(valThread, tabInt);
            }

        }
    }

    /**
     * Fonction qui permet de calculer de f'afficher les moyennes de valeurs des
     * threads
     */
    private void afficheStats() {
        // on fait une boucle qui commence à la valeur du plus petit thread et 
        // qui s'arrete à la valeur du plus grand thread.
        for (int i = minThread; i <= maxThread; i++) {
            DataThread data = hashData.get(i);

            // on fait la moyenne du temps qu'on divise par le nombre de tour
            // du thread
            Double tempsMoyen = data.getTemps() * 1.0 / data.getNbTourThread();
            // on fait la moyenne de cycles qu'on divise par le nombre de tour
            // du thread
            Double nbCycleMoyen = data.getNbCycle() * 1.0 / data.getNbTourThread();
            System.out.println("NbThread : " + i + " Temps moyen : " + tempsMoyen + " Nb Cycle Moyen : " + nbCycleMoyen);
            if (valeurMoyenneCycle == null || valeurMoyenneCycle < nbCycleMoyen) {
                valeurMoyenneCycle = nbCycleMoyen;
                threadMoyenneCycle = i;
            }
            if (valeurMoyenneTemps == null || valeurMoyenneTemps > tempsMoyen) {
                valeurMoyenneTemps = tempsMoyen;
                threadMoyenneTemps = i;
            }
        }
    }

    /**
     * Fonction qui permet d'afficher la valeur médiane pour chaque thread
     */
    private void afficheMediane() {

        for (int i = minThread; i <= maxThread; i++) {
            Integer tableauATrierCycle[] = hashMediantCycle.get(i);
            Arrays.sort(tableauATrierCycle);
            Integer tableauATrierTemps[] = hashMediantTemps.get(i);
            Arrays.sort(tableauATrierTemps);
            System.out.println("NbThread : " + i + " Temps median : " + tableauATrierTemps[50] + " Nb Cycle median : " + tableauATrierCycle[50]);
            if (valeurMedianeCycle == null || valeurMedianeCycle < tableauATrierCycle[50]) {
                valeurMedianeCycle = tableauATrierCycle[50];
                threadMedianeCycle = i;
            }
            if (valeurMedianeTemps == null || valeurMedianeTemps > tableauATrierTemps[50]) {
                valeurMedianeTemps = tableauATrierTemps[50];
                threadMedianeTemps = i;
            }
        }
    }

    /**
     * Fonction qui permet d'afficher les resultats optimums pour les moyennes
     * et les medianes
     */
    private void afficheResultat() {
        System.out.println("");
        System.out.println("Temps : ");
        System.out.println("Moyenne : NbThread optimal : " + threadMoyenneTemps + " Temps optimal : " + valeurMoyenneTemps);
        System.out.println("Mediane : NbThread optimal : " + threadMedianeTemps + " Temps optimal : " + valeurMedianeTemps);
        System.out.println("");
        System.out.println("Nombre de cyles : ");
        System.out.println("Moyenne : NbThread optimal : " + threadMoyenneCycle + " Cycle optimal : " + valeurMoyenneCycle);
        System.out.println("Mediane : NbThread optimal : " + threadMedianeCycle + " Cycle optimal : " + valeurMedianeCycle);
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Debut prog : StatisticThread");
        StatisticThread stat = new StatisticThread();
        stat.readResultFile();
        stat.afficheStats();
        stat.afficheMediane();
        stat.afficheResultat();
        System.out.println("Fin prog : StatisticThread");
    }
}
