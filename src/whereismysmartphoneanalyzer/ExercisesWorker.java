/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whereismysmartphoneanalyzer;

import java.util.ArrayList;
import models.Exercise;

/**
 *
 * @author Matteo
 */
public class ExercisesWorker 
{
    private static final String TASCA_DESTRA_DAVANTI_PANTALONI = "TASCA_DESTRA_DAVANTI_PANTALONI";
    private static final String TASCA_DESTRA_DIETRO_PANTALONI = "TASCA_DESTRA_DIETRO_PANTALONI";
    private static final String TASCA_SINISTRA_DAVANTI_PANTALONI = "TASCA_SINISTRA_DAVANTI_PANTALONI";
    private static final String TASCA_SINISTRA_DIETRO_PANTALONI = "TASCA_SINISTRA_DIETRO_PANTALONI";
    private static final String TASCA_GIACCA_BASSA = "TASCA_GIACCA_BASSA";
    private static final String TASCA_GIACCA_ALTA = "TASCA_GIACCA_ALTA";
    private static final String BORSA = "BORSA";
    private static final String MARSUPIO = "MARSUPIO";
    private static final String ZAINO = "ZAINO";
    
    private ArrayList<Exercise> exercises;
    
    public ExercisesWorker(ArrayList<Exercise> exercises)
    {
        this.exercises = exercises;
    }
    
    public void countNumberExercisesPerLabel()
    {
        int counterTascaDestraDavantiPantaloni = 0, counterTascaDestraDietroPantaloni = 0,
                counterTascaSinistraDavantiPantaloni = 0, counterTascaSinistraDietroPantaloni = 0,
                counterTascaGiaccaBassa = 0, counterTascaGiaccaAlta = 0, 
                counterBorsa = 0, counterMarsupio = 0, counterZaino = 0;
        
        for (Exercise exercise: exercises)
        {
            switch (exercise.getDestination())
            {
                case TASCA_DESTRA_DAVANTI_PANTALONI: 
                {
                    counterTascaDestraDavantiPantaloni++;
                    break;
                }
                case TASCA_DESTRA_DIETRO_PANTALONI: 
                {
                    counterTascaDestraDietroPantaloni++;
                    break;
                }
                case TASCA_SINISTRA_DAVANTI_PANTALONI:
                {
                    counterTascaSinistraDavantiPantaloni++;
                    break;
                }
                case TASCA_SINISTRA_DIETRO_PANTALONI: 
                {
                    counterTascaSinistraDietroPantaloni++;
                }
                case TASCA_GIACCA_ALTA:
                {
                    counterTascaGiaccaAlta++;
                    break;
                }
                case TASCA_GIACCA_BASSA: 
                {
                    counterTascaGiaccaBassa++;
                    break;
                }
                case BORSA:
                {
                    counterBorsa++;
                    break;
                }
                case MARSUPIO:
                {
                    counterMarsupio++;
                    break;
                }
                case ZAINO: 
                {
                    counterZaino++;
                    break;
                }
            }
        }
        
        // TODO print report
    }
}
