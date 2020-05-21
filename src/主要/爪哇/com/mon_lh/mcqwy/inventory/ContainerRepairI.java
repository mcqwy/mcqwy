package com.mon_lh.mcqwy.inventory;

import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityReform;
import com.mon_lh.mcqwy.tileentity.TileEntityRepair;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class ContainerRepairI extends Container{
	
	private IItemHandler upItems;
	private IItemHandler upItemma;
    private IItemHandler downItems;

    protected TileEntityRepair tileEntity;

    protected int burnTime = 0;
    
    protected int ReceivedEnergyUnit = 0;

    public ContainerRepairI(EntityPlayer player, TileEntity tileEntity)
    {
        super();
        this.upItems = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.downItems = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.upItemma = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        
        this.addSlotToContainer(new SlotItemHandler(this.upItems, 0, 56, 18)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack != null && stack.getItem() == ItemLoader.energyloop && stack.getTagCompound() != null && super.isItemValid(stack))
        		{
        			if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("mcqwy"))
        			{
        				NBTTagCompound nbt = stack.getTagCompound().getCompoundTag("mcqwy");
        				int a = nbt.getInteger("mcqwya");
        				int b = nbt.getInteger("mcqwyb");
        				int c = nbt.getInteger("mcqwyc");
        				int d = nbt.getInteger("mcqwyd");
        				int e = nbt.getInteger("mcqwye");
        				int f = nbt.getInteger("mcqwyf");
        				if((a >= 20 && a < 40) || (b >= 20 && b < 40) || (c >= 20 && c < 40) || (d >= 20 && d < 40) || (e >= 20 && e < 40) || (f >= 20 && f < 40))
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
        
        this.addSlotToContainer(new SlotItemHandler(this.upItemma, 0, 56, 41)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		ItemStack item = new ItemStack(ItemLoader.refit,1,6);
        		if(stack.getItem() == item.getItem())
        		{
        			if(stack.getMetadata() == 6)
        			{
        				return true;
        			}
        			
        			if(stack.getMetadata() == new ItemStack(ItemLoader.refit,1,7).getMetadata())
        			{
        				return true;
        			}
        			
        			return false;
        		}else
        		{
        			return false;
        		}
            }
        	
        	@Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        
        this.addSlotToContainer(new SlotItemHandler(this.downItems, 0, 110, 30)
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

        this.tileEntity = (TileEntityRepair) tileEntity;
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
                if (!this.mergeItemStack(itemstack1, 3, 38, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 2 && index != 0)
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
                else if (index >= 3 && index < 29)
                {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index <= 38 && !this.mergeItemStack(itemstack1, 3, 29, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 38, false))
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
