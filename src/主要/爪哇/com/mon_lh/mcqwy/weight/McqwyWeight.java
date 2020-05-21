package com.mon_lh.mcqwy.weight;

import java.lang.reflect.Field;

import net.minecraft.nbt.NBTTagCompound;

public class McqwyWeight {
	
	public static Object getValueOfVariableName(String name)
	{
		McqwyWeight f = new McqwyWeight();
		Field strs;
		try {
			strs = f.getClass().getDeclaredField(name);
			return strs.get(f);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			System.out.println("找不到变量:" + name);
		}
		return null;
	}
	
	public static int getArmorSize(int a, int b,int c,int d,int e,int f)
	{
		int eee = 0;
		int aa = 0;
		int bb = 0;
		int cc = 0;
		int dd = 0;
		int ee = 0;
		int ff = 0;
		if(a >= 10 && a < 20)
		{
			aa = (int) (Double.parseDouble(getValueOfVariableName("mcqwya" + a).toString()) * 100);
		}else if (a >= 20 && a < 30)
		{
			aa = (int) (Double.parseDouble(getValueOfVariableName("mcqwya" + (a-10)).toString()) * 100);
		}else if (a >= 30 && a < 40)
		{
			aa = (int) (Double.parseDouble(getValueOfVariableName("mcqwya" + (a-20)).toString()) * 100);
		}
		if (b >= 10 && b <20)
		{
			bb = (int)(Double.parseDouble(getValueOfVariableName("mcqwyb" + b).toString()) * 100);
		}else if (b >= 20 && b <30)
		{
			bb = (int)(Double.parseDouble(getValueOfVariableName("mcqwyb" + (b-10)).toString()) * 100);
		}else if (b >= 30 && b <40)
		{
			bb = (int)(Double.parseDouble(getValueOfVariableName("mcqwyb" + (b-20)).toString()) * 100);
		}
		if (c >= 10 && c <20)
		{
			cc = (int)(Double.parseDouble(getValueOfVariableName("mcqwyc" + c).toString()) * 100);
		}else if (c >= 20 && c <30)
		{
			cc = (int)(Double.parseDouble(getValueOfVariableName("mcqwyc" + (c-10)).toString()) * 100);
		}else if (c >= 30 && c <40)
		{
			cc = (int)(Double.parseDouble(getValueOfVariableName("mcqwyc" + (c-20)).toString()) * 100);
		}
		if (e >= 10 && e <20)
		{
			ee = (int)(Double.parseDouble(getValueOfVariableName("mcqwye" + e).toString()) * 100);
			eee = (int)(Double.parseDouble(getValueOfVariableName("mcqwyee" + e).toString()) * 100);
		}else if (e >= 20 && e <30)
		{
			ee = (int)(Double.parseDouble(getValueOfVariableName("mcqwye" + (e-10)).toString()) * 100);
			eee = (int)(Double.parseDouble(getValueOfVariableName("mcqwyee" + (e-10)).toString()) * 100);
		}else if (e >= 30 && e <40)
		{
			ee = (int)(Double.parseDouble(getValueOfVariableName("mcqwye" + (e-20)).toString()) * 100);
			eee = (int)(Double.parseDouble(getValueOfVariableName("mcqwyee" + (e-20)).toString()) * 100);
		}
		if (f >= 10 && f <20)
		{
			ff = (int)(Double.parseDouble(getValueOfVariableName("mcqwyf" + f).toString()) * 100);
		}else if (f >= 20 && f <30)
		{
			ff = (int)(Double.parseDouble(getValueOfVariableName("mcqwyf" + (f-10)).toString()) * 100);
		}else if (f >= 30 && f <40)
		{
			ff = (int)(Double.parseDouble(getValueOfVariableName("mcqwyf" + (f-20)).toString()) * 100);
		}
		
		if(d >= 10 && d <20)
		{
			dd = (int)(Double.parseDouble(getValueOfVariableName("mcqwyd" + d).toString()) * 100);
		}else if (d >= 20 && d <30)
		{
			dd = (int)(Double.parseDouble(getValueOfVariableName("mcqwyd" + (d-10)).toString()) * 100);
		}else if (d >= 30 && d <40)
		{
			dd = (int)(Double.parseDouble(getValueOfVariableName("mcqwyd" + (d-20)).toString()) * 100);
		}
		
		int i = ee - (aa + bb + cc + dd + eee + ff);
		return i;
	}
	
