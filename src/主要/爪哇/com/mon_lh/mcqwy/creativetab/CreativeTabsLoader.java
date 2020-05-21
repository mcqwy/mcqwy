package com.mon_lh.mcqwy.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {
	
	public static CreativeTabs tabMcqwy;
	public static CreativeTabs tabMcqwyItem;

    public CreativeTabsLoader(FMLPreInitializationEvent event)
    {
        tabMcqwy = new CreativeTabsMcqwy();
        tabMcqwyItem = new CreativeTabsMcqwyItem();
    }

}
