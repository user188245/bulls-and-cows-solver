package com.user188245.bullsandcowssolver.run;

import com.user188245.bullsandcowssolver.BullsAndCowsSolver;
import com.user188245.bullsandcowssolver.Guess;
import com.user188245.bullsandcowssolver.GuessImpl;
import com.user188245.bullsandcowssolver.Trial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UI_Main {
    private BullsAndCowsSolver solver;
    private JPanel mainArea;
    private JButton inputButton;
    private JPanel controllerArea;
    private JPanel trialArea;
    private JPanel guessArea;
    private JFormattedTextField cowsField;
    private JFormattedTextField bullsField;
    private JTextArea textArea;
    private JButton outputButton;
    private JButton resetButton;
    private JPanel messageArea;
    private List<JComboBox<Integer>> comboBoxList;

    public static void start(BullsAndCowsSolver solver) {
        JFrame frame = new JFrame("BullsAndCowsSolver");
        frame.setContentPane(new UI_Main(solver).mainArea);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private UI_Main(BullsAndCowsSolver solver) {

        this.solver = solver;
        this.comboBoxList = buildUnitSelectionComboList(solver);
        for (JComboBox<Integer> comboBox : comboBoxList) {
            this.guessArea.add(comboBox);
        }
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input();
            }
        });
        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        reset();
    }

    private void input() {
        try {
            Trial trial = getTrialFromJComponents(getGuessFromComboBox(comboBoxList), bullsField, cowsField);
            solver.putClue(trial);
            textArea.append(trial + " was inserted." + "\n");
        } catch (RuntimeException e1) {
            textArea.append("Exception : " + e1.getMessage() + "\n");
        }
    }

    private void output() {
        try {
            Guess guess = solver.getSolution();
            setComboBoxFromGuess(comboBoxList, guess);
            bullsField.setText("");
            cowsField.setText("");
            textArea.append("AI recommends " + guess + "." + "\n");
        } catch (RuntimeException e1) {
            textArea.append("Exception : " + e1.getMessage() + "\n");
        }
    }

    private void reset() {
        try {
            solver.reset();
            textArea.setText("");
            setComboBoxFromGuess(comboBoxList, solver.getSolution());
            textArea.append("The game has been initialized.\n");
        } catch (RuntimeException e1) {
            textArea.append("Exception : " + e1.getMessage() + "\n");
        }
    }

    private static List<JComboBox<Integer>> buildUnitSelectionComboList(BullsAndCowsSolver solver) {
        int boxSize = solver.getBoxSize();
        int unitSize = solver.getUnitSize();
        List<JComboBox<Integer>> result = new ArrayList<>();
        for (int i = 0; i < boxSize; i++) {
            JComboBox<Integer> comboBox = new JComboBox<>();
            for (int j = 0; j < unitSize; j++) {
                comboBox.addItem(j);
            }
            result.add(comboBox);
        }
        return result;
    }

    private static Guess getGuessFromComboBox(List<JComboBox<Integer>> comboBoxList) {
        List<Integer> guess = new ArrayList<>();
        for (JComboBox<Integer> comboBox : comboBoxList) {
            guess.add((Integer) comboBox.getSelectedItem());
        }
        return GuessImpl.generate(guess);
    }

    private static void setComboBoxFromGuess(List<JComboBox<Integer>> comboBoxList, Guess guess) {
        for (int i = 0; i < comboBoxList.size(); i++) {
            comboBoxList.get(i).setSelectedItem(guess.get(i));
        }
    }

    private static Trial getTrialFromJComponents(Guess guess, JTextField bullsField, JTextField cowsField) {
        return new Trial(guess, Integer.parseInt(bullsField.getText()), Integer.parseInt(cowsField.getText()));
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainArea = new JPanel();
        mainArea.setLayout(new BorderLayout(5, 5));
        mainArea.setEnabled(true);
        mainArea.setMinimumSize(new Dimension(600, 480));
        messageArea = new JPanel();
        messageArea.setLayout(new BorderLayout(10, 10));
        mainArea.add(messageArea, BorderLayout.NORTH);
        textArea = new JTextArea();
        textArea.setColumns(0);
        textArea.setMargin(new Insets(0, 0, 0, 0));
        textArea.setMinimumSize(new Dimension(0, 60));
        textArea.setRows(30);
        messageArea.add(textArea, BorderLayout.CENTER);
        controllerArea = new JPanel();
        controllerArea.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        mainArea.add(controllerArea, BorderLayout.CENTER);
        trialArea = new JPanel();
        trialArea.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        controllerArea.add(trialArea);
        guessArea = new JPanel();
        guessArea.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        guessArea.setMinimumSize(new Dimension(48, 10));
        trialArea.add(guessArea);
        final JLabel label1 = new JLabel();
        label1.setText("bulls : ");
        trialArea.add(label1);
        bullsField = new JFormattedTextField();
        bullsField.setColumns(4);
        bullsField.setText("");
        trialArea.add(bullsField);
        final JLabel label2 = new JLabel();
        label2.setText("cows : ");
        trialArea.add(label2);
        cowsField = new JFormattedTextField();
        cowsField.setColumns(4);
        cowsField.setText("");
        trialArea.add(cowsField);
        inputButton = new JButton();
        inputButton.setText("InputTrial");
        controllerArea.add(inputButton);
        outputButton = new JButton();
        outputButton.setText("GetAdvice");
        controllerArea.add(outputButton);
        resetButton = new JButton();
        resetButton.setText("Reset");
        controllerArea.add(resetButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainArea;
    }

}