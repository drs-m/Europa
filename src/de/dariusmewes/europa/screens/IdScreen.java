/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import de.dariusmewes.europa.Frame;

public final class IdScreen implements Screen {

	JTextField input = new JTextField();

	@Override
	public void display(JFrame frame) {
		int width = 240;
		int height = 45;
		input.setBounds((Frame.width - width) / 2, (Frame.height - height) / 2, width, height);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				input_actionPerformed(event);
			}
		});
		input.setText("Bitte gib die Ländernummer ein...");
		frame.getContentPane().add(input);
		input.requestFocus();
		input.selectAll();
	}

	private void input_actionPerformed(ActionEvent event) {
		try {
			int n = Integer.valueOf(input.getText());
			if (n < 1 || n > 16) {
				JOptionPane.showMessageDialog(null, "Die Nummer muss zwischen 1 und 16 liegen!");
				return;
			}
			Frame.lvlNumber = n;
			Frame.getInstance().switchScreen(new DifficultyScreen());
		} catch (Exception e) {
			return;
		}
	}

}