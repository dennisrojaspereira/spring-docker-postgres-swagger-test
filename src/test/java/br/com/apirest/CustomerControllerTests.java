package br.com.apirest;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import br.com.apirest.controller.CustomerController;
import br.com.apirest.model.Customer;
import br.com.apirest.repository.CustomerRepository;
import br.com.apirest.service.CustomerService;
import io.restassured.http.ContentType;

@ActiveProfiles("test")
@WebMvcTest
public class CustomerControllerTests {

	@Autowired
	private CustomerController customerController;
	
	@MockBean
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.customerController);
	}
	
	@Test
	public void testRetrieveSuccess_WhenFindCustomer() {

		when(this.customerRepository.findById(1L))
		.thenReturn(Optional.of(new Customer(1L, "Customer Name ", "123456789","Customer Address")));
		
		when(this.customerService.getById(1L))
			.thenReturn(new Customer(1L, "Customer Name ", "123456789","Customer Address"));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/customers/{id}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testRetrieveNotFound() {
		
		when(this.customerService.getById(5L))
			.thenReturn(null);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/customers/{id}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
}