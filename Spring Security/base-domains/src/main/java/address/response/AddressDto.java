package address.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
	
	private Long id;
	
	private Long userId;
	
	private String cp;
	
	private String street;
	
	private String houseNumber;

}