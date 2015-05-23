public class Main {
	
	public static void main(String[] args){
		
		Info myInfo = new Info();
		BotClass bot = new BotClass();
		
		try {
			String channel = myInfo.returnJoin();
			String password = myInfo.returnKey();
			bot.connect("irc.twitch.tv", 6667, password);
			bot.setVerbose(false);
			bot.joinChannel(channel);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	
	}
}
