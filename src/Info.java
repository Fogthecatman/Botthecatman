/*
 * 
 * This class gets input from the user for:
 *   what channel to join
 *   who is the owner of that channel
 *   oauth key for the bot joining the channel
 * 
 * 
 */



import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Info {
	
	public static String joinChannel;
	public static String channelOwner;
	public static String oauthKey;
	
	public Info(){
		getChannelInfo();
	}
	
	public void getChannelInfo(){
		JTextField field1 = new JTextField(); 
		JTextField field2 = new JTextField(); 
		JTextField field3 = new JTextField();
		Object[] message = {
				"Channel to Join: ", field1,
				"Owner of Channel: ", field2,
				"OAuth-key for Bot", field3,
			
		};
		int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION){
			joinChannel = "#" + field1.getText();
			channelOwner = field2.getText();
			oauthKey = field3.getText();
		}
	}
	public String returnJoin(){
		return joinChannel;
	}
	public String returnOwner(){
		return channelOwner;
	}
	public String returnKey(){
		return oauthKey;
	}
	
}
