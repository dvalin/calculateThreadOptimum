package info.dvalin.calculthreadoptimum.nbthread;

import java.util.concurrent.Callable;

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
public class GenereBoucle implements Callable {

    private NbCycle data;

    /**
     *
     * @param data
     */
    public GenereBoucle(NbCycle data) {
        this.data = data;
    }

    /**
     * Cette fonction tourne pour un temps défini
     *
     * @return NbCycle
     * @throws Exception
     */
    @Override
    public Object call() throws Exception {
        // TODO Auto-generated method stub
        long temps_depart = System.currentTimeMillis();
        long duree = 1; // en millisecondes
        while ((System.currentTimeMillis() - temps_depart) < duree) {
            data.getNbBoucle().getAndIncrement();
            System.out.println("data : " + data.getNbBoucle().get()
                    + " Thread : " + Thread.currentThread().getId());
        }
        return data;
    }
}
