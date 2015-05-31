/*
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;

public class GUI {
	
	static String joinChannel;
	static String channelOwner;
	static String oauthKey;
	static JTextArea chatBox;
	static JFrame preFrame;
	static JTextField messageBox;
	static JTextField field1 = new JTextField(); 
	static JTextField field2 = new JTextField();
		
    /** Creates new form GUI 
     * @throws IOException 
     * @throws JSONException */
    public GUI() throws JSONException, IOException {
    	BotClass bot = new BotClass();
    	preFrame(bot);
    }
 
    public void preFrame(BotClass bot)
    {
    	//reads info from file
    	Info readInfo = new Info();
    	
    	
    	JButton enterServer = new JButton("Enter Chat Server");
        preFrame = new JFrame("Enter your information");
 		JLabel field1Label = new JLabel("Channel to Join: ");
 		field1Label.setForeground(Color.WHITE);
 		JLabel field2Label = new JLabel("OAuthKey: ");
 		field2Label.setForeground(Color.WHITE);
        JPanel prePanel = new JPanel(new GridBagLayout());
         
        GridBagConstraints preRight = new GridBagConstraints();
        preRight.anchor = GridBagConstraints.EAST;
        GridBagConstraints preLeft = new GridBagConstraints();
        preLeft.anchor = GridBagConstraints.WEST;
        preRight.weightx = 2.0;
        preRight.fill = GridBagConstraints.HORIZONTAL;
        preRight.gridwidth = GridBagConstraints.REMAINDER;

        prePanel.add(field1Label, preLeft);
        prePanel.add(field1, preRight);
        prePanel.add(field2Label, preLeft);
        prePanel.add(field2, preRight);
        preFrame.add(BorderLayout.CENTER, prePanel);
        preFrame.add(BorderLayout.SOUTH, enterServer);
        preFrame.setVisible(true);
        preFrame.setSize(300, 300);
        preFrame.setBackground(Color.DARK_GRAY);
        prePanel.setBackground(Color.DARK_GRAY);
        
        if(readInfo.getOAuth() != null && readInfo.getJoin() != null)
        {
        	field1.setText(readInfo.getJoin());
        	field2.setText(readInfo.getOAuth());
        }
        
        enterServer.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent arg0)
        	{
        		String writeJoin = "";
        		
        		joinChannel = "#" + GUI.field1.getText().toLowerCase();
        		channelOwner = GUI.field1.getText();
        		oauthKey = GUI.field2.getText();
        		
        		//Used to make sure that the file doesn't keep writing your login info
        		if(readInfo.getJoin() == null && readInfo.getOAuth() == null)
        		{
        			writeJoin = joinChannel.substring(1);
        			readInfo.writeInfo(writeJoin, oauthKey);
        		}
        		
        	    GUI.preFrame.setVisible(false);
        	    initComponents();
        	    
        	    //Logs the Bot into the irc channel
        	    try {
        	    	
        	    	String channel  = joinChannel;
    				String password = oauthKey;
    				bot.connect("irc.twitch.tv", 6667, password);
    				bot.setVerbose(false);
    				bot.joinChannel(channel);
    				
    			}
    			catch(Exception e){
    				e.printStackTrace();
    			}
        	 }
         });
    }
    public void initComponents()
    {
    	JFrame newFrame = new JFrame("Botthecatman");
    	JButton sendMessage;
    	
    	newFrame.setResizable(true);
    	newFrame.setVisible(true);
        newFrame.setBackground(Color.DARK_GRAY);
        JPanel southPanel = new JPanel();
        newFrame.add(BorderLayout.SOUTH, southPanel);
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        sendMessage = new JButton("Send Message");
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setBackground(Color.DARK_GRAY);
        chatBox.setForeground(Color.WHITE);
        JScrollPane scroller = new JScrollPane(chatBox);
        newFrame.add(scroller, BorderLayout.CENTER);
        
        scroller.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener()
        {
        	public void adjustmentValueChanged(AdjustmentEvent e)
        	{
        		e.getAdjustable().setValue(e.getAdjustable().getMaximum());
        	}
        	
        });

       //chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;
        right.weightx = 2.0;
         
        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        sendMessage.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent event) 
        	{
        		
                if (GUI.messageBox.getText().length() < 1) 
                {
                    // do nothing 
                } 
                else if (GUI.messageBox.getText().equals(".clear"))
                {
                    GUI.chatBox.setText("Cleared all messages\n");
                    GUI.messageBox.setText("");
                } 
                else 
                {
                	BotClass.bot.messageExecute(joinChannel, GUI.messageBox.getText());
                    GUI.messageBox.setText("");
                    //getCmd.regCommand(getCmd.splitMessage(GUI.messageBox.getText()), GUI.joinChannel, "BOTthecatman");
                }
        	}
        });
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
     
         
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