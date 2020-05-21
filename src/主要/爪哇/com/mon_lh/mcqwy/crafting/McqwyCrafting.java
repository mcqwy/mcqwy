package com.mon_lh.mcqwy.crafting;

import java.util.Collections;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;

import com.mon_lh.mcqwy.item.ItemLoader;

import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.Recipes;

public class McqwyCrafting {
	
	public McqwyCrafting()
    {
        registerRecipe();
        registerSmelting();
        registerFuel();
    }
	
	@Optional.Method(modid = "ic2")
	private static void registerRecipe()
	{
		registerRecipeMetalformer(new ItemStack(Items.DIAMOND),new ItemStack(ItemLoader.plate,1,0));
		registerRecipeCompressor(new ItemStack(ItemLoader.plate,9,0),new ItemStack(ItemLoader.plate,1,1));
		
		registerRecipeMetalformer(new ItemStack(IC2Items.getItemAPI().getItem("nuclear"),1,3),new ItemStack(ItemLoader.plate,1,2));
		registerRecipeCompressor(new ItemStack(ItemLoader.plate,9,2),new ItemStack(ItemLoader.plate,1,3));
		
		registerRecipeMetalformer(new ItemStack(IC2Items.getItemAPI().getItem("ingot"),1,4),new ItemStack(ItemLoader.plate,1,4));
		registerRecipeCompressor(new ItemStack(ItemLoader.plate,9,4),new ItemStack(ItemLoader.plate,1,5));
		Recipes.semiFluidGenerator.addFluid("ic2pahoehoe_lava", 10000, 10);
		Recipes.fluidHeatGenerator.addFluid("ic2pahoehoe_lava", 300, 100);
	}
	
	@Optional.Method(modid = "ic2")
	private static void registerRecipeMetalformer(ItemStack inputstack,ItemStack output)
    {
		IRecipeInput input = Recipes.inputFactory.forStack(inputstack);
		Recipes.metalformerRolling.addRecipe(input, Collections.singletonList(output), null, false);
    }
	
	@Optional.Method(modid = "ic2")
	private static void registerRecipeCompressor(ItemStack inputstack,ItemStack output)
    {
		IRecipeInput input = Recipes.inputFactory.forStack(inputstack);
		Recipes.compressor.addRecipe(input, Collections.singletonList(output), null, false);
    }

    private static void registerSmelting()
    {

    }

    private static void registerFuel()
    {

    }

}
