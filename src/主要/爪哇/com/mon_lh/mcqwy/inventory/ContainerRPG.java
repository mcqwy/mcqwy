package com.mon_lh.mcqwy.inventory;

import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityRPG;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerRPG extends Container
{
	private IItemHandler upItems;
	private IItemHandler upItemma;
	private IItemHandler upItemmb;
	private IItemHandler upItemmc;
    private IItemHandler downItems;

    protected TileEntityRPG tileEntity;

    protected int burnTime = 0;
    
    protected int end = 0;
    
    protected ItemStack rpgitem = null;

    public ContainerRPG(EntityPlayer player, TileEntity tileEntit)
    {
        super();
        this.upItems = tileEntit.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.downItems = tileEntit.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.upItemma = tileEntit.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        this.upItemmb = tileEntit.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.WEST);
        this.upItemmc = tileEntit.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        
        ItemStack itemstack = player.getHeldItemMainhand();
        
        this.addSlotToContainer(new SlotItemHandler(this.upItems, 0, 38, 45)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {//是否可以放入
        		if(stack != null && stack.getItem() == upItemmc.extractItem(0, 1, true).getItem() && end == 1 && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() == null)
        			{
        				stack.setTagCompound(new NBTTagCompound());
    		    		NBTTagCompound nbt = new NBTTagCompound();
    		    		nbt.setString("playername", player.getName());
    		    		nbt.setInteger("player", 1);
    		    		stack.setTagCompound(nbt);
    		    		return true;
        			}else if(stack.getTagCompound().getString("playername") == player.getName())
        			{
        				NBTTagCompound nbt = stack.getTagCompound();
    		    		nbt.setInteger("player", 1);
    		    		stack.setTagCompound(nbt);
        				return true;
        			}else if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
            		{
            			return true;
            		}
        		}
        		return false;
            }
        	
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
        		if(end == 1 && this.getStack().getTagCompound() != null)
        		{
        			String name = this.getStack().getTagCompound().getString("playername");
        			if(name.equals(playerIn.getName()))
        			{
        				return true;
        			}
        		}
        		if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
                return false;
            }
        	
        	@Override
            public void onSlotChanged()
            {
        		
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemma, 0, 70, 45)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack != null && stack.getItem() == upItemmc.extractItem(0, 1, true).getItem() && end == 1 && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() == null)
        			{
        				stack.setTagCompound(new NBTTagCompound());
    		    		NBTTagCompound nbt = new NBTTagCompound();
    		    		nbt.setString("playername", player.getName());
    		    		nbt.setInteger("player", 2);
    		    		stack.setTagCompound(nbt);
    		    		return true;
        			}else if(stack.getTagCompound().getString("playername") == player.getName())
        			{
        				NBTTagCompound nbt = stack.getTagCompound();
    		    		nbt.setInteger("player", 2);
    		    		stack.setTagCompound(nbt);
        				return true;
        			}else if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
            		{
            			return true;
            		}
        		}
        		return false;
            }
        	
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
        		if(end == 1 && this.getStack().getTagCompound() != null)
        		{
        			String name = this.getStack().getTagCompound().getString("playername");
        			if(name.equals(playerIn.getName()))
        			{
        				return true;
        			}
        		}
        		if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
                return false;
            }
        	
        	@Override
            public void onSlotChanged()
            {
        		
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemmb, 0, 102, 45)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack != null && stack.getItem() == upItemmc.extractItem(0, 1, true).getItem() && end == 1 && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() == null)
        			{
        				stack.setTagCompound(new NBTTagCompound());
    		    		NBTTagCompound nbt = new NBTTagCompound();
    		    		nbt.setString("playername", player.getName());
    		    		nbt.setInteger("player", 3);
    		    		stack.setTagCompound(nbt);
    		    		return true;
        			}else if(stack.getTagCompound().getString("playername") == player.getName())
        			{
        				NBTTagCompound nbt = stack.getTagCompound();
    		    		nbt.setInteger("player", 3);
    		    		stack.setTagCompound(nbt);
        				return true;
        			}else if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
            		{
            			return true;
            		}
        		}
        		return false;
            }
        	
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
        		if(end == 1 && this.getStack().getTagCompound() != null)
        		{
        			String name = this.getStack().getTagCompound().getString("playername");
        			if(name.equals(playerIn.getName()))
        			{
        				return true;
        			}
        		}
        		if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
                return false;
            }
        	
        	@Override
            public void onSlotChanged()
            {
        		
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.downItems, 0, 134, 45)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack != null && stack.getItem() == upItemmc.extractItem(0, 1, true).getItem() && end == 1 && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() == null)
        			{
        				stack.setTagCompound(new NBTTagCompound());
    		    		NBTTagCompound nbt = new NBTTagCompound();
    		    		nbt.setString("playername", player.getName());
    		    		nbt.setInteger("player", 4);
    		    		stack.setTagCompound(nbt);
    		    		return true;
        			}else if(stack.getTagCompound().getString("playername") == player.getName())
        			{
        				NBTTagCompound nbt = stack.getTagCompound();
    		    		nbt.setInteger("player", 4);
    		    		stack.setTagCompound(nbt);
        				return true;
        			}else if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
            		{
            			return true;
            		}
        		}
        		return false;
            }
        	
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
        		if(end == 1 && this.getStack().getTagCompound() != null)
        		{
        			String name = this.getStack().getTagCompound().getString("playername");
        			if(name.equals(playerIn.getName()))
        			{
        				return true;
        			}
        		}
        		if(itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
                return false;
            }
        	
        	@Override
            public void onSlotChanged()
            {
        		
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemmc, 0, 15, 45)
        {
    		@Override
            public boolean isItemValid(ItemStack stack)
            {
    			if(stack != null && itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
        		return false;
            }
    		
    		@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
        		if(end == 1 && this.getStack() != null && itemstack.getItem() == ItemLoader.rpg && itemstack.getMetadata() == new ItemStack(ItemLoader.rpg,1,1).getMetadata())
        		{
        			return true;
        		}
                return false;
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
        }
        
        this.tileEntity = (TileEntityRPG) tileEntit;
        rpgitem = upItemmc.extractItem(0, 1, true);
        end = this.tileEntity.getEnable();
        
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
    	BlockPos pos = tileEntity.getPos();
    	Block block = playerIn.world.getBlockState(pos).getBlock();
    	if(block == Blocks.AIR)
    	{
    		return false;
    	}else
    	{
    		return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 24;
    	}
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);
        switch (id)
        {
        case 0:
            this.burnTime = data;
            break;
        case 2:
            this.end = data;
            break;
        default:
            break;
        }
    }
    
    
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return ItemStack.EMPTY;
    }
	
	public int getBurnTime()
    {
        return this.burnTime;
    }
	
	public int getEnd()
    {
        return this.end;
    }
	
	public void setEnd(int i)
    {
		if(this.upItemmc.extractItem(0, 1, true) != null &&  this.upItems.extractItem(0, 1, true) != null)
		{
			this.tileEntity.setEnable(i);
		}
    }

	public int getTotalBurnTime() {
		return this.tileEntity.getTotalBurnTime();
	}
	
	public ItemStack getRPGitem() {
		return this.rpgitem;
	}
	
	
	@Override
    public void detectAndSendChanges()
    {
		super.detectAndSendChanges();
		this.burnTime = tileEntity.getBurnTime();
		this.end = tileEntity.getEnable();
		this.rpgitem = tileEntity.getRPGitem();
        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);
            icontainerlistener.sendWindowProperty(this, 0, this.burnTime);
            icontainerlistener.sendWindowProperty(this, 2, this.end);
        }
    }
}
