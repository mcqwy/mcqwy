package com.mon_lh.mcqwy.inventory;

import ic2.api.item.IC2Items;

import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityReform;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerReform extends Container{
	
	private IItemHandler upItems;
	private IItemHandler upItemma;
	private IItemHandler upItemmb;
    private IItemHandler downItems;

    protected TileEntityReform tileEntity;

    protected int burnTime = 0;
    
    protected int ReceivedEnergyUnit = 0;

    public ContainerReform(EntityPlayer player, TileEntity tileEntity)
    {
        super();
        this.upItems = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.downItems = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.upItemma = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        this.upItemmb = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.WEST);

        this.addSlotToContainer(new SlotItemHandler(this.upItems, 0, 44, 30)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		
        		if(stack != null && stack.getItem() == ItemLoader.energyloop && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() == null)
    		    	{
    					stack.setTagCompound(new NBTTagCompound());
    		    		NBTTagCompound nbt = new NBTTagCompound();
    		    		nbt.setString("username", player.getName());
    		    		nbt.setInteger("itemname", stack.getMetadata() + 1);
    		    		nbt.setInteger("mcqwya", 0);
    		    		nbt.setInteger("mcqwyb", 0);
    		    		nbt.setInteger("mcqwyc", 0);
    		    		nbt.setInteger("mcqwyd", 10);
    		    		nbt.setInteger("mcqwye", 0);
    		    		nbt.setInteger("mcqwyf", 0);
    		    		nbt.setInteger("armor", 0);
    		    		nbt.setInteger("count", 0);
    		    		nbt.setInteger("shellcount", 8);
    		    		nbt.setInteger("Enable", 1);
    		    		stack.getTagCompound().setTag("mcqwy", nbt);
    		    	}else if (stack.getTagCompound().getCompoundTag("mcqwy").getString("username").isEmpty())
    		    	{
    		    		if(stack.getTagCompound().hasKey("mcqwy"))
    		    		{
    		    			stack.getTagCompound().getCompoundTag("mcqwy").setString("username", player.getName());
    		    		}else
    		    		{
    		    			stack.setTagCompound(new NBTTagCompound());
        		    		NBTTagCompound nbt = new NBTTagCompound();
        		    		nbt.setString("username", player.getName());
        		    		nbt.setInteger("itemname", stack.getMetadata() + 1);
        		    		nbt.setInteger("mcqwya", 0);
        		    		nbt.setInteger("mcqwyb", 0);
        		    		nbt.setInteger("mcqwyc", 0);
        		    		nbt.setInteger("mcqwyd", 10);
        		    		nbt.setInteger("mcqwye", 0);
        		    		nbt.setInteger("mcqwyf", 0);
        		    		nbt.setInteger("armor", 0);
        		    		nbt.setInteger("count", 0);
        		    		nbt.setInteger("shellcount", 8);
        		    		nbt.setInteger("Enable", 1);
        		    		stack.getTagCompound().setTag("mcqwy", nbt);
    		    		}
    		    	}
        			return true;
        		}else
        		{
        			return false;
        		}
            }

            @Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 64;
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemma, 0, 71, 19)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		Item a = IC2Items.getItemAPI().getItem("iridium_drill");
        		if(stack != null && (stack.getItem() == ItemLoader.plate || stack.getItem() == a ) && super.isItemValid(stack))
        		{
        			if(stack.getItem() == a || stack.getMetadata() == 1 || stack.getMetadata() == 3 || stack.getMetadata() == 5 || stack.getMetadata() == 6)
        			{
        				return true;
        			}else
        			{
        				return false;
        			}
        		}else
        		{
        			return false;
        		}
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemmb, 0, 71, 41)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack != null && stack.getItem() == Items.SKULL && super.isItemValid(stack))
        		{
        			if(stack.getMetadata() == 1)
        			{
        				return true;
        			}else
        			{
        				return false;
        			}
        		}else
    			{
    				return false;
    			}
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.downItems, 0, 123, 30)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
                return false;
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

        this.tileEntity = (TileEntityReform) tileEntity;
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
        case 1:
            this.ReceivedEnergyUnit = data;
            break;
        default:
            break;
        }
    }
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 1)
            {
                if (!this.mergeItemStack(itemstack1, 4, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 2 && index != 3 && index != 0)
            {
                if (!FurnaceRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityReform.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 4 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 31 && index <= 39 && !this.mergeItemStack(itemstack1, 4, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 39, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
	
	public int getBurnTime()
    {
        return this.burnTime;
    }
    
    public double getReceivedEnergyUnit()
    {
        return this.ReceivedEnergyUnit;
    }

	public int getTotalBurnTime() {
		return this.tileEntity.getTotalBurnTime();
	}
	
	@Override
    public void detectAndSendChanges()
    {
		super.detectAndSendChanges();
		this.burnTime = tileEntity.getBurnTime();
		this.ReceivedEnergyUnit = (int) tileEntity.getDemandedEnergy();
        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);
            icontainerlistener.sendWindowProperty(this, 0, this.burnTime);
            icontainerlistener.sendWindowProperty(this, 1, (int) tileEntity.getDemandedEnergy());
        }
    }
	
}
