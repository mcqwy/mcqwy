package com.mon_lh.mcqwy.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiXingxi extends GuiScreen{
	
	private GuiScreen parentScreen;
	private GuiButton btnClose = new GuiButton(223, 0, 0, "\u8fd4\u56de");;
	private GuiButton sethome = new GuiButton(223, 0, 0, "\u8bbe\u7f6e\u5bb6");
	private GuiButton yueqiu = new GuiButton(223, 0, 0, "\u6708\u7403");
	private GuiButton huoxing = new GuiButton(223, 0, 0, "\u706b\u661f");
	private GuiButton jinxing = new GuiButton(223, 0, 0, "\u91d1\u661f");
	private GuiButton diyu = new GuiButton(223, 0, 0, "\u5730\u72f1");
	private GuiButton modi = new GuiButton(223, 0, 0, "\u672b\u5730");
	private GuiButton gushen = new GuiButton(223, 0, 0, "\u8c37\u795e\u661f");
	private GuiButton home = new GuiButton(223, 0, 0, "\u56de\u5bb6/home");
	private GuiButton jinbi = new GuiButton(223, 0, 0, "\u6e38\u620f\u5e01");
	private GuiButton xingbi = new GuiButton(223, 0, 0, "\u661f\u5e01");
	private GuiButton diqiu = new GuiButton(223, 0, 0, "\u5730\u7403\u4e3b\u57ce");
	private GuiButton suiji= new GuiButton(223, 0, 0, "\u968f\u673a\u4f20\u9001");
	
	public GuiXingxi(GuiScreen parent)
    {
         parentScreen = parent; 
    }
	
	public void initGui()
    {
        sethome.x = (int)(width * 0.02);
        sethome.y = (int)(height * 0.27);
        sethome.width =  98;
        diyu.x = (int)(width * 0.25);
        diyu.y = (int)(height * 0.27);
        diyu.width =  98;
        yueqiu.x = (int)(width * 0.02);
        yueqiu.y = (int)(height * 0.37);
        yueqiu.width =  98;
        modi.x = (int)(width * 0.25);
        modi.y = (int)(height * 0.37);
        modi.width =  98;
        huoxing.x = (int)(width * 0.02);
        huoxing.y = (int)(height * 0.47);
        huoxing.width =  98;
        gushen.x = (int)(width * 0.25);
        gushen.y = (int)(height * 0.47);
        gushen.width =  98;
        jinxing.x = (int)(width * 0.02);
        jinxing.y = (int)(height * 0.57);
        jinxing.width =  98;
        home.x = (int)(width * 0.75);
        home.y = (int)(height * 0.27);
        home.width =  98;
        diqiu.x = (int)(width * 0.02);
        diqiu.y = (int)(height * 0.67);
        diqiu.width =  98;
        jinbi.x = (int)(width * 0.75);
        jinbi.y = (int)(height * 0.37);
        jinbi.width =  98;
        xingbi.x = (int)(width * 0.75);
        xingbi.y = (int)(height * 0.47);
        xingbi.width =  98;
        suiji.x = (int)(width * 0.75);
        suiji.y = (int)(height * 0.57);
        suiji.width =  98;
        btnClose.x = (int)(width * 0.75);
    	btnClose.y = (int)(height * 0.8);
    	btnClose.width =  98;
    	buttonList.add(btnClose);
    	buttonList.add(sethome);
    	buttonList.add(diqiu);
    	buttonList.add(home);
    	buttonList.add(yueqiu);
    	buttonList.add(huoxing);
    	buttonList.add(jinxing);
    	buttonList.add(diyu);
    	buttonList.add(modi);
    	buttonList.add(gushen);
    	buttonList.add(jinbi);
    	buttonList.add(xingbi);
    	buttonList.add(suiji);
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
        if(button == yueqiu)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp yq");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == huoxing)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp hx");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == jinxing)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp jx");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == diqiu)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp zc");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == diyu)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp dy");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == modi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/warp md");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == gushen)
        {
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == jinbi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/money");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == xingbi)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/b money");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
        if(button == suiji)
        {
        	Minecraft.getMinecraft().player.sendChatMessage("/rtp");
        	Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
    }

}
