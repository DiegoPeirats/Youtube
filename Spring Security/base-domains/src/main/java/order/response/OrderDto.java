package order.response;


import address.response.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	private Long userId;
	
	private Integer amount;
	
	private Double price;
	
	private String product;
	
	private AddressDto address;

}
