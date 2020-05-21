package com.mon_lh.mcqwy.item;

import java.util.List;

import javax.annotation.Nullable;

import ic2.api.item.ElectricItem;
import ic2.api.item.ICustomDamageItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import ic2.api.item.IMetalArmor;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class McqwySword extends ItemSword implements IElectricItem, IMetalArmor{
	
	public static int i;
	public static int ii;

	private static Item.ToolMaterial material;
	
	public McqwySword(ToolMaterial metarial) {
		super(metarial);
		this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
	}
	
	@Override
	public void onUpdate(ItemStack stack,World worldIn,Entity entity,int itemSlot,boolean isSelected)
	{
		try
		{
			EntityPlayer player = (EntityPlayer)entity;
			if(player instanceof EntityPlayer)
			{
				ItemStack item = player.getHeldItemMainhand();
				if(item.getItem() == ItemLoader.lanyingSword || item.getItem() == ItemLoader.zichenSword || item.getItem() == ItemLoader.qingfengSword || item.getItem() == ItemLoader.qinghongSword || item.getItem() == ItemLoader.zhanrenSword)
				{
					if(ElectricItem.manager.canUse(item,10))
					{
						ElectricItem.manager.use(item,10, (EntityPlayer) entity);
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
            return;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
		return super.onItemRightClick(worldIn, playerIn, hand);
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn)
    {
    	list.add("\u5207\u52ff\u5728\u6ca1\u7535\u65f6\u4f7f\u7528");
    }

	@Override
	public boolean isMetalArmor(ItemStack itemstack, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return false;
	}

	@Override
	public double getMaxCharge(ItemStack stack) {
		return 300000;
	}

	@Override
	public int getTier(ItemStack stack) {
		return 1;
	}

	@Override
	public double getTransferLimit(ItemStack stack) {
		return 5;
	}
	
}
