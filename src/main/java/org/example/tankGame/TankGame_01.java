package org.example.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame_01 extends JFrame {

    MyPanel mp = null;
    AePlayWave aePlayWave = new AePlayWave("src\\7301.wav");
    static Scanner scanner = new Scanner(System.in);
    String key;

    public static void main(String[] args) {
        TankGame_01 tankGame01 = new TankGame_01();

    }
    public TankGame_01() {
        if (Record.isNullFile()) {
            key = "1";
        } else {
            System.out.println("RULE\n Use arrow key to control your tank.\n " +
                    "Use key 'j' to shot the enemy tank.\n" +
                    " Be careful do not attack by the enemy tank.\n" +
                    " If the tank cannot move, please click the gaming area first.\n" +
                    " If the 'J' key is not function please make sure your keyboard mode is in English.\n\n ");
            System.out.println("Please enter \n 1: if want to new a game\n 2: if want to continue the previous game\n Your choice: ");
            key = scanner.next();
        }
        aePlayWave.play();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300, 950);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("CLoseeeeeeeee");
                Record.keepRecord();
                System.exit(0);
            }
        });

    }
}
