package com.mon_lh.mcqwy.item;

import java.util.List;

import javax.annotation.Nullable;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderTooltipEvent.Color;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGoldenEgg extends Item
{
    public ItemGoldenEgg()
    {
        super();
        this.setUnlocalizedName("energy");
        this.setRegistryName("energy");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
    {
    	if (!worldIn.isRemote)
        {
    		System.out.println(player.getServer().getGameType().getID());
    		ItemStack item = player.getHeldItemMainhand();
    		if(item != null && item.getTagCompound() != null)
    		{
    			System.out.println(item.getTagCompound());
    		}
        }
    	return super.onItemRightClick(worldIn, player, hand);
    }
    
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn)
    {
    	if (stack.hasTagCompound())
        {
    		String sun = I18n.format("item.list.zc.sun");
    		String huai = I18n.format("item.list.zc.huai");
    		list.add(" ------------");
    		if(stack.getTagCompound().getCompoundTag("mcqwy").getString("username") != null)
    		{		
    			list.add(I18n.format("item.list.energy.name") + stack.getTagCompound().getCompoundTag("mcqwy").getString("username") + "        ");
    		}
    		list.add(" ");
    		list.add(I18n.format("item.list.energy.gz"));
    		
    		int a = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwya");
    		if(a == 0)
    		{
    			list.add(I18n.format("item.list.energy.zc"));
    		}else if (a == 1)
    		{
    			list.add(I18n.format("item.list.energy.zca"));
    		}else if (a == 2)
    		{
    			list.add(I18n.format("item.list.energy.zcb"));
    		}else if (a == 10)
    		{
    			list.add(I18n.format("item.list.energy.zcc"));
    		}else if (a >= 20 && a < 30)
    		{
    			list.add(I18n.format("item.list.energy.zcc") + sun);
    		}else if (a >= 30 && a < 40)
    		{
    			list.add(I18n.format("item.list.energy.zcc") + huai);
    		}
    		
    		int b = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwyb");
    		if(b == 0)
    		{
    			list.add(I18n.format("item.list.energy.fc"));
    		}else if (b == 1)
    		{
    			list.add(I18n.format("item.list.energy.fca"));
    		}else if (b == 2)
    		{
    			list.add(I18n.format("item.list.energy.fcb"));
    		}else if (b == 10)
    		{
    			list.add(I18n.format("item.list.energy.fcc"));
    		}else if (b >= 20 && b < 30)
    		{
    			list.add(I18n.format("item.list.energy.fcc") + sun);
    		}else if (b >= 30 && b < 40)
    		{
    			list.add(I18n.format("item.list.energy.fcc") + huai);
    		}
    		
    		int c = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwyc");
    		if(c == 0)
    		{
    			list.add(I18n.format("item.list.energy.czz"));
    		}else if (c == 1)
    		{
    			list.add(I18n.format("item.list.energy.czza"));
    		}else if (c == 2)
    		{
    			list.add(I18n.format("item.list.energy.czzb"));
    		}else if (c == 10)
    		{
    			list.add(I18n.format("item.list.energy.czzc"));
    		}else if (c >= 20 && c < 30)
    		{
    			list.add(I18n.format("item.list.energy.czzc") + sun);
    		}else if (c >= 30 && c < 40)
    		{
    			list.add(I18n.format("item.list.energy.czzc") + huai);
    		}
    		
    		int d = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwyd");
    		if (d >= 20 && d < 30)
    		{
    			list.add(I18n.format("item.list.energy.dp") + sun);
    		}else if (d >= 30 && d < 40)
    		{
    			list.add(I18n.format("item.list.energy.dp") + huai);
    		}else
    		{
    			list.add(I18n.format("item.list.energy.dp"));
    		}
    		
    		int e = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwye");
    		if(e == 0)
    		{
    			list.add(I18n.format("item.list.energy.yq"));
    		}else if (e == 1)
    		{
    			list.add(I18n.format("item.list.energy.yqa"));
    		}else if (e == 2)
    		{
    			list.add(I18n.format("item.list.energy.yqb"));
    		}else if (e == 10)
    		{
    			list.add(I18n.format("item.list.energy.yqc"));
    		}else if (e >= 20 && e < 30)
    		{
    			list.add(I18n.format("item.list.energy.yqc") + sun);
    		}else if (e >= 30 && e < 40)
    		{
    			list.add(I18n.format("item.list.energy.yqc") + huai);
    		}
    		
    		int f = stack.getTagCompound().getCompoundTag("mcqwy").getInteger("mcqwyf");
    		if(f == 0)
    		{
    			list.add(I18n.format("item.list.energy.se"));
    		}else if (f == 1)
    		{
    			list.add(I18n.format("item.list.energy.sea"));
    		}else if (f == 2)
    		{
    			list.add(I18n.format("item.list.energy.seb"));
    		}else if (f == 10)
    		{
    			list.add(I18n.format("item.list.energy.sec"));
    		}else if (f >= 20 && f < 30)
    		{
    			list.add(I18n.format("item.list.energy.sec") + sun);
    		}else if (f >= 30 && f < 40)
    		{
    			list.add(I18n.format("item.list.energy.sec") + huai);
    		}
    		list.add(" ");
    		list.add(I18n.format("item.list.energy.sx"));
    		
    		if(stack.getTagCompound().getCompoundTag("mcqwy").getInteger("armor") != 0)
    		{
    			list.add(I18n.format("item.list.energy.armor") + stack.getTagCompound().getCompoundTag("mcqwy").getInteger("armor"));
    		}
    		
    		
        }
    }
    
    public static enum IceSki implements IStringSerializable {
        LOOPA("loopa",0),
        ;


        private int ID;
        private String name;
        
        private IceSki(String nameIn,int IDIn){
                ID = IDIn;
                name = nameIn;
        }
        
        public String getName() {
            return this.name;
        }
        
        public int getID() {
                return ID;
        }
        
        @Override
        public String toString()
        {
            return this.name;
        }
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
		if(tab == CreativeTabsLoader.tabMcqwyItem)
		{
			for(int i = 0;i < IceSki.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
			for(int a = 0;a < 4;a++)
			{
				ItemStack item = new ItemStack(this);
				item.setTagCompound(new NBTTagCompound());
	    		NBTTagCompound nbt = new NBTTagCompound();
	    		if(a == 0)
	    		{
	    			nbt.setString("username", "");
	    			nbt.setInteger("itemname", item.getMetadata() + 1);
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
		    		item.getTagCompound().setTag("mcqwy", nbt);
					items.add(item);
	    		}else if (a ==1)
	    		{
	    			nbt.setString("username", "");
	    			nbt.setInteger("itemname", item.getMetadata() + 1);
		    		nbt.setInteger("mcqwya", 0);
		    		nbt.setInteger("mcqwyb", 0);
		    		nbt.setInteger("mcqwyc", 0);
		    		nbt.setInteger("mcqwyd", 10);
		    		nbt.setInteger("mcqwye", 0);
		    		nbt.setInteger("mcqwyf", 2);
		    		nbt.setInteger("armor", 0);
		    		nbt.setInteger("count", 0);
		    		nbt.setInteger("shellcount", 0);
		    		nbt.setInteger("Enable", 1);
		    		item.getTagCompound().setTag("mcqwy", nbt);
					items.add(item);
	    		}else if (a ==2)
	    		{
	    			nbt.setString("username", "");
	    			nbt.setInteger("itemname", item.getMetadata() + 1);
		    		nbt.setInteger("mcqwya", 0);
		    		nbt.setInteger("mcqwyb", 0);
		    		nbt.setInteger("mcqwyc", 2);
		    		nbt.setInteger("mcqwyd", 10);
		    		nbt.setInteger("mcqwye", 0);
		    		nbt.setInteger("mcqwyf", 2);
		    		nbt.setInteger("armor", 0);
		    		nbt.setInteger("count", 0);
		    		nbt.setInteger("shellcount", 0);
		    		nbt.setInteger("Enable", 1);
		    		item.getTagCompound().setTag("mcqwy", nbt);
					items.add(item);
	    		}else
	    		{
	    			nbt.setString("username", "");
	    			nbt.setInteger("itemname", item.getMetadata() + 1);
		    		nbt.setInteger("mcqwya", 0);
		    		nbt.setInteger("mcqwyb", 0);
		    		nbt.setInteger("mcqwyc", 20);
		    		nbt.setInteger("mcqwyd", 20);
		    		nbt.setInteger("mcqwye", 0);
		    		nbt.setInteger("mcqwyf", 20);
		    		nbt.setInteger("armor", 0);
		    		nbt.setInteger("count", 0);
		    		nbt.setInteger("shellcount", 0);
		    		nbt.setInteger("Enable", 1);
		    		item.getTagCompound().setTag("mcqwy", nbt);
					items.add(item);
	    		}
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSki.values()[stack.getMetadata()].getName();
    }
    
}
