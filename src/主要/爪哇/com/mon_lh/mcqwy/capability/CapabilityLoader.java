package com.mon_lh.mcqwy.capability;

import com.mon_lh.mcqwy.capability.ManaCapability.CapStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {

    public CapabilityLoader(FMLPreInitializationEvent event)
    {
        CapabilityManager.INSTANCE.register(IManaCapability.class, CapStorage.capStorge,
        		ManaCapability.class);
    }
    

}
