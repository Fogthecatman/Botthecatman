package com.fogthecatman.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckFollower {
	
	public static JSONObject json;
	public static HashSet<String> followersArray = new HashSet<String>();
	public Info myInfo = new Info();
	public CheckFollower()
	{
		
		
	}
	
	public boolean isFollower(String user) throws JSONException, IOException
	{
		try
		{
			if(user.equals(myInfo.getJoin()))
				return true;
			
			json = readJsonFromUrl("https://api.twitch.tv/kraken/users/" + user + "/follows/channels/fogthecatman");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(user + " : is not a follower of the channel");
			return false;
		}
		
		
		if(!followersArray.contains(user))
			followersArray.add(user);
		
		
		return true;
		
	}
	
	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException 
	{
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
	public HashSet<String> getFollowers()
	{
		return followersArray;
	}
}
