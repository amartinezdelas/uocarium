package edu.uoc.uocarium.model;

public class Danio extends Fish {
	
	private int oxygenConsumption = 12;

	public Danio(double xCoord, double yCoord, Gender gender, int age,
			int energy, Tank tank)
			throws AnimalException, ItemException, MovableException {
		super(xCoord, yCoord, "./images/danio/danio", 64, 64, gender, age, 1, 0.2, 0.002,
				Color.BRONZE, energy, tank);
	}

	@Override
	public int getOxygenConsumption() {
		return oxygenConsumption;
	}

	@Override
	public void breathe() {
		// TODO
	}
	
	@Override
	public String toString() {
		return super.toString()+" : Danio\n";
	}
}
