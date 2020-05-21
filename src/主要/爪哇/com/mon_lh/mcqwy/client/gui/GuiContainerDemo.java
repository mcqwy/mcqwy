package com.mon_lh.mcqwy.client.gui;

import java.io.IOException;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.inventory.ContainerDemo;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.weight.McqwyWeight;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiContainerDemo extends GuiContainer
{
	
	private static final String TEXTURE_PATH = Mcqwy.MODID + ":" + "textures/gui/container/gui_demo.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    
    private NBTTagCompound nbt;
    
    public GuiContainerDemo(ContainerDemo inventorySlotsIn)
    {
        super(inventorySlotsIn);
        this.xSize = 362;
        this.ySize = 200;
    }
    

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
    	GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2 - 10;
        
        this.drawModalRectWithCustomSizedTexture(offsetX, offsetY, 0, 0, 362, 200, 362, 200); 
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String title = I18n.format("container.mcqwy.demo");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, -9, 0x404040);
        nbt = ContainerDemo.nbtt;
        if(this.nbt != null)
        {
            String title2 = I18n.format("container.mcqwy.zt");
            String title3 = I18n.format("container.mcqwy.zt.sun");
            String title4 = I18n.format("container.mcqwy.zt.huai");
            for(int i = 0; i < 3; ++i)
            {
            	int ii = nbt.getInteger("mcqwya");
            	int iii = nbt.getInteger("mcqwyc");
            	if(i==0)
            	{
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.zp"), 100 + 30 * 1, 18, 0xFFA500);
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.czz"), 225 + 30 * 1, 18, 0xFFA500);
            	}else if(i==1)
            	{
            		if(ii >= 10 && ii < 20)
            		{
            			this.fontRenderer.drawString(title2, 100 + 30 * 2, 18, 0xFFA500);
            		}else if(ii >= 20 && ii < 30)
            		{
            			this.fontRenderer.drawString(title3, 100 + 30 * 2, 18, 0xFFA500);
            		}else if(ii >= 30 && ii < 40)
            		{
            			this.fontRenderer.drawString(title4, 100 + 30 * 2, 18, 0xFFA500);
            		}
            		
            		if(iii >= 10 && iii < 20)
            		{
            			this.fontRenderer.drawString(title2, 225 + 30 * 2, 18, 0xFFA500);
            		}else if(iii >= 20 && iii < 30)
            		{
            			this.fontRenderer.drawString(title3, 225 + 30 * 2, 18, 0xFFA500);
            		}else if(iii >= 30 && iii < 40)
            		{
            			this.fontRenderer.drawString(title4, 225 + 30 * 2, 18, 0xFFA500);
            		}
            	}else if(i==2)
            	{
            		if(nbt.getInteger("mcqwya") >= 10 && nbt.getInteger("mcqwya") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwya" + nbt.getInteger("mcqwya")).toString() + "t", 100 + 30 * 3, 18, 0xFFA500);
            		}
            		
            		if(nbt.getInteger("mcqwyc") >= 10 && nbt.getInteger("mcqwyc") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwyc" + nbt.getInteger("mcqwyc")).toString() + "t", 225 + 30 * 3, 18, 0xFFA500);
            		}
            	}
            }
            
            for(int i = 0; i < 3; ++i)
            {
            	int ii = nbt.getInteger("mcqwyb");
            	int iii = nbt.getInteger("mcqwyf");
            	if(i==0)
            	{
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.fc"), 100 + 30 * 1, 39, 0xFFA500);
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.se"), 225 + 30 * 1, 39, 0xFFA500);
            	}else if(i==1)
            	{
            		if(ii >= 10 && ii < 20)
            		{
            			this.fontRenderer.drawString(title2, 100 + 30 * 2, 39, 0xFFA500);
            		}else if(ii >= 20 && ii < 30)
            		{
            			this.fontRenderer.drawString(title3, 100 + 30 * 2, 39, 0xFFA500);
            		}else if(ii >= 30 && ii < 40)
            		{
            			this.fontRenderer.drawString(title4, 100 + 30 * 2, 39, 0xFFA500);
            		}
            		
            		if(iii >= 10 && iii < 20)
            		{
            			this.fontRenderer.drawString(title2, 225 + 30 * 2, 39, 0xFFA500);
            		}else if(iii >= 20 && iii < 30)
            		{
            			this.fontRenderer.drawString(title3, 225 + 30 * 2, 39, 0xFFA500);
            		}else if(iii >= 30 && iii < 40)
            		{
            			this.fontRenderer.drawString(title4, 225 + 30 * 2, 39, 0xFFA500);
            		}
            	}else if(i==2)
            	{
            		if(nbt.getInteger("mcqwyb") >= 10 && nbt.getInteger("mcqwyb") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwyb" + nbt.getInteger("mcqwyb")).toString() + "t", 100 + 30 * 3, 39, 0xFFA500);
            		}
            		
            		if(nbt.getInteger("mcqwyf") >= 10 && nbt.getInteger("mcqwyf") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwyf" + nbt.getInteger("mcqwyf")).toString() + "t", 225 + 30 * 3, 39, 0xFFA500);
            		}
            	}
            }
            
            for(int i = 0; i < 3; ++i)
            {
            	int ii = nbt.getInteger("mcqwye");
            	int iii = nbt.getInteger("mcqwyd");
            	if(i==0)
            	{
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.yq"), 100 + 30 * 1, 60, 0xFFA500);
            		this.fontRenderer.drawString(I18n.format("container.mcqwy.dp"), 225 + 30 * 1, 60, 0xFFA500);
            	}else if(i==1)
            	{
            		if(ii >= 10 && ii < 20)
            		{
            			this.fontRenderer.drawString(title2, 100 + 30 * 2, 60, 0xFFA500);
            		}else if(ii >= 20 && ii < 30)
            		{
            			this.fontRenderer.drawString(title3, 100 + 30 * 2, 60, 0xFFA500);
            		}else if(ii >= 30 && ii < 40)
            		{
            			this.fontRenderer.drawString(title3, 100 + 30 * 2, 60, 0xFFA500);
            		}
            		
            		if(iii >= 10 && iii < 20)
            		{
            			this.fontRenderer.drawString(title2, 225 + 30 * 2, 60, 0xFFA500);
            		}else if(iii >= 20 && iii < 30)
            		{
            			this.fontRenderer.drawString(title3, 225 + 30 * 2, 60, 0xFFA500);
            		}else if(iii >= 30 && iii < 40)
            		{
            			this.fontRenderer.drawString(title4, 225 + 30 * 2, 60, 0xFFA500);
            		}
            	}else if(i==2)
            	{
            		if(nbt.getInteger("mcqwye") >= 10 && nbt.getInteger("mcqwye") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwyee" + nbt.getInteger("mcqwye")).toString() + "t", 100 + 30 * 3, 60, 0xFFA500);
            		}
            		
            		if(nbt.getInteger("mcqwyd") >= 10  && nbt.getInteger("mcqwyd") < 20)
            		{
            			this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwyd" + nbt.getInteger("mcqwyd")).toString() + "t", 225 + 30 * 3, 60, 0xFFA500);
            		}
            	}
            	
            }
            int i = McqwyWeight.getArmorSize(nbt.getInteger("mcqwya"), nbt.getInteger("mcqwyb"), nbt.getInteger("mcqwyc"), nbt.getInteger("mcqwyd"), nbt.getInteger("mcqwye"), nbt.getInteger("mcqwyf"));
            
            this.fontRenderer.drawString("SP", 195 + 30 * 1, 103, 0xFFA500);
            if(nbt.getInteger("mcqwye") >= 3)
            {
            	this.fontRenderer.drawString(nbt.getInteger("armor") + "/" + i, 260 + 30 * 1, 103, 0xFFA500);
            }else
            {
            	this.fontRenderer.drawString("0/" + i, 260 + 30 * 1, 103, 0xFFA500);
            }
            this.fontRenderer.drawString(I18n.format("container.info.drl"), 195 + 30 * 1, 118, 0xFFA500);
            if(nbt.getInteger("mcqwya") >= 3)
            {
            	this.fontRenderer.drawString(nbt.getInteger("count") + "/32", 260 + 30 * 1, 103, 0xFFA500);
            }else
            {
            	this.fontRenderer.drawString("0/32", 260 + 30 * 1, 118, 0xFFA500);
            }
            
            this.fontRenderer.drawString(I18n.format("container.info.zzz"), 195 + 30 * 1, 133, 0xFFA500);
            
            if(nbt.getInteger("mcqwye") >= 10 && nbt.getInteger("mcqwye") < 20)
            {
            	this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwye" + (nbt.getInteger("mcqwye"))).toString() + "t", 260 + 30 * 1, 133, 0xFFA500);
            }else if(nbt.getInteger("mcqwye") >= 20 && nbt.getInteger("mcqwye") < 30)
            {
            	this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwye" + (nbt.getInteger("mcqwye")-10)).toString() + "t", 260 + 30 * 1, 133, 0xFFA500);
            }else if(nbt.getInteger("mcqwye") >= 30 && nbt.getInteger("mcqwye") < 40)
            {
            	this.fontRenderer.drawString(McqwyWeight.getValueOfVariableName("mcqwye" + (nbt.getInteger("mcqwye")-20)).toString() + "t", 260 + 30 * 1, 133, 0xFFA500);
            }else
            {
            	this.fontRenderer.drawString("0t", 260 + 30 * 1, 133, 0xFFA500);
            }
            
            this.fontRenderer.drawString(I18n.format("container.info.zl"), 195 + 30 * 1, 148, 0xFFA500);
            
            this.fontRenderer.drawString(((double)(i - nbt.getInteger("armor")) / 100) + "t", 260 + 30 * 1, 148, 0xFFA500);
            this.fontRenderer.drawString(I18n.format("container.info.tsd"), 195 + 30 * 1, 163, 0xFFA500);
            if(nbt.getInteger("mcqwyf") == 3)
            {
            	this.fontRenderer.drawString(nbt.getInteger("shellcount") + "/8", 260 + 30 * 1, 103, 0xFFA500);
            }else
            {
            	this.fontRenderer.drawString("0/8", 260 + 30 * 1, 163, 0xFFA500);
            }
        }
        
        
        
        //ItemStack item = new ItemStack(ItemLoader.energyloop);
        //this.itemRender.renderItemAndEffectIntoGUI(item, 8, 20);
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
    	super.drawScreen(mouseX, mouseY, partialTick);
    	this.renderHoveredToolTip(mouseX, mouseY);
    }
    
}
