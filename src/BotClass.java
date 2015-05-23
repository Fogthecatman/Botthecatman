/*
 * FIX:
 * MESSAGE SPLITS
 *  - spaces align paragraph with sender right 
 *  MORE COMMANDS!
 *  ADD OAUTH KEY INPUT for GitHub
 *  
 *  ADD Toggleable chat filter for swearing
 *  
 *  Maybe change chatpane to the name of bot channel?
 *  
 * I want message to split down to a new line after a decent amount of words.
 * look into entering down after message, can sometimes create an extra line.
 * 
 * 
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jibble.pircbot.PircBot;

public class BotClass extends PircBot {
	
	static GUI currentGUI = new GUI();
	static String dateAndTime = "";
	static ArrayList<String> viewerQueue = new ArrayList<String>();
	static String recentPeopleQ = ""; 

	public BotClass(){	
    	
		setName("Botthecatman");
		currentGUI.ShowComponents();
	}
/*
	@Override
	public void log(String line) {
         GUI.ChatLog.append(line + "\n");
         GUI.ChatLog.setCaretPosition(GUI.ChatLog.getText().length());
    }
*/
	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		
    	//Variables
    	String[] 	messageSplit;
		String 		channelOwner = Info.channelOwner,
		 			chatMessage = "",
		 			line = "",
		
		
		//Processing
		dateAndTime = "  [" + sdf.format(cal.getTime()) + "] ";
		
		messageSplit = message.split(" ");
		
		for(int i = 0; i < messageSplit.length; i++)
		{
			
			line += (messageSplit[i] + " ");
			if(line.length() > 50  && i % 10 == 0)
			{		
				line += "\n";
			}
				
		}
		
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		GUI.ChatLog.append(dateAndTime + sender + ": " + message + "\n");
		
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
				
			default: break;					
			
		}
		
	}
	
	public void onVersion(String sourceNick, String sourceLogin, String sourceHostname, String target)
	{
		sendNotice(sourceNick,"\001VERSION myBot:1.0:YOURMOM\001");
	}
	
	public void messageExecute(String channel, String chatMessage)
	{
		String sender = "BOTthecatman";
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		sendMessage(channel, chatMessage);
		GUI.ChatLog.append(dateAndTime + sender + ": " + chatMessage + "\n");
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
	
}