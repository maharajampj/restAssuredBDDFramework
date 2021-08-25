package com.qa.utility;

import java.io.File;

public class fileFunctions 
{
	public File readFile(String path)
	{
		File file = null;
		try
		{
		 file =new File(System.getProperty("user.dir")+path);
		
		}
		catch(Exception e)
		{
			System.out.println("File not Found");
		}
		return file;
	}

}
