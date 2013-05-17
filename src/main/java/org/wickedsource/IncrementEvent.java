package org.wickedsource;

public class IncrementEvent {

	private final int increment;

	public IncrementEvent(int increment) {
		this.increment = increment;
	}

	public int getIncrement() {
		return increment;
	}

}