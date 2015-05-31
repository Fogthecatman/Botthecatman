/* IMPORTANT: THIS IS AN UNUSED CLASS
 * 
 * This class gets input from the user for:
 *   what channel to join
 *   who is the owner of that channel
 *   oauth key for the bot joining the channel
 * 
 * 
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Info {
	
	private String joinChannel;
	private String oauthKey;
	static Info thisInfo;
	
	public Info()
	{
		thisInfo = this;
		readInfo();
	}
		
		

	public void writeInfo(String joinChannel, String oauthKey)
	{
		try
		{
			FileWriter f = new FileWriter("res/util/information.sav", true);
			BufferedWriter infoSave = new BufferedWriter(f);
			this.joinChannel = joinChannel;
			this.oauthKey = oauthKey;
			infoSave.write(joinChannel);
			infoSave.newLine();
			infoSave.write(oauthKey);
			infoSave.newLine();
			infoSave.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	
	public void readInfo()
	{
		try
		{		
			BufferedReader infoSave = new BufferedReader(new FileReader(new File("res/util/information.sav")));
			joinChannel = infoSave.readLine();
			oauthKey = infoSave.readLine();
			infoSave.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getJoin()
	{
		return joinChannel;
	}
	
	public String getOAuth()
	{
		return oauthKey;
	}
}
