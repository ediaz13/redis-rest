package ar.com.santander.mobile.backend.individual.controller;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ar.com.santander.mobile.backend.individual.dto.AuthenticationRequest;
import ar.com.santander.mobile.backend.individual.dto.AuthenticationResponse;
import ar.com.santander.mobile.backend.individual.helper.JwtHelper;


@SpringBootTest
@WebMvcTest
public class LoginControllerTest {
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	AuthenticationManager authenticationManager;
	@Mock
	UserDetailsService userDetailsService;
	@Mock
	JwtHelper jwtHelper;
	
	@Mock
	UserDetails userDetails;
	
	@Mock
	HttpServletRequest request;
	
	@Test
	@WithMockUser
	public void testLogin() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		
		authenticationRequest.setPassword("1234");
		authenticationRequest.setUsername("demo");

		when(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).
			thenReturn(userDetails);
		
		ResponseEntity<AuthenticationResponse> responseEntity = loginController.login(authenticationRequest);
		
		
	}
	

}
