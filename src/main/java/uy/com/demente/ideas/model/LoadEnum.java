package uy.com.demente.ideas.model;

/**
 * @author 1987diegog
 */
public enum LoadEnum {

	ALL(0), //
	PERSONS(1), //
	BOOKS(2); //

	private int type;

	LoadEnum(int type) {

		this.type = type;
	}

	public int getType() {
		return type;
	}
}
