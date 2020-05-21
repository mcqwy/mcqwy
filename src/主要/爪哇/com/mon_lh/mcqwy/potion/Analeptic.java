package com.mon_lh.mcqwy.potion;

import java.awt.Color;

import com.mon_lh.mcqwy.Mcqwy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Analeptic extends Potion{

	private static final ResourceLocation res = new ResourceLocation(Mcqwy.MODID + ":" + "textures/gui/Analeptic.png");
	
	public Analeptic() {
		super(false, new Color(88,178,220).getRGB());
        this.setPotionName("effect.analeptic");
        this.setRegistryName(Mcqwy.MODID, "analeptic");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
    {
		mc.getTextureManager().bindTexture(res);
		Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
            mc.getTextureManager().bindTexture(res);
            Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }
}
