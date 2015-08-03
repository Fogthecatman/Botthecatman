package com.fogthecatman.main;
/*
 * Add toggle sounds
 * 
 * @author Fogthecatman
 */

import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.HashSet;

//import javax.swing.Timer;

import org.jibble.pircbot.PircBot;
import org.json.JSONException;

//import com.fogthecatman.currency.Lottery;
//import com.fogthecatman.currency.ViewerProperties;

//Static vars
public class BotClass extends PircBot {

	// static ChatFilter filter = new ChatFilter();
	static BotClass bot;
	static String dateAndTime = "";
	static ArrayList<String> viewerQueue = new ArrayList<String>();
	//private HashSet<String> followerList = new HashSet<String>();
	//private ArrayList<String> viewersWatching = new ArrayList<String>();
	static String recentPeopleQ = "";
	static boolean playSounds = true;
	private Sounds player;
	private Op opPerson;
	private ChatFilter filter;
	//public static ViewerProperties coinAmt = null;
	//private Lottery lotto = null;
	private long uptimeStart = 0;
	private CheckFollower follower = new CheckFollower();

	public BotClass() throws JSONException, IOException {
		setName("Botthecatman");
		bot = this;
		player = new Sounds();
		opPerson = new Op();
		filter = new ChatFilter();
		//coinAmt = new ViewerProperties();
		
		uptimeStart = System.currentTimeMillis();
/*		
		int delay = 1200000;
		ActionListener taskPerformer = new ActionListener() {
		     public void actionPerformed(ActionEvent evt) {
		    	 coinAmt.addPoints(33, viewersWatching);
		    	 try {
					 follower = new Followers();
					 follower.clearFile();
					 follower.getFollowers();
					 followerList = follower.getFollowerArray();
				} catch (JSONException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		     }
		 };
		 new Timer(delay, taskPerformer).start();
*/
	}

	/*
	 * @Override public void log(String line) {
	 * GUI.chatBox.setText(GUI.chatBox.getText() + line + "\n");
	 * GUI.chatBox.setCaretPosition(GUI.chatBox.getText().length()); }
	 */

	public void onAction(String sender, String login, String hostname,
			String target, String action) {
		GUI.chatBox.setFont(new Font("Verdana", Font.PLAIN, 12));
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		GUI.chatBox.setText(GUI.chatBox.getText() + "\t " + sender + " "
				+ action + "\n");

	}

