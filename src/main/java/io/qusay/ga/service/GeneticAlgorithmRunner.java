package io.qusay.ga.service;

import io.qusay.ga.geneticalgorithm.GeneticAlgorithmJob;
import io.qusay.ga.geneticalgorithm.GeneticAlgorithmJobData;

import java.util.concurrent.ExecutorService;

public class GeneticAlgorithmRunner {
    private final ExecutorService threadPool;
    private GeneticAlgorithmJob job;

    public GeneticAlgorithmRunner(GeneticAlgorithmJobData geneticAlgorithmJobData, ExecutorService threadPool) {
        this.job = new GeneticAlgorithmJob(geneticAlgorithmJobData, threadPool);
        this.threadPool = threadPool;
    }

    public void start() {
        threadPool.execute(job);
    }

    // Control (start, interrupt, and get data from) a sub thread
    // Interacts over Atomic variables
    // Method is from: https://www.baeldung.com/java-thread-stop
    public void stop() {
        job.setIsRunning(false);
    }

    public int getCurrentGeneration() {
        return job.getCurrentGeneration();
    }
}
