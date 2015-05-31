import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * ADD .txt file for read/ write for easy addition of swear words
 * 
 * 
 */

public class ChatFilter {
	
	private ArrayList<String> swearList = new ArrayList<String>();
	
	private String message = "";
	
	
	public ChatFilter()
	{
		readSwears();
	}
	
	public String filterMessage(String[] inMessage)
	{
		String 	outMessage = "";
		
		for(int i = 0; i < swearList.size(); i++)
		{
			for(int j = 0; j < inMessage.length; j++)
			{
				if(swearList.contains(inMessage[j]))
					inMessage[j] = "***";
			}
			
			
		}
		for(int k = 0; k < inMessage.length; k++)
		{
			outMessage += inMessage[k] + " ";
		}
		
		return outMessage;
		
	}
	
	//Overloaded Method
	public String filterMessage(String message)
	{
		String[] inMessage = message.split(" ");
		
		String 	outMessage = "";
		
		
		for(int i = 0; i < swearList.size(); i++)
		{
			for(int j = 0; j < inMessage.length; j++)
			{
				if(swearList.contains(inMessage[j]))
					inMessage[j] = "***";
			}
			
			
		}
		for(int k = 0; k < inMessage.length; k++)
		{
			outMessage += inMessage[k] + " ";
		}
		
		return outMessage;
		
	}
	public void writeInfo()
	{	
		try
		{
			int i = 0;
			FileWriter f = new FileWriter("res/util/swearwords.txt", true);
			BufferedWriter infoSave = new BufferedWriter(f);
			while( i < swearList.size())
			{
				infoSave.write(swearList.get(i));
				infoSave.newLine();
				i++;
			}
			infoSave.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void readSwears()
	{
		try
		{	
			String line = "";
			BufferedReader infoSave = new BufferedReader(new FileReader(new File("res/util/swearwords.txt")));
			while( (line = infoSave.readLine()) != null)
			{
				swearList.add(line);
			}
			//System.out.println(swearList);
			infoSave.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void clearFile()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("res/util/swearwords.txt");
			writer.write("");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSwear(String inSwear)
	{
		if(!swearList.contains(inSwear))
			swearList.add(inSwear);
		
		writeInfo();
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public ArrayList<String> getSwearList()
	{
		return swearList;
	}

	
	
}
