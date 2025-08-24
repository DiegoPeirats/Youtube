package example.order.response;

import example.order.ProductType;
import example.user.response.AddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Long userId;
	
	private ProductType productType;
	
	private Integer amount;
	
	private Double price;
	
	private AddressDto address;

}
