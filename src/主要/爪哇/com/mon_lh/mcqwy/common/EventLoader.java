package com.mon_lh.mcqwy.common;

import ic2.api.event.ExplosionEvent;
import ic2.api.item.IC2Items;

import com.mon_lh.mcqwy.capability.ManaCapability;
import com.mon_lh.mcqwy.capability.ManaCapability.CapProvider;
import com.mon_lh.mcqwy.item.ItemLoader;
import com.mon_lh.mcqwy.potion.PotionLoader;
import com.mon_lh.mcqwy.random.Random;
import com.mon_lh.mcqwy.weight.McqwyWeight;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.ICommandManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

public class EventLoader
{
	private Random ran = new Random();
	
    public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
        EventLoader.EVENT_BUS.register(this);
    }
    
    public static final EventBus EVENT_BUS = new EventBus();
    
    @Cancelable
    public static class PlayerEnergyRingEvent extends net.minecraftforge.event.entity.player.PlayerEvent
    {
    	public final BlockPos pos;
        public final World world;
    	public PlayerEnergyRingEvent(EntityPlayer player, BlockPos pos, World world)
    	{
    		super(player);
    		this.pos = pos;
            this.world = world;
    	}
    }
    
    // player.getAbsorptionAmount() 获取附加生命值 
    @SubscribeEvent
    public void onPlayerLogged(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
    	if(event.isWasDeath())
    	{
    		CapProvider.get(event.getEntityPlayer()).loadNBTData(CapProvider.get(event.getOriginal()).saveNBTData());
            CapProvider.get(event.getEntityPlayer()).dataChanged(event.getEntityPlayer());
    	}
    }
    
    @SubscribeEvent
    public void entityJoinWorld(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) 
    {
        if (!event.player.world.isRemote) {
        	if(CapProvider.get(event.player).getMana() != null)
        	{
        		CapProvider.get(event.player).dataChanged(event.player);
        	}else
        	{
        		NBTTagCompound nbt = new NBTTagCompound();
        		CapProvider.get(event.player).setMana(nbt);
        	}
        }
    }
    
    @SubscribeEvent
    public void AttachCapability(AttachCapabilitiesEvent<Entity> event)
    {
    	if(!event.getObject().hasCapability(CapProvider.MANA_CAP, null) && event.getObject() instanceof EntityPlayer)
    	{
    		event.addCapability(new ResourceLocation("mana_epp"), new CapProvider(new ManaCapability()));
    	} 
    		
    }
    
    @SubscribeEvent
    public void onKnockback(LivingKnockBackEvent event)
    {//被击退事件
    	PotionEffect effect = event.getEntityLiving().getActivePotionEffect(PotionLoader.SuperArmor);
        if (effect != null)
        {
        	event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onDamage(LivingDamageEvent event)
    {//被伤害事件
    	EntityLivingBase effect = event.getEntityLiving();
    	EntityLivingBase eff = event.getEntityLiving().getAttackingEntity();
        if (effect != null && effect instanceof EntityPlayer && eff != null )
        {//被其他实体攻击
        	PotionEffect effc = effect.getActivePotionEffect(PotionLoader.SuperArmor);
        	PotionEffect effe = eff.getActivePotionEffect(PotionLoader.Analeptic);
        	NBTTagCompound playernbt = CapProvider.get((EntityPlayer) effect).getMana();
        	if(effc != null || effe != null)
        	{//自身药水是霸体效果 或者 对手有兴奋剂 时的处理
        		if(playernbt.hasKey("username") && playernbt.getInteger("Enable") == 1)
            	{//判断 是否 装配  能量环 并且是否有效
            		if(!ran.getDodge())
            		{//没有被闪避
            			//event.getEntityLiving().world.playSound();
            			NBTTagCompound nbt = new NBTTagCompound();
            			int a = playernbt.getInteger("mcqwya");
    					int b = playernbt.getInteger("mcqwyb");
    					int c = playernbt.getInteger("mcqwyc");
    					int d = playernbt.getInteger("mcqwyd");
    					int e = playernbt.getInteger("mcqwye");
    					int f = playernbt.getInteger("mcqwyf");
            			if(playernbt.getInteger("armor") - (int)event.getAmount() * 2 >= 0)
            			{//扣除装甲值 并取消伤害
            				event.setCanceled(true);
            				nbt.setString("username", playernbt.getString("username"));
            				nbt.setInteger("itemname", playernbt.getInteger("itemname"));
            				nbt.setInteger("mcqwya", a);
            				nbt.setInteger("mcqwyb", b);
            				nbt.setInteger("mcqwyc", c);
            				nbt.setInteger("mcqwyd", d);
            				nbt.setInteger("mcqwye", e);
            				nbt.setInteger("mcqwyf", f);
            				nbt.setInteger("armor", playernbt.getInteger("armor") - (int)event.getAmount() * 2);
            				nbt.setInteger("count", playernbt.getInteger("count"));
            				nbt.setInteger("shellcount", playernbt.getInteger("shellcount"));
            				nbt.setInteger("Enable", playernbt.getInteger("Enable"));
                			CapProvider.get((EntityPlayer) effect).setMana(nbt);
            			}else
            			{
            				event.setCanceled(true);
            				nbt.setString("username", playernbt.getString("username"));
            				nbt.setInteger("itemname", playernbt.getInteger("itemname"));
            				nbt.setInteger("mcqwya", a);
            				nbt.setInteger("mcqwyb", b);
            				nbt.setInteger("mcqwyc", c);
            				nbt.setInteger("mcqwyd", d);
            				nbt.setInteger("mcqwye", e);
            				nbt.setInteger("mcqwyf", f);
            				nbt.setInteger("armor", 0);
            				nbt.setInteger("count", playernbt.getInteger("count"));
            				nbt.setInteger("shellcount", playernbt.getInteger("shellcount"));
            				nbt.setInteger("Enable", playernbt.getInteger("Enable"));
            				if(ran.getDamage())
            				{//损坏率成功
            					int damage = ran.getPartsDamage();
            					if(damage == 1 && a >= 10 && a < 20)
            					{//OK状态
            						nbt.setInteger("mcqwya", a + 10);
            					}else if(damage == 1 && a >= 20 && a < 30)
            					{//破损1次
            						if(ran.getDamageHuai())
            						{//破损2次 损坏
            							nbt.setInteger("mcqwya", a + 10);
            						}else
            						{
            							nbt.setInteger("mcqwya", a);
            						}
            					}
            					
            					if(damage == 2 && b >= 10 && b < 20)
            					{
            						nbt.setInteger("mcqwyb", b + 10);
            					}else if(damage == 2 && b >= 20 && b < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyb", b + 10);
            						}else
            						{
            							nbt.setInteger("mcqwyb", b);
            						}
            					}
            					
            					if(damage == 3 && c >= 10 && c < 20)
            					{
            						nbt.setInteger("mcqwyc", c + 10);
            					}else if(damage == 3 && c >= 20 && c < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyc", c + 10);
            							effect.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 99999999, 1));
            						}else
            						{
            							nbt.setInteger("mcqwyc", c);
            						}
            					}
            					
            					if(damage == 4 && d >= 10 && d < 20)
            					{
            						nbt.setInteger("mcqwyd", d + 10);
            					}else if(damage == 3 && d >= 20 && d < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyd", d + 10);
            							effect.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 99999999, 5));
            						}else
            						{
            							nbt.setInteger("mcqwyd", d);
            						}
            					}
            					
            					if(damage == 5 && e >= 10 && e < 20)
            					{
            						nbt.setInteger("mcqwye", e + 10);
            					}else if(damage == 3 && e >= 20 && e < 30)
            					{
            						if(ran.getDamageYq())
            						{
            							nbt.setInteger("mcqwye", e + 10);
            							nbt.setInteger("Enable", 2);
            						}else
            						{
            							nbt.setInteger("mcqwye", e);
            						}
            					}
            					
            					if(damage == 6 && f >= 10 && f < 20)
            					{
            						nbt.setInteger("mcqwyf", f + 10);
            					}else if(damage == 3 && f >= 20 && f < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyf", f + 10);
            						}else
            						{
            							nbt.setInteger("mcqwyf", f);
            						}
            					}
            				}
            				CapProvider.get((EntityPlayer) effect).setMana(nbt);
            			}
            		}else
            		{//被闪避
            			event.setCanceled(true);
            			if(effect.world.isRemote)
                    	{
            				effect.sendMessage(new TextComponentString(I18n.format("player.info.sb")));
                    	}
            		}
            		
            	}else
            	{//能量环未装配或无效
            		event.setAmount(event.getAmount() * 2);
            	}
        	}else
        	{//自身药水不是霸体时
        		if(eff != null && effect != null && playernbt.hasKey("username") && playernbt.getInteger("Enable") == 1)
            	{//判断 是否 装配  能量环 并且是否有效
            		if(!ran.getDodge())
            		{//没有被闪避
            			//event.getEntityLiving().world.playSound();
            			NBTTagCompound nbt = new NBTTagCompound();
            			int a = playernbt.getInteger("mcqwya");
    					int b = playernbt.getInteger("mcqwyb");
    					int c = playernbt.getInteger("mcqwyc");
    					int d = playernbt.getInteger("mcqwyd");
    					int e = playernbt.getInteger("mcqwye");
    					int f = playernbt.getInteger("mcqwyf");
            			if(playernbt.getInteger("armor") - (int)event.getAmount() >= 0)
            			{//扣除装甲值 并取消伤害
            				event.setCanceled(true);
            				nbt.setString("username", playernbt.getString("username"));
            				nbt.setInteger("itemname", playernbt.getInteger("itemname"));
            				nbt.setInteger("mcqwya", a);
            				nbt.setInteger("mcqwyb", b);
            				nbt.setInteger("mcqwyc", c);
            				nbt.setInteger("mcqwyd", d);
            				nbt.setInteger("mcqwye", e);
            				nbt.setInteger("mcqwyf", f);
            				nbt.setInteger("armor", playernbt.getInteger("armor") - (int)event.getAmount());
            				nbt.setInteger("count", playernbt.getInteger("count"));
            				nbt.setInteger("shellcount", playernbt.getInteger("shellcount"));
            				nbt.setInteger("Enable", playernbt.getInteger("Enable"));
                			CapProvider.get((EntityPlayer) effect).setMana(nbt);
            			}else
            			{
            				event.setCanceled(true);
            				nbt.setString("username", playernbt.getString("username"));
            				nbt.setInteger("itemname", playernbt.getInteger("itemname"));
            				nbt.setInteger("mcqwya", a);
            				nbt.setInteger("mcqwyb", b);
            				nbt.setInteger("mcqwyc", c);
            				nbt.setInteger("mcqwyd", d);
            				nbt.setInteger("mcqwye", e);
            				nbt.setInteger("mcqwyf", f);
            				nbt.setInteger("armor", 0);
            				nbt.setInteger("count", playernbt.getInteger("count"));
            				nbt.setInteger("shellcount", playernbt.getInteger("shellcount"));
            				nbt.setInteger("Enable", playernbt.getInteger("Enable"));
            				CapProvider.get((EntityPlayer) effect).setMana(nbt);
            				if(ran.getDamage())
            				{//损坏率成功
            					int damage = ran.getPartsDamage();
            					if(damage == 1 && a >= 10 && a < 20)
            					{
            						nbt.setInteger("mcqwya", a + 10);
            					}else if(damage == 1 && a >= 20 && a < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwya", a + 10);
            						}else
            						{
            							nbt.setInteger("mcqwya", a);
            						}
            					}
            					
            					if(damage == 2 && b >= 10 && b < 20)
            					{
            						nbt.setInteger("mcqwyb", b + 10);
            					}else if(damage == 2 && b >= 20 && b < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyb", b + 10);
            						}else
            						{
            							nbt.setInteger("mcqwyb", b);
            						}
            					}
            					
            					if(damage == 3 && c >= 10 && c < 20)
            					{
            						nbt.setInteger("mcqwyc", c + 10);
            					}else if(damage == 3 && c >= 20 && c < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyc", c + 10);
            							effect.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 99999999, 1));
            						}else
            						{
            							nbt.setInteger("mcqwyc", c);
            						}
            					}
            					
            					if(damage == 4 && d >= 10 && d < 20)
            					{
            						nbt.setInteger("mcqwyd", d + 10);
            					}else if(damage == 3 && d >= 20 && d < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyd", d + 10);
            							effect.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 99999999, 5));
            						}else
            						{
            							nbt.setInteger("mcqwyd", d);
            						}
            					}
            					
            					if(damage == 5 && e >= 10 && e < 20)
            					{
            						nbt.setInteger("mcqwye", e + 10);
            					}else if(damage == 3 && e >= 20 && e < 30)
            					{
            						if(ran.getDamageYq())
            						{
            							nbt.setInteger("mcqwye", e + 10);
            							nbt.setInteger("Enable", 2);
            						}else
            						{
            							nbt.setInteger("mcqwye", e);
            						}
            					}
            					
            					if(damage == 6 && f >= 10 && f < 20)
            					{
            						nbt.setInteger("mcqwyf", f + 10);
            					}else if(damage == 3 && f >= 20 && f < 30)
            					{
            						if(ran.getDamageHuai())
            						{
            							nbt.setInteger("mcqwyf", f + 10);
            						}else
            						{
            							nbt.setInteger("mcqwyf", f);
            						}
            					}
            				}
            				CapProvider.get((EntityPlayer) effect).setMana(nbt);
            			}
            		}else
            		{//被闪避
            			event.setCanceled(true);
            			if(effect.world.isRemote)
                    	{
            				effect.sendMessage(new TextComponentString(I18n.format("player.info.sb")));
                    	}
            		}
            	}
        	}
        }else if(effect != null && eff != null)
        {//被攻击实体不是玩家
        	PotionEffect effe = eff.getActivePotionEffect(PotionLoader.Analeptic);
        	if(effe != null)
        	{//攻击实体有兴奋剂药水
        		if(ran.getCrit())
        		{//暴击率计算
            		event.setAmount(event.getAmount() * 2);
            		if(effect.world.isRemote)
                	{
            			if(eff instanceof EntityPlayer)
            			{
            				effect.sendMessage(new TextComponentString(I18n.format("player.info.bj")));
            			}
                	}
        		}
        	}
        }
        
        
        
        if(effect instanceof EntityPlayer)
        {
        	EntityPlayer player = (EntityPlayer) event.getEntity();
    		if(McqwyWeight.getEnable(CapProvider.get((EntityPlayer) event.getEntity()).getMana()))
            {
            	player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 99999999, 5));
            	player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 99999999, 1));
            	if(player.world.isRemote)
            	{
            		NBTTagCompound playernbt = CapProvider.get(player).getMana();
            		int a = playernbt.getInteger("mcqwya");
            		int b = playernbt.getInteger("mcqwyb");
            		int c = playernbt.getInteger("mcqwyc");
            		int d = playernbt.getInteger("mcqwyd");
            		int e = playernbt.getInteger("mcqwye");
            		int f = playernbt.getInteger("mcqwyf");
            		if(a < 10)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.tips")));
            		}else if (a >= 20 && a < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.zc.sun")));
            		}else if (a >= 30 && a < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.zc.huai")));
            		}
            		
            		if (b >= 20 && b < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.fc.sun")));
            		}else if (b >= 30 && b < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.fc.huai")));
            		}
            		
            		if (c >= 20 && c < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.czz.sun")));
            		}else if (c >= 30 && c < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.czz.huai")));
            		}
            		
            		if (d >= 20 && d < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.dp.sun")));
            		}else if (d >= 30 && d < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.dp.huai")));
            		}
            		
            		if (e >= 20 && e < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.yq.sun")));
            		}else if (e >= 30 && e < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.yq.huai")));
            		}
            		
            		if (f >= 20 && f < 30)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.se.sun")));
            		}else if (f >= 30 && f < 40)
            		{
            			player.sendMessage(new TextComponentString(I18n.format("player.info.se.huai")));
            		}
            		
            		
            	}
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerUsetItem(PlayerInteractEvent.RightClickItem event)
	{//PlayerInteractEvent=交互事件  RightClickItem=物品右击事件
    	if(event.getEntityPlayer().getHeldItemMainhand().getItem() == IC2Items.getItemAPI().getItem("mining_laser"))
    	{
    		NBTTagCompound nbt = event.getEntityPlayer().getHeldItemMainhand().getTagCompound();
    		if(nbt.hasKey("laserSetting") && nbt.getInteger("laserSetting") == 1)
    		{
    			if(nbt.getDouble("charge") -5000 >= 0)
    			{
    				nbt.setDouble("charge", nbt.getDouble("charge") - 5000);
    			}else
    			{
    				event.setCanceled(true);
    			}
    		}else
    		{
    			nbt.setInteger("laserSetting", 1);
    			event.setCanceled(true);
    		}
    	}else if(event.getEntityPlayer().getHeldItemOffhand().getItem() == IC2Items.getItemAPI().getItem("mining_laser"))
    	{
    		event.setCanceled(true);
    	}else if(event.getEntityPlayer().getHeldItemMainhand().getItem() == Items.MILK_BUCKET)
    	{
    		NBTTagCompound nbt = CapProvider.get(event.getEntityPlayer()).getMana();
    		if(nbt != null && nbt.hasKey("username"))
    		{
    			event.setCanceled(true);
    		}
    	}else if(event.getEntityPlayer().getHeldItemMainhand().getItem() == Items.LAVA_BUCKET)
    	{
    		event.setCanceled(true);
    	}else if(event.getEntityPlayer().getHeldItemMainhand().getItem() == IC2Items.getItemAPI().getItem("fluid_cell"))
    	{
    		NBTTagCompound nbt = event.getEntityPlayer().getHeldItemMainhand().getTagCompound();
    		if(nbt != null && nbt.getCompoundTag("Fluid").getString("FluidName").equals("ic2air"))
    		{
    			event.setCanceled(true);
    		}
    	}else if(event.getEntityPlayer().getHeldItemOffhand().getItem() == IC2Items.getItemAPI().getItem("fluid_cell"))
    	{
    		event.setCanceled(true);
    	}
    	
	}
    
    @SubscribeEvent
    public void onic2explosion(ExplosionEvent e)
	{
		e.setCanceled(true);
		e.getWorld().createExplosion(e.entity, e.pos.x, e.pos.y, e.pos.z, (float)e.power, false);
		MinecraftServer server = e.getWorld().getMinecraftServer();
    	//e.getWorld().getMinecraftServer().getServer()//ic2 server 实例
    	ICommandManager comm = server.getCommandManager();
    	comm.executeCommand(e.getWorld().getMinecraftServer().getServer(), "broadcast &a[{{o:world-name}}&a] &c\u53d1\u751f\u7206\u70b8.&a \u5750\u6807:&c" + e.pos.x + "&a,&c" + e.pos.y + "&a,&c" + e.pos.z);
	}

    @SubscribeEvent
    public void onDiscarditems(ItemTossEvent e)
    {
    	Item item = e.getEntityItem().getItem().getItem();
    	Item iitem = IC2Items.getItemAPI().getItem("nuclear");
    	Item Fuelrod = IC2Items.getItemAPI().getItem("uranium_fuel_rod");
    	Item dualFuelrod = IC2Items.getItemAPI().getItem("dual_uranium_fuel_rod");
    	Item quadFuelrod = IC2Items.getItemAPI().getItem("quad_uranium_fuel_rod");
    	Item moxFuelrod = IC2Items.getItemAPI().getItem("mox_fuel_rod");
    	Item moxdualFuelrod = IC2Items.getItemAPI().getItem("dual_mox_fuel_rod");
    	Item moxquadFuelrod = IC2Items.getItemAPI().getItem("quad_mox_fuel_rod");
    	if(item == iitem || item == Fuelrod || item == dualFuelrod || item == quadFuelrod  || item == moxFuelrod || item == moxdualFuelrod || item == moxquadFuelrod)
    	{
    		e.setCanceled(true);
    	}
    }
}
