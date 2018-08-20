package com.company;

import javafx.scene.control.TextField;

public class InputParser {

    public static int parseInput(TextField inputField) {

        int inputValue = -1;

        try {
            inputValue = Integer.parseInt(inputField.getText());
            if(inputValue < 0) {
                throw new NumberFormatException();
            }
            else {
                return inputValue;
            }

        }
        catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }

    }
}
