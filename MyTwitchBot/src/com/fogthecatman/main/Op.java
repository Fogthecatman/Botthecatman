
package com.fogthecatman.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Op {
	
	private ArrayList<String> ops = new ArrayList<String>();
	
	public Op()
	{
		readOps();
	}
	
	private void readOps() 
	{
		try
		{	
			String line;
			BufferedReader infoSave = new BufferedReader(new FileReader(new File("res/util/ops.txt")));
			while( (line = infoSave.readLine()) != null)
			{
				ops.add(line);
			}
			
			infoSave.close();
			//System.out.println(ops);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean hasOp(String person)
	{
		person = person.toLowerCase();
		if(ops.contains(person))
			return true;
		else
			return false;
	}
		
	public ArrayList<String> getOps()
	{
		return ops;
	}
}
