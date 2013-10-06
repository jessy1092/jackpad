package org.lee.hackpad.jackpad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;

public class JackpadClientTest
{
	private static final String API_KEYS_FILE = "api_keys.txt";
	
	private String HACKPAD_CLIENT_ID;
	private String HACKPAD_SECRET;
	private JackpadClient jackpadClient;
	
	@Before
	public void setUp()
	{
		setApiKey();
		jackpadClient = new JackpadClient(HACKPAD_CLIENT_ID, HACKPAD_SECRET);
		jackpadClient.build();
	}
	
	public void setApiKey()
	{
		try
		{
			FileReader fr = new FileReader(API_KEYS_FILE);
			BufferedReader br = new BufferedReader(fr);
			String line;
			line = br.readLine();
			String[] para= line.split(" ");
			HACKPAD_CLIENT_ID = para[0];
			HACKPAD_SECRET = para[1];
			br.close();
			fr.close();
		}
		catch(IOException e)
		{
			System.out.println(e.toString());
		}
	}
}
