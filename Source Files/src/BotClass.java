/*
 * Add toggle sounds
 * 
 * @author Fogthecatman
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



import org.jibble.pircbot.PircBot;
import org.json.JSONException;

//Static vars
public class BotClass extends PircBot {
	
	//static ChatFilter filter = new ChatFilter();
	static BotClass bot;
	static String dateAndTime = "";
	static ArrayList<String> viewerQueue = new ArrayList<String>();
	static String recentPeopleQ = "";
	static boolean  playSounds = false;
	private Sounds player;
	private Op opPerson;
	private ChatFilter filter;

	public BotClass() throws JSONException, IOException{
		setName("Botthecatman");
		bot = this;
		player = new Sounds();
		opPerson = new Op();
		filter = new ChatFilter();
	}
/*
	@Override
	public void log(String line) {
         GUI.chatBox.append(line + "\n");
         GUI.chatBox.setCaretPosition(GUI.chatBox.getText().length());
    }
*/
	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
		//Objects
		
    	//Variables
    	String[] 	messageSplit;
		String 		channelOwner = GUI.channelOwner,
		 			chatMessage = "",
		 			line = "",
		 			indicator = "";
		
		//Processing
		message = filter.filterMessage(message);
		messageSplit = message.split(" ");		
		line = "\t";
		

		
		for(int i = 0; i < messageSplit.length; i++)
		{		
			line += (messageSplit[i] + " ");
			if(line.length() > 50  && i % 10 == 0)
			{		
				line += "\n\t\t\t";
			}
				
		}
		
		
		if(message.contains("fog"))
			indicator = "*";
		else
			indicator = "  ";
		
		
		message = line;
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		GUI.chatBox.append(indicator  + updateTime() + sender + ":\t " + message + "\n");

		
		//regCmd.regCommand(messageSplit, channel, sender);
		
		switch(messageSplit[0].charAt(0))
		{
//!
			case '!':
				if(messageSplit[0].equalsIgnoreCase("!hello"))
				{
					chatMessage = "Hello " + sender;
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!botinfo"))
				{
					chatMessage = "Hi I am Botthecatman, your humble servant.";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!naughty"))
				{
					chatMessage = "Auu " + sender + " you so naughty!";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!disconnect"))
				{
					if(opPerson.hasOp(sender))
					{
						chatMessage = "BOTthecatman is disconnecting";
						messageExecute(channel, chatMessage);
						disconnect();
						break;
					}				
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!getFollowers"))
				{
					if(opPerson.hasOp(sender))
					{
						try {
							Followers follower = new Followers();
							follower.clearFile();
							follower.getFollowers();
						} catch (JSONException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						chatMessage = "Updating Followerlist";
						messageExecute(channel, chatMessage);
						break;
					}				
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!addToQ"))
				{
					chatMessage= sender + " has been added to play queue";
					messageExecute(channel, chatMessage);			
					if(!viewerQueue.contains(sender))
						viewerQueue.add(sender);			
					break;			
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!addSwear"))
				{
					if(opPerson.hasOp(sender))
					{
						filter.clearFile();
						filter.addSwear(messageSplit[1]);
						chatMessage = "Swear has been added";
						messageExecute(channel, chatMessage);
					}
					break;
				}
				break;
//?				
			case '?':
				if(messageSplit[0].equalsIgnoreCase("?owner"))
				{
					chatMessage = "Owner of the channel: " + channelOwner;
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?art"))
				{
					chatMessage = "Check out some of Fog's Art!: http://fogthecatman.deviantart.com";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?git"))
				{
					chatMessage = "Check out my Code!: https://github.com/Fogthecatman/Botthecatman";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?commands"))
				{
					chatMessage = "Check out my Commands!: http://pastebin.com/X3JSaBdf";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?queue"))
				{
					if(opPerson.hasOp(sender))
					{
						if(viewerQueue.isEmpty())
							chatMessage = "Viewer queue is empty";
						else
						{
							int indexNum = Integer.parseInt(messageSplit[1]);
							chatMessage = "Next in queue is: " + getPlayersInQue(channel, indexNum);
						}
					
						messageExecute(channel, chatMessage);
					}
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?recentQ"))
				{
					chatMessage = "Recent people in Queue: " + recentPeopleQ;
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?youtube"))
				{
					chatMessage = "Fog's Youtube: https://www.youtube.com/user/Fogthecatman";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?twitter"))
				{
					chatMessage = "Fog's Twitter: https://twitter.com/fogthecatman";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("?multitwitch"))
				{
					String people = "";
					for(int i = 1; i < messageSplit.length; i++)
					{
						people += (messageSplit[i] + "/");
					}
					chatMessage = "http://multitwitch.tv/" + people;
					messageExecute(channel, chatMessage);
					break;
				}	
				break;
//*
			case '*':
				if(messageSplit[0].equalsIgnoreCase("*tSound"))
				{
					if(opPerson.hasOp(sender))
					{
						if(messageSplit[1].equals("on"))
							playSounds = true;
						else if(messageSplit[1].equals("off"))
							playSounds = false;
						
						chatMessage = "Sounds have been set to : " + playSounds;
						messageExecute(channel, chatMessage);
					}
					break;		
				}
//giving followers permissions with this example				
				else
				if(messageSplit[0].equalsIgnoreCase("*triple"))
				{
						try {
							if(playSounds)
							{
								chatMessage = "OH BABY A TRIPLE";
								player.playSong("OHBabyTriple.wav");
							}
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//messageExecute(channel, chatMessage);
						break;
					
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*nyaaa"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "I STILL LOVE YOU";
							player.playSong("BestCry.wav");
						}
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
						
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*bill"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "BILL BILL BILL";
							player.playSong("BillNye.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
							
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*jpg"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "Do I look like I know what a jpg is?";
							player.playSong("LookJPG.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*profanity"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "Hey, watch your profanity";
							player.playSong("Profanity.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*spooky"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "3Spooky5Me";
							player.playSong("Spooky.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*wizard"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "That wizard came from the moon!";
							player.playSong("Wizard.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*wombo"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "Wombo Combo!";
							player.playSong("Wombo.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*wombofull"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "OH MY GOD WOMBO COMBO?";
							player.playSong("WomboFull.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*woowoo"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "Whistle Goes Woo Woo";
							player.playSong("WooWoo.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("*eggsalad"))
				{
					try {
						if(playSounds)
						{
							chatMessage = "HELP ME";
							player.playSong("Eggsalad.wav");
						}
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//messageExecute(channel, chatMessage);
					break;
				}
				break;
				
		}
	}
/*	
	public void onJoin(String channel, String sender, String login, String hostname)
	{
		String chatMessage = "";
		
		chatMessage = "Hello, " + sender + " welcome to the stream!";	
		
		messageExecute(channel, chatMessage);
	}
*/	
	public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target)
	{
		sendNotice(sourceNick,"\001VERSION myBot:1.0:YOURMOM\001");
	}
	
	public void messageExecute(String channel, String chatMessage)
	{
		//filter.filterMessage(chatMessage);
		String sender = "BOTthecatman";
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		sendMessage(channel, chatMessage);
		GUI.chatBox.append("  " + updateTime() + sender + ": \t" + chatMessage + "\n");
	}
	
	/*
	 * Gets the first person in the que and removes that person from the que
	 * 
	 * @recentPeople@ is a static variable that is updated to show the most 
	 *  recent people requested from the Queue
	 */
	public String getPlayersInQue(String channel, int indexAmt)
	{
		String people = "";
		
		if(indexAmt > viewerQueue.size())
		{
			sendMessage(channel, "Amount of viewers requested is larger than the queue");
			people = "";
		}
		else
			for(int i = 0; i < indexAmt; i++)
			{
				people += (" " + viewerQueue.get(0) + ",");
				viewerQueue.remove(0);
				
			}
		recentPeopleQ = people;
		
		return people;
	}
	
	public ArrayList<String> getQueue(String channel)
	{
		
		if(viewerQueue.isEmpty())
			sendMessage(channel, "Error: viewer queue is empty");
		
		return viewerQueue;
	}
	
	public String updateTime()
	{
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    	dateAndTime = "  [" + sdf.format(cal.getTime()) + "] ";
    	return dateAndTime;
	}
}