package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class ResultsWriter {

    private BufferedWriter bw;
    private FileWriter fw;
    private DateFormat dateFormat;
    private String fileName;

    public ResultsWriter(String fileName) {
        this.dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss:SSS");
        this.fileName = fileName;
    }

    public void resultFileCreation() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                FileWriter newFileWriter = new FileWriter(file);
                BufferedWriter newBufferedWriter = new BufferedWriter(newFileWriter);
                newFileWriter.write("");
                newBufferedWriter.close();
                newFileWriter.close();
            }
        } catch (IOException ex) {
        }
    }


    public void resultsStart(int firstNumber, int lastNumber, int increaseAmount) {

        try {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);
            bw.write(dateFormat.format(System.currentTimeMillis()) +
                    " Skaičiavimo pradžia. Naudojami skaičiai: " +
                    firstNumber + ", " + lastNumber + ", " + increaseAmount);
            newLine();
        } catch (IOException ex) {
        }
    }

    public void factorisationResult(long startTime, int number, String result) {
        try {
            bw.write(dateFormat.format(startTime) + " " + number + "=" +
                    result);
        } catch (IOException ex) {
        }
    }

    public void newLine() {
        try {
            bw.newLine();
        } catch (IOException ex) {
        }
    }


    public void resultsEnd() {
        try {
            bw.write(dateFormat.format(System.currentTimeMillis()) + " Skaičiavimo pabaiga");
            newLine();
        } catch (IOException ex) {
        }
    }

    public void closeResultsWriter() {
        try {
            bw.close();
            fw.close();
        } catch (IOException ex) {
        }
    }


}