	public static boolean getEnable(NBTTagCompound nbt)
	{
		if(nbt != null)
		{;
			int e = nbt.getInteger("mcqwye");
			if(e >= 30 && e < 40)
			{
				return true;
			}else
			{
				return false;
			}
		}else
		{
			return true;
		}
	}
	
	//
	private static final double mcqwye10 = 8.00;
	private static final double mcqwye11 = 0;
	private static final double mcqwye12 = 0;
	private static final double mcqwye13 = 0;
	private static final double mcqwye14 = 0;
	
	private static final double mcqwye20 = 10;
	private static final double mcqwye21 = 11;
	private static final double mcqwye22 = 12;
	private static final double mcqwye23 = 13;
	private static final double mcqwye24 = 14;
	
	//
	private static final double mcqwyee10 = 0.10;
	private static final double mcqwyee11 = 0;
	private static final double mcqwyee12 = 0;
	private static final double mcqwyee13 = 0;
	private static final double mcqwyee14 = 10.84;
	
	private static final double mcqwyee20 = 10;
	private static final double mcqwyee21 = 11;
	private static final double mcqwyee22 = 12;
	private static final double mcqwyee23 = 13;
	private static final double mcqwyee24 = 14;
	//
	private static final double mcqwya10 = 1.20;
	private static final double mcqwya11 = 0;
	private static final double mcqwya12 = 0;
	private static final double mcqwya13 = 0;
	private static final double mcqwya14 = 0;
	
	private static final double mcqwya20 = 10;
	private static final double mcqwya21 = 11;
	private static final double mcqwya22 = 12;
	private static final double mcqwya23 = 13;
	private static final double mcqwya24 = 14;
	//
	private static final double mcqwyb10 = 0.80;
	private static final double mcqwyb11 = 0;
	private static final double mcqwyb12 = 0;
	private static final double mcqwyb13 = 0;
	private static final double mcqwyb14 = 0;
	
	private static final double mcqwyb20 = 10;
	private static final double mcqwyb21 = 11;
	private static final double mcqwyb22 = 12;
	private static final double mcqwyb23 = 13;
	private static final double mcqwyb24 = 14;
	//
	private static final double mcqwyc10 = 0.80;
	private static final double mcqwyc11 = 0;
	private static final double mcqwyc12 = 0;
	private static final double mcqwyc13 = 0;
	private static final double mcqwyc14 = 0;
	
	private static final double mcqwyc20 = 10;
	private static final double mcqwyc21 = 11;
	private static final double mcqwyc22 = 12;
	private static final double mcqwyc23 = 13;
	private static final double mcqwyc24 = 14;
	//
	private static final double mcqwyd10 = 4.00;
	private static final double mcqwyd11 = 0;
	private static final double mcqwyd12 = 0;
	private static final double mcqwyd13 = 0;
	private static final double mcqwyd14 = 10.84;
	
	private static final double mcqwyd20 = 10;
	private static final double mcqwyd21 = 11;
	private static final double mcqwyd22 = 12;
	private static final double mcqwyd23 = 13;
	private static final double mcqwyd24 = 14;
	//
	private static final double mcqwyf10 = 2.00;
	private static final double mcqwyf11 = 0;
	private static final double mcqwyf12 = 0;
	private static final double mcqwyf13 = 0;
	private static final double mcqwyf14 = 10.84;
	
	private static final double mcqwyf20 = 10;
	private static final double mcqwyf21 = 11;
	private static final double mcqwyf22 = 12;
	private static final double mcqwyf23 = 13;
	private static final double mcqwyf24 = 14;
	

}
