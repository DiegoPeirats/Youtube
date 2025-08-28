package user.requests;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
	
	private Long id;

	private String name;
	
	private String surname;
	
	private LocalDate birthday;
	


}

