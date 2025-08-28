package address.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreationRequest {
	
	private Long userId;
	
	private String cp;
	
	private String street;
	
	private String houseNumber;

}