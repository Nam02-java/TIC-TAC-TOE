package SourceCode;

import static java.lang.System.out;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import static SourceCode.DataUser.saveNamePlayer1;
import static SourceCode.DataUser.saveNamePlayer2;

public class MyFrame extends JFrame {
    int row = 7;
    int column = 7;
    JButton[][] buttons = new JButton[row][column];
    JPanel panelCenter = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelNorth = new JPanel();
    JTextField textField = new JTextField("TIC TAC TOE");
    boolean playerX_turn = true;
    boolean playerO_turn = false;
    boolean undoCheckOfPlayerX = true;
    boolean undoCheckOfPlayerO = true;
    JButton buttonNewGame = new JButton("New Game");
    JButton buttonContinue = new JButton("Continue");
    JButton buttonStart = new JButton("Start");
    JButton buttonUndo = new JButton("Undo");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    int point_PlayerX = 0;
    int point_PlayerO = 0;
    JLabel label = new JLabel(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
    int countSystem1 = 0;
    int countSystem2 = 0;
    ArrayList<Integer> arrayList = new ArrayList<>();
    Timer stopWach;
    int count = 0;
    int delay = 1000;

    MyFrame() {
        setPanelCenter();
        setButtons();
        setTextField();
        setPanelSouth();
        setButtonContinue();
        setButtonNewGame();
        setButtonStart();
        systemCount();
        setButtonUndo();
        setWindow();
    }

    private void setWindow() {
        add(textField, BorderLayout.NORTH);
        add(panelCenter, BorderLayout.CENTER);
        add(panelSouth, BorderLayout.SOUTH);
        setTitle("TIC TAC TOE");
        setBounds(300, 350, 1000, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setPanelCenter() {
        panelCenter.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                panelCenter.add(buttons[i][j] = new JButton());
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFont(new Font("Ink Free", Font.BOLD, 75));
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void setButtons() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttons[finalI][finalJ]) {
                            if (playerX_turn == true) {
                                if (buttons[finalI][finalJ].getText() == "") {
                                    buttons[finalI][finalJ].setText("X");
                                    stopWach.stop();
                                    setStartTimerOfPlayerO(10); // wanring
                                    playerX_turn = false;
                                    playerO_turn = true;
                                    if (win(finalI, finalJ, buttons[finalI][finalJ].getText())) {
                                        buttonContinue.setEnabled(true);
                                        buttons[finalI][finalJ].setBackground(Color.red);
                                        stopWach.stop();
                                        textField.setText(saveNamePlayer1 + " " + "Wins" + " " + "and got 1 point");
                                        if (point_PlayerX < 4) {
                                            JOptionPane.showMessageDialog(null, saveNamePlayer1 + " " + " Wins ", "Title", JOptionPane.ERROR_MESSAGE);
                                        } else {
                                        }
                                        point_PlayerX += 1;
                                        label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                                        checkPoint();
                                        for (int i = 0; i < row; i++) {
                                            for (int j = 0; j < column; j++) {
                                                buttons[i][j].setEnabled(false);
                                            }
                                        }
                                    }
                                }
                            } else if (playerO_turn == true) {
                                if (buttons[finalI][finalJ].getText() == "") {
                                    buttons[finalI][finalJ].setText("O");
                                    stopWach.stop();
                                    setStartTimerOfPlayerX(10);
                                    playerO_turn = false;
                                    playerX_turn = true;
                                    if (win(finalI, finalJ, buttons[finalI][finalJ].getText())) {
                                        buttonContinue.setEnabled(true);
                                        buttons[finalI][finalJ].setBackground(Color.RED);
                                        stopWach.stop();
                                        textField.setText(saveNamePlayer2 + " " + "Wins" + " " + "and got 1 point");
                                        if (point_PlayerO < 4) {
                                            JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + " Wins ", "Title", JOptionPane.ERROR_MESSAGE);
                                        } else {
                                        }
                                        point_PlayerO += 1;
                                        label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                                        checkPoint();
                                        for (int i = 0; i < row; i++) {
                                            for (int j = 0; j < column; j++) {
                                                buttons[i][j].setEnabled(false);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public boolean win(int x, int y, String name) {
        int k, j;
        int d = 0;
        // kt chieu doc
        for (k = -4; k <= 4; k++) {
            if (x + k >= 0 && x + k < row) {
                if (buttons[x + k][y].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        //xet ngang
        for (k = -5; k <= 5; k++) {
            if (y + k >= 0 && y + k < row) {
                if (buttons[x][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        //cheo
        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
            if (y + k >= 0 && y + k < row && x + j >= 0 && x + j < column) {
                if (buttons[x + j][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        for (k = -4; k <= 4; k++) {
            if (y + k >= 0 && y + k < row && x + k >= 0 && x + k < column) {
                if (buttons[x + k][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        }
        return false;
    }

    private void setTextField() {
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setEditable(false);
    }

    private void setPanelSouth() {
        panelSouth.setLayout(new FlowLayout(1));
        /**
         * button for new game
         */
        panelSouth.add(buttonNewGame);
        buttonNewGame.setVisible(false);
        panelSouth.setBackground(Color.BLACK);
        buttonNewGame.setFont(new Font("Ink Free", Font.BOLD, 25));
        buttonNewGame.setBackground(Color.GRAY);
        buttonNewGame.setForeground(Color.WHITE);
        /**
         * Jlabel for point and nick name
         */
        panelSouth.add(label);
        label.setVisible(false);
        label.setFont(new Font("Ink Free", Font.BOLD, 25));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.GRAY);
        /**
         * button for undo
         */
        panelSouth.add(buttonUndo);
        buttonUndo.setVisible(false);
        buttonUndo.setFont(new Font("Ink Free", Font.BOLD, 25));
        buttonUndo.setBackground(Color.GRAY);
        buttonUndo.setForeground(Color.WHITE);
        /**
         * button for continue
         */
        panelSouth.add(buttonContinue);
        buttonContinue.setVisible(false);
        buttonContinue.setFont(new Font("Ink Free", Font.BOLD, 25));
        buttonContinue.setBackground(Color.GRAY);
        buttonContinue.setForeground(Color.WHITE);
        /**
         * button for Start
         */
        panelSouth.add(buttonStart);
        buttonStart.setFont(new Font("Ink Free", Font.BOLD, 25));
        buttonStart.setBackground(Color.GRAY);
        buttonStart.setForeground(Color.WHITE);
    }

    public void setButtonContinue() {
        buttonContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayList.size() >= 1) {
                    int a = arrayList.get(0);
                    int b = arrayList.get(1);
                    if (buttons[a][b].getText() == "X") {
                        playerX_turn = false;
                        playerO_turn = true;
                        stopWach.stop();
                        setStartTimerOfPlayerO(10);
                    } else if (buttons[a][b].getText() == "O") {
                        playerX_turn = true;
                        playerO_turn = false;
                        stopWach.stop();
                        setStartTimerOfPlayerX(10);
                    }
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setText("");
                            buttons[i][j].setBackground(Color.WHITE);
                            buttons[i][j].setEnabled(true);
                        }
                    }
                } else if (arrayList.size() <= 0) {
                    if (textField.getText().contains(saveNamePlayer1 + " " + "Wins" + " " + "and got 1 point")) {
                        // playerX_turn = false;
                        // playerO_turn = true;
                        playerX_turn = true;
                        playerO_turn = false;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                buttons[i][j].setText("");
                                buttons[i][j].setBackground(Color.WHITE);
                                buttons[i][j].setEnabled(true);
                            }
                        }
                        setStartTimerOfPlayerX(10);
                    } else if (textField.getText().contains(saveNamePlayer2 + " " + "Wins" + " " + "and got 1 point")) {
                        // playerX_turn = true;
                        //  playerO_turn = false;
                        playerX_turn = false;
                        playerO_turn = true;
                        for (int i = 0; i < row; i++) {
                            for (int j = 0; j < column; j++) {
                                buttons[i][j].setText("");
                                buttons[i][j].setBackground(Color.WHITE);
                                buttons[i][j].setEnabled(true);
                            }
                        }
                        //   setStartTimerOfPlayerX(10);
                        setStartTimerOfPlayerO(10);
                    }
                }
                undoCheckOfPlayerX = true;
                undoCheckOfPlayerO = true;
                buttonContinue.setEnabled(false);
                arrayList.clear();
            }
        });
    }

    public void setButtonNewGame() {
        buttonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        buttons[i][j].setText("");
                        buttons[i][j].setBackground(Color.WHITE);
                        buttons[i][j].setEnabled(true);
                    }
                }
                playerX_turn = true;
                playerO_turn = false;
                point_PlayerX = 0;
                point_PlayerO = 0;
                textField.setText(saveNamePlayer1 + " - X turn");
                label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
                textField.setText("TIC TAC TOE");
                buttonContinue.setVisible(false);
                buttonNewGame.setVisible(false);
                buttonUndo.setVisible(false);
                label.setVisible(false);
                buttonStart.setVisible(true);
                arrayList.clear();
                undoCheckOfPlayerX = true;
                undoCheckOfPlayerO = true;
                setVisible(false);
                new DataUser();
            }
        });
    }

    public void setButtonStart() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arrayList.clear();
                buttonStart.setVisible(false);
                setStartTimerWhenPressStart(3);
                buttonNewGame.setVisible(true);
                label.setVisible(true);
                buttonUndo.setVisible(true);
                buttonContinue.setVisible(true);
                buttonContinue.setEnabled(true);
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < column; j++) {
                        buttons[i][j].setEnabled(true);
                    }
                }
            }
        });
    }

    public void checkPoint() {
        if (point_PlayerX == 5) {
            textField.setText(saveNamePlayer1 + " is the Winner !");
            buttonContinue.setEnabled(false);
            JOptionPane.showMessageDialog(null, saveNamePlayer1 + " " + "is the Winner !", "Title", JOptionPane.ERROR_MESSAGE);
        } else if (point_PlayerO == 5) {
            textField.setText(saveNamePlayer2 + " is the Winner !");
            buttonContinue.setEnabled(false);
            JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + "is the Winner !", "Title", JOptionPane.ERROR_MESSAGE);
            // } else if (point_PlayerX < 0) {
            //     textField.setText(saveNamePlayer2 + " is the Winner !");
            // /    label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
            //     buttonContinue.setEnabled(false);
            //    JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + "is the Winner !", "Title", JOptionPane.ERROR_MESSAGE);
            //} else if (point_PlayerO < 0) {
            //    textField.setText(saveNamePlayer1 + " is the Winner !");
            //   label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
            //  buttonContinue.setEnabled(false);
            // JOptionPane.showMessageDialog(null, saveNamePlayer1 + " " + "is the Winner !", "Title", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setStartTimerWhenPressStart(int countPassed) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setEnabled(true);
                        }
                    }
                    stopWach.stop();
                    setStartTimerAfterPressStart(10);
                } else {
                    buttonContinue.setEnabled(false);
                    textField.setText("Ready in " + count);
                    count--;
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                }
            }
        };
        // you can use stopWach = new Timer (delay , new ActionListener() {
        stopWach = new Timer(delay, actionListener);
        stopWach.setInitialDelay(3); //delay second 1 2 3 - update this line at 26/10/2021
        stopWach.start();
        count = countPassed;
    } // this method is important

    public void setStartTimerAfterPressStart(int countPassed) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    stopWach.stop();
                    textField.setText(saveNamePlayer2 + " " + "Wins" + " " + "and got 1 point");
                    point_PlayerO += 1;
                    checkPoint();
                    label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                    JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + " Wins ", "Title", JOptionPane.ERROR_MESSAGE);
                    buttonContinue.setEnabled(true);
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                } else if (count == 1) {
                    textField.setText(saveNamePlayer1 + " - X turn " + "(You have " + count + " second)");
                    count--;
                } else {
                    textField.setText(saveNamePlayer1 + " - X turn " + "(You have " + count + " seconds)");
                    count--;
                }
            }
        };
        stopWach = new Timer(delay, actionListener);
        stopWach.setInitialDelay(3);
        stopWach.start();
        count = countPassed;
    }

    public void setStartTimerOfPlayerX(int countPassed) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    buttonContinue.setEnabled(true);
                    stopWach.stop();
                    textField.setText(saveNamePlayer2 + " " + "Wins" + " " + "and got 1 point");
                    buttonContinue.setEnabled(true);// be care full !
                    playerX_turn = false; //
                    playerO_turn = true; //
                    // point_PlayerX -= 1;
                    //  label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                    point_PlayerO += 1;
                    checkPoint();
                    label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                    JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + " Wins ", "Title", JOptionPane.ERROR_MESSAGE);
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                } else if (count == 1) {
                    textField.setText(saveNamePlayer1 + " - X turn " + "(You have " + count + " second)");
                    count--;
                    playerX_turn = true; //
                    playerO_turn = false; //
                } else {
                    textField.setText(saveNamePlayer1 + " - X turn " + "(You have " + count + " seconds)");
                    count--;
                    playerX_turn = true; //
                    playerO_turn = false; //
                }
            }
        };
        stopWach = new Timer(delay, actionListener);
        stopWach.setInitialDelay(3);
        stopWach.start();
        count = countPassed;
    }

    public void setStartTimerOfPlayerO(int countPassed) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    buttonContinue.setEnabled(true);
                    stopWach.stop();
                    textField.setText(saveNamePlayer1 + " " + "Wins" + " " + "and got 1 point");
                    buttonContinue.setEnabled(true); // be care full !
                    playerX_turn = true; //
                    playerO_turn = false; //
                    //  point_PlayerO -= 1;
                    //    label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                    point_PlayerX += 1;
                    checkPoint();
                    label.setText(saveNamePlayer1 + " " + point_PlayerX + " vs " + point_PlayerO + " " + saveNamePlayer2);
                    JOptionPane.showMessageDialog(null, saveNamePlayer1 + " " + " Wins ", "Title", JOptionPane.ERROR_MESSAGE);
                    for (int i = 0; i < row; i++) {
                        for (int j = 0; j < column; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                } else if (count == 1) {
                    textField.setText(saveNamePlayer2 + " - O turn " + "(You have " + count + " second)");
                    count--;
                    playerX_turn = false; //
                    playerO_turn = true; //
                } else {
                    textField.setText(saveNamePlayer2 + " - O turn " + "(You have " + count + " seconds)");
                    count--;
                    playerX_turn = false; //
                    playerO_turn = true; //
                }
            }
        };
        stopWach = new Timer(delay, actionListener);
        stopWach.setInitialDelay(3);
        stopWach.start();
        count = countPassed;
    }

    public void systemCount() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == buttons[finalI][finalJ]) {
                            countSystem1 = finalI;
                            countSystem2 = finalJ;
                            arrayList.add(countSystem1);
                            arrayList.add(countSystem2);
                        }
                    }
                });
            }
        }
    }

    public void setButtonUndo() {
        buttonUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.reverse(arrayList);
                if (textField.getText().contains(saveNamePlayer1 + " - X turn ")) {
                    if (undoCheckOfPlayerO == true) {
                        undoCheckOfPlayerO = false;
                        int index0 = arrayList.get(1); // row
                        int index1 = arrayList.get(0); // column
                        out.println("O will be undo soon");
                        out.println(index0 + " " + index1);
                        buttons[index0][index1].setText("");
                        arrayList.remove(0);
                        arrayList.remove(0);

                        //important
                        stopWach.stop();
                        setStartTimerOfPlayerO(10);
                    } else if (undoCheckOfPlayerO == false) {
                        JOptionPane.showMessageDialog(null, saveNamePlayer2 + " " + " , you can't undo twice in a match !", "Title", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (textField.getText().contains(saveNamePlayer2 + " - O turn ")) {
                    if (undoCheckOfPlayerX == true) {
                        undoCheckOfPlayerX = false;
                        int index0 = arrayList.get(1); // row
                        int index1 = arrayList.get(0); // column
                        out.println("X will be undo soon");
                        out.println(index0 + " " + index1);
                        buttons[index0][index1].setText("");
                        arrayList.remove(0);
                        arrayList.remove(0);

                        //importtant
                        stopWach.stop();
                        setStartTimerOfPlayerX(10);
                    } else if (undoCheckOfPlayerX == false) {
                        JOptionPane.showMessageDialog(null, saveNamePlayer1 + " " + " , you can't undo twice in a match !", "Title", JOptionPane.ERROR_MESSAGE);
                    }
                }
                Collections.reverse(arrayList);
            }
        });
    }
}
