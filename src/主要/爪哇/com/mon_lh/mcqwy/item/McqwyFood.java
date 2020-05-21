package com.mon_lh.mcqwy.item;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;
import com.mon_lh.mcqwy.potion.PotionLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class McqwyFood extends ItemFood{
	
	public McqwyFood()
    {
        super(4, 0.6F, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("food");
        this.setRegistryName("food");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }
	
	@Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
		if (!worldIn.isRemote)
        {
			int i = stack.getMetadata();
			if(i == 0)
			{
				player.addPotionEffect(new PotionEffect(PotionLoader.SuperArmor, 400, 1));
			}else if (i == 1)
			{
				player.addPotionEffect(new PotionEffect(PotionLoader.Analeptic, 200, 1));
			}
        }
        super.onFoodEaten(stack, worldIn, player);
    }
	
	public static enum IceSkillFood implements IStringSerializable {
        FOODA("fooda",0),
        FOODB("foodb",1),
        FOODC("foodc",2),
        ;


        private int ID;
        private String name;
        
        private IceSkillFood(String nameIn,int IDIn){
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
			for(int i = 0;i < IceSkillFood.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSkillFood.values()[stack.getMetadata()].getName();
    }

}
