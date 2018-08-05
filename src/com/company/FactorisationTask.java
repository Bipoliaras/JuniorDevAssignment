package com.company;

import javafx.concurrent.Task;
import java.util.concurrent.CancellationException;


public class FactorisationTask extends Task<Void> {
    private int firstNumber;
    private int lastNumber;
    private int increaseAmount;
    private String fileName;

    public FactorisationTask() {

    }

    public FactorisationTask(int firstNumber, int lastNumber, int increaseAmount, String file) {
        this.firstNumber = firstNumber;
        this.lastNumber = lastNumber;
        this.increaseAmount = increaseAmount;
        this.fileName = file;
    }

    @Override
    protected Void call() throws Exception {

        ResultsWriter resultsWriter = new ResultsWriter(fileName);
        resultsWriter.resultFileCreation();


        long startTime;
        long timeDifference;

        double progressValue = 0;
        double maxProgressValue = 100;
        double progressParts = Math.round(((double) lastNumber - (double) firstNumber) /
                (double) increaseAmount);

        try {
            resultsWriter.resultsStart(firstNumber, lastNumber, increaseAmount);
            updateProgress(progressValue, maxProgressValue);

            for (int i = firstNumber; i < lastNumber; i += increaseAmount) {
                updateMessage("Skaldomas skaičius " + i);


                startTime = System.currentTimeMillis();
                Factoriser mainFactoriser = new Factoriser();
                String factorisationResult = mainFactoriser.factorisation(i);
                resultsWriter.factorisationResult(startTime, i, factorisationResult);

                timeDifference = System.currentTimeMillis() - startTime;

                //checks if 500ms have passed from the start of the factorisation
                if (timeDifference >= 500) {
                    resultsWriter.newLine();
                } else {
                    Thread.sleep(500 - timeDifference);
                    resultsWriter.newLine();
                }

                progressValue += maxProgressValue / progressParts;
                updateProgress(progressValue, maxProgressValue);

            }
        } catch (InterruptedException ex) {
            //if the thread is cancelled during sleep it throws an InterruptedException
            if (isCancelled()) {
                resultsWriter.newLine();
                updateMessage("Skaldymas nutrauktas");
            }
        } catch (CancellationException ex) {
            resultsWriter.newLine();
            updateMessage("Skaldymas nutrauktas");
        } finally {
            if (!isCancelled()) {
                updateMessage("Skaičiavimai baigti. Rezultatai išsaugoti " + fileName);
                resultsWriter.resultsEnd();
            }

            resultsWriter.closeResultsWriter();

            return null;
        }
    }


}