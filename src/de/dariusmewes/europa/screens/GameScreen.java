/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import de.dariusmewes.europa.Frame;

public final class GameScreen implements Screen {

	private static Iterator<String> iter;

	private JLabel lab = new JLabel();
	private JTextField input = new JTextField();
	private JLabel solutionLabel = new JLabel("", SwingConstants.CENTER);
	private String solution;
	private String lastFile;
	private int attempts = 3;

	@Override
	public void display(JFrame frame) {
		try {
			lastFile = getFileName(frame);

			BufferedImage img = ImageIO.read(this.getClass().getResource("/img/" + lastFile));
			lab.setBounds((Frame.width - img.getWidth()) / 2, 20, img.getWidth(), img.getHeight());
			lab.setIcon(new ImageIcon(img));
			frame.getContentPane().add(lab);

			int width = 400;
			int height = 45;
			solutionLabel.setBounds((Frame.width - width) / 2, Frame.height - 110 - 40, width, height);
			solutionLabel.setText(shuffle(solution.replaceAll(" ", "").toUpperCase()));
			frame.getContentPane().add(solutionLabel);

			input.setBounds((Frame.width - width) / 2, Frame.height - 110, width, height);
			input.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					input_actionPerformed(evt);
				}
			});
			frame.getContentPane().add(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void input_actionPerformed(ActionEvent evt) {
		if (input.getText().equalsIgnoreCase(""))
			return;
		if (input.getText().equalsIgnoreCase(solution)) {
			JOptionPane.showMessageDialog(null, "Richtig! Du darfst nun " + Frame.diff.toInt() + " Felder vorrücken!");
			if (Frame.debug)
				Frame.getInstance().switchScreen(new GameScreen());
			else
				Frame.getInstance().switchScreen(new IdScreen());
		} else if (input.getText().equalsIgnoreCase("##!!!##")) {
			JOptionPane.showMessageDialog(null, solution + "\n(" + lastFile + ")");
			if (Frame.debug)
				Frame.getInstance().switchScreen(new GameScreen());
			else
				Frame.getInstance().switchScreen(new IdScreen());
		} else {
			attempts--;
			if (attempts == 0) {
				JOptionPane.showMessageDialog(null, "Du hast 3 mal die falsche Antwort eingegeben.");
				Frame.getInstance().switchScreen(new IdScreen());
				return;
			}
			JOptionPane.showMessageDialog(null, "Das war falsch. Du hast noch " + attempts + " Versuche!");
		}
	}

	private String getFileName(JFrame frame) {
		if (Frame.debug) {
			if (iter == null)
				iter = Frame.debugList.keySet().iterator();

			if (iter.hasNext()) {
				String fileName = iter.next();
				solution = Frame.debugList.get(fileName);
				System.out.println(fileName);
				return fileName;
			} else {
				JOptionPane.showMessageDialog(null, "Fertig!");
				System.exit(0);
			}
		} else {
			String fileName = Frame.lvlNumber + "_" + Frame.diff.toInt();
			fileName += "-" + (new Random().nextInt(Frame.limits.get(fileName)) + 1);
			solution = Frame.solutions.get(fileName);
			fileName += Frame.suffixes.get(fileName);
			System.out.println(fileName);
			return fileName;
		}
		return null;
	}

	private static String shuffle(String word) {
		List<Character> characters = new ArrayList<Character>();
		for (char c : word.toCharArray())
			characters.add(c);

		Collections.shuffle(characters);
		StringBuilder sb = new StringBuilder();
		for (char c : characters)
			sb.append(c);

		return sb.toString();
	}

}