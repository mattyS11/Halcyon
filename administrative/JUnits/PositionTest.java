package administrative.JUnits;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import components.Position;
import gameObjects.Entity;
import gameObjects.Player;

public class PositionTest {

	@Test
	public void test_constructors_never_negative() {
		Position p = new Position(-45, -2, -1);
		assertEquals(0, p.getRoomIndex());
		assertEquals(0, p.getPosX());
		assertEquals(0, p.getPosY());
	}
	
	@Test
	public void testConstructor_all_zeros() {
		Position p = new Position(0,0,0);
		assertEquals(0,p.getRoomIndex());
		assertEquals(0,p.getPosX());
		assertEquals(0,p.getPosY());
	}
	
	@Test
	public void test_copy_constructor() {
		Position p = new Position(1,2,3);
		Position p_copy = new Position(p);
		assertEquals(1, p_copy.getRoomIndex());
		assertEquals(2, p_copy.getPosX());
		assertEquals(3, p_copy.getPosY());
		
		p_copy.setPosX(10);
		assertEquals(10, p_copy.getPosX());
		
	}
	
	@Test
	public void test_setters_are_always_positive_or_0() {
		Position p = new Position(1, 13, 2);
		p.setPosX(-1); 
		assertEquals(13, p.getPosX());

		p.setPosY(-20);
		assertEquals(2, p.getPosY());

		p.setRoomIndex(-3);
		assertEquals(1, p.getRoomIndex());
		
		p.setRoomIndex(0);
		assertEquals(0, p.getRoomIndex());
	}
	
	@Test
	public void test_playerdistance_to_upperleft_corner() {
		Position p1 = new Position(1,0,0); // Upper left corner used as reference 
        Entity hero = Player.getPlayer();  // Player's position is default spawn position (64, 96)
		assertEquals((int) Math.sqrt(64*64 + 96*96), (int) p1.distanceTo(hero)); // convert to int to avoid rounding errors
		
		Position p2 = new Position(1,3,4);
		hero.setPosition(p2);			  // Reset the player's position to (3,4)
		assertEquals((int) 5, (int) p1.distanceTo(hero)); // because sqrt(3^2 + 4^4) = 5
		
	}

}
