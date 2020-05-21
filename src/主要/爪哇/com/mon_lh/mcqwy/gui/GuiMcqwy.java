package com.mon_lh.mcqwy.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMcqwy extends GuiScreen{
	
	private GuiScreen parentScreen;
	private GuiButton btnClose = new GuiButton(223, 0, 0, "\u8fd4\u56de");;
	private GuiButton back = new GuiButton(223, 0, 0, "/back");
	private GuiButton sethome = new GuiButton(223, 0, 0, "\u8bbe\u7f6e\u5bb6");
	private GuiButton home = new GuiButton(223, 0, 0, "\u56de\u5bb6/home");
	private GuiButton jinbi = new GuiButton(223, 0, 0, "\u6e38\u620f\u5e01");
	private GuiButton xingbi = new GuiButton(223, 0, 0, "\u661f\u5e01");
	private GuiButton diyu = new GuiButton(223, 0, 0, "\u5730\u72f1");
	private GuiButton modi = new GuiButton(223, 0, 0, "\u672b\u5730/\u6389\u843d");
	private GuiButton zisha = new GuiButton(223, 0, 0, "\u81ea\u6740");
	private GuiButton zhucheng = new GuiButton(223, 0, 0, "\u4e3b\u57ce");
	private GuiButton lingdi = new GuiButton(223, 0, 0, "\u9886\u5730\u4e16\u754c");
	 
    public GuiMcqwy(GuiScreen parent)
    {
         parentScreen = parent; 
    }
 
    public void initGui()
    {
    	
    	back.x = (int)(width * 0.75);
        back.y = (int)(height * 0.27);
        back.width =  98;
        sethome.x = (int)(width * 0.02);
        sethome.y = (int)(height * 0.27);
        sethome.width =  98;
        home.x = (int)(width * 0.75);
        home.y = (int)(height * 0.37);
        home.width =  98;
        diyu.x = (int)(width * 0.02);
        diyu.y = (int)(height * 0.37);
        diyu.width =  98;
        jinbi.x = (int)(width * 0.75);
        jinbi.y = (int)(height * 0.47);
        jinbi.width =  98;
        modi.x = (int)(width * 0.02);
        modi.y = (int)(height * 0.47);
        modi.width =  98;
        lingdi.x = (int)(width * 0.02);
        lingdi.y = (int)(height * 0.57);
        lingdi.width =  98;
        xingbi.x = (int)(width * 0.75);
        xingbi.y = (int)(height * 0.57);
        xingbi.width =  98;
        zhucheng.x = (int)(width * 0.02);
        zhucheng.y = (int)(height * 0.67);
        zhucheng.width =  98;
        zisha.x = (int)(width * 0.75);
        zisha.y = (int)(height * 0.67);
        zisha.width =  98;
        btnClose.x = (int)(width * 0.75);
    	btnClose.y = (int)(height * 0.8);
    	btnClose.width =  98;
    	buttonList.add(btnClose);
    	buttonList.add(back);
    	buttonList.add(sethome);
    	buttonList.add(diyu);
    	buttonList.add(home);
    	buttonList.add(jinbi);
    	buttonList.add(modi);
    	buttonList.add(xingbi);
    	buttonList.add(zisha);
    	buttonList.add(lingdi);
    	buttonList.add(zhucheng);
    }
 
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        super.drawScreen(par1,par2,par3);
    }
    
    @Override
    protected void actionPerformed(GuiButton button) {
        if(button == btnClose)
        {
        	mc.displayGuiScreen(parentScreen);
        }
        if(button == back)
        {
        	//调用指令
        	Minecraft.getMinecraft().player.sendChatMessage("/back");
        	//关闭当前gui   并回到游戏界面
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == sethome)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/sethome");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == home)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/home");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == diyu)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp dy");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == jinbi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/money");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == modi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp md");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == xingbi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/b money");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == zisha)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/suicide");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == lingdi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp zy");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == zhucheng)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp zc");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
    }

}
