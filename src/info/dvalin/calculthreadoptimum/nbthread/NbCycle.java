package info.dvalin.calculthreadoptimum.nbthread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
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
public class NbCycle {

    private AtomicInteger nbBoucle = new AtomicInteger(0);
    private AtomicLong tempsDebut = new AtomicLong(System.currentTimeMillis());
    private AtomicLong tempsFin = new AtomicLong(System.currentTimeMillis());
    private String chemin;
    private int nbThreadMin;
    private int nbThreadMax;

    /**
     *
     * @return getNbBoucle
     */
    public synchronized AtomicInteger getNbBoucle() {
        return nbBoucle;
    }

    /**
     *
     * @param nbBoucle
     */
    public synchronized void setNbBoucle(AtomicInteger nbBoucle) {
        this.nbBoucle = nbBoucle;
    }

    /**
     *
     * @return getTempsDebut
     */
    public synchronized AtomicLong getTempsDebut() {
        return tempsDebut;
    }

    /**
     *
     * @param setTempsDebut
     */
    public synchronized void setTempsDebut(AtomicLong temps) {
        this.tempsDebut = temps;
    }

    /**
     *
     * @return getTempsFin
     */
    public synchronized AtomicLong getTempsFin() {
        return tempsFin;
    }

    /**
     *
     * @param tempsFin
     */
    public synchronized void setTempsFin(AtomicLong tempsFin) {
        this.tempsFin = tempsFin;
    }

    /**
     *
     * @return getNbThreadMin
     */
    public synchronized int getNbThreadMin() {
        return nbThreadMin;
    }

    /**
     *
     * @param nbThreadMin
     */
    public synchronized void setNbThreadMin(int nbThreadMin) {
        this.nbThreadMin = nbThreadMin;
    }

    /**
     *
     * @return getNbThreadMax
     */
    public synchronized int getNbThreadMax() {
        return nbThreadMax;
    }

    /**
     *
     * @param nbThreadMax
     */
    public synchronized void setNbThreadMax(int nbThreadMax) {
        this.nbThreadMax = nbThreadMax;
    }

    /**
     *
     * @return chemin
     */
    public synchronized String getChemin() {
        return chemin;
    }

    /**
     *
     * @param chemin
     */
    public synchronized void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
