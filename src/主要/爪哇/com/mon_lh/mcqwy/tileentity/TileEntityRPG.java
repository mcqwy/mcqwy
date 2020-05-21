package com.mon_lh.mcqwy.tileentity;

import com.mon_lh.mcqwy.block.BlockRPG;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityRPG extends TileEntity implements ITickable
{
	protected int burnTime = 0;
	
	protected double rotationDegree = 0;

    protected ItemStackHandler upInventory = new ItemStackHandler();
    protected ItemStackHandler pjInventorya = new ItemStackHandler();
    protected ItemStackHandler pjInventoryb = new ItemStackHandler();
    protected ItemStackHandler pjInventoryc = new ItemStackHandler();
    protected ItemStackHandler downInventory = new ItemStackHandler();
    
    protected int FBTime = 0;
    

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
            }else if(facing == EnumFacing.NORTH)
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)pjInventoryb;
            	return result;
            }else
            {
            	@SuppressWarnings("unchecked")
            	T result = (T)pjInventoryc;
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
        this.pjInventoryc.deserializeNBT(compound.getCompoundTag("PjInventoryc"));
        this.downInventory.deserializeNBT(compound.getCompoundTag("DownInventory"));
        this.burnTime = compound.getInteger("BurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("UpInventory", this.upInventory.serializeNBT());
        compound.setTag("PjInventorya", this.pjInventorya.serializeNBT());
        compound.setTag("PjInventoryb", this.pjInventoryb.serializeNBT());
        compound.setTag("PjInventoryc", this.pjInventoryc.serializeNBT());
        compound.setTag("DownInventory", this.downInventory.serializeNBT());
        compound.setInteger("BurnTime", this.burnTime);
        return compound;
    }
    
    private int updated = 1;
    
    public void setEnable(int in)
    {
    	this.updated = in;
    }
    
    public int getEnable()
    {
    	return this.updated;
    }
    
    int i = 0;
    
    protected String name1 = "";
    protected String name2 = "";
    protected String name3 = "";
    protected String name4 = "";
    
    @Override
    public void update()
    {
    	if (!this.world.isRemote)
        {
    		ItemStack itemstack = upInventory.extractItem(0, 1, true);
    		
    		if(updated == 1 && pjInventoryb.extractItem(0, 1, true).getItem() != Items.AIR &&  pjInventoryc.extractItem(0, 1, true).getItem() != Items.AIR && upInventory.extractItem(0, 1, true).getItem() != Items.AIR && pjInventorya.extractItem(0, 1, true).getItem() != Items.AIR && downInventory.extractItem(0, 1, true).getItem() != Items.AIR)
    		{
    			name1 = itemstack.getTagCompound().getString("playername");
    			name2 = pjInventorya.extractItem(0, 1, true).getTagCompound().getString("playername");
    			name3 = pjInventoryc.extractItem(0, 1, true).getTagCompound().getString("playername");
    			name4 = downInventory.extractItem(0, 1, true).getTagCompound().getString("playername");
    			NBTTagCompound nbt = pjInventoryb.extractItem(0, 1, true).getTagCompound();
    			if(nbt != null)
    			{
    				int x = nbt.getCompoundTag("mcqwy").getInteger("posx");
    				int y = nbt.getCompoundTag("mcqwy").getInteger("posy");
    				int z = nbt.getCompoundTag("mcqwy").getInteger("posz");
    				FBTime = nbt.getCompoundTag("mcqwy").getInteger("time");
    				i = i + 1;
    				if(i <= 4)
    				{
    					if(i == 1)
    					{
    						EntityPlayer player = this.world.getPlayerEntityByName(name1);
        					if(player != null)
        					{
        						player.getServer().getCommandManager().executeCommand(player.getServer(), "tppos " + player.getName() + " " + x + " " + y + " " + z);
        					}
    					}else if(i == 2)
    					{
    						EntityPlayer player = this.world.getPlayerEntityByName(name2);
    						if(player != null)
        					{
        						player.getServer().getCommandManager().executeCommand(player.getServer(), "tppos " + player.getName() + " " + x + " " + y + " " + z);
        					}
    					}else if(i == 3)
    					{
    						EntityPlayer player = this.world.getPlayerEntityByName(name3);
    						if(player != null)
        					{
        						player.getServer().getCommandManager().executeCommand(player.getServer(), "tppos " + player.getName() + " " + x + " " + y + " " + z);
        					}
    					}
    					if(i == 4)
    					{
    						i = 0;
    						EntityPlayer player = this.world.getPlayerEntityByName(name4);
    						if(player != null)
        					{
        						player.getServer().getCommandManager().executeCommand(player.getServer(), "tppos " + player.getName() + " " + x + " " + y + " " + z);
        					}
    						this.updated = 2;
    					}
    				}
    				//this.updated = true;
    			}
    		}
    		
            IBlockState state = this.world.getBlockState(pos);
            
            if (updated == 2 && name1 != null && this.getTotalBurnTime() > 0)
            {
            	
            	EntityPlayer player1 = this.world.getPlayerEntityByName(name1);
				EntityPlayer player2 = this.world.getPlayerEntityByName(name2);
				EntityPlayer player3 = this.world.getPlayerEntityByName(name3);
				EntityPlayer player4 = this.world.getPlayerEntityByName(name4);
				int burnTotalTime = this.getTotalBurnTime();
				if(i == 1200)
				{
					if(player1 != null)
					{
						player1.getServer().getCommandManager().executeCommand(player1.getServer(), "msg " + player1.getName() + " \u79bb\u7ed3\u675f\u8fd8\u526960\u79d2!");
					}
					if(player2 != null)
					{
						player2.getServer().getCommandManager().executeCommand(player2.getServer(), "msg " + player2.getName() + " \u79bb\u7ed3\u675f\u8fd8\u526960\u79d2!");
					}
					if(player3 != null)
					{
						player3.getServer().getCommandManager().executeCommand(player3.getServer(), "msg " + player3.getName() + " \u79bb\u7ed3\u675f\u8fd8\u526960\u79d2!");
					}
					if(player4 != null)
					{
						player4.getServer().getCommandManager().executeCommand(player4.getServer(), "msg " + player4.getName() + " \u79bb\u7ed3\u675f\u8fd8\u526960\u79d2!");
					}
				}
    			this.world.setBlockState(pos, state.withProperty(BlockRPG.BURNING, Boolean.TRUE));
    			if (++this.burnTime >= burnTotalTime)
                {
    				upInventory.extractItem(0, 1, false);
    				pjInventorya.extractItem(0, 1, false);
    				pjInventoryc.extractItem(0, 1, false);
                    downInventory.extractItem(0, 1, false);
                    if(player1 != null)
                    {
                    	player1.getServer().getCommandManager().executeCommand(player1.getServer(), "tppos " + player1.getName() + " " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    }
                    if(player2 != null)
                    {
                    	player2.getServer().getCommandManager().executeCommand(player2.getServer(), "tppos " + player2.getName() + " " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    }
                    if(player3 !=null)
                    {
                    	player3.getServer().getCommandManager().executeCommand(player3.getServer(), "tppos " + player3.getName() + " " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    }
                    if(player4 != null)
                    {
                    	player4.getServer().getCommandManager().executeCommand(player4.getServer(), "tppos " + player4.getName() + " " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    }
    				this.name1 = "";
    				this.name2 = "";
    				this.name3 = "";
    				this.name4 = "";
					this.burnTime = 0;
    				this.updated = 1;
                    this.markDirty();
                }
            }else
            {
                this.burnTime = 0;
                this.world.setBlockState(pos, state.withProperty(BlockRPG.BURNING, Boolean.FALSE));
            }
        }else
        {
            IBlockState blockState = this.world.getBlockState(this.pos);
            boolean burning = blockState.getProperties().containsKey(BlockRPG.BLOCK_STATE_IDS)
                    && blockState.getValue(BlockRPG.FACING).equals(blockState);
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
    
    
    public int getTotalBurnTime() {
		return this.FBTime;
	}
    
    public ItemStack getRPGitem()
    {
		return this.pjInventoryc.extractItem(0, 1, true);
    }

}
