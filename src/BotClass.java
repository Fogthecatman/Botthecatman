/*
 * Add toggle sounds
 * 
 * @author Fogthecatman
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jibble.pircbot.PircBot;

//Static vars
public class BotClass extends PircBot {
	
	//static ChatFilter filter = new ChatFilter();
	static BotClass bot;
	static String dateAndTime = "";
	static ArrayList<String> viewerQueue = new ArrayList<String>();
	static String recentPeopleQ = "";
	static boolean  playSounds = false;

	public BotClass(){
		setName("Botthecatman");
		bot = this;
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
		Sounds player = new Sounds();
		//Command regCmd = new Command();
		
    	//Variables
    	String[] 	messageSplit;
		String 		channelOwner = GUI.channelOwner,
		 			chatMessage = "",
		 			line = "";
		
		
		//Processing
		messageSplit = message.split(" ");		
		line = "\t";
		
		for(int i = 0; i < messageSplit.length; i++)
		{
			
			line += (messageSplit[i] + " ");
			if(line.length() > 50  && i % 10 == 0)
			{		
				line += "\n\t\t";
			}
				
		}
		
		message = line;
		
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		GUI.chatBox.append(updateTime() + sender + ": " + message + "\n");
		
		//regCmd.regCommand(messageSplit, channel, sender);
		
		
		//filter.filterMessage(messageSplit);
		switch(messageSplit[0]){
			case "!hello":
				chatMessage = "Hello " + sender;
				messageExecute(channel, chatMessage);
				break;
				
			case "!botinfo":
				chatMessage = "Hi I am Botthecatman, your humble servent.";
				messageExecute(channel, chatMessage);
				break;
				
			case "!naughty":
				chatMessage = "Auu " + sender + " you so naughty!";
				messageExecute(channel, chatMessage);
				break;
				
			case "!users":
				String userList = "";
				userList = ("" + getQueue(channel));
				chatMessage = "The list of online viewers: " + userList;
				messageExecute(channel, chatMessage);
				break;
				
			case "!disconnect":	
				if(sender.equalsIgnoreCase(channelOwner)){
					chatMessage = "BOTthecatman is disconnecting";
					messageExecute(channel, chatMessage);
					disconnect();
				}
				break;
				
			case "?owner":
				chatMessage = "Owner of the channel: " + channelOwner;
				messageExecute(channel, chatMessage);
				break;
				
			case "?art":
				chatMessage = "Check out some of Fog's Art!: http://fogthecatman.deviantart.com";
				messageExecute(channel, chatMessage);
				break;
				
			//Used as ?queue #	
			case "?queue":
				if(sender.equalsIgnoreCase(channelOwner))
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
				
			case "!addToQ":
				chatMessage= sender + " has been added to play queue";
				messageExecute(channel, chatMessage);			
				if(!viewerQueue.contains(sender))
					viewerQueue.add(sender);			
				break;
			
			case "?recentQ":	
				chatMessage = "Recent people in Queue: " + recentPeopleQ;
				messageExecute(channel, chatMessage);
				break;
			
			//Used as ?multitwitch person person ...
			case "?multitwitch":
				
				String people = "";
				for(int i = 1; i < messageSplit.length; i++)
				{
					people += (messageSplit[i] + "/");
				}
				chatMessage = "http://multitwitch.tv/" + people;
				messageExecute(channel, chatMessage);
				break;
				
			case "!*tSound":
				if(sender.equalsIgnoreCase(channelOwner))
				{
					if(messageSplit[1].equals("on"))
						playSounds = true;
					else if(messageSplit[1].equals("off"))
						playSounds = false;
					
					chatMessage = "Sounds have been set to : " + playSounds;
					messageExecute(channel, chatMessage);
				}
			break;
				
			case "*triple":		
				
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
					messageExecute(channel, chatMessage);
					break;
				
				
			default: break;					
			
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
		GUI.chatBox.append(updateTime() + sender + ":\t" + chatMessage + "\n");
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