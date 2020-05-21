package com.mon_lh.mcqwy.client;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTextField;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.common.CommonProxy;
import com.mon_lh.mcqwy.gui.GuiMcqwy;
import com.mon_lh.mcqwy.gui.GuiServer;
import com.mon_lh.mcqwy.gui.GuiXingxi;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;
import com.mon_lh.mcqwy.network.EnergyRing;
import com.mon_lh.mcqwy.network.NetworkLoader;

public class ClientProxy extends CommonProxy{
	
	@Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        new ItemRenderLoader();
    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
        new KeyLoader();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
    
    private GuiButton qq = new GuiButton(223, 0, 0, "\u52a0\u5165Q\u7fa4:196441972");
    private GuiButton chongzhi = new GuiButton(223, 0, 0, "\u5b98\u7f51/\u5145\u503c");
    private GuiButton kongzhi = new GuiButton(223, 0, 0, "\u63a7\u5236/\u6309\u952e");
    private GuiButton yinyue = new GuiButton(223, 0, 0, "\u97f3\u4e50/\u58f0\u97f3");
    private GuiButton gamechongzhi = new GuiButton(223, 0, 0, "\u5b98\u7f51/\u5145\u503c");
    private GuiButton haoyou = new GuiButton(223, 0, 0, "\u793e\u4ea4/\u597d\u53cb");
    private GuiButton backpack = new GuiButton(223, 0, 0, "\u8de8\u670d\u80cc\u5305");
    private GuiButton shangdian = new GuiButton(223, 0, 0, "\u5546\u5e97");
    private GuiButton xingxi = new GuiButton(223, 0, 0, "\u661f\u7cfb/\u6307\u4ee4");
    private GuiButton gongye = new GuiButton(223, 0, 0, "\u5de5\u4e1a/\u6307\u4ee4");
    private GuiButton xuanze = new GuiButton(223, 0, 0, "\u9009\u62e9\u670d\u52a1\u5668");
    
