package com.rapipay.otp.app;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OTPRepository extends JpaRepository<OTP, Integer> {

    List<OTP> findByEmailAndOtpAndVerified(String email, Integer otp,boolean verified);
    List<OTP> findByEmailAndVerified(String email,boolean verified);
    List<OTP> findByPhoneNoAndVerified(Long phoneNo,boolean verified);
}