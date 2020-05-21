package com.mon_lh.mcqwy.inventory;

import com.mon_lh.mcqwy.capability.ManaCapability.CapProvider;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.weight.McqwyWeight;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container
{
	
	private ItemStackHandler items = new ItemStackHandler(7);
	
	protected Slot energyloopSlot;
	protected Slot goldSlot;
    protected Slot diamondSlot;
    protected Slot emeraldSlot;
    protected Slot ironSlot;
    protected Slot wuSlot;
    protected Slot liuSlot;
    
    public static NBTTagCompound nbtt;
	
    public ContainerDemo(EntityPlayer player)
    {
        super();
        this.addSlotToContainer(this.energyloopSlot = new SlotItemHandler(items, 0, 50, 34)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
        		if(stack.getItem() == ItemLoader.energyloop && stack.getTagCompound() != null)
        		{
        			if(stack.getTagCompound().getCompoundTag("mcqwy").getString("username").equals(player.getName()))
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
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("itemname") == 1)
        			{
        				nbtt = nbt;
        				ItemStack stack = new ItemStack(ItemLoader.energyloop);
        				stack.setTagCompound(new NBTTagCompound());
                		stack.getTagCompound().setTag("mcqwy", nbt);
            			this.putStack(stack);
        			}else
        			{
        				nbtt = null;
        			}
        		}
        	}
        	
        	@Override
            public int getItemStackLimit(ItemStack stack)
            {
                return 1;
            }
        });
        this.addSlotToContainer(this.goldSlot = new SlotItemHandler(items, 1, 100, 13 + 0 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("mcqwya") == 10)
        			{
        				ItemStack stack = new ItemStack(ItemLoader.refit,1,0);
            			this.putStack(stack);
        			}
        		}
        	}
        });
        this.addSlotToContainer(this.diamondSlot = new SlotItemHandler(items, 2, 100, 13 + 1 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("mcqwyb") == 10)
        			{
        				ItemStack stack = new ItemStack(ItemLoader.refit,1,1);
            			this.putStack(stack);
        			}
        		}
        	}

            @Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        });
        this.addSlotToContainer(this.emeraldSlot =new SlotItemHandler(items, 3, 100, 13 + 2 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("mcqwye") == 10)
        			{
        				ItemStack stack = new ItemStack(ItemLoader.refit,1,3);
            			this.putStack(stack);
        			}
        		}
        	}
        });
        this.addSlotToContainer(this.ironSlot = new SlotItemHandler(items, 4, 225, 13 + 0 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("mcqwyc") == 10)
        			{
        				ItemStack stack = new ItemStack(ItemLoader.refit,1,2);
            			this.putStack(stack);
        			}
        		}
        	}
        });
        this.addSlotToContainer(this.wuSlot = new SlotItemHandler(items, 5, 225, 13 + 1 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        	
        	{
        		NBTTagCompound nbt = CapProvider.get(player).getMana();
        		if(nbt != null)
        		{
        			if(nbt.getInteger("mcqwyf") == 10)
        			{
        				ItemStack stack = new ItemStack(ItemLoader.refit,1,4);
            			this.putStack(stack);
        			}
        		}
        	}
        });
        this.addSlotToContainer(this.liuSlot = new SlotItemHandler(items, 6, 225, 13 + 2 * 21)
        {
        	@Override
            public boolean isItemValid(ItemStack stack)
            {
				return false;
            }
        	@Override
            public boolean canTakeStack(EntityPlayer playerIn)
            {
                return false;
            }
        });
        
        
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(this.emeraldSlot = new Slot(player.inventory, j + i * 9 + 9, 8 + j * 21, 100 + i * 21){
                	
                });
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 21, 167));
        }
    }
    

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
    	return true;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return ItemStack.EMPTY;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer player)
    {
    	if(!this.energyloopSlot.getStack().isEmpty())
    	{
    		NBTTagCompound nbt = this.energyloopSlot.getStack().getTagCompound();
    		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20 + 20);
    		if(nbt != null)
    		{
    			CapProvider.get(player).setMana(nbt.getCompoundTag("mcqwy"));
    		}else
    		{
    			CapProvider.get(player).setMana(new NBTTagCompound());
    		}
    		
    		int i = nbt.getCompoundTag("mcqwy").getInteger("mcqwye");
    		if(nbt != null && (i >= 20 && i < 40) || i < 10 || nbt.getCompoundTag("mcqwy").getInteger("Enable") == 2)
    		{
    			player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 99999999, 5));
    			player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 99999999, 1));
    			if(player.world.isRemote)
    			{
    				player.sendMessage(new TextComponentString(I18n.format("player.info.tips")));
    			}
    		}
    	}else
    	{
    		player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
    		player.removePotionEffect(Potion.getPotionById(2));
    		player.removePotionEffect(Potion.getPotionById(15));
    		CapProvider.get(player).setMana(new NBTTagCompound());
    	}
    }
}

