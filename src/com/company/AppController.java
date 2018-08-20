package com.company;

public class AppController {

    private AppView view;
    private FactorisationTask factorisationThread;
    private String factorisationFileName;
    private Factoriser factoriser;
    private InputParser parser;

    public AppController(AppView view) {
        this.view = view;
        this.factorisationThread = new FactorisationTask();
        this.factorisationFileName = "rezultatai.txt";
        this.factoriser = new Factoriser();
        this.parser = new InputParser();
    }

    public void alertCreation(String title, String headerText, String contentText) {
        view.alertCreation(title, headerText, contentText);
    }

    //creates a new thread or cancels if there was already an active one
    public void startThread() {
        try {
            int firstNumberInput = parser.parseInput(view.getFirstNumberInput());
            int lastNumberInput = parser.parseInput(view.getLastNumberInput());
            int increaseAmountInput = parser.parseInput(view.getIncreaseAmountInput());

            stopThread();

            factorisationThread = new FactorisationTask(
                    firstNumberInput,
                    lastNumberInput,
                    increaseAmountInput,
                    factorisationFileName,
                    factoriser
            );

            view.getProgressBar().progressProperty().bind(factorisationThread.progressProperty());
            view.getStatusLabel().textProperty().bind(factorisationThread.messageProperty());

            new Thread(factorisationThread).start();
        }
        catch (NumberFormatException ex) {
            alertCreation("Error","There is an issue with the input numbers",
                    "A non number or a negative number is entered in an input field");
        }



    }


    public void stopThread() {
        if (factorisationThread.isRunning()) {
            factorisationThread.cancel();
        }
    }


}
