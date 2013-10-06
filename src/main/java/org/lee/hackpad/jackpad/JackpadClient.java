package org.lee.hackpad.jackpad;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.lee.hackpad.jackpad.content.Pad;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;

/**
 * This is the base JackpadClient that is used to make requests to the Hackpad
 * API.
 * @author Lee
 */
public class JackpadClient 
{
	private String HACKPAD_CLIENT_ID;
	private String HACKPAD_SECRET;
	
	private ClientConfig hackpadConfig;
	private Client client;
	private Gson gson;
	
	public JackpadClient()
	{
		setClientID("");
		setSecret("");
	}
	
	/**
     * Instantiate a new Jackpad Client with Client ID and Secret
     * @param clientID The Hackpad's Client ID 
     * @param secret The Hackpad's Secret
     */
	public JackpadClient(String clientID, String secret)
	{
		setClientID(clientID);
		setSecret(secret);
	}
	
	/**
	 * Build Connection
	 * */
	public void build()
	{
		gson = new GsonBuilder().enableComplexMapKeySerialization().create();  
		hackpadConfig = new DefaultClientConfig();
		client = Client.create(hackpadConfig);
		OAuthClientFilter filter = new OAuthClientFilter(
				client.getProviders(), 
				new OAuthParameters().consumerKey(HACKPAD_CLIENT_ID), 
				new OAuthSecrets().consumerSecret(HACKPAD_SECRET));		
		client.addFilter(filter);
	}
	
	public void createPad(Pad pad)
	{
		WebResource servicePOST = client.resource(UriBuilder.fromUri("https://g0v.hackpad.com/api/1.0/pad/create").build());			
		String json = servicePOST.accept(MediaType.APPLICATION_JSON).header("Content-Type", "text/plain").post(String.class, pad.getTitle() + '\n' + pad.getContent());
		System.out.println(json);
		Map<String, String> retMap = gson.fromJson(json,  
                new TypeToken<Map<String, String>>() {}.getType());  
		System.out.println(retMap.get("padId").toString());
		pad.setPadID(retMap.get("padId").toString());
	}
	
	/**
	 *Set Hackpad Client ID
	 *@param clientID The Hackpad's Client ID 
	 * */
	public void setClientID(String clientID)
	{
		this.HACKPAD_CLIENT_ID = clientID;
	}
	
	/**
	 * Set Hackpad Secret
	 * @param secret The Hackpad's Secret
	 * */
	public void setSecret(String secret)
	{
		this.HACKPAD_SECRET = secret;
	}
	
}
