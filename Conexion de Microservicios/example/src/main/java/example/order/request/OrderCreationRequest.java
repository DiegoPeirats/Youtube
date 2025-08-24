package example.order.request;

import example.order.ProductType;
import example.user.request.AddressRequest;
import lombok.Getter;

@Getter
public class OrderCreationRequest {
	
	private Long userId;
	
	private ProductType productType;
	
	private Integer amount;
	
	private Double price;
	
	private AddressRequest address;

}
