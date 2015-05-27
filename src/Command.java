/*
 * Reference the static bot object and call messageExecute from that as in GUI class
 * 
 * BotClass.bot.messageExecute(joinChannel, GUI.messageBox.getText());
 * 
 * 
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Command extends BotClass {
	
	static ArrayList<String> viewerQueue = new ArrayList<String>();
	static String recentPeopleQ = "";
	
	public Command()
	{
		
	}
	
	
	public String[] splitMessage(String message)
	{
		String[] messageSplit = message.split(" ");
		return  messageSplit;
	}
	
	public void regCommand(String[] messageSplit, String channel, String sender)
	{
		Sounds player = new Sounds();
		String chatMessage = "";
		String channelOwner = GUI.channelOwner;
		
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
		/*
			case "*triple":
				chatMessage = "OH BABY A TRIPLE";
				try {
					player.playSong("OHBabyTriple.wav");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageExecute(channel, chatMessage);
				break;			
		*/	
			
			default: break;	
		
		}
		
	}
	
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
