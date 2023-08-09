package authenticationService;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@CrossOrigin
@RequestMapping("/account")
@RestController
public class TokenApi {
	
//	JWTUtil jwtUtil = new JWTHelper();
	
	@GetMapping("/token")
	public String getString() {
		return "GET for token";
	}
	
	@CrossOrigin
	@PostMapping("/token")
	public <T> ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer) {
		//check the name
		//name is valid, check the password
		//If they both pass, then generate a java JWT token.
		//send it off to the client
		RestTemplate rt = new RestTemplate();
		CustomerList customers = rt.getForObject(
				"http://localhost:8080/api/customerlist", 
				CustomerList.class);
		System.out.println(customers.toString());
		
				for (Customer c : customers.getCustomers()) {
			System.out.println(c.getName());
			System.out.println(c.getPassword());
			
			if (customer.getName().equals(c.getName())) {
				if (customer.getPassword().equals(c.getPassword())) {
					System.out.println("password and username match.");
					long fiveHousrInMillis = 1000 * 60 * 60 * 5;
					Date expireDate = new Date(System.currentTimeMillis() + fiveHousrInMillis);
					String tokenString = JWT.create()
							.withSubject("apiuser")
							.withIssuer("hyen@gmail.com")
							.withClaim("scopes", "*")
							.withExpiresAt(expireDate)
							.sign(Algorithm.HMAC256("secret"));
					tokenString = "Bearer " + tokenString + "comeonin";
					System.out.println(tokenString);
					
					ResponseEntity<?> response = ResponseEntity.ok(new Token(tokenString));
					return response;

				}
			}
		}
		
		
		//bad request
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				

	}

}