    private int i = 0;
    private int ii = 0;
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiScree(InputEvent.KeyInputEvent event)
    {
    	if (Keyboard.getEventKey() == 28 && ii != 1)
    	{
    		i++;
			Robot rb=null;
        	try {
				rb = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			rb.keyPress(KeyEvent.VK_CONTROL );
        	rb.keyPress(KeyEvent.VK_SPACE);
        	rb.keyRelease(KeyEvent.VK_CONTROL);
        	rb.keyRelease(KeyEvent.VK_SPACE);
        	ii = 0;
        	if(i >= 2)
        	{
        		i = 0;
        	}
    	}else if(Keyboard.getEventKey() == 53)
    	{
    		ii++;
    		if(ii >= 2)
    		{
    			ii = 0;
    		}
    		
    	}else if(ii == 1)
		{
			ii = 0;
			if(i >= 2)
			{
				i = 0;
			}
		}else if(Keyboard.getEventKey() == 1 && i == 1)
    	{
    		Robot rb=null;
        	try {
				rb = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			rb.keyPress(KeyEvent.VK_CONTROL );
        	rb.keyPress(KeyEvent.VK_SPACE);
        	rb.keyRelease(KeyEvent.VK_CONTROL);
        	rb.keyRelease(KeyEvent.VK_SPACE);
        	i = 0;
        	ii = 0;
    	}
    	
    	
    	if(KeyLoader.showTime.isPressed())
    	{
    		NBTTagCompound nbt = new NBTTagCompound();
    		nbt.setString("mcqwy", "ss");
    		EntityPlayer player = Minecraft.getMinecraft().player;
    		NetworkLoader.network.sendToServer(new EnergyRing(nbt));
    	}
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiScreenShow(InitGuiEvent.Post event)
    {
        if(event.getGui() instanceof GuiMainMenu)
        {
            GuiScreen screen = event.getGui();
            for(Object o : event.getButtonList())
            {
                GuiButton btn = (GuiButton)o;
                if(btn.id == 4)
                {
                	btn.y = (int)(screen.height * 0.8);
                    btn.x = (int)(screen.width * 0.75);
                }
                if(btn.id == 0)
                {
                    btn.y = (int)(screen.height * 0.7);
                    btn.x = (int)(screen.width * 0.75);
                }
                if(btn.id == 1)
                {
                    btn.enabled = false;
                    btn.visible = false;
                    btn.displayString = "Q\u7fa4:196441972";
                }
                if(btn.id == 2) 
                {
                	btn.y = (int)(screen.height * 0.5);
                    btn.x = (int)(screen.width * 0.75);
                    btn.width = 98;
                	btn.displayString = "\u8fdb\u5165[\u661f\u7a7a\u4e4b\u9645]";
                }
                if(btn.id != 0 && btn.id != 1 && btn.id != 2 && btn.id != 4)
                {
                	btn.visible = false;
                }
            }
            qq.x = (int)(screen.width * 0.75);
            qq.y = (int)(screen.height * 0.6);
            qq.width =  98;
            chongzhi.x = (int)(screen.width * 0.75);
            chongzhi.y = (int)(screen.height * 0.4);
            chongzhi.width =  98;
            event.getButtonList().add(qq);
            event.getButtonList().add(chongzhi);
        }
        if(event.getGui() instanceof GuiOptions)
        {
        	GuiScreen screen = event.getGui();
            for(Object o : event.getButtonList())
            {
            	GuiButton btn = (GuiButton)o;
                if(btn.id == 1)
                {
                	
                }
            }
        }
        if(event.getGui() instanceof GuiChat)
        {
        	
        }
        if(event.getGui() instanceof GuiIngameMenu)
        {
        	GuiScreen screen = event.getGui();
            for(Object o : event.getButtonList())
            {
            	GuiButton btn = (GuiButton)o;
                if(btn.id == 0)
                {
                	btn.enabled = false;
                	btn.x = (int)(screen.width / 2) - 100;
                	btn.y = (int)(screen.height * 0.58);
                }
                if(btn.id == 4)
                {
                	btn.x = (int)(screen.width / 2) - btn.width / 2;
                	btn.y = (int)(screen.height * 0.27);
                }
                if(btn.id == 5)
                {
                	btn.x = (int)(screen.width * 0.02);
                	btn.y = (int)(screen.height * 0.38);
                }
                if(btn.id == 6)
                {
                	btn.x = (int)(screen.width * 0.75);
                	btn.y = (int)(screen.height * 0.38);
                }
                if(btn.id == 7)
                {
                	btn.visible = false;
                }
                if(btn.id == 12)
                {
                	btn.visible = false;
                	btn.x = (int)(screen.width / 2) + 2;
                	btn.y = (int)(screen.height * 0.58);
                }
                if(btn.id == 1)
                {
                	btn.x = (int)(screen.width / 2) - btn.width / 2;
                    btn.y = (int)(screen.height * 0.68);
                }
            }
            yinyue.x = (int)(screen.width / 2) - 100;
            yinyue.y = (int)(screen.height * 0.38);
            yinyue.width =  98;
            kongzhi.x = (int)(screen.width / 2) + 2;
            kongzhi.y = (int)(screen.height * 0.38);
            kongzhi.width =  98;
            gamechongzhi.x = (int)(screen.width * 0.02);
        	gamechongzhi.y = (int)(screen.height * 0.48);
        	gamechongzhi.width =  98;
            haoyou.x = (int)(screen.width / 2) - 100;
            haoyou.y = (int)(screen.height * 0.48);
            haoyou.width =  98;
            backpack.x = (int)(screen.width / 2) + 2;
            backpack.y = (int)(screen.height * 0.48);
            backpack.width =  98;
            shangdian.x = (int)(screen.width * 0.75);
            shangdian.y = (int)(screen.height * 0.48);
            shangdian.width =  98;
        	gongye.x = (int)(screen.width * 0.02);
        	gongye.y = (int)(screen.height * 0.58);
        	gongye.width =  98;
        	xingxi.x = (int)(screen.width * 0.75);
        	xingxi.y = (int)(screen.height * 0.58);
        	xingxi.width =  98;
        	xuanze.x = (int)(screen.width / 2) + 2;
        	xuanze.y = (int)(screen.height * 0.58);
        	xuanze.width =  98;
            event.getButtonList().add(kongzhi);
            event.getButtonList().add(yinyue);
            event.getButtonList().add(gamechongzhi);
            event.getButtonList().add(haoyou);
            event.getButtonList().add(backpack);
            event.getButtonList().add(shangdian);
            event.getButtonList().add(gongye);
            event.getButtonList().add(xingxi);
            event.getButtonList().add(xuanze);
        }
        
    }
    
    @SubscribeEvent
    public void guiClickButton(ActionPerformedEvent.Post event)
    {
    	if(event.getButton() == kongzhi)
        {
            Minecraft mc = Minecraft.getMinecraft();
            Minecraft.getMinecraft().displayGuiScreen(new GuiControls(mc.currentScreen, mc.gameSettings));
        }
    	if(event.getButton() == yinyue)
        {
            Minecraft mc = Minecraft.getMinecraft();
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreenOptionsSounds(mc.currentScreen, mc.gameSettings));
        }
    	if(event.getButton() == backpack)
        {
    		Minecraft.getMinecraft().player.sendChatMessage("/backpack");
    		Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
    	if(event.getButton() == shangdian)
        {
    		Minecraft.getMinecraft().player.sendChatMessage("/warp sd");
    		Minecraft.getMinecraft().player.closeScreenAndDropStack();
        }
    	if(event.getButton() == gongye)
        {
    		Minecraft mc = Minecraft.getMinecraft();
            mc.displayGuiScreen(new GuiMcqwy(mc.currentScreen));
        }
    	if(event.getButton() == xingxi)
        {
    		Minecraft mc = Minecraft.getMinecraft();
            mc.displayGuiScreen(new GuiXingxi(mc.currentScreen));
        }
        if(event.getButton() == qq)
        {
        	Desktop desktop = Desktop.getDesktop();
        	try {
    			desktop.browse(new URI("https://jq.qq.com/?_wv=1027&k=5KWyqz7"));
    		} catch (IOException | URISyntaxException e) {
    			e.printStackTrace();
    		}
        }
        if(event.getButton() == chongzhi)
        {
        	Desktop desktop = Desktop.getDesktop();
        	try {
    			desktop.browse(new URI("https://www.mcqwy.com"));
    		} catch (IOException | URISyntaxException e) {
    			e.printStackTrace();
    		}
        }
        if(event.getButton() == gamechongzhi)
        {
        	Desktop desktop = Desktop.getDesktop();
        	try {
    			desktop.browse(new URI("http://www.mcrmb.com/cz/14414"));
    		} catch (IOException | URISyntaxException e) {
    			e.printStackTrace();
    		}
        }
        if(event.getButton() == xuanze)
        {
    		Minecraft mc = Minecraft.getMinecraft();
            mc.displayGuiScreen(new GuiServer(mc.currentScreen));
        }
        
    } 
}
