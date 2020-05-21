package com.mon_lh.mcqwy.item;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

import ic2.api.item.IElectricItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class McqwyItem extends Item implements IElectricItem{
	
	public McqwyItem()
    {
        super();
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
    }

	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return true;
	}

	@Override
	public double getMaxCharge(ItemStack stack) {
		return 160000000;
	}

	@Override
	public int getTier(ItemStack stack) {
		return 4;
	}

	@Override
	public double getTransferLimit(ItemStack stack) {
		return 30000;
	}

}
