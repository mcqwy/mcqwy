package com.mon_lh.mcqwy.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.mon_lh.mcqwy.block.BlockLoader;
import com.mon_lh.mcqwy.capability.CapabilityLoader;
import com.mon_lh.mcqwy.capability.ManaCapability.CapProvider;
import com.mon_lh.mcqwy.crafting.McqwyCrafting;
import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.network.NetworkLoader;
import com.mon_lh.mcqwy.potion.PotionLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityLoader;
import com.mon_lh.mcqwy.weight.McqwyWeight;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event)
    {
		new ConfigLoader(event);
		new CreativeTabsLoader(event);
		new CapabilityLoader(event);
		new ItemLoader(event);
		new BlockLoader(event);
		new PotionLoader(event);
		new TileEntityLoader(event);
		new NetworkLoader(event);
    }

    public void init(FMLInitializationEvent event)
    {
    	new McqwyCrafting();
    	new GuiElementLoader();
    	new EventLoader();
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
