package com.mon_lh.mcqwy.tileentity;

import com.mon_lh.mcqwy.block.BlockReform;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.weight.McqwyWeight;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
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
public class TileEntityAssembling extends TileEntity implements ITickable, IEnergySink
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
    		ItemStack a = pjInventorya.extractItem(0, 1, true);
    		ItemStack b = pjInventoryb.extractItem(0, 1, true);
            IBlockState state = this.world.getBlockState(pos);
            if (!itemStack.isEmpty() && itemStack.getItem() == ItemLoader.energyloop && itemStack.getTagCompound() != null && downInventory.insertItem(0, itemStack, true).isEmpty() && a.getItem() == ItemLoader.refit && b.getItem() == Items.NETHER_STAR)
            {
            	int i = a.getMetadata();
            	NBTTagCompound nbt = itemStack.getTagCompound().getCompoundTag("mcqwy");
            	if(i == 5 || (i == 2 && nbt.getInteger("mcqwyc") >= 2) || (i == 3 && nbt.getInteger("mcqwye") >= 2) || (i == 4 && nbt.getInteger("mcqwyf") >= 2))
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
                				if(i == 2)
            					{
                					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyc", 10);
                					System.out.println(itemStack.getTagCompound().getCompoundTag("mcqwy").getString("username") + " \u88c5\u914d\u4e86" + a.getMetadata());
                					pjInventorya.extractItem(0, 1, false);
                					pjInventoryb.extractItem(0, 1, false);
            					}
                				if(i == 3)
                				{
                					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwye", 10);
                					System.out.println(itemStack.getTagCompound().getCompoundTag("mcqwy").getString("username") + " \u88c5\u914d\u4e86" + a.getMetadata());
                					pjInventorya.extractItem(0, 1, false);
                					pjInventoryb.extractItem(0, 1, false);
                				}
                				if(i == 4)
                				{
                					itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("mcqwyf", 10);
                					System.out.println(itemStack.getTagCompound().getCompoundTag("mcqwy").getString("username") + " \u88c5\u914d\u4e86" + a.getMetadata());
                					pjInventorya.extractItem(0, 1, false);
                					pjInventoryb.extractItem(0, 1, false);
                				}
                				if(i == 5)
                				{
                					int armor = McqwyWeight.getArmorSize(nbt.getInteger("mcqwya"), nbt.getInteger("mcqwyb"), nbt.getInteger("mcqwyc"),nbt.getInteger("mcqwyd"), nbt.getInteger("mcqwye"), nbt.getInteger("mcqwyf"));
                					int count = pjInventorya.extractItem(0, 64, true).getCount();
                					if(armor >= nbt.getInteger("armor") + count)
                					{
                						pjInventorya.extractItem(0, count, false);
                						itemStack.getTagCompound().getCompoundTag("mcqwy").setInteger("armor", nbt.getInteger("armor") + count);
                					}
                				}
                				upInventory.extractItem(0, 1, false);
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
