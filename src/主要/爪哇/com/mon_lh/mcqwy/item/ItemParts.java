package com.mon_lh.mcqwy.item;

import java.util.List;

import javax.annotation.Nullable;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemParts extends Item{
	
	public ItemParts()
    {
        super();
        this.setUnlocalizedName("refit");
        this.setRegistryName("refit");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }
	
	public static enum IceSkill implements IStringSerializable {
        NLPJA("partsa",0),
        NLPJB("partsb",1),
        NLPJC("partsc",2),
        NLPJD("partsd",3),
        NLPJE("partse",4),
        NLPJf("partsf",5),
        NLPJG("partsg",6),
        NLPJH("partsh",7),
        ;


        private int ID;
        private String name;
        
        private IceSkill(String nameIn,int IDIn){
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
			for(int i = 0;i < IceSkill.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSkill.values()[stack.getMetadata()].getName();
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn)
    {
		int i = stack.getMetadata();
		if(i == 0)
		{
			list.add(I18n.format("item.list.zc"));
			list.add(I18n.format("item.list.zc.sm"));
		}
		if(i == 1)
		{
			list.add(I18n.format("item.list.fc"));
			list.add(I18n.format("item.list.fc.sm"));
		}
		if(i == 2)
		{
			list.add(I18n.format("item.list.czz"));
			list.add(I18n.format("item.list.czz.sm"));
		}
		if(i == 3)
		{
			list.add(I18n.format("item.list.yq"));
			list.add(I18n.format("item.list.yq.sm"));
		}
		if(i == 4)
		{
			list.add(I18n.format("item.list.se"));
			list.add(I18n.format("item.list.se.sm"));
		}
		if(i == 5)
		{
			list.add(I18n.format("item.list.zj"));
			list.add(I18n.format("item.list.zj.sm"));
		}
		if(i == 6)
		{
			list.add(I18n.format("item.list.bs"));
		}
		if(i == 7)
		{
			list.add(I18n.format("item.list.gjbs"));
		}
    }

}
