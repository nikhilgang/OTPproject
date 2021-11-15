package com.rapipay.otp.app.Services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rapipay.otp.app.OTP;
import com.rapipay.otp.app.OTPRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest
class OTPServiceTest {
	
	@Autowired
	private OTPService otpService;
	
	@MockBean
	private OTPRepository dao;

	@Test
	@DisplayName("Validate Email 1")
	void testCheckEmail1() {
		//fail("Not yet implemented");
		OTPService otpService=new OTPService();
	    boolean expected=true;
	    boolean actual=otpService.checkEmail("abc@gmail.com");
	    assertEquals(expected,actual);
	}
	
	@Test
	@DisplayName("Validate Email 2")
	void testCheckEmail2() {
		//fail("Not yet implemented");
		OTPService otpService=new OTPService();
	    boolean expected=false;
	    boolean actual=otpService.checkEmail("abc@gmail.");
	    assertEquals(expected,actual);
	}

	@Test
	void testSendOTP() {
		List<OTP>checkEmail=dao.findByEmailAndVerified("kk4112919@gmail.com", false);
		OTP otp=new OTP(24,"email","kk4112919@gmail.com",false);
		String actual=otpService.sendOTP(otp);
		String expected="successfully";
		assertEquals(expected,actual);
	}

	@Test
	void testVerifyOTP() {
		//fail("Not yet implemented");
		
		OTP otp=new OTP("kk4112919@gmail.com",132089,0,false);
		
		assertThrows(Exception.class, ()->{
			otpService.verifyOTP(otp);
	});
		}
	@Test
	void testSendOTPSMS() {
//		OTPService otpService=new OTPService();
		OTP otp=new OTP(24,"sms","9197630200");

		String actual=otpService.sendOTPSMS(otp);
		String expected="successfully";
		assertEquals(expected,actual);
	}
}