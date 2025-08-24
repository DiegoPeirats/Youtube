package example.order;

import lombok.Getter;

@Getter
public enum ProductType {
	
	WATER_BOTTLE(1.50),
	COCA_COLA(2.00),
	FANTA(1.80);
	
	private final Double price;
	
	ProductType(Double price) {
		this.price = price;
	}
}
