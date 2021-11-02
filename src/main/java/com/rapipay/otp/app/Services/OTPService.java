package com.rapipay.otp.app.Services;

import java.util.Date;
import java.util.List;

import com.rapipay.otp.app.OTP;
import com.rapipay.otp.app.OTPRepository;
import com.rapipay.otp.app.OTPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    public OTPRepository dao;

    @Autowired
    public EmailService emailService;

    public Integer generateOTP() {
        return (int) ((Math.random() * 900000) + 100000);
    }
    
    public OTP sendOTP(String email) {
        OTP otp = new OTP();
        otp.setEmail(email);
        otp.setOtp(generateOTP());
        otp.setVerified(false);
        otp.setCreated_at(new Date().getTime() /1000);
        sendOTPMail(otp);
        return dao.saveAndFlush(otp);
    }

    public OTPResponse verifyOTP(OTP otp) {
        OTPResponse response = new OTPResponse();
        List<OTP> otps = dao.findByEmailAndOtpAndVerified(otp.getEmail(),otp.getOtp(),false);
        if (otps == null || otps.size() == 0) {
            response.setMessage("OTP verification failed");
                response.setStatus(false);
                return response;
        }

        for(OTP o : otps) {
                if (o.getCreated_at() > (new Date().getTime() / 1000) - 300) {
                    o.setVerified(true);
                    dao.save(o);
                    response.setMessage("OTP verified successfully");
                    response.setStatus(true);
                    return response;
                }
            }
            response.setMessage("This OTP has expired. Please request a new one");
            response.setStatus(false);
            return response;
        }

        public void sendOTPMail(OTP otp) {
            emailService.sendSimpleMessage(otp.getEmail(),"OTP for email verification","The otp for verifying your email address is: "+otp.getOtp());

        }
    }
