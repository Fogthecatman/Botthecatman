package com.fogthecatman.currency;

import java.util.ArrayList;
import java.util.Random;

public class Lottery {
	
	public ArrayList<String> entryNames = new ArrayList<String>();
	public int lotteryAmt = 100;
	public boolean activeLottery = false;
	
	public Lottery()
	{
		//Default Constructor
	}
	
	public void setEntry(String userName, String entryAmt)
	{
		int account = 0;
		int entry = Integer.parseInt(entryAmt);
		userName = userName.toLowerCase();
		
		//account = BotClass.coinAmt.getCoins(userName);
		
		
		if(activeLottery)
		{
			if(entry > 0 && entry <= account)
			{
				if(!entryNames.contains(userName))
				{
					entryNames.add(userName);
					//BotClass.coinAmt.setCoins(userName,"" + (account - entry));
					lotteryAmt += entry;
				}
			}
		}
	}
	
	public void lotteryPick()
	{
		Random rng = new Random();
		Integer winnerNum = rng.nextInt(entryNames.size());
		String winner =  entryNames.get(winnerNum);
		
		//int account = BotClass.coinAmt.getCoins(winner);
		
		//String newAccount = "" + (account + lotteryAmt);
		
		//System.out.println(account);
		//BotClass.coinAmt.writeProperties(winner, newAccount);
		System.out.println("The winner is: " + winner + ":" + lotteryAmt);
		
		
	}
	
	public void activateLottery()
	{
		System.out.println("Inside Lotterty Activated");
		activeLottery = true;
	}
	
	public void deactiveLottery()
	{
		activeLottery = false;
	}
	
}
