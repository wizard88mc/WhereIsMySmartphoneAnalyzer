package whereismysmartphoneanalyzer;

import java.util.ArrayList;
import models.Exercise;

/**
 *
 * @author Matteo
 */
public class ExercisesWorker 
{
    public static final String TASCA_DESTRA_DAVANTI_PANTALONI = "TASCA_DESTRA_DAVANTI_PANTALONI";
    public static final String TASCA_DESTRA_DIETRO_PANTALONI = "TASCA_DESTRA_DIETRO_PANTALONI";
    public static final String TASCA_SINISTRA_DAVANTI_PANTALONI = "TASCA_SINISTRA_DAVANTI_PANTALONI";
    public static final String TASCA_SINISTRA_DIETRO_PANTALONI = "TASCA_SINISTRA_DIETRO_PANTALONI";
    public static final String TASCA_GIACCA_BASSA = "TASCA_GIACCA_BASSA";
    public static final String TASCA_GIACCA_ALTA = "TASCA_GIACCA_ALTA";
    public static final String BORSA = "BORSA";
    public static final String MARSUPIO = "MARSUPIO";
    public static final String ZAINO = "ZAINO";
    
    private static final String FERMO = "FERMO";
    private static final String SEDUTO = "SEDUTO";
    private static final String CAMMINANDO = "CAMMINANDO";
    private static final String SCALE_SU = "SCALE_SU";
    private static final String SCALE_GIU = "SCALE_GIU";
    
    private ArrayList<Exercise> exercises;
    
    public ExercisesWorker(ArrayList<Exercise> exercises)
    {
        this.exercises = exercises;
    }
    
    /**
     * Prints a report of the exercises: counts the number of different 
     * destinations and the activity performed during this exercises
     */
    public void countNumberExercisesPerLabel()
    {
        int counterTascaDestraDavantiPantaloni = 0, counterTascaDestraDietroPantaloni = 0,
                counterTascaSinistraDavantiPantaloni = 0, counterTascaSinistraDietroPantaloni = 0,
                counterTascaGiaccaBassa = 0, counterTascaGiaccaAlta = 0, 
                counterBorsa = 0, counterMarsupio = 0, counterZaino = 0;
        
        int counterStanding = 0, counterSitting = 0, counterWalking = 0, 
                counterStairsUp = 0, counterStairsDown = 0;
        
        for (Exercise exercise: exercises)
        {
            
            if (exercise.getDestination().equals(TASCA_DESTRA_DAVANTI_PANTALONI))
            {
                counterTascaDestraDavantiPantaloni++;
            }
            else if (exercise.getDestination().equals(TASCA_DESTRA_DIETRO_PANTALONI))
            {
                counterTascaDestraDietroPantaloni++;
            }
            else if (exercise.getDestination().equals(TASCA_SINISTRA_DAVANTI_PANTALONI))
            {
                counterTascaSinistraDavantiPantaloni++;
            }
            else if (exercise.getDestination().equals(TASCA_SINISTRA_DIETRO_PANTALONI))
            {
                counterTascaSinistraDietroPantaloni++;
            }
            else if (exercise.getDestination().equals(TASCA_GIACCA_ALTA))
            {
                counterTascaGiaccaAlta++;
            }
            else if (exercise.getDestination().equals(TASCA_GIACCA_BASSA))
            {
                counterTascaGiaccaBassa++;
            }
            else if (exercise.getDestination().equals(BORSA))
            {
                counterBorsa++;
            }
            else if (exercise.getDestination().equals(MARSUPIO))
            {
                counterMarsupio++;
            }
            else if (exercise.getDestination().equals(ZAINO))
            {
                counterZaino++;
            }
            
            if (exercise.getAction().equals(FERMO))
            {
                counterStanding++;
            }
            else if (exercise.getAction().equals(SEDUTO))
            {
                counterSitting++;
            }
            else if (exercise.getAction().equals(CAMMINANDO))
            {
                counterWalking++;
            }
            else if (exercise.getAction().equals(SCALE_SU))
            {
                counterStairsUp++;
            }
            else if (exercise.getAction().equals(SCALE_GIU))
            {
                counterStairsDown++;
            }
        }
        
        System.out.println("***** DATA REPORT *****");
        System.out.println("Numero totale esercizi: " + exercises.size());
        System.out.println("Divisi per: ");
        System.out.println("Tasca destra davanti pantaloni: " + counterTascaDestraDavantiPantaloni);
        System.out.println("Tasca destra dietro pantaloni: " + counterTascaDestraDietroPantaloni);
        System.out.println("Tasca sinistra davanti pantaloni: " + counterTascaSinistraDavantiPantaloni);
        System.out.println("Tasca sinistra dietro pantaloni: " + counterTascaSinistraDietroPantaloni);
        System.out.println("Tasca giacca alta: " + counterTascaGiaccaAlta);
        System.out.println("Tasca giacca bassa: " + counterTascaGiaccaBassa);
        System.out.println("Borsa: " + counterBorsa);
        System.out.println("Marsupio: " + counterMarsupio);
        System.out.println("Zaino: " + counterZaino);
        System.out.println("***** ***** ***** *****");
        System.out.println("Azioni durante esercizi:");
        System.out.println("Fermo: " + counterStanding);
        System.out.println("Seduto: " + counterSitting);
        System.out.println("Camminando: " + counterWalking);
        System.out.println("Scale su: " + counterStairsUp);
        System.out.println("Scale giu: " + counterStairsDown);
        System.out.println("***** END REPORT *****");
    }
    
    public void cleanExercises()
    {
        deleteInconsistentExercises();
    }
    
    /**
     * Removes all the exercises that do not change the value of the 
     * proximity sensor
     */
    private void deleteInconsistentExercises()
    {
        ArrayList<Exercise> exercisesToEliminate = new ArrayList<Exercise>();
        
        for (Exercise exercise: exercises)
        {
            if (!exercise.hasConsistentProximityValue())
            {
                exercisesToEliminate.add(exercise);
            }
        }
        
        for (Exercise exercise: exercisesToEliminate)
        {
            exercises.remove(exercise);
        }
        
        exercisesToEliminate.clear();
    }
}
