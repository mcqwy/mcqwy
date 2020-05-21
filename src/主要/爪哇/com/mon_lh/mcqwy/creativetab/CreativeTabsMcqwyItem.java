package com.mon_lh.mcqwy.creativetab;

import com.mon_lh.mcqwy.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMcqwyItem extends CreativeTabs
{
	public CreativeTabsMcqwyItem()
    {
        super("mcqwyitem");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemLoader.energyloop);
    }
}
