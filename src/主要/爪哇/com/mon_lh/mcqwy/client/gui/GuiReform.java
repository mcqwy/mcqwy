package com.mon_lh.mcqwy.client.gui;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.inventory.ContainerReform;
import com.mon_lh.mcqwy.random.Random;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiReform extends GuiContainer{
	
	private static final String TEXTURE_PATH = Mcqwy.MODID + ":" + "textures/gui/container/gui_reform.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    protected ContainerReform inventory;

    private int totalBurnTime;

    public GuiReform(ContainerReform inventorySlotsIn)
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
    	String title = I18n.format("container.mcqwy.reform");
    	String result = String.valueOf(I18n.format("container.mcqwy.dianliang") + String.format("%.2f",((double)400000 - (double)this.inventory.getReceivedEnergyUnit()) / (double)400000 * 100) + "%");
    	this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 7, 0x404040);
    	this.fontRenderer.drawString(result, (this.xSize - this.fontRenderer.getStringWidth(result)) / 2 + 46, 50, 0x404040);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
    	super.drawScreen(mouseX, mouseY, partialTick);
    	this.renderHoveredToolTip(mouseX, mouseY);
    }

}
