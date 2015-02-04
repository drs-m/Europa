/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.dariusmewes.europa.Difficulty;
import de.dariusmewes.europa.Frame;

public final class DifficultyScreen implements Screen {

    private JButton easy = new JButton();
    private JButton middle = new JButton();
    private JButton hard = new JButton();

    @Override
    public void display(JFrame frame) {
        easy.setBounds(150, 160, 400, 45);
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                easy_actionPerformed(event);
            }
        });
        easy.setText("Einfach");
        easy.setVisible(true);
        frame.getContentPane().add(easy);

        middle.setBounds(150, 317, 400, 45);
        middle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                middle_actionPerformed(event);
            }
        });
        middle.setText("Mittel");
        middle.setVisible(true);
        frame.getContentPane().add(middle);

        hard.setBounds(150, 468, 400, 45);
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                hard_actionPerformed(event);
            }
        });
        hard.setText("Schwer");
        hard.setVisible(true);
        frame.getContentPane().add(hard);

    }

    private void easy_actionPerformed(ActionEvent event) {
        Frame.diff = Difficulty.EASY;
        Frame.getInstance().switchScreen(new GameScreen());
    }

    private void middle_actionPerformed(ActionEvent event) {
        Frame.diff = Difficulty.MIDDLE;
        Frame.getInstance().switchScreen(new GameScreen());
    }

    private void hard_actionPerformed(ActionEvent event) {
        Frame.diff = Difficulty.HARD;
        Frame.getInstance().switchScreen(new GameScreen());
    }

}