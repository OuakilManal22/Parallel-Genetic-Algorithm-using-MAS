package ENTIERS;

import java.util.Arrays;
import java.util.Random;

public class Individual1 implements Comparable<Individual1>
{
    int []chromosome = new int[GAUtils.CHROMOSOME_SIZE];
    int fitness;

    public Individual1()
    {
        Random random = new Random();
        for(int i=0;i<GAUtils.CHROMOSOME_SIZE;i++)
        {
            //GENERATE RANDOM NUMER 0 OR 1
            chromosome[i]=random.nextInt(2);
        }
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void calculFitness()
    {
        for(int gene:chromosome)
        {
            fitness+=gene;
        }
    }

    public int getFitness()
    {
        return fitness;
    }

    @Override
    public int compareTo(Individual1 o)
    {
        if(this.fitness > o.fitness)
        {
            return 1;
        } else if (this.fitness < o.fitness) {
            return -1;

        }
        return 0;
    }

    public Individual1(int[] chromosome) {
        this.chromosome = Arrays.copyOf(chromosome, GAUtils.CHROMOSOME_SIZE);
    }
}
