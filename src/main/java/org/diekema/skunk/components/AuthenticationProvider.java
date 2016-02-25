package org.diekema.skunk.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by rdiekema on 2/24/16.
 */

@Component
public class AuthenticationProvider
{
   Logger LOG = Logger.getLogger(AuthenticationProvider.class);

	private static final String loginRequest = "{\n" +
			"  \"loginTokens\": [\n" +
			"   {\n" +
			"      \"key\": \"com.bottomline.security.provider.login.user\",\n" +
			"      \"value\": \"jdoe\"\n" +
			"   },\n" +
			"   {\n" +
			"      \"key\": \"com.bottomline.security.provider.login.password\",\n" +
			"      \"value\": \"jdoe\"\n" +
			"   },\n" +
			"   {\n" +
			"      \"key\": \"com.bottomline.security.provider.login.realm\",\n" +
			"      \"value\": \"demo\"\n" +
			"   }\n" +
			"  ],\n" +
			"  \"apiVersion\": {\n" +
			"        \"major\": \"1\",\n" +
			"        \"minor\": \"1\",\n" +
			"        \"patch\": \"0\",\n" +
			"        \"build\": \"0\"\n" +
			"   },\n" +
			"  \"purpose\": \"scoring\"\n" +
			"}";
	private String ENDPOINT = "http://localhost";
	private CountDownLatch authenticated = new CountDownLatch(1);
	private AtomicReference<HttpHeaders> headers = new AtomicReference<>(new HttpHeaders());
	final RateLimiter rateLimiter = RateLimiter.create(1);

	public AuthenticationProvider()
	{
	}

	public synchronized void authenticate() throws IOException
	{
		authenticated = new CountDownLatch(1);
		while(true)
		{
			rateLimiter.acquire();
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper objectMapper = new ObjectMapper();
			ApiLogin apiLogin = objectMapper.readValue(loginRequest, ApiLogin.class);

			HttpEntity httpEntity = new HttpEntity(null);
			try
			{
				ResponseEntity<Handshake> handshakeResponseEntity = restTemplate.exchange(ENDPOINT + ApiEndpoints.HANDSHAKE, HttpMethod.GET, httpEntity, Handshake.class);
				HttpHeaders responseHeaders = handshakeResponseEntity.getHeaders();
				List<String> setCookie = responseHeaders.get(AuthenticationHeaders.SET_COOKIE);
				List<String> csrf = responseHeaders.get(AuthenticationHeaders.CSRF);

				headers.get().setContentType(MediaType.APPLICATION_JSON);
				headers.get().set(AuthenticationHeaders.CSRF, csrf.get(0));
				headers.get().set(AuthenticationHeaders.COOKIE, setCookie.get(0));

				HttpEntity<ApiLogin> apiLoginHttpEntity = new HttpEntity<>(apiLogin, headers.get());
				ResponseEntity<String> responseEntity = restTemplate.exchange(ENDPOINT + ApiEndpoints.LOGIN, HttpMethod.POST, apiLoginHttpEntity, String.class);
				assert (responseEntity.getStatusCode().is2xxSuccessful());
				headers.get().set(AuthenticationHeaders.COOKIE, responseEntity.getHeaders().get(AuthenticationHeaders.SET_COOKIE).get(0));
				headers.get().set(AuthenticationHeaders.CSRF, responseEntity.getHeaders().get(AuthenticationHeaders.CSRF).get(0));
				authenticated.countDown();
				break;
			}
			catch (RestClientException e)
			{
			   LOG.error(e.getMessage());
			}
		}
	}

	public String getENDPOINT()
	{
		return ENDPOINT;
	}

	public void setENDPOINT(String ENDPOINT)
	{
		this.ENDPOINT = ENDPOINT;
	}

	public HttpHeaders getHeaders() throws InterruptedException
	{
		authenticated.await();
		return headers.get();
	}

	public interface AuthenticationHeaders
	{
		final static String CSRF = "X-Csrf";
		final static String SET_COOKIE = "Set-Cookie";
		final static String COOKIE = "Cookie";
	}
}


