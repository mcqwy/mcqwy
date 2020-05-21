package com.mon_lh.mcqwy.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionLoader {
	
	public static Potion SuperArmor = new SuperArmor();
	public static Potion Analeptic = new Analeptic();

    public PotionLoader(FMLPreInitializationEvent event)
    {
    	ForgeRegistries.POTIONS.register(SuperArmor);
    	ForgeRegistries.POTIONS.register(Analeptic);
    }

}
