package com.mon_lh.mcqwy.block;

import java.util.Random;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.common.EventLoader;
import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;
import com.mon_lh.mcqwy.item.ItemLoader;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockGrassBlock extends Block{
	
	public BlockGrassBlock()
    {
        super(Material.GROUND);
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabsLoader.tabMcqwy);
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return true;
    }

}
