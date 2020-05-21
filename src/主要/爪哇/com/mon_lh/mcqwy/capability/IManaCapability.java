package com.mon_lh.mcqwy.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IManaCapability {
	
	NBTTagCompound getMana();
	
	void setMana(NBTTagCompound nbtBase);
	
	NBTTagCompound saveNBTData();

	void loadNBTData(NBTTagCompound compound);
	
	void dataChanged(EntityPlayer player);

}
