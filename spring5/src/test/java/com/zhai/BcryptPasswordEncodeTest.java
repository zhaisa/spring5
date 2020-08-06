package com.zhai;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncodeTest {
	  public static void main(String[] args) {
	        String pass = "123456";
	        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
	        String hashPass = bcryptPasswordEncoder.encode(pass);
	        System.out.println(hashPass);

	        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
	        System.out.println(f);

	    }
}
