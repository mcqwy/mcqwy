package com.mon_lh.mcqwy.item;

import java.util.List;

import javax.annotation.Nullable;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlate extends Item{
	
	public ItemPlate()
    {
        super();
        this.setUnlocalizedName("plate");
        this.setRegistryName("plate");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }
	
	public static enum IceSkillType implements IStringSerializable {
        NLPJA("platea",0),
        NLPJB("plateb",1),
        NLPJC("platec",2),
        NLPJD("plated",3),
        NLPJE("platee",4),
        NLPJF("platef",5),
        NLPJG("plateg",6),
        ;


        private int ID;
        private String name;
        
        private IceSkillType(String nameIn,int IDIn){
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
			for(int i = 0;i < IceSkillType.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSkillType.values()[stack.getMetadata()].getName();
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn)
    {
		int i = stack.getMetadata();
		if(i == 1)
		{
			list.add(" \u6539\u9020\u90e8\u4f4d: C\u88c5\u7f6e");
		}
		if(i == 3)
		{
			list.add(" \u6539\u9020\u90e8\u4f4d: \u5f15 \u64ce");
		}
		if(i == 5)
		{
			list.add(" \u6539\u9020\u90e8\u4f4d: S-E");
		}
		if(i == 6)
		{
			list.add(" \u6539\u9020\u90e8\u4f4d: \u526f C");
		}
    }

}
