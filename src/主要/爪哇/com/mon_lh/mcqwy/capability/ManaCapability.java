package com.mon_lh.mcqwy.capability;

import com.mon_lh.mcqwy.network.EnergyRing;
import com.mon_lh.mcqwy.network.NetworkLoader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class ManaCapability implements IManaCapability{
	
	protected NBTTagCompound nbt;
    public ManaCapability() {}
    public NBTTagCompound getMana() {
    	return this.nbt;
    }
    
    public void setMana(NBTTagCompound mana) {
        this.nbt = mana;
    }
    
    public static class CapStorage implements Capability.IStorage<IManaCapability> 
    {
        public static final CapStorage capStorge = new CapStorage();
        @Override
        public NBTBase writeNBT(Capability<IManaCapability> capability, IManaCapability instance, EnumFacing side) {
        	NBTTagCompound compound = new NBTTagCompound();
        	if(instance.getMana() != null)
        	{
        		compound.setTag("Mana", (NBTBase)instance.getMana());
        	}
        	return compound;
        }
        @Override
        public void readNBT(Capability<IManaCapability> capability, IManaCapability instance, EnumFacing side, NBTBase nbt) {
        	instance.setMana((NBTTagCompound) ((NBTTagCompound)nbt).getCompoundTag("Mana"));
        }
    }
    
    public static class CapProvider implements ICapabilityProvider, INBTSerializable 
    {
    	@CapabilityInject(IManaCapability.class)
    	public static Capability<IManaCapability> MANA_CAP = null;
    	private IManaCapability manaCapability;
    	
    	public CapProvider() {
            manaCapability = new ManaCapability();
        }
    	
    	public CapProvider(IManaCapability manaCapability) {
            this.manaCapability = manaCapability;
        }
    	
    	@Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return MANA_CAP != null && capability == MANA_CAP;
        }
    	
    	@Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if (MANA_CAP != null && capability == MANA_CAP) return (T)manaCapability;
            return null;
        }
    	
    	public static IManaCapability get(EntityPlayer player) {
            return player.hasCapability(MANA_CAP, null)? player.getCapability(MANA_CAP, null): null;
        }
    	
    	@Override
        public NBTBase serializeNBT() {
            return manaCapability.saveNBTData();
        }
    	
    	@Override
        public void deserializeNBT(NBTBase nbt) {
            manaCapability.loadNBTData((NBTTagCompound) nbt);
        }
    	
    }

	@Override
	public NBTTagCompound saveNBTData() {
		return (NBTTagCompound) CapStorage.capStorge.writeNBT(CapProvider.MANA_CAP, this, null);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound compound) {
		CapStorage.capStorge.readNBT(CapProvider.MANA_CAP, this, null, compound);
	}
	@Override
	public void dataChanged(EntityPlayer player) {
		if(player != null){
			NetworkLoader.network.sendTo(new EnergyRing(saveNBTData()), (EntityPlayerMP) player);
        }
	}

}
