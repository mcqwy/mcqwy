package com.mon_lh.mcqwy.block;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.item.ItemEnergy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

public class BlockLoader {
	
	
	public static Block yjxwk = new BlockGrassBlock().setUnlocalizedName("yjxwk");
	public static Block tzjxwk = new BlockGrassBlock().setUnlocalizedName("tzjxwk");
	
	//public static Block gaizhuangtai = new BlockMcqwy().setUnlocalizedName("gold_furnace");
	
	public static Block reform = new BlockReform().setUnlocalizedName("reform");
	public static Block assembling = new BlockAssembling().setUnlocalizedName("assembling");
	public static Block repair = new BlockRepair().setUnlocalizedName("repair");
	
	public static Block refitblock = new BlockRepair().setUnlocalizedName("refit_block");
	
	public static Block rpg = new BlockRPG().setUnlocalizedName("rpg");

    public BlockLoader(FMLPreInitializationEvent event)
    {
        register(yjxwk, "y_jxwk");
        register(tzjxwk, "tz_jxwk");
        
        register(reform, "reform");
        register(assembling, "assembling");
        register(repair, "repair");
        register(refitblock, "refit_block");
        register(rpg, "rpg");
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(yjxwk);
        registerRender(tzjxwk);
        
        registerRender(reform, 0 ,"reform");
        registerRender(assembling, 0 ,"assembling");
        registerRender(repair, 0 ,"repair");
        registerRender(refitblock, 0 ,"refit_block");
        
        registerRender(rpg, 0 ,"rpg");
    }
    
    private static void register(Block block, ItemBlock itemBlock, String name)
    {
    	ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    	ForgeRegistries.ITEMS.register(itemBlock.setRegistryName(name));
        GameData.getBlockItemMap().put(block, itemBlock);
    }

    private static void register(Block block, String name)
    {
    	ForgeRegistries.BLOCKS.register(block.setRegistryName(name));
    	ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));;
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
    
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, int meta, String name)
    {
    	ResourceLocation location = new ResourceLocation(Mcqwy.MODID, name);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(location, "inventory"));
    }

}
