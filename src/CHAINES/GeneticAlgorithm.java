package CHAINES;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static CHAINES.GAUtils.ALPHABET;

public class GeneticAlgorithm
{
    private Individual ind1;
    private Individual ind2;
    private Individual[] population= new Individual[GAUtils.POPULATION_SIZE];
    public void initializePopulation()
    {
        for(int i = 0; i< GAUtils.POPULATION_SIZE; i++)
        {
            population[i] = new Individual();
            population[i].calculFitness();
        }
    }

    public void showPopulation() {
        for (Individual individual : population) {
            System.out.println(individual.getChromosome() + " Fitness Value: " + individual.getFitness());
        }
    }

    public void sortPopulation()
    {
        Arrays.sort(population, Comparator.reverseOrder());
    }

    public void crossover() {
        ind1 = new Individual();
        ind2 = new Individual();

        Random random = new Random();
        int crossPoint = random.nextInt(GAUtils.CHROMOSOME_SIZE - 1) + 1;

        String chromosome1 = population[0].getChromosome();
        String chromosome2 = population[1].getChromosome();

        String crossedChromosome1 = chromosome1.substring(0, crossPoint) + chromosome2.substring(crossPoint);
        String crossedChromosome2 = chromosome2.substring(0, crossPoint) + chromosome1.substring(crossPoint);

        ind1.setChromosome(crossedChromosome1);
        ind2.setChromosome(crossedChromosome2);

        population[GAUtils.POPULATION_SIZE - 2] = ind1;
        population[GAUtils.POPULATION_SIZE - 1] = ind2;

        System.out.println("Crossover Point: " + crossPoint);
        System.out.println("Individual 1: " + crossedChromosome1);
        System.out.println("Individual 2: " + crossedChromosome2);
    }


    public void mutation() {
        Random random = new Random();
        double mutationProb = GAUtils.MUTATION_PROB;

        StringBuilder mutatedChromosome1 = new StringBuilder(ind1.getChromosome());
        StringBuilder mutatedChromosome2 = new StringBuilder(ind2.getChromosome());

        for (int i = 0; i < GAUtils.CHROMOSOME_SIZE; i++) {
            if (random.nextDouble() < mutationProb) {
                char newChar = ALPHABET.charAt(random.nextInt(ALPHABET.length()));  // Générer un nouveau caractère aléatoire entre A et Z
                mutatedChromosome1.setCharAt(i, newChar);
            }

            if (random.nextDouble() < mutationProb)
            {
                char newChar = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
                mutatedChromosome2.setCharAt(i, newChar);
            }
        }

        ind1.setChromosome(mutatedChromosome1.toString());
        ind2.setChromosome(mutatedChromosome2.toString());
        ind1.calculFitness();
        ind2.calculFitness();
    }

    public int bestFitness() {
        int maxFitness = GAUtils.CHROMOSOME_SIZE;
        if (population[0].getFitness() == maxFitness) {
            return maxFitness;
        } else {
            return population[0].getFitness();
        }
    }
}
