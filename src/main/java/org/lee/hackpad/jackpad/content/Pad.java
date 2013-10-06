package org.lee.hackpad.jackpad.content;

public class Pad
{
	private String title;
	private String contentType;
	private String content;
	private String padID;
	
	
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
}
