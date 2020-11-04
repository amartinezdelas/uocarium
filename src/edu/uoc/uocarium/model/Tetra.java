package edu.uoc.uocarium.model;

public class Tetra extends Fish {
	
	private int oxygenConsumption = 10;

	public Tetra(double xCoord, double yCoord, Gender gender, int age,
			int energy, Tank tank)
			throws AnimalException, ItemException, MovableException {
		super(xCoord, yCoord, "./images/tetra/tetra", 64, 64, gender, age, 0.5, 0.3, 0.002,
				Color.RED, energy, tank);
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
		return super.toString()+" : Tetra\n";
	}
}
