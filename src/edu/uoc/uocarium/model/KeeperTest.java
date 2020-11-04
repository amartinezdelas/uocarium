package edu.uoc.uocarium.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class KeeperTest {

	@Test
	void testSetId() throws KeeperException {
		//se crea un Keeper cualquiera
		Keeper instance = new Keeper("G0001", "Ángel", "Martínez");
		assertEquals("G0001",instance.getId());
		//primera comprobación de la asignación de id
		instance.setId("G1212");
		assertEquals("G1212",instance.getId());
		//se añade un id null
		NullPointerException nullException = assertThrows(NullPointerException.class, () -> instance.setId(null));
		assertEquals(null, nullException.getMessage());
		//se añade un id que no empieza por G
		KeeperException gException = assertThrows(KeeperException.class, () -> instance.setId("T0101"));
		assertEquals("[ERROR] A keeper's id must start with letter 'G'!!", gException.getMessage());
		//se añade un id que no tiene 5 carácteres
		KeeperException fiveException = assertThrows(KeeperException.class, () -> instance.setId("G121"));
		assertEquals("[ERROR] A keeper's id must have 5 characters!!", fiveException.getMessage());
		
		//se añade un id null en el constructor
		NullPointerException nullConstructorException = assertThrows(NullPointerException.class, () -> new Keeper(null, "Ángel", "Martínez"));
		assertEquals(null, nullConstructorException.getMessage());
		//se añade un id que no empieza por G en el constructor
		KeeperException gConstructorException = assertThrows(KeeperException.class, () -> new Keeper("g0001", "Ángel", "Martínez"));
		assertEquals("[ERROR] A keeper's id must start with letter 'G'!!", gConstructorException.getMessage());
		//se añade un id que no tiene 5 carácteres en el constructor
		KeeperException fiveConstructorException = assertThrows(KeeperException.class, () -> new Keeper("G131", "Ángel", "Martínez"));
		assertEquals("[ERROR] A keeper's id must have 5 characters!!", fiveConstructorException.getMessage());
	}

	@Test
	void testAddTank() throws KeeperException, TankException {
		//se crea un Keeper cualquiera
		Keeper instance = new Keeper("G0001", "Ángel", "Martínez");
		//se crean 5 tanques y una arraylist auxiliar para comparar
		Tank tank1 = new Tank();
		Tank tank2 = new Tank();
		Tank tank3 = new Tank();
		Tank tank4 = new Tank();
		Tank tank5 = new Tank();
		ArrayList<Tank> tanks = new ArrayList<Tank>();
		//se van añadiendo los tanques tanto a Keeper como a la arraylist auxiliar y se comprueba
		instance.addTank(tank1);
		tanks.add(tank1);
		assertEquals(tanks,instance.getTanks());
		instance.addTank(tank2);
		tanks.add(tank2);
		assertEquals(tanks,instance.getTanks());
		instance.addTank(tank1);//se comprueba que no añade un tank repetido
		assertEquals(tanks,instance.getTanks());
		instance.addTank(tank3);
		tanks.add(tank3);
		assertEquals(tanks,instance.getTanks());
		instance.addTank(tank4);
		tanks.add(tank4);
		assertEquals(tanks,instance.getTanks());
		instance.addTank(tank5);
		tanks.add(tank5);
		assertEquals(tanks,instance.getTanks());
		//se comprueba que no se puede añadir el sexto tank y su excepción
		KeeperException exception = assertThrows(KeeperException.class, () -> instance.addTank(new Tank()));
		assertEquals("[ERROR] A keeper cannot take care of more than 5 tanks!!", exception.getMessage());
	}

}
