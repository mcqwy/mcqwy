package com.mon_lh.mcqwy.tileentity;

import com.mon_lh.mcqwy.Mcqwy;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	
	public TileEntityLoader(FMLPreInitializationEvent event)
    {
        registerTileEntity(TileEntityReform.class, "Reform");
        registerTileEntity(TileEntityAssembling.class, "Assembling");
        registerTileEntity(TileEntityRepair.class, "repairi");
        registerTileEntity(TileEntityRPG.class, "RPG");
    }

    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, Mcqwy.MODID + ":" + id);
    }

}
