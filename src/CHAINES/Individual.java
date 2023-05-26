package CHAINES;

import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable<Individual>
{
    String chromosome;
    int fitness;

    public Individual()
    {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int targetLength = "Bonjour BDCC".length();
        for (int i = 0; i < targetLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            sb.append(randomChar);
        }
        chromosome = sb.toString();
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void calculFitness() {
        String target = GAUtils.TARGET_STRING;
        int targetLength = target.length();
        int matchCount = 0;
        for (int i = 0; i < targetLength; i++) {
            if (chromosome.charAt(i) == target.charAt(i)) {
                matchCount++;
            }
        }
        fitness = matchCount;
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

    public Individual(String chromosome) {
        this.chromosome = chromosome;
    }
}
