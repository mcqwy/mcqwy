package com.mon_lh.mcqwy.tileentity;

import com.mon_lh.mcqwy.block.BlockReform;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.random.Random;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.item.IC2Items;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
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
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.fml.common.Loader;

@Optional.Interface(iface = "ic2.api.energy.tile.IEnergySink", modid = "ic2")
public class TileEntityReform extends TileEntity implements ITickable, IEnergySink
{
	protected int burnTime = 0;
	
	protected double rotationDegree = 0;

    protected ItemStackHandler upInventory = new ItemStackHandler();
    protected ItemStackHandler pjInventorya = new ItemStackHandler();
    protected ItemStackHandler pjInventoryb = new ItemStackHandler();
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
            }else
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)pjInventoryb;
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
        this.pjInventoryb.deserializeNBT(compound.getCompoundTag("PjInventoryb"));
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
        compound.setTag("PjInventoryb", this.pjInventoryb.serializeNBT());
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
    		Item a = pjInventorya.extractItem(0, 1, true).getItem();
    		Item b = IC2Items.getItemAPI().getItem("iridium_drill");
            IBlockState state = this.world.getBlockState(pos);
            if (!itemStack.isEmpty() && downInventory.insertItem(0, itemStack, true).isEmpty() && itemStack.getItem() == ItemLoader.energyloop && (a == ItemLoader.plate || a == b) && pjInventoryb.extractItem(0, 1, true).getItem() == Items.SKULL)
            {
            	int i = pjInventorya.extractItem(0, 1, true).getMetadata();
            	int w = pjInventoryb.extractItem(0, 1, true).getMetadata();
            	//System.out.println("gggggggggggggggggggg:" + pjInventorya.extractItem(0, 1, true).getMetadata());
            	if(w == 1 && (a == b || i == 1 || i ==3 || i == 5 || i == 6 ) && itemStack.getTagCompound() != null)
            	{
            		if (downInventory.insertItem(0, itemStack, true) !=  null)
                    {
                		double requiredEnergyPerTick = this.getRequiredEnergyPerTick();
                		if (this.receivedEnergyUnit >= requiredEnergyPerTick)
                        {
                			this.receivedEnergyUnit -= requiredEnergyPerTick;
                			int burnTotalTime = this.getTotalBurnTime();
                			this.world.setBlockState(pos, state.withProperty(BlockReform.BURNING, Boolean.TRUE));
                			if (++this.burnTime >= burnTotalTime)
                            {
                				NBTTagCompound nbt = itemStack.getTagCompound().getCompoundTag("mcqwy");
            					Random and = new Random();
            					if(i == 1 && nbt.getInteger("mcqwyc") == 0)
            					{
            						if(and.getReultC())
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyc", 2);
            						}else
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyc", 1);
            						}
            					}else if (i == 3 && nbt.getInteger("mcqwye") == 0)
            					{
            						if(and.getReultE())
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwye", 2);
            						}else
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwye", 1);
            						}
            					}else if (i == 5 && nbt.getInteger("mcqwyf") == 0)
            					{
            						if(and.getReultF())
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyf", 2);
            						}else
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyf", 1);
            						}
            					}else if (a == b && nbt.getInteger("mcqwya") == 0)
            					{
            						if(and.getReultA())
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwya", 2);
            						}else
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwya", 1);
            						}
            					}else if (i == 6 && nbt.getInteger("mcqwyb") == 0)
            					{
            						if(and.getReultB())
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyb", 2);
            						}else
            						{
            							itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyb", 1);
            						}
            					}
                				upInventory.extractItem(0, 1, false);
                				pjInventorya.extractItem(0, 1, false);
                				pjInventoryb.extractItem(0, 1, false);
                				this.burnTime = 0;
                                downInventory.insertItem(0, itemStack, false);
                                
                                this.markDirty();
                            }
                        }
                    }
            	}
            }else
            {
                this.burnTime = 0;
                this.world.setBlockState(pos, state.withProperty(BlockReform.BURNING, Boolean.FALSE));
            }
        }else
        {
            IBlockState blockState = this.world.getBlockState(this.pos);
            boolean burning = blockState.getProperties().containsKey(BlockReform.BLOCK_STATE_IDS)
                    && blockState.getValue(BlockReform.FACING).equals(blockState);
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
