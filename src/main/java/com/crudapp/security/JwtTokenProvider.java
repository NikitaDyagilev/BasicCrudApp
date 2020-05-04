package com.crudapp.security;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crudapp.crudapp.model.Account;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
	// TODO: generate keys, and add to application.properties file
	// TODO: add other properties to application.properties file
	@Value("${security.jwt.token.private-key}")
	private String privateKeyContent;
	@Value("${security.jwt.token.public-key}")
	private String publicKeyContent;
	@Value("${security.jwt.token.expire-length-secs:3600}")
	private Long expireLengthSecs;
	@Value("${security.jwt.token.timeout-max-secs}")
	private Long timeoutMaxSecs;
	@Value("${issuer}")
	private String issuer;

	private Algorithm algorithm;
	private JWTVerifier verifier;

	@Autowired
	private UserDetailsService userDetailsService;

	public Long getExpireLengthSecs() {
		return this.expireLengthSecs;
	}

	public Long getTimeoutMaxSecs() {
		return this.timeoutMaxSecs;
	}

	@PostConstruct
	public void init() throws Exception {
		algorithm = getAlgorithm();
		verifier = JWT.require(algorithm)//
				.withIssuer(this.issuer)//
				.build();
	}

	private RSAPrivateKey getRSAPrivateKey() throws Exception {
		PemReader pemReader = new PemReader(new StringReader(privateKeyContent));
		byte[] pemContent = pemReader.readPemObject().getContent();
		pemReader.close();
		return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(pemContent));
	}

	private RSAPublicKey getRsaPublicKey() throws Exception {
		return (RSAPublicKey) CertificateFactory.getInstance("X.509")
				.generateCertificate(new ByteArrayInputStream(publicKeyContent.getBytes())).getPublicKey();
	}

	private Algorithm getAlgorithm() throws Exception {
		return Algorithm.RSA256(getRsaPublicKey(), getRSAPrivateKey());
	}

	public String createToken(String username, Account acount) {
		LocalDateTime iat = LocalDateTime.now();
		return JWT.create()//
				.withSubject(username)//
				.withIssuer(this.issuer)//
				.withIssuedAt(Date.from(iat.atZone(ZoneId.systemDefault()).toInstant()))//
				.withExpiresAt(Date.from(iat.plusSeconds(this.expireLengthSecs).atZone(ZoneId.systemDefault()).toInstant()))//
				.sign(this.algorithm);
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUserName(String token) {
		return JWT.decode(token).getSubject();
	}

	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("X-Auth-Token");
	}

	public boolean validateToken(String token) {
		DecodedJWT jwt = verifier.verify(token);
		if (jwt.getExpiresAt().before(new Date()))
			return false;
		return true;
	}

}