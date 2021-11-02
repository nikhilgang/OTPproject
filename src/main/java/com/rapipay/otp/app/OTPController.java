package com.rapipay.otp.app;

import com.rapipay.otp.app.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OTPController {

    @Autowired
    OTPService otpService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "OTP api up and running";
    }

    @RequestMapping(method =RequestMethod.POST,value ="/sendOTP")
    public String sendOTP(@RequestBody OTP otp){
        otpService.sendOTP(otp.getEmail());
        return "OTP sent successfully";
    }

    @RequestMapping(method =RequestMethod.POST,value ="/verifyOTP")
    public OTPResponse verifyOTP(@RequestBody OTP otp){
        return otpService.verifyOTP(otp);
    }
    
}
