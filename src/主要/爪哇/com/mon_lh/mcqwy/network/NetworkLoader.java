package com.mon_lh.mcqwy.network;

import com.mon_lh.mcqwy.Mcqwy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkLoader {
	
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Mcqwy.MODID);
	
	int messageCount = 0;
	
	public NetworkLoader(FMLPreInitializationEvent event)
    {
		network.registerMessage(new EnergyRing.Handler(), EnergyRing.class, messageCount++,Side.CLIENT);
    }

}
