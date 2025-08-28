package user.requests;

import java.time.LocalDate;

import address.request.AddressCreationRequest;
import lombok.Getter;

@Getter
public class UserCreationRequest {

	private String name;
	
	private String surname;
	
	private LocalDate birthday;
	
	private AddressCreationRequest address;

}
