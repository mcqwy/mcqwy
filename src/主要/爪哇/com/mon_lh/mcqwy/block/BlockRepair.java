package com.mon_lh.mcqwy.block;

import javax.annotation.Nullable;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.creativetab.CreativeTabsLoader;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;
import com.mon_lh.mcqwy.tileentity.TileEntityRepair;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockRepair extends BlockContainer{
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool BURNING = PropertyBool.create("burning");

	public BlockRepair()
    {
        super(Material.GROUND);
        this.setHardness(0.5F);
        this.setCreativeTab(CreativeTabsLoader.tabMcqwy);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
        		.withProperty(BURNING, Boolean.FALSE));
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (!worldIn.isRemote)
        {
			int id = GuiElementLoader.GUI_REPAIR;
            playerIn.openGui(Mcqwy.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int facing = state.getValue(FACING).getHorizontalIndex();
		int burning = state.getValue(BURNING).booleanValue() ? 4 : 0;
		return facing | burning;
	}
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
		EnumFacing facing = EnumFacing.getHorizontal(meta & 3);
		Boolean burning = Boolean.valueOf((meta & 4) != 0);
		return this.getDefaultState().withProperty(FACING, facing).withProperty(BURNING, burning);
    }
	
	@Override
    protected BlockStateContainer createBlockState()
    {
		return new BlockStateContainer(this,new IProperty<?>[]{FACING,BURNING});
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
    }
	
	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
		TileEntity tile = worldIn.getTileEntity(pos);
        
        if (tile instanceof TileEntityRepair)
        {
        	IItemHandler up = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
            IItemHandler down = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
            IItemHandler east = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        	for (int i = up.getSlots() - 1; i >= 0; --i)
            {
                if (up.getStackInSlot(i) != null)
                {
                    Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(i));
                }
            }

            for (int i = down.getSlots() - 1; i >= 0; --i)
            {
                if (down.getStackInSlot(i) != null)
                {
                    Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(i));
                }
            }
            
            for (int i = east.getSlots() - 1; i >= 0; --i)
            {
                if (east.getStackInSlot(i) != null)
                {
                    Block.spawnAsEntity(worldIn, pos, east.getStackInSlot(i));
                }
            }
            Block.spawnAsEntity(worldIn, pos, new ItemStack(BlockLoader.repair));
        }
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
		if(state.getBlock() == Blocks.AIR)
		{
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityFurnace)te);
			worldIn.removeTileEntity(pos);
		}
    }
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRepair();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

}
