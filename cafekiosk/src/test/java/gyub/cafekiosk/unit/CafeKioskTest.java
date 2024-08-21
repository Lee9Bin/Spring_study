package gyub.cafekiosk.unit;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gyub.cafekiosk.unit.beverages.Americano;

class CafeKioskTest {

	@Test
	void add_menual_test() {
		//given
		CafeKiosk cafeKiosk = new CafeKiosk();
		//when
		cafeKiosk.add(new Americano());
		//then
		System.out.println(cafeKiosk.getBeverages().size());
		System.out.println(cafeKiosk.getBeverages().get(0).getName());
	}

	@Test
	void add() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		//when
		cafeKiosk.add(new Americano());

		assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
		assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
	}

	@Test
	void remove() {
		CafeKiosk cafeKiosk = new CafeKiosk();
		Americano americano = new Americano();
		//when
		cafeKiosk.add(americano);

		assertThat(cafeKiosk.getBeverages()).hasSize(1);

		cafeKiosk.remove(americano);
		assertThat(cafeKiosk.getBeverages()).isEmpty();
	}
}