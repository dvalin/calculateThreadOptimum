package info.dvalin.calculthreadoptimum.statistique;

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
public class DataThread {

    private int nbCycle = 0;
    private int temps = 0;
    private int nbTourThread = 0;

    /**
     *
     * @return nbCycle
     */
    public int getNbCycle() {
        return nbCycle;
    }

    /**
     *
     * @param nbCycle
     */
    public void setNbCycle(int nbCycle) {
        this.nbCycle = nbCycle;
    }

    /**
     *
     * @return temps
     */
    public int getTemps() {
        return temps;
    }

    /**
     *
     * @param temps
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     *
     * @return nbTourThread
     */
    public int getNbTourThread() {
        return nbTourThread;
    }

    /**
     *
     * @param nbTourThread
     */
    public void setNbTourThread(int nbTourThread) {
        this.nbTourThread = nbTourThread;
    }
}
