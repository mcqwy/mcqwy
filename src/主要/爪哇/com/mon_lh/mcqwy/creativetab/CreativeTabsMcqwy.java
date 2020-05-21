package com.mon_lh.mcqwy.creativetab;

import com.mon_lh.mcqwy.block.BlockLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsMcqwy extends CreativeTabs{
	
	public CreativeTabsMcqwy()
    {
        super("mcqwy");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(BlockLoader.yjxwk);
    }

}
