package gyub.cafekiosk.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gyub.cafekiosk.unit.beverages.Americano;

class CafeKioskTest {

	@Test
	void add() {
		//given
		CafeKiosk cafeKiosk = new CafeKiosk();
		//when
		cafeKiosk.add(new Americano());
		//then
		System.out.println(cafeKiosk.getBeverages().size());
		System.out.println(cafeKiosk.getBeverages().get(0).getName());
	}
}