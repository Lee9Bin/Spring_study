package gyub.cafekiosk.unit.order;

import java.time.LocalDateTime;
import java.util.List;

import gyub.cafekiosk.unit.beverages.Beverage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Order {

	private final LocalDateTime createTime;
	private final List<Beverage> beverages;
}
