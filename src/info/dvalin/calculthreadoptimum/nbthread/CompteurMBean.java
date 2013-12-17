package info.dvalin.calculthreadoptimum.nbthread;

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
public interface CompteurMBean {

    /**
     *
     * @return getCycle
     */
    public int getCycle();

    /**
     *
     * @return getCoeurs
     */
    public int getCoeurs();

    /**
     *
     * @return getTempsExecution
     */
    public long getTempsExecution();

    /**
     *
     * @param nbThreadMin
     */
    public void setNbThreadMin(int nbThreadMin);

    /**
     *
     * @return getNbThreadMin
     */
    public int getNbThreadMin();

    /**
     *
     * @param nbThreadMax
     */
    public void setNbThreadMax(int nbThreadMax);

    /**
     *
     * @return getNbThreadMax
     */
    public int getNbThreadMax();

    /**
     *
     * @param chemin
     */
    public void setChemin(String chemin);

    /**
     *
     * @return chemin
     */
    public String getChemin();
}
