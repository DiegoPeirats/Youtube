package order.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import order.ProductType;
import user.response.AddressDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	private Long userId;
	
	private Integer amount;
	
	private Double price;
	
	private ProductType product;
	
	private AddressDto address;

}
