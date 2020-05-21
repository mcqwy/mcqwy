package com.mon_lh.mcqwy.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyLoader {
	
	public static KeyBinding showTime;

    public KeyLoader()
    {
        KeyLoader.showTime = new KeyBinding("key.mcqwy.showTime", Keyboard.KEY_R, "key.categories.mcqwy");

        ClientRegistry.registerKeyBinding(KeyLoader.showTime);
    }

}
