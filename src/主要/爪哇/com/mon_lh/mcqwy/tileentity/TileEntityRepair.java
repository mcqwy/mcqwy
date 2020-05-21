package com.mon_lh.mcqwy.tileentity;

import com.mon_lh.mcqwy.block.BlockRepair;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.weight.McqwyWeight;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.item.IC2Items;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "ic2")
public class TileEntityRepair extends TileEntity implements ITickable, IEnergySink
{
	protected int burnTime = 0;
	
	protected double rotationDegree = 0;

    protected ItemStackHandler upInventory = new ItemStackHandler();
    protected ItemStackHandler pjInventorya = new ItemStackHandler();
    protected ItemStackHandler downInventory = new ItemStackHandler();
    
    protected double receivedEnergyUnit = 0;
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            
            if(facing == EnumFacing.UP)
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)upInventory;
            	return result;
            }else if(facing == EnumFacing.DOWN)
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)downInventory;
            	return result;
            }else if(facing == EnumFacing.EAST)
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)pjInventorya;
            	return result;
            }
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.upInventory.deserializeNBT(compound.getCompoundTag("UpInventory"));
        this.pjInventorya.deserializeNBT(compound.getCompoundTag("PjInventorya"));
        this.downInventory.deserializeNBT(compound.getCompoundTag("DownInventory"));
        this.receivedEnergyUnit = compound.getDouble("ReceivedEnergyUnit");
        this.burnTime = compound.getInteger("BurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("UpInventory", this.upInventory.serializeNBT());
        compound.setTag("PjInventorya", this.pjInventorya.serializeNBT());
        compound.setTag("DownInventory", this.downInventory.serializeNBT());
        compound.setDouble("ReceivedEnergyUnit", this.receivedEnergyUnit);
        compound.setInteger("BurnTime", this.burnTime);
        return compound;
    }
    
    private boolean updated = false;
    
    @Override
    public void update()
    {
    	if (!this.world.isRemote)
        {
    		if (!this.updated && Loader.isModLoaded("ic2"))
            {
    			this.onIC2MachineLoaded();
                this.updated = true;
            }
    		
    		ItemStack itemStack = upInventory.extractItem(0, 1, true);
    		ItemStack a = pjInventorya.extractItem(0, 1, true);
            IBlockState state = this.world.getBlockState(pos);
            if (!itemStack.isEmpty() && downInventory.insertItem(0, itemStack, true).isEmpty() && a.getItem() == ItemLoader.refit && itemStack.getTagCompound() != null)
            {
            	NBTTagCompound nbt = itemStack.getTagCompound().getCompoundTag("mcqwy");
            	if(nbt != null)
            	{
            		int ai = nbt.getInteger("mcqwya");
            		int bi = nbt.getInteger("mcqwyb");
            		int ci = nbt.getInteger("mcqwyc");
            		int di = nbt.getInteger("mcqwyd");
            		int ei = nbt.getInteger("mcqwye");
            		int fi = nbt.getInteger("mcqwyf");
            		if((ai >= 20 && ai < 40) || (bi >= 20 && bi < 40) || (bi >= 20 && bi < 40) || (ci >= 20 && ci < 40) || (di >= 20 && di < 40) || (ei >= 20 && ei < 40) || (fi >= 20 && fi < 40))
            		{
            			if (downInventory.insertItem(0, itemStack, true) !=  null)
                        {
                    		double requiredEnergyPerTick = this.getRequiredEnergyPerTick();
                    		if (this.receivedEnergyUnit >= requiredEnergyPerTick)
                            {
                    			this.receivedEnergyUnit -= requiredEnergyPerTick;
                    			int burnTotalTime = this.getTotalBurnTime();
                    			this.world.setBlockState(pos, state.withProperty(BlockRepair.BURNING, Boolean.TRUE));
                    			if (++this.burnTime >= burnTotalTime)
                                {
                    				System.out.println("qqqqqqqqqqqq:" + ci + ":" + a.getMetadata());
                    				if(ei >= 20 && ei < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwye", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(ei >= 30 && ei < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwye", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(ci >= 20 && ci < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyc", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(ci >= 30 && ci < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyc", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(di >= 20 && di < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyd", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(di >= 30 && di < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyd", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(ai >= 20 && ai < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwya", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(ai >= 30 && ai < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwya", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(bi >= 20 && bi < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyb", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(bi >= 30 && bi < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyb", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(fi >= 20 && fi < 30 && a.getMetadata() == 6)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyf", 10);
                    					pjInventorya.extractItem(0, 1, false);
                    				}else if(fi >= 30 && fi < 40 && a.getMetadata() == 7)
                    				{
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyf", 10);
                    					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("Enable", 1);
                    					pjInventorya.extractItem(0, 1, false);
                    				}
                    				upInventory.extractItem(0, 1, false);
                    				this.burnTime = 0;
                                    downInventory.insertItem(0, itemStack, false);
                                    this.markDirty();
                                }
                            }
                        }
            		}
            	}
            }else
            {
                this.burnTime = 0;
                this.world.setBlockState(pos, state.withProperty(BlockRepair.BURNING, Boolean.FALSE));
            }
        }else
        {
            IBlockState blockState = this.world.getBlockState(this.pos);
            boolean burning = blockState.getProperties().containsKey(BlockRepair.BLOCK_STATE_IDS)
                    && blockState.getValue(BlockRepair.FACING).equals(blockState);
            if (burning || this.rotationDegree > 0)
            {
                this.rotationDegree += 11.25;
                if (this.rotationDegree >= 360.0)
                {
                    this.rotationDegree -= 360.0;
                }
                this.world.markBlockRangeForRenderUpdate(this.pos, this.pos);
            }
        }
    }
    
    @Override
    public void invalidate()
    {
    	super.invalidate();
        if (!this.world.isRemote && Loader.isModLoaded("ic2"))
        {
            this.onIC2MachineUnloaded();
        }
    }
    
    private double getRequiredEnergyPerTick() {
		return 300;
	}
    
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
    
    public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
    
    public static int getItemBurnTime(ItemStack stack)
    {
    	if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
        	return 200;
        }
    }
    
    public int getBurnTime()
    {
        return this.burnTime;
    }
    
    public int getReceivedEnergyUnit()
    {
		return (int) this.receivedEnergyUnit;
    }
    
    public int getTotalBurnTime() {
		return 200;
	}
    
    @Override
    @Optional.Method(modid = "ic2")
	public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
    	return true;
	}
    
    public double getMaxEnergyCapacity()
    {
        return 400000;
    }
    
    @Override
	public double getDemandedEnergy() {
    	return Math.max(0, this.getMaxEnergyCapacity() - this.receivedEnergyUnit);
	}
    
    @Override
	public int getSinkTier() {
		return 3;
	}
    
    @Override
	public double injectEnergy(EnumFacing directionFrom, double amount,
			double voltage) {
    	this.receivedEnergyUnit += amount;
		return 0;
	}
	
	

    @Optional.Method(modid = "ic2")
    private void onIC2MachineLoaded()
    {
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }

    @Optional.Method(modid = "ic2")
    private void onIC2MachineUnloaded()
    {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }
}
