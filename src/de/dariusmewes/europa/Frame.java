/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.UIManager;

import de.dariusmewes.europa.screens.GameScreen;
import de.dariusmewes.europa.screens.IdScreen;
import de.dariusmewes.europa.screens.Screen;

public final class Frame extends JFrame {

	public static final long serialVersionUID = 1L;
	public static int lvlNumber;
	public static Difficulty diff;

	public static final int height = 1000;
	public static final int width = 1000;

	public static final Map<String, String> solutions = new HashMap<String, String>();
	public static final Map<String, Integer> limits = new HashMap<String, Integer>();
	public static final Map<String, String> suffixes = new HashMap<String, String>();

	public static boolean debug = true;
	public static Map<String, String> debugList;

	private static Frame main;

	public Frame() {
		super("Euromania");
		main = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int frameWidth = width;
		int frameHeight = height;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		getContentPane().setLayout(null);

		addMouseListener(new MouseListenerDebug());
		loadData();
		lvlNumber = 1;
		diff = Difficulty.EASY;
		if (debug)
			switchScreen(new GameScreen());
		else
			switchScreen(new IdScreen());
		setVisible(true);
	}

	public void switchScreen(Screen s) {
		getContentPane().removeAll();
		s.display(this);
		repaint();
	}

	private void loadData() {
		try {
			if (debug) {
				debugList = new HashMap<String, String>();
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/data.txt"), "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.equalsIgnoreCase("") || line.startsWith("#"))
						continue;

					String data[] = line.split("=");
					String suffix = ".jpg";
					if (data.length == 3)
						suffix = "." + data[2].toLowerCase();

					debugList.put(data[0] + suffix, data[1]);
				}
				reader.close();

				System.out.println("Größe: " + debugList.size());
				Iterator<String> iter = debugList.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					String value = debugList.get(key);
					System.out.println(key + ", " + value);
				}
			} else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/data.txt"), "UTF-8"));
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.equalsIgnoreCase("") || line.startsWith("#"))
						continue;

					String data[] = line.split("=");
					String suffix = ".jpg";
					if (data.length == 3)
						suffix = "." + data[2].toLowerCase();

					solutions.put(data[0], data[1]);
					limits.put(data[0].split("-")[0], Integer.valueOf(data[0].split("-")[1]));
					suffixes.put(data[0], suffix);
				}
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Frame getInstance() {
		return main;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		new Frame();
	}

}