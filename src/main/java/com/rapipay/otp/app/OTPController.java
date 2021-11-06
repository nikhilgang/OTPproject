package com.rapipay.otp.app;

import com.rapipay.otp.app.Services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
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
