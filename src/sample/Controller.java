package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    private static final int ZERO = 48;
    private static final int COLON = 58;
    private static final int A_BIG = 65;
    private static final int SQUARE_OPEN_BRACKET = 91;
    private static final int A_LITTLE = 97;
    private static final int CURLY_OPEN_BRACKET = 123;

    private static final int RANGE = 75;

    private int numberOfPasswords;
    private int numberOfSymbols;
    private ArrayList<Integer> scope;

    @FXML
    private TextArea passwordsTextArea;
    @FXML
    private CheckBox smallLettersCheckBox;
    @FXML
    private CheckBox bigLettersCheckBox;
    @FXML
    private CheckBox numbersCheckBox;
    @FXML
    private CheckBox specialSymbolsCheckBox;
    @FXML
    private Slider passwordsSlider;
    @FXML
    private Slider symbolsSlider;
    @FXML
    private Label passwordsLabel;
    @FXML
    private Label symbolsLabel;

    /**
     * Method launches with mainButton, starting the handler of password generator
     */
    @FXML
    private void buttonClicked() {
        numberOfPasswords = (int) passwordsSlider.getValue();
        numberOfSymbols = (int) symbolsSlider.getValue();

        scopeUpdate();

        generateAndWritePasswords();

        updateLabels();
    }

    /**
     * Method updates the range of appropriate symbols for generating password (from main window information)
     */
    @FXML
    private void scopeUpdate() {
        scope = new ArrayList<>();

        for (int i = 0; i < RANGE; i++) {
            scope.add(i);
        }

        if (!smallLettersCheckBox.isSelected()) {
            for (int i = A_LITTLE; i < CURLY_OPEN_BRACKET; i++) {
                scope.set(i - ZERO, -1);
            }
        }
        if (!bigLettersCheckBox.isSelected()) {
            for (int i = A_BIG; i < SQUARE_OPEN_BRACKET; i++) {
                scope.set(i - ZERO, -1);
            }
        }
        if (!numbersCheckBox.isSelected()) {
            for (int i = ZERO; i < COLON; i++) {
                scope.set(i - ZERO, -1);
            }
        }
        if (!specialSymbolsCheckBox.isSelected()) {
            for (int i = COLON; i < A_BIG; i++) {
                scope.set(i - ZERO, -1);
            }

            for (int i = SQUARE_OPEN_BRACKET; i < A_LITTLE; i++) {
                scope.set(i - ZERO, -1);
            }
        }

        scope.removeAll(Collections.singleton(-1));
    }

    /**
     * Method generates random symbols from the settled range, concantinates them to password and show on main window
     */
    @FXML
    private void generateAndWritePasswords() {
        String password;
        int rand;

        passwordsTextArea.setText("");

        try {
            if (scope.size() == 0) {
                passwordsTextArea.appendText("Error parameters for symbols");

                throw new Exception();
            }

            if (numberOfPasswords == 0 || numberOfSymbols == 0) {
                passwordsTextArea.appendText("Error number of passwords or symbols");

                throw new Exception();
            }

            try {
                for (int i = 0; i < numberOfPasswords; i++) {
                    password = "";

                    for (int j = 0; j < numberOfSymbols; j++) {
                        rand = ZERO + scope.get((int) Math.floor(Math.random() * scope.size()));

                        password = password.concat(Character.toString((char) rand));
                    }

                    passwordsTextArea.appendText(password + "\n");
                }
            } catch (Exception e) {
                passwordsTextArea.appendText("Error idk");
            }
        } catch (Exception e) {

        }
    }

    /**
     * Method updates field in the main window to show user how many symbols has been generated and from which range
     */
    @FXML
    private void updateLabels() {
        passwordsLabel.setText("Number of passwords: " + numberOfPasswords);
        symbolsLabel.setText("Number of symbols: " + numberOfSymbols);
    }
}
