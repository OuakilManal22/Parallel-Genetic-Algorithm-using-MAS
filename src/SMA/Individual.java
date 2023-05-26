package SMA;

import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable<Individual>
{
    private char [] chromosome = new char[GAUtils.CHROMOSOME_SIZE];
    private int fitness;

    public Individual()
    {
        Random random = new Random();
        for (int i = 0; i < GAUtils.CHROMOSOME_SIZE; i++) {
            int index=random.nextInt(GAUtils.ALPHABETICS.length());
            chromosome[i] = GAUtils.ALPHABETICS.charAt(index);
        }
    }

    public char[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(char[] chromosome) {
        this.chromosome = chromosome;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void calculFitness() {

        fitness=0;
        for (int i = 0; i < GAUtils.CHROMOSOME_SIZE; i++)
        {
            if (chromosome[i] == GAUtils.TARGET_STRING.charAt(i))
            {
                fitness++;
            }
        }
    }

    public int getFitness()
    {
        return fitness;
    }

    @Override
    public int compareTo(Individual o)
    {
        if(this.fitness > o.fitness)
        {
            return 1;
        } else if (this.fitness < o.fitness) {
            return -1;

        }
        return 0;
    }

    public Individual(char[] chromosome) {
        this.chromosome = Arrays.copyOf(chromosome, GAUtils.CHROMOSOME_SIZE);
    }
}
