package ENTIERS;

public class GAApp
{

    public static void main(String[] args)
    {
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ga.initializePopulation();
        ga.sortPopulation();
        ga.showPopulation();
        int cpt=0;
        while(cpt < GAUtils.MAX_ITERATION && ga.bestFitness() < 5)
        {
            ga.crossover();
            ga.mutation();
            System.out.println("Generation "+cpt+": ");
            ga.sortPopulation();
            ga.showPopulation();
            cpt++;
        }


    }


}