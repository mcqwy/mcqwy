package com.mon_lh.mcqwy.client.gui;

import java.io.IOException;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.inventory.ContainerRPG;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRPG extends GuiContainer{
	
	private static final String TEXTURE_PATH = Mcqwy.MODID + ":" + "textures/gui/container/gui_rpg.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    protected ContainerRPG inventory;

    private int totalBurnTime;

    public GuiRPG(ContainerRPG inventorySlotsIn)
    {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 156;
        this.inventory = inventorySlotsIn;
        this.totalBurnTime = inventorySlotsIn.getTotalBurnTime();
    }
    

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    	GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int burnTime = this.inventory.getBurnTime();
        int textureWidth = 1 + (int) Math.ceil(22.0 * burnTime / this.totalBurnTime);
        this.drawTexturedModalRect(offsetX + 92, offsetY + 30, 0, 156, textureWidth, 17);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
    	String name = "";
    	String time = "";
    	int end = this.inventory.getEnd();
    	ItemStack rpgitem = this.inventory.getRPGitem();
    	if(rpgitem != null && rpgitem.getTagCompound() != null && rpgitem.getTagCompound().getCompoundTag("mcqwy") != null)
    	{
    		name = rpgitem.getTagCompound().getCompoundTag("mcqwy").getString("fbname");
    		time = (rpgitem.getTagCompound().getCompoundTag("mcqwy").getInteger("time") / 20) + "s";
    	}
    	String result = String.valueOf(I18n.format("rpg.list.cj"));
    	String resulta = String.valueOf(I18n.format("rpg.list.sj"));
    	String resultb = String.valueOf(I18n.format("rpg.list.xq"));
    	this.fontRenderer.drawString(result + name, (this.xSize - this.fontRenderer.getStringWidth(result)) / 2 - 40, 9, 0x404040);
    	this.fontRenderer.drawString(resulta + time, (this.xSize - this.fontRenderer.getStringWidth(result)) / 2 - 40, 20, 0x404040);
    	if(end == 2)
    	{
    		this.fontRenderer.drawString(resultb + " \u221a", (this.xSize - this.fontRenderer.getStringWidth(result)) / 2 - 40, 31, 0x404040);
    	}else
    	{
    		this.fontRenderer.drawString(resultb + " \u00d7", (this.xSize - this.fontRenderer.getStringWidth(result)) / 2 - 40, 31, 0x404040);
    	}
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
    	super.drawScreen(mouseX, mouseY, partialTick);
    	this.renderHoveredToolTip(mouseX, mouseY);
    }

}
