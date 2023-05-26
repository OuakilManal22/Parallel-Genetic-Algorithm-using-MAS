package SMA;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class IslandAgent extends Agent
{
    private Individual ind1;
    private Individual ind2;
    private Individual[] population = new Individual[GAUtils.POPULATION_SIZE];

    @Override
    protected void setup()
    {



        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour()
        {
            @Override
            public void action()
            {
                        initializePopulation();
                        sortPopulation();


            }
        });
        sequentialBehaviour.addSubBehaviour(new Behaviour() {
            int iter = 0;
            @Override
            public void action()
            {
                    crossover();
                    mutation();
                    sortPopulation();
                    iter++;

            }

            @Override
            public boolean done() {
                return SMA.GAUtils.MAX_ITERATION == iter || bestFitness() == SMA.GAUtils.CHROMOSOME_SIZE;
            }
        });
        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                DFAgentDescription dfAgentDescription = new DFAgentDescription();
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setType("ga");
                dfAgentDescription.addServices(serviceDescription);
                DFAgentDescription[] dfAgentDescriptions=null;
                try {
                    dfAgentDescriptions = DFService.search(getAgent(), dfAgentDescription);
                } catch (FIPAException e) {
                    throw new RuntimeException(e);
                }
                ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
                aclMessage.addReceiver(dfAgentDescriptions[0].getName());
                aclMessage.setContent(String.valueOf(population[0].getFitness()+" "+ Arrays.toString(population[0].getChromosome())));
                send(aclMessage);



            }
        });

        addBehaviour(sequentialBehaviour);


    }


    public void initializePopulation()
    {
        for(int i = 0; i< GAUtils.POPULATION_SIZE; i++)
        {
            population[i] = new Individual();
            population[i].calculFitness();
        }
    }


    public void crossover()
    {
        ind1 = new Individual(population[0].getChromosome());
        ind2 = new Individual(population[1].getChromosome());

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
        System.out.println("Individual 1: "+ Arrays.toString(ind1.getChromosome()));
        System.out.println("Individual 2: "+Arrays.toString(ind2.getChromosome()));


    }

    public void mutation()
    {
        Random random = new Random();
        if(random.nextDouble()>GAUtils.MUTATION_PROB)
        {
            int index = random.nextInt(GAUtils.ALPHABETICS.length());
            int chromIndex = random.nextInt(GAUtils.CHROMOSOME_SIZE);
            ind1.getChromosome()[chromIndex] = SMA.GAUtils.ALPHABETICS.charAt(index);

        }

        if(random.nextDouble()<GAUtils.MUTATION_PROB)
        {
            int index = random.nextInt(GAUtils.ALPHABETICS.length());
            int chromIndex = random.nextInt(GAUtils.CHROMOSOME_SIZE);
            ind2.getChromosome()[chromIndex] = GAUtils.ALPHABETICS.charAt(index);
        }
        ind1.calculFitness();
        ind2.calculFitness();
    }


    public void showPopulation()
    {
        for(Individual individual:population)
        {
            System.out.println(Arrays.toString(individual.getChromosome())+" Fitness Value: "+individual.getFitness());
        }
    }
    public void sortPopulation()
    {
        Arrays.sort(population, Comparator.reverseOrder());
    }

    public int bestFitness()
    {

        return population[0].getFitness();
    }


   /* @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
    }*/
}
