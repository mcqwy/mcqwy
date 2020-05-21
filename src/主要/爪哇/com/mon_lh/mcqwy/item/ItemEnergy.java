package com.mon_lh.mcqwy.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

public class ItemEnergy extends Item
{
	public ItemEnergy()
    {
        super();
        this.setUnlocalizedName("parts");
        this.setRegistryName("parts");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }
	
	public static enum IceSkillTypes implements IStringSerializable {
        NLPJA("nlpja",0),
        NLPJB("nlpjb",1),
        NLPJC("nlpjc",2),
        NLPJD("nlpjd",3),
        NLPJE("nlpje",4),
        NLPJF("nlpjf",5),
        NLPJG("nlpjg",6),
        NLPJH("nlpjh",7),
        NLPJI("nlpji",8),
        NLPJJ("nlpjj",9),
        NLPJK("nlpjk",10),
        ;


        private int ID;
        private String name;
        
        private IceSkillTypes(String nameIn,int IDIn){
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
			for(int i = 0;i < IceSkillTypes.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSkillTypes.values()[stack.getMetadata()].getName();
    }

}
