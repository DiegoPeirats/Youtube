package order.request;

import address.request.AddressCreationRequest;
import lombok.Getter;

@Getter
public class OrderCreationRequest {
	
	private Long productId;
	
	private Integer amount;
	
	private AddressCreationRequest address;

}
