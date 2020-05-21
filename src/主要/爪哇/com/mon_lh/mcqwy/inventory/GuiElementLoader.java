package com.mon_lh.mcqwy.inventory;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.client.gui.GuiAssembling;
import com.mon_lh.mcqwy.client.gui.GuiContainerDemo;
import com.mon_lh.mcqwy.client.gui.GuiRPG;
import com.mon_lh.mcqwy.client.gui.GuiReform;
import com.mon_lh.mcqwy.client.gui.GuiRepairI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler
{
    public static final int GUI_DEMO = 1;
    public static final int GUI_METAL_FURNACE = 2;
    public static final int GUI_ASS_ASS = 3;
    public static final int GUI_REPAIR = 4;
    public static final int GUI_RPG = 5;

    public GuiElementLoader()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Mcqwy.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
        case GUI_DEMO:
            return new ContainerDemo(player);
        case GUI_METAL_FURNACE:
            return new ContainerReform(player, world.getTileEntity(new BlockPos(x, y, z)));
        case GUI_ASS_ASS:
            return new ContainerAssembling(player, world.getTileEntity(new BlockPos(x, y, z)));
        case GUI_REPAIR:
        	return new ContainerRepairI(player, world.getTileEntity(new BlockPos(x, y, z)));
        case GUI_RPG:
        	return new ContainerRPG(player, world.getTileEntity(new BlockPos(x, y, z)));
        default:
            return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
        case GUI_DEMO:
            return new GuiContainerDemo(new ContainerDemo(player));
        case GUI_METAL_FURNACE:
        	return new GuiReform(new ContainerReform(player, world.getTileEntity(new BlockPos(x, y, z))));
        case GUI_ASS_ASS:
        	return new GuiAssembling(new ContainerAssembling(player, world.getTileEntity(new BlockPos(x, y, z))));
        case GUI_REPAIR:
        	return new GuiRepairI(new ContainerRepairI(player, world.getTileEntity(new BlockPos(x, y, z))));
        case GUI_RPG:
        	return new GuiRPG(new ContainerRPG(player, world.getTileEntity(new BlockPos(x, y, z))));
        default:
            return null;
        }
    }
}
