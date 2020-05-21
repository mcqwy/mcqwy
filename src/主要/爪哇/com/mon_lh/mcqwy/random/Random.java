package com.mon_lh.mcqwy.random;

public class Random 
{
	
	public boolean getDodge()
	{
		int i = this.getRandomB();
		if(i <= 10)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getCrit()
	{
		int i = this.getRandomB();
		if(i <= 20)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public int getPartsDamage()
	{
		return this.getRandomG();
	}
	
	public boolean getDamage()
	{
		int i = this.getRandomB();
		if(i <= 60)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getDamageHuai()
	{
		int i = this.getRandomB();
		if(i <= 35)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getDamageYq()
	{
		int i = this.getRandomB();
		if(i <= 15)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getReultA()
	{
		int i = this.getRandomB();
		if(i <= 40)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getReultB()
	{
		int i = this.getRandomB();
		if(i <= 60)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getReultC()
	{
		int i = this.getRandomB();
		if(i <= 85)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getReultE()
	{
		int i = this.getRandomB();
		if(i <= 98)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public boolean getReultF()
	{
		int i = this.getRandomQ();
		if(i <= 8)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	private int getRandomG()
	{
		int i = 1+(int)(Math.random()*6);
		return i;
	}
	
	private int getRandomB()
	{
		int i = 1+(int)(Math.random()*100);
		return i;
	}
	
	private int getRandomQ()
	{
		int i = 1+(int)(Math.random()*1000);
		return i;
	}
	
	
}
