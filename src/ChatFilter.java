public class ChatFilter {
	
	private String[] swearList = {"shit", "fuck", "yourmom", "twat", "cunt", "damn", "piss", "motherfucker"};
	
	private String message = "";
	
	
	public ChatFilter()
	{
		
	}
	
	public String filterMessage(String[] inMessage)
	{
		String 	outMessage = "";
		
		for(int i = 0; i < swearList.length; i++)
		{
			for(int j = 0; j < inMessage.length; j++)
			{
				if(inMessage[j].equalsIgnoreCase(swearList[i]))
					inMessage[j] = "****";
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
		
		
		for(int i = 0; i < swearList.length; i++)
		{
			for(int j = 0; j < inMessage.length; j++)
			{
				if(inMessage[j].equalsIgnoreCase(swearList[i]))
					inMessage[j] = "****";
			}
			
			
		}
		for(int k = 0; k < inMessage.length; k++)
		{
			outMessage += inMessage[k] + " ";
		}
		
		return outMessage;
		
	}
	
	public String getMessage()
	{
		return message;
	}

	
	
}
