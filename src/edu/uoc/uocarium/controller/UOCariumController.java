package edu.uoc.uocarium.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import edu.uoc.uocarium.model.Animal;
import edu.uoc.uocarium.model.AnimalException;
import edu.uoc.uocarium.model.AnimalStatus;
import edu.uoc.uocarium.model.Corydoras;
import edu.uoc.uocarium.model.Danio;
import edu.uoc.uocarium.model.Fish;
import edu.uoc.uocarium.model.Food;
import edu.uoc.uocarium.model.Gender;
import edu.uoc.uocarium.model.Item;
import edu.uoc.uocarium.model.ItemException;
import edu.uoc.uocarium.model.Keeper;
import edu.uoc.uocarium.model.Movable;
import edu.uoc.uocarium.model.MovableException;
import edu.uoc.uocarium.model.SubmarineToy;
import edu.uoc.uocarium.model.Tank;
import edu.uoc.uocarium.model.TankException;
import edu.uoc.uocarium.model.Tetra;

public class UOCariumController {

	Database database;
	String tankSelected;
	
	public UOCariumController(String folderName) throws ItemException {
		database = new Database(folderName);
		tankSelected = (database.getTanks().size()!=0)? database.getTanks().get(0).getId():null;
	}
		
	public String getTankSelected() {
		return tankSelected;
	}
	
	public void setTankSelected(String tankSelected) {
		this.tankSelected = tankSelected;
	}

	public List<Tank> getTanks(){
		List<Tank> tanks = database.getTanks();
		tanks.sort(Comparator.comparing(Tank::getId));
		return tanks;
	}
	
	public Tank getTank(String id) {
		return database.getTank(id);
	}
	
	public List<Item> getMovableItems(){
		List<Item> movItems = database.getItems(); //se crea una list con la que trabajar
		movItems.stream().filter(Item -> Item instanceof Movable);//escoge solo los items que sean movable
		return movItems;//devuelve la list con solo items movable
	}
	
	public List<Item> getItems(){		
		return database.getTank(getTankSelected()).getItems();
	}
	
	public void addFish() throws AnimalException, TankException, ItemException, MovableException{
		int fishDecision = new Random().nextInt(10);//valor de decisión para el tipo de pez entre 0 y 9
		Gender gender;
		if(new Random().nextBoolean()) {//decision sobre el género
			gender = Gender.MALE;
		} else {
			gender = Gender.FEMALE;
		}
		if(fishDecision<3) {//Danio
			getTank(getTankSelected()).addItem(new Danio(new Random().nextInt(301), new Random().nextInt(301), gender, 0, 100, null));
		}
		else if(fishDecision<6) {//Tetra
			getTank(getTankSelected()).addItem(new Tetra(new Random().nextInt(301), new Random().nextInt(301), gender, 0, 100, null));
		} else {//Corydoras
			getTank(getTankSelected()).addItem(new Corydoras(new Random().nextInt(301), new Random().nextInt(301), gender, 0, 100, null));
		}
	}
	
	public SubmarineToy getSubmarineToy() {
		
		Optional<Item> op = database.getTank(getTankSelected()).getItems().stream().filter(p -> p instanceof SubmarineToy).findFirst();
		
		return op.isEmpty()?null:(SubmarineToy) op.get();			
				
	}
	
	public boolean isSubmarineToy() {
		return getSubmarineToy() != null;
	}
	
	public void toggleSubmarineToy() throws TankException, ItemException, MovableException{
		if(isSubmarineToy()) {//si ya hay uno, lo borra
			getTank(getTankSelected()).removeItem(getSubmarineToy());
		} else {//si no hay en el tank seleccionado, crea uno y lo añade al tank
			getTank(getTankSelected()).addItem(new SubmarineToy(50, 50, 100, 50, null));
		}
	}
	
	public String getTankInfo() {
		return getTank(getTankSelected()).toString();
	}
	
	public List<Item> removeDeadItems(){
		List<Item> deadItems = new ArrayList<Item>();
		//crea una list a la que añade los items con status dead (que solo pueden ser Animal)
		for(Item item : getItems()) {
			if(item instanceof Animal && ((Animal)item).getStatus() == AnimalStatus.DEAD) {
				deadItems.add(item);
			}
		}
		//hace uso del método propio de tank para borrar los items dead
		getTank(getTankSelected()).removeDeadItems();
		//devuelve una list con los items dead
		return deadItems;
	}
	
	public void feed() throws Exception {
		new Food((new Random()).nextInt(Movable.TANK_PANE_WIDTH-10),0,1,1,5,getTank(getTankSelected()));	
		
	}
	
	public List<Keeper> getKeepers(){
		return database.getKeepers();
	}
}
