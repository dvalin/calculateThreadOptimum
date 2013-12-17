package info.dvalin.calculthreadoptimum.nbthread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Cette classe récupère via la jconsole le minThread et le maxThread. Sans ces
 * valeurs, le main attendra sans fin.
 *
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
public class CalculeThreadOptimum {

    /**
     * Main
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Debut prog : CompteurBoucleForThreadRenseigne");
        NbCycle data = new NbCycle();
        // Get the Platform MBean Server
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        // Construct the ObjectName for the MBean we will register
        ObjectName name = new ObjectName("info.dvalin.calculthreadoptimum.nbthread:type=Compteur");

        // Create the Compteur MBean
        Compteur mbean = new Compteur(data);

        // Register the Compteur MBean
        mbs.registerMBean(mbean, name);

        // On attend que l'utilisateur renseigne un minThread via la jconsole
        // et un maxThread 
        while (data.getNbThreadMin() == 0 || data.getNbThreadMax() == 0) {
            Thread.sleep(1000);
        }

        // on fait tourner 100 fois la fonction qui va travailler du min thread
        // au max thread définit par l'utilisateur
        for (int i = 0; i < 100; i++) {
            travailThread(data);
        }
        //mbean.
        System.out.println("Fin prog : CompteurBoucleForThreadRenseigne");
    }

    /**
     * Cette fonction permet de faire tourner un nombre de thread dans un pool
     *
     * @param nbThread
     * @param data
     * @throws Exception
     */
    private static void travailThread(NbCycle data) throws Exception {
        for (int minThread = data.getNbThreadMin(); minThread <= data.getNbThreadMax(); minThread++) {
            tournerThread(data, minThread);
        }
    }

    /**
     * Cette fonction fait tourner un pool de thread C'est cette fonction qu'il
     * faut modifier si l'on veut appeler une fonction externe d'un projet
     * existant.
     *
     * @param data
     * @param thread
     * @throws Exception
     */
    private static void tournerThread(NbCycle data, int thread) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        GenereBoucle genereBoucle;
        Future[] futures = new Future[20];
        data.setTempsDebut(new AtomicLong(System.currentTimeMillis()));
        for (int cpt = 0; cpt < 20; cpt++) {
            // Debut de la partie à modifier pour appeler une fonction d'un 
            // projet existant importé sous forme de jar
            genereBoucle = new GenereBoucle(data);
            futures[cpt] = executorService.submit(genereBoucle);
            // Fin de la partie à modifier pour appeler une fonction d'un 
            // projet existant importé sous forme de jar
        }
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("Tache main " + i + " : " + futures[i].get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdownNow();
        data.setTempsFin(new AtomicLong(System.currentTimeMillis()));
        writeResultFile(data, thread);
    }

    /**
     * Cette fonction permet d'écrire les résultats dans un fichier csv
     *
     * @param data
     * @throws Exception
     */
    private static void writeResultFile(NbCycle data, int threadEnCours) throws Exception {
        new File("C:\\tmp").mkdir();
        File file = new File("C:\\tmp\\result.csv");
        file.createNewFile();
        System.out.println("Fonction writeResultFile : " + file.exists());
        if (file.exists()) {
            System.out.println("Debut ecriture fichier");
            try (
                    BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufWriter.newLine();
                int temps = data.getTempsFin().intValue() - data.getTempsDebut().intValue();
                bufWriter.write(threadEnCours + ";" + data.getNbBoucle().get() + ";" + temps);
            }
            System.out.println("Fin ecriture fichier");
        }
    }
}
