import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Code from http://examples.javacodegeeks.com/core-java/net/url/read-text-from-url/

public class Followers {
	
	public static JSONObject json;
	public static ArrayList<String> followsArray = new ArrayList<String>();

	public Followers() throws JSONException, IOException {
		getFollowers();
	}

	public void getFollowers() throws JSONException, IOException {
		clearFile();
		json = readJsonFromUrl("https://api.twitch.tv/kraken/channels/" + Info.thisInfo.getJoin() + "/follows?&limit=100&offset=00");
		//int amtFollowers = json.getInt("_total");
		String follower = "";
		for (int k = 0; k < 2; k++) {
			json = readJsonFromUrl("https://api.twitch.tv/kraken/channels/" + Info.thisInfo.getJoin() +"/follows?&limit=100&offset=" + k + "00");
			JSONArray follows = json.getJSONArray("follows");

			for (int i = 0; i < follows.length(); i++) {
				follower = follows.getJSONObject(i).getJSONObject("user")
						.getString("name");
				followsArray.add(follower);
			}
		}
		writeInfo();

	}

	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
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
	
	public void writeInfo()
	{	
		try
		{
			int i = 0;
			FileWriter f = new FileWriter("res/util/followers.txt", true);
			BufferedWriter infoSave = new BufferedWriter(f);
			while( i < followsArray.size())
			{
				infoSave.write(followsArray.get(i));
				infoSave.newLine();
				i++;
			}
			infoSave.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void clearFile()
	{
		followsArray.clear();
		PrintWriter writer;
		try {
			writer = new PrintWriter("res/util/followers.txt");
			writer.write("");
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
