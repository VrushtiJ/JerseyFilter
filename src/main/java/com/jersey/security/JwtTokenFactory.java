package com.jersey.security;

import java.io.UnsupportedEncodingException;

import javax.security.sasl.AuthenticationException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jersey.entities.core.UserModel;

/**
 * Factory class that should be always used to create {@link JwtToken}.
 * 
 * @author vladimir.stankovic
 *
 * May 31, 2016
 */
@Component
public class JwtTokenFactory {


	public JwtTokenFactory() {
		super();
	}

	/**
	 * Factory method for issuing new JW;T Tokens.
	 * 
	 * @param username
	 * @param roles
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalArgumentException 
	 */
	public String signJwtToken(UserModel userModel, String idToken, String expiresOn,String notBefore) throws IllegalArgumentException, UnsupportedEncodingException {

		Algorithm algorithm = Algorithm.HMAC512("anthonyTamato");

		String signedJwttoken = JWT.create().withSubject(userModel.getUserEmail())
				.withClaim("expires_on", expiresOn)
				.withClaim("not_before", notBefore)
				.withClaim("id_token", idToken)
				.sign(algorithm);

		return signedJwttoken;
	}

	public boolean isTokenValid(String signedToken, String userEmail) throws AuthenticationException, JWTDecodeException {

		Boolean isTokenValid = false;
		try{
			Algorithm algorithm = Algorithm.HMAC512("anthonyTamato");
			JWTVerifier verifier = JWT.require(algorithm).withSubject(userEmail).build();

			DecodedJWT jwt = verifier.verify(signedToken);
			isTokenValid = true;
		}
		catch(JWTVerificationException | IllegalArgumentException | UnsupportedEncodingException exception){
			System.out.println("Invalid Token!!");
		}

		return isTokenValid;
	}

}
