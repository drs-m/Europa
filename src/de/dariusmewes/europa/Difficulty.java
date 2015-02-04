/*
 *  Copyright:
 *  2013 Darius Mewes
 */

package de.dariusmewes.europa;

public enum Difficulty {

	EASY(1), MIDDLE(2), HARD(3);

	private int diffNo;

	private Difficulty(int diffNo) {
		this.diffNo = diffNo;
	}

	public int toInt() {
		return this.diffNo;
	}

}