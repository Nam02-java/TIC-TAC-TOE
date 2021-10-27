package SourceCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataUser extends JFrame {
    static String player1, player2, saveNamePlayer1, saveNamePlayer2;
    static JTextField textField1, textField2;
    static JLabel label1, label2;
    static JButton buttonOk;

    DataUser() {
        setTextField1();
        setTextField2();
        setLabel1();
        setLabel2();
        setButtonOk();
        setWindow();
        setActionForButtonOk();
    }

    public void setWindow() {
        add(textField1);
        add(textField2);
        add(label1);
        add(label2);
        add(buttonOk);
        setTitle("Set Name");
        setBounds(300, 350, 800, 420);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setTextField1() {
        textField1 = new JTextField();
        textField1.setBounds(135, 10, 300, 50);
        textField1.setBackground(Color.GRAY);
        textField1.setForeground(Color.WHITE);
        textField1.setFont(new Font("Ink Free", Font.BOLD, 30));
        textField1.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setLabel1() {
        label1 = new JLabel("Player1");
        label1.setBounds(10, 10, 100, 50);
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("Ink Free", Font.BOLD, 25));
    }

    public void setTextField2() {
        textField2 = new JTextField();
        textField2.setBounds(135, 80, 300, 50);
        textField2.setBackground(Color.GRAY);
        textField2.setForeground(Color.WHITE);
        textField2.setFont(new Font("Ink Free", Font.BOLD, 30));
        textField2.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setLabel2() {
        label2 = new JLabel("Player2");
        label2.setBounds(10, 80, 100, 50);
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("Ink Free", Font.BOLD, 25));
    }

    public void setButtonOk() {
        buttonOk = new JButton("Ok");
        buttonOk.setBounds(10, 150, 70, 40);
        buttonOk.setForeground(Color.BLACK);
        buttonOk.setBackground(Color.GRAY);
        buttonOk.setFont(new Font("Ink Free", Font.BOLD, 25));
    }

    public void setActionForButtonOk() {
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() >= 1 && textField2.getText().length() >= 1) {
                    saveNamePlayer1 = textField1.getText();
                    saveNamePlayer2 = textField2.getText();
                    new MyFrame();
                    setVisible(false);
                } else {
                }
            }
        });
    }
}
