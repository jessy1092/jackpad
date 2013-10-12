package org.lee.hackpad.jackpad;

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
	
	/**
	 * Create a pad
	 * @param pad The pad of hackpad 
	 * */
	public String createPad(Pad pad)
	{
		String uri = pad.getUri() + "/pad/create";
		WebResource servicePOST = client.resource(UriBuilder.fromUri(uri).build());		
		String json = servicePOST.accept(MediaType.APPLICATION_JSON).header("Content-Type", pad.getContentType()).post(String.class, pad.getTitle() + '\n' + pad.getContent());
//		System.out.println(json);
		Map<String, String> retMap = gson.fromJson(json,  
                new TypeToken<Map<String, String>>() {}.getType());  
//		System.out.println(retMap.get("padId").toString());
		return retMap.get("padId").toString();
	}
	
	/**
	 * Get the pad's content
	 * @param site The site of the pad
	 * @param padID The ID of the pad
	 * @param revision Revision number or "latest"
	 * @param format File type requested ("txt", "html", or "md")
	 * */
	public String getPadContent(String site, String padID, String revision, String format)
	{
		String uri = String.format("https://%s.hackpad.com/api/1.0/pad/%s/content/%s.%s", site, padID, revision, format);
		WebResource serviceGET = client.resource(UriBuilder.fromUri(uri).build());
		return serviceGET.accept(MediaType.APPLICATION_JSON).get(String.class);
	}
	
	/**
	 * Get the pad's content
	 * @param padID The ID of the pad
	 * @param revision Revision number or "latest"
	 * @param format File type requested ("txt", "html", or "md")
	 * */
	public String getPadContent(String padID, String revision, String format)
	{
		String uri = String.format("https://hackpad.com/api/1.0/pad/%s/content/%s.%s", padID, revision, format);
		WebResource serviceGET = client.resource(UriBuilder.fromUri(uri).build());
		return serviceGET.accept(MediaType.APPLICATION_JSON).get(String.class);
	}
	
	public String getPadContentMD(String site, String padID, String revision)
	{
		return this.getPadContent(site, padID, revision, "md");
	}
	
	public String getPadContentMD(String padID, String revision)
	{
		return this.getPadContent(padID, revision, "md");
	}
	
	public String getPadContentHTML(String site, String padID, String revision)
	{
		return this.getPadContent(site, padID, revision, "html");
	}
	
	public String getPadContentHTML(String padID, String revision)
	{
		return this.getPadContent(padID, revision, "html");
	}
	
	public String getPadContentTXT(String site, String padID, String revision)
	{
		return this.getPadContent(site, padID, revision, "txt");
	}
	
	public String getPadContentTXT(String padID, String revision)
	{
		return this.getPadContent(padID, revision, "txt");
	}
	
	/**
	 * Update pad's content
	 * @param pad Set the pad's ID, content-type, title and content
	 * */
	public boolean updatePadContent(Pad pad)
	{
		String uri = String.format("%s/pad/%s/content", pad.getUri(), pad.getPadID()) ;
		WebResource servicePOST = client.resource(UriBuilder.fromUri(uri).build());		
		String json = servicePOST.accept(MediaType.APPLICATION_JSON).header("Content-Type", pad.getContentType()).post(String.class, pad.getTitle() + '\n' + pad.getContent());
//		System.out.println(json);
		Map<String, String> retMap = gson.fromJson(json,  
                new TypeToken<Map<String, String>>() {}.getType());  
		return Boolean.parseBoolean(retMap.get("success").toString());
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
