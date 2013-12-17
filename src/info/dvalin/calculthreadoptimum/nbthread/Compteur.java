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
public class Compteur implements CompteurMBean {

    private static final int nbCoeur = Runtime.getRuntime().availableProcessors();
    private NbCycle data;

    /**
     *
     * @param data
     */
    public Compteur(NbCycle data) {
        this.data = data;
    }

    /**
     *
     * @return getCycle
     */
    @Override
    public int getCycle() {
        // TODO Auto-generated method stub
        return data.getNbBoucle().get();
    }

    /**
     *
     * @return getCoeurs
     */
    @Override
    public int getCoeurs() {
        // TODO Auto-generated method stub
        return this.nbCoeur;
    }

    /**
     *
     * @return getTempsExecution
     */
    @Override
    public long getTempsExecution() {
        return (data.getTempsFin().get() - data.getTempsDebut().get());
    }

    /**
     *
     * @param nbThreadMin
     */
    @Override
    public void setNbThreadMin(int nbThreadMin) {
        data.setNbThreadMin(nbThreadMin);
    }

    /**
     *
     * @return getNbThreadMin
     */
    @Override
    public int getNbThreadMin() {
        return data.getNbThreadMin();
    }

    /**
     *
     * @param nbThreadMax
     */
    @Override
    public void setNbThreadMax(int nbThreadMax) {
        data.setNbThreadMax(nbThreadMax);
    }

    /**
     *
     * @return getNbThreadMax
     */
    @Override
    public int getNbThreadMax() {
        return data.getNbThreadMax();
    }

    /**
     *
     * @param chemin
     */
    @Override
    public void setChemin(String chemin) {
        data.setChemin(chemin);
    }

    /**
     *
     * @return chemin
     */
    @Override
    public String getChemin() {
        return data.getChemin();
    }
}
