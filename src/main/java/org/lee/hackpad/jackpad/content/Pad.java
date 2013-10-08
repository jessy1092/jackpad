package org.lee.hackpad.jackpad.content;

public class Pad
{
	private String title;
	private String contentType;
	private String content;
	private String padID;
	private String site;
	private String uri;
	
	public Pad()
	{
		uri = new String();
		setTitle("");
		setContentType("text/plain");
		setContent("");
		setPadID("");
		setSite("");
	}
	
	public Pad(String contentType, String content)
	{
		uri = new String();
		setTitle("");
		setContentType(contentType);
		setContent(content);
		setPadID("");
		setSite("");
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setPadID(String padID)
	{
		this.padID = padID;
	}
	
	public void setSite(String site)
	{
		this.site = site;
		if(site.equals(""))
		{
			uri = "https://hackpad.com/api/1.0";
		}
		else
		{
			uri = String.format("https://%s.hackpad.com/api/1.0", site);
		}
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getContentType()
	{
		return this.contentType;
	}
	
	public String getContent()
	{
		return this.content;
	}
	
	public String getPadID()
	{
		return this.padID; 
	}
	
	public String getSite()
	{
		return this.site;
	}
	
	public String getUri()
	{
		return this.uri;
	}
}
