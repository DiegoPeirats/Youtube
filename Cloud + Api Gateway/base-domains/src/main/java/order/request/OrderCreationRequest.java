package order.request;

import address.request.AddressCreationRequest;
import lombok.Getter;
import order.ProductType;

@Getter
public class OrderCreationRequest {
	
	private Long userId;
	
	private Integer amount;
	
	private ProductType product;
	
	private AddressCreationRequest address;

}
