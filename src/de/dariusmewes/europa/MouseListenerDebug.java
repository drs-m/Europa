/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public final class MouseListenerDebug implements MouseListener {

	private int lastX;
	private int lastY;

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		System.out.println((lastX = event.getX()) + " + " + (lastY = event.getY()));
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		System.out.println((event.getX() - lastX) + " + " + (event.getY() - lastY));
	}

}