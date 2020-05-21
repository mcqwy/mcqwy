package com.mon_lh.mcqwy.network;

import com.mon_lh.mcqwy.Mcqwy;
import com.mon_lh.mcqwy.capability.ManaCapability.CapProvider;
import com.mon_lh.mcqwy.inventory.GuiElementLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class EnergyRing implements IMessage{
	
	private NBTTagCompound nbt;
	
	public EnergyRing() {}
	
	public EnergyRing(NBTTagCompound nbtTag) {
        this.nbt = nbtTag;
    }

    @Override
    public void fromBytes(ByteBuf buf) 
    {
    	nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) 
    {
    	ByteBufUtils.writeTag(buf, nbt);
    }
    
    public static class Handler implements IMessageHandler<EnergyRing, IMessage> 
    {
    	@Override
        public IMessage onMessage(EnergyRing message, MessageContext ctx) 
        {
    		if (ctx.side == Side.CLIENT && !message.nbt.getString("mcqwy").equals("ss"))
            {
                Minecraft.getMinecraft().addScheduledTask(new Runnable()
                {
					@Override
					public void run() {
						CapProvider.get(Minecraft.getMinecraft().player).loadNBTData(message.nbt);
					}
                });
            }else if (ctx.side == Side.SERVER && message.nbt.getString("mcqwy").equals("ss"))
            {
            	EntityPlayer player = ctx.getServerHandler().player;
        		MinecraftServer server = player.getServer();
        		server.addScheduledTask(new Runnable()
                {
					@Override
					public void run() {
						int id = GuiElementLoader.GUI_DEMO;
						int x = player.getPosition().getX();
						int y = player.getPosition().getY();
						int z = player.getPosition().getZ();
						player.openGui(Mcqwy.MODID, id, player.world, x, y, z);
					}
                });
            }
			return null;
        }
    }

}
