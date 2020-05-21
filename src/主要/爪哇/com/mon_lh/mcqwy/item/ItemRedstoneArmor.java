package com.mon_lh.mcqwy.item;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRedstoneArmor extends ItemArmor
{
    public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelper.addArmorMaterial("REDSTONE",
            Mcqwy.MODID + ":" + "redstone", 10, new int[]
            { 2, 6, 4, 2 }, 10, null, (float) 1);

    public ItemRedstoneArmor(EntityEquipmentSlot armorType)
    {
        super(REDSTONE_ARMOR, REDSTONE_ARMOR.ordinal(), armorType);
    }
    
    public static class Leggings extends ItemRedstoneArmor
    {
        public Leggings()
        {
            super(EntityEquipmentSlot.LEGS);
            this.setUnlocalizedName("redstoneLeggings");
            this.setCreativeTab(CreativeTabsLoader.tabMcqwyItem);
        }
    }
}