	public void onMessage(String channel, String sender, String login, String hostname, String message) 
	{
/*
		if(!coinAmt.containsPerson(sender))
			coinAmt.writeProperties(sender, "" + 100);
*/
		
		GUI.chatBox.setFont(new Font("Verdana", Font.PLAIN, 12));
		
    	//Variables
    	String[] 	messageSplit;
		String 		channelOwner = GUI.channelOwner,
		 			chatMessage = "",
		 			line = "";
		
		//Processing
		message = filter.filterMessage(message);
		messageSplit = message.split(" ");
		
		for(int i = 0; i < messageSplit.length; i++)
		{		
			line += (messageSplit[i] + " ");
			if(line.length() > 50  && i % 10 == 0)
			{		
				line += "\n\t\t";
			}
				
		}
		
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		message = line;		
		
		GUI.chatBox.setText(GUI.chatBox.getText() + updateTime() + sender + ": ");		
		GUI.chatBox.setText(GUI.chatBox.getText() + message);
		GUI.chatBox.setText(GUI.chatBox.getText() + "\n");
	
		switch(messageSplit[0].charAt(0))
		{
//!
			case '!':
				/*
				if(messageSplit[0].equalsIgnoreCase("!setCoins"))
				{
					if(sender.equals(GUI.channelOwner))
					{
						sender = sender.toLowerCase();
						coinAmt.writeProperties(messageSplit[1].toLowerCase(), messageSplit[2]);
						chatMessage = "" + sender + "'s coins: " + coinAmt.getCoins(sender);
						messageExecute(channel, chatMessage);
						break;
					}
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!startLotto"))
				{
					if(sender.equalsIgnoreCase(GUI.channelOwner))
					{
						lotto = new Lottery();
						System.out.println("Started Lottery");
						lotto.activateLottery();
						break;
					}
				
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!endLotto"))
				{
					if(sender.equalsIgnoreCase(GUI.channelOwner))
					{
					
						lotto.deactiveLottery();
						lotto.lotteryPick();
						commandSong("Coin.wav");
						
						break;
					}
				}
				else
				
				if(messageSplit[0].equalsIgnoreCase("!bet"))
				{	
					try
					{
						int numberBet = Integer.parseInt(messageSplit[1]);
						
						if(numberBet != 0)
						{
							lotto.setEntry(sender, messageSplit[1]);
							break;
						}
						else
							messageExecute(channel, "That bet is not allowed!");
					}
					catch(Exception e)
					{
						messageExecute(channel, "That bet is not allowed!");
					}
					
				}
				else
				*/
				if(messageSplit[0].equalsIgnoreCase("!song"))
				{
					chatMessage = "!currentsong";
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!hello"))
				{
					chatMessage = "Hello " + sender;
					messageExecute(channel, chatMessage);
					break;
				}
				else
				if(messageSplit[0].equalsIgnoreCase("!slap"))
				{
					String person = messageSplit[1];
					chatMessage = sender + " slaps " + person + "!";
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
				if(messageSplit[0].equalsIgnoreCase("?uptime"))
				{
					long uptime = System.currentTimeMillis();
					long howLongUp = uptime - uptimeStart;
					double hours = howLongUp / 3600000;
					double remainder = howLongUp % 3600000;
					double minutes = remainder / 60000;
					
					chatMessage = "Stream Uptime: " + (int) hours + " hours and " + (int) minutes + " minute(s)";
					messageExecute(channel, chatMessage);
					break;
				}
				/*
				else
				if(messageSplit[0].equalsIgnoreCase("?coins"))
				{
					sender = sender.toLowerCase();
					chatMessage = "" + sender + "'s coins: " + coinAmt.getCoins(sender);
					messageExecute(channel, chatMessage);
					break;
				}
				*/
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
				try
				{
					if(messageSplit[0].equalsIgnoreCase("*triple"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("OHBabyTriple.wav");
							break;
						}
						
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*raggidyass"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("RaggidyAss.wav");
							break;
						}
						
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*smokeweed"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Smokeweed.wav");
							break;
						}
							
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*noscoped"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Noscoped.wav");
							break;
						}
							
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*airhorn"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Airhorn.wav");
							break;
						}
							
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*nyaaa"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("BestCry.wav");
							break;
						}
							
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*bill"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("BillNye.wav");
							break;
						}
								
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*party"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Party.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*batman"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Batman.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*hax"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Hax.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*wow"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Wow.wav");
							break;
						}
					}
					/*
					else
					if(messageSplit[0].equalsIgnoreCase("*jpg"))
					{
						sender = sender.toLowerCase();
						if(followerList.contains(sender) || opPerson.hasOp(sender))
						{
							commandSong("LookJPG.wav");
							break;
						}
					}
					*/
					else
					if(messageSplit[0].equalsIgnoreCase("*potatoes"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Potatoes.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*nogodplease"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Nogodplease.wav");
							break;
						}
					}
					//Curteosy of Sivart0 on Twitch
					else
					if(messageSplit[0].equalsIgnoreCase("*pewpew"))
					{
						sender = sender.toLowerCase();
						if(follower.isFollower(sender) || opPerson.hasOp(sender))
						{
							commandSong("Pewpew.wav");
							break;
						}
					}
					else
					if (messageSplit[0].equalsIgnoreCase("*profanity")) 
					{
							sender = sender.toLowerCase();
							if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
							{
								commandSong("Profanity.wav");
								break;
							}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*spooky"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("Spooky.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*wizard"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("Wizard.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*suprise"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("Suprise.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*wombo"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("Wombo.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*wombofull"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("WomboFull.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*woowoo"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("WooWoo.wav");
							break;
						}
					}
					else
					if(messageSplit[0].equalsIgnoreCase("*eggsalad"))
					{
						sender = sender.toLowerCase();
						if (follower.isFollower(sender) || opPerson.hasOp(sender)) 
						{
							commandSong("Eggsalad.wav");
							break;
						}
					}
					break;
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
				
		}
	}

	public void commandSong(String songName) {
		try {
			if (playSounds) {
				player.playSong(songName);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onVersion(String sourceNick, String sourceLogin,
			String sourceHostname, String target) {
		sendNotice(sourceNick, "\001VERSION myBot:1.0:YOURMOM\001");
	}

	public void messageExecute(String channel, String chatMessage) {
		// filter.filterMessage(chatMessage);
		String sender = "BOT";
		sender = Character.toUpperCase(sender.charAt(0)) + sender.substring(1);
		sendMessage(channel, chatMessage);
		GUI.chatBox.setText(GUI.chatBox.getText() + updateTime() + sender
				+ ": " + chatMessage + "\n");
	}

	/*
	 * Gets the first person in the que and removes that person from the que
	 * 
	 * @recentPeople is a static variable that is updated to show the most
	 * recent people requested from the Queue
	 */
	public String getPlayersInQue(String channel, int indexAmt) {
		String people = "";

		if (indexAmt > viewerQueue.size()) {
			sendMessage(channel,
					"Amount of viewers requested is larger than the queue");
			people = "";
		} else
			for (int i = 0; i < indexAmt; i++) {
				people += (" " + viewerQueue.get(0) + ",");
				viewerQueue.remove(0);

			}
		recentPeopleQ = people;

		return people;
	}

	public ArrayList<String> getQueue(String channel) {
		if (viewerQueue.isEmpty())
			sendMessage(channel, "Error: viewer queue is empty");

		return viewerQueue;
	}

	public String updateTime() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		dateAndTime = "[" + sdf.format(cal.getTime()) + "] ";
		return dateAndTime;
	}
}