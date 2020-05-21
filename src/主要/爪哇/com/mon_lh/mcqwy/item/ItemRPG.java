package com.mon_lh.mcqwy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

public class ItemRPG extends Item{
	
	public ItemRPG()
    {
        super();
        this.setUnlocalizedName("irpg");
        this.setRegistryName("irpg");
        this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        this.setHasSubtypes(true);
    }
	
	public static enum IceSkillTypeee implements IStringSerializable {
        RPGA("rpga",0),
        RPGB("rpgb",1),
        ;


        private int ID;
        private String name;
        
        private IceSkillTypeee(String nameIn,int IDIn){
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
			for(int i = 0;i < IceSkillTypeee.values().length;i++){
	            items.add(new ItemStack(this,1,i));
			}
		}
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
		return super.getUnlocalizedName(stack)+ "." + IceSkillTypeee.values()[stack.getMetadata()].getName();
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn)
    {
		int i = stack.getMetadata();
		if(i == 0)
		{
			NBTTagCompound nbt = stack.getTagCompound();
			if(stack.getTagCompound() != null && nbt.getString("playername") != null && nbt.getInteger("player") == 1)
			{
				list.add(" \u00a7a1P\u961f\u957f: " + stack.getTagCompound().getString("playername"));
			}else if(stack.getTagCompound() != null && nbt.getString("playername") != null && nbt.getInteger("player") == 2)
			{
				list.add(" \u00a7a2P\u961f\u5458: " + stack.getTagCompound().getString("playername"));
			}else if(stack.getTagCompound() != null && nbt.getString("playername") != null && nbt.getInteger("player") == 3)
			{
				list.add(" \u00a7a3P\u961f\u5458: " + stack.getTagCompound().getString("playername"));
			}else if(stack.getTagCompound() != null && nbt.getString("playername") != null && nbt.getInteger("player") == 4)
			{
				list.add(" \u00a7a4P\u961f\u5458: " + stack.getTagCompound().getString("playername"));
			}
			
			if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("mcqwy"))
			{
				list.add(" \u00a7a\u573a\u666f: " + stack.getTagCompound().getCompoundTag("mcqwy").getString("fbname"));
				list.add(" \u00a7a\u5750\u6807X: " + stack.getTagCompound().getCompoundTag("mcqwy").getInteger("posx"));
				list.add(" \u00a7a\u5750\u6807Y: " + stack.getTagCompound().getCompoundTag("mcqwy").getInteger("posy"));
				list.add(" \u00a7a\u5750\u6807Z: " + stack.getTagCompound().getCompoundTag("mcqwy").getInteger("posz"));
				list.add(" \u00a7a\u65f6\u95f4: " + (stack.getTagCompound().getCompoundTag("mcqwy").getInteger("time") / 20) + "\u79d2");
			}
		}
    }

}
