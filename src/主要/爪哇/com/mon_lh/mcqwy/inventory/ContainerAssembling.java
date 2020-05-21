package com.mon_lh.mcqwy.inventory;

import ic2.api.item.IC2Items;

import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityAssembling;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
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

public class ContainerAssembling extends Container
{
	private IItemHandler upItems;
	private IItemHandler upItemma;
	private IItemHandler upItemmb;
    private IItemHandler downItems;

    protected TileEntityAssembling tileEntity;

    protected int burnTime = 0;
    
    protected int ReceivedEnergyUnit = 0;

    public ContainerAssembling(EntityPlayer player, TileEntity tileEntity)
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
        			if(stack.getTagCompound() != null)
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
        		return stack != null && stack.getItem() == ItemLoader.refit && super.isItemValid(stack);
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemmb, 0, 71, 41)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		return stack != null && stack.getItem() == Items.NETHER_STAR && super.isItemValid(stack);
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

        this.tileEntity = (TileEntityAssembling) tileEntity;
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
                else if (TileEntityAssembling.isItemFuel(itemstack1))
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
		this.ReceivedEnergyUnit = tileEntity.getReceivedEnergyUnit();
        for (int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener icontainerlistener = this.listeners.get(i);
            icontainerlistener.sendWindowProperty(this, 0, this.burnTime);
            icontainerlistener.sendWindowProperty(this, 1, this.ReceivedEnergyUnit);
        }
    }
}
