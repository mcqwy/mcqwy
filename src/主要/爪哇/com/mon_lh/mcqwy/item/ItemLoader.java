package com.mon_lh.mcqwy.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoader {
	
	public static final Item.ToolMaterial QINGFENG = EnumHelper.addToolMaterial("QINGFENG", 30, 27, 16.0F, 36.0F, 2);
	public static final Item.ToolMaterial ZICHEN = EnumHelper.addToolMaterial("ZICHEN", 30, 57, 16.0F, 46.0F, 2);
	public static final Item.ToolMaterial LANYING = EnumHelper.addToolMaterial("LANYING", 27, 16, 16.0F, 26.0F, 2);
	public static final Item.ToolMaterial QINGHONG = EnumHelper.addToolMaterial("QINGHONG", 80, 16, 16.0F, 56.0F, 2);
	public static final Item.ToolMaterial ZHANREN = EnumHelper.addToolMaterial("ZHANREN", 80, 16, 16.0F, 56.0F, 2);
	
	public static ItemGoldenEgg energyloop = new ItemGoldenEgg();
	
	public static Item qingfengSword = new McqwySword(QINGFENG).setUnlocalizedName("swordqingfeng");
	public static Item zichenSword = new McqwySword(ZICHEN).setUnlocalizedName("swordzichen");
	public static Item lanyingSword = new McqwySword(LANYING).setUnlocalizedName("swordlanying");
	public static Item qinghongSword = new McqwySword(QINGHONG).setUnlocalizedName("swordqinghong");
	public static Item zhanrenSword = new McqwySword(ZHANREN).setUnlocalizedName("swordzhanren");
	
	public static Item nlccq = new McqwyItem().setUnlocalizedName("nlccq");
	
	public static ItemEnergy nlpj = new ItemEnergy();
	public static ItemPlate plate = new ItemPlate();
	public static ItemParts refit = new ItemParts();
	public static McqwyFood food = new McqwyFood();
	public static ItemRPG rpg = new ItemRPG();
	

    public ItemLoader(FMLPreInitializationEvent event)
    {
    	register(nlpj);
    	register(plate);
    	register(refit);
    	register(food);
    	register(rpg);
    	
        register(energyloop);
        
        register(nlccq, "nl_ccq");
        
        register(qingfengSword, "mcqwy_qingfeng");
        register(zichenSword, "mcqwy_zichen");
        register(lanyingSword, "mcqwy_lanying");
        register(qinghongSword, "mcqwy_qinghong");
        register(zhanrenSword, "mcqwy_zhanren");
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
    	for(int i = 0;i < ItemEnergy.IceSkillTypes.values().length; i++)
    	{
    		registerRender(nlpj,i,ItemEnergy.IceSkillTypes.values()[i].getName());
    	};
    	for(int i = 0;i < ItemPlate.IceSkillType.values().length; i++)
    	{
    		registerRender(plate,i,ItemPlate.IceSkillType.values()[i].getName());
    	};
    	for(int i = 0;i < ItemParts.IceSkill.values().length; i++)
    	{
    		registerRender(refit,i,ItemParts.IceSkill.values()[i].getName());
    	};
    	for(int i = 0;i < ItemGoldenEgg.IceSki.values().length; i++)
    	{
    		registerRender(energyloop,i,ItemGoldenEgg.IceSki.values()[i].getName());
    	};
    	for(int i = 0;i < McqwyFood.IceSkillFood.values().length; i++)
    	{
    		registerRender(food,i,McqwyFood.IceSkillFood.values()[i].getName());
    	};
    	for(int i = 0;i < ItemRPG.IceSkillTypeee.values().length; i++)
    	{
    		registerRender(rpg,i,ItemRPG.IceSkillTypeee.values()[i].getName());
    	};
    	
        registerRender(nlccq);
        
        registerRender(qingfengSword);
        registerRender(zichenSword);
        registerRender(lanyingSword);
        registerRender(qinghongSword);
        registerRender(zhanrenSword);
    }
    
    private static void register(Item item)
    {
    	ForgeRegistries.ITEMS.register(item);
    }

    private static void register(Item item, String name)
    {
    	ForgeRegistries.ITEMS.register(item.setRegistryName(name));
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item,int meta,String name)
    {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName() + "_" + name, "inventory");
        ModelLoader.setCustomModelResourceLocation(item, meta, model);
    }

}
