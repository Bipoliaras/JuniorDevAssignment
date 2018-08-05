package com.company;

public class AppController {

    private AppView view;
    private FactorisationTask factorisationThread;
    private String factorisationFileName;

    public AppController(AppView view) {
        this.view = view;
        this.factorisationThread = new FactorisationTask();
        this.factorisationFileName = "rezultatai.txt";
    }

    public void alertCreation(String title, String headerText, String contentText) {
        view.alertCreation(title, headerText, contentText);
    }

    //creates a new thread or cancels if there was already an active one
    public void startThread() {

        if (view.getFirstNumberInput() != -1 && view.getLastNumberInput() != -1 &&
                view.getIncreaseAmountInput() != -1) {

            stopThread();

            factorisationThread = new FactorisationTask(
                    view.getFirstNumberInput(),
                    view.getLastNumberInput(),
                    view.getIncreaseAmountInput(),
                    factorisationFileName
            );

            view.getProgressBar().progressProperty().bind(factorisationThread.progressProperty());
            view.getStatusLabel().textProperty().bind(factorisationThread.messageProperty());

            new Thread(factorisationThread).start();
        }
    }

    public void stopThread() {
        if (factorisationThread.isRunning()) {
            factorisationThread.cancel();
        }
    }


}
