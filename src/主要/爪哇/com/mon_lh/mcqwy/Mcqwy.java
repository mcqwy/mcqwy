package com.mon_lh.mcqwy;

import com.mon_lh.mcqwy.common.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @author Mon_lh
 */
@Mod(modid = Mcqwy.MODID, name = Mcqwy.NAME, version = Mcqwy.VERSION, acceptedMinecraftVersions = "1.12.2")
public class Mcqwy {
	
	public static final String MODID = "mcqwy";
    public static final String NAME = "Mc Qwy";
    public static final String VERSION = "1.3.8";

    @Instance(Mcqwy.MODID)
    public static Mcqwy instance;
	
	@SidedProxy(clientSide = "com.mon_lh.mcqwy.client.ClientProxy", 
            serverSide = "com.mon_lh.mcqwy.common.CommonProxy")
    public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

}
