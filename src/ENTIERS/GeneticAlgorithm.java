package ENTIERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithm
{
    private Individual1 ind1;
    private Individual1 ind2;
    private Individual1[] population= new Individual1[GAUtils.POPULATION_SIZE];
    public void initializePopulation()
    {
        for(int i=0;i<GAUtils.POPULATION_SIZE;i++)
        {
            population[i] = new Individual1();
            population[i].calculFitness();
        }
    }

    public void showPopulation()
    {
        for(Individual1 individual:population)
        {
            System.out.println(Arrays.toString(individual.getChromosome())+" Fitness Value: "+individual.getFitness());
        }
    }
    public void sortPopulation()
    {
        Arrays.sort(population, Comparator.reverseOrder());
    }

    //SELECT TWO BEST INDIVIDUALS TO CROSS
    public void crossover()
    {
        ind1 = new Individual1(population[0].getChromosome());
        ind2 = new Individual1(population[1].getChromosome());

        Random random = new Random();
        int crossPoint = random.nextInt(GAUtils.CHROMOSOME_SIZE-1);
        crossPoint++;
        for(int i=0;i<crossPoint;i++)
        {
            ind1.getChromosome()[i] = population[1].getChromosome()[i];
            ind2.getChromosome()[i] = population[0].getChromosome()[i];
        }
        //AJOUTER LES INDIVIDUS GEBERES A LA POP??!!!
        population[GAUtils.POPULATION_SIZE-2] = ind1;
        population[GAUtils.POPULATION_SIZE-1] = ind2;

        System.out.println("Crossover Point: "+crossPoint);
        System.out.println("ENTIERS.Individual 1: "+Arrays.toString(ind1.getChromosome()));
        System.out.println("ENTIERS.Individual 2: "+Arrays.toString(ind2.getChromosome()));


    }

    public void mutation()
    {
        Random random = new Random();
        if(random.nextDouble()>GAUtils.MUTATION_PROB)
        {
            int index = random.nextInt(GAUtils.CHROMOSOME_SIZE);
            ind1.getChromosome()[index] = 1 -  ind1.getChromosome()[index];
        }

            if(random.nextDouble()<GAUtils.MUTATION_PROB)
            {
                int index = random.nextInt(GAUtils.CHROMOSOME_SIZE);
                ind2.getChromosome()[index] = 1 -  ind2.getChromosome()[index];
            }
            ind1.calculFitness();
            ind2.calculFitness();

      /*  System.out.println("Après Mutation");
        System.out.println("ENTIERS.Individual 1: "+Arrays.toString(ind1.getChromosome()));
        System.out.println("ENTIERS.Individual 2: "+Arrays.toString(ind2.getChromosome()));*/
     //   population[ENTIERS.GAUtils.POPULATION_SIZE-2] = ind1;
       // population[ENTIERS.GAUtils.POPULATION_SIZE-1] = ind2;
    }


    public int bestFitness()
    {

        return population[0].getFitness();
    }
}
