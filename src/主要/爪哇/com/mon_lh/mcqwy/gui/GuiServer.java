package com.mon_lh.mcqwy.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiServer extends GuiScreen{
	
	private GuiScreen parentScreen;
	private GuiButton btnClose = new GuiButton(223, 0, 0, "\u8fdb\u5165\u5de5\u4e1a\u670d");
	private GuiButton xingxi = new GuiButton(223, 0, 0, "\u8fdb\u5165\u661f\u7cfb\u670d");
	
	public GuiServer(GuiScreen parent)
    {
         parentScreen = parent; 
    }
	
	public void initGui()
    {
		btnClose.x = (int)(width * 0.15);
		btnClose.y = (int)(height * 0.8);
		btnClose.width =  98;
		xingxi.x = (int)(width * 0.62);
		xingxi.y = (int)(height * 0.8);
		xingxi.width =  98;
		
		buttonList.add(btnClose);
		buttonList.add(xingxi);
    }
	
	public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        super.drawScreen(par1,par2,par3);
        drawRect((int)(width*0.1), (int)(height*0.1), (int)(width*0.9) / 2, (int)(height*0.7), 0x80FFFFFF);
        drawCenteredString(fontRenderer, "\u00a76\u00a7n\u5de5\u4e1a\u670d", (int)(width*0.2), (int)(height*0.2), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u5de5\u4e1a\u3001NPC\u3001\u80fd\u91cf\u88c5\u7f6e", (int)(width*0.25), (int)(height*0.3), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u9886\u5730\u6700\u5927\u8303\u56f4150*150\uff0c\u6700\u5927\u6570\u91cf2", (int)(width*0.3), (int)(height*0.4), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7c\u5730\u72f1\u6709\u751f\u7269\u3001\u672b\u5730\u6b7b\u4ea1\u6389\u843d  ", (int)(width*0.3), (int)(height*0.5), 0xFFFF00);
        
        drawRect((int)(width*0.55), (int)(height*0.1), (int)(width*0.9), (int)(height*0.7), 0x80FFFFFF);
        drawCenteredString(fontRenderer, "\u00a76\u00a7n\u661f\u7cfb\u670d", (int)(width*0.65), (int)(height*0.2), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u5de5\u4e1a2\u3001\u661f\u7cfb4\u3001NPC\u3001\u80fd\u91cf\u88c5\u7f6e", (int)(width*0.75), (int)(height*0.3), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u9886\u5730\u6700\u5927\u8303\u56f450*50\uff0c\u6700\u5927\u6570\u91cf3", (int)(width*0.75), (int)(height*0.4), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u9664\u4e13\u7528[PVP]\u4e16\u754c\u5916\uff0c\u6240\u6709\u4e16\u754c    ", (int)(width*0.75), (int)(height*0.5), 0xFFFF00);
        drawCenteredString(fontRenderer, "\u00a7a\u6b7b\u4ea1\u4e0d\u6389\u843d             ", (int)(width*0.75), (int)(height*0.6), 0xFFFF00);
    }
	
	@Override
    protected void actionPerformed(GuiButton button)
	{
		if(button == btnClose)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/server gongye");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
		if(button == xingxi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/server xingxi");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
	}

}
