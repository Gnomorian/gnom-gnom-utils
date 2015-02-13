/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import nz.co.crookedhill.ggutils.achievements.GGUAchievements;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import nz.co.crookedhill.ggutils.enchantment.GGUEnchantment;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityMob;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityTile;
import nz.co.crookedhill.ggutils.handlers.ExtendedPropertiesHandler;
import nz.co.crookedhill.ggutils.handlers.GGUAchievementHandler;
import nz.co.crookedhill.ggutils.handlers.GGUBlockHandler;
import nz.co.crookedhill.ggutils.handlers.GGUCommandHandler;
import nz.co.crookedhill.ggutils.handlers.GGUEnchantmentHandler;
import nz.co.crookedhill.ggutils.handlers.GGUItemEffectHandler;
import nz.co.crookedhill.ggutils.handlers.GGUMobHandler;
import nz.co.crookedhill.ggutils.handlers.GGUToolTipHandler;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import nz.co.crookedhill.ggutils.item.GGUItems;
import nz.co.crookedhill.ggutils.network.GGUInventorySwitchHandler;
import nz.co.crookedhill.ggutils.network.GGUInventorySwitchPacket;
import nz.co.crookedhill.ggutils.network.GGUSortPacket;
import nz.co.crookedhill.ggutils.network.GGUSortPacketHandler;
import nz.co.crookedhill.ggutils.network.GGUSyncPlayerPropertiesPacketHandler;
import nz.co.crookedhill.ggutils.network.GGUSyncPlayerPropsPacket;
import nz.co.crookedhill.ggutils.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
	public static final String MODID = "ggutils";

    /**
     * 0.0.0.0 first 0= the number of Minecraft versions supported since making
     * the mod. second 0= the number of milestones reached. third 0= the number
     * of features added (blocks, items etc.) forth 0= the number of bug
     * fixes/sub features added since last feature added.
     */

    public static final String VERSION = "0.1.1.5";


	// Setting proxy for client and server side
	@SidedProxy(clientSide = "nz.co.crookedhill.ggutils.proxy.ClientProxy", serverSide = "nz.co.crookedhill.ggutils.proxy.CommonProxy")
	public static CommonProxy proxy;

	// Mod instance
	@Instance(MODID)
	public static GGUtils instance;

	public static SimpleNetworkWrapper network;

	// Set Creative Tabs
	public static CreativeTabs ggutilsCreativeTab = new GGUCreativeTabBlock(CreativeTabs.getNextID(), MODID);



	/** This is used to keep track of GUIs that we make*/
	private static int modGuiIndex = 10;
	/** Custom GUI indices: */
	public static final int
	GUI_MESS_INV = modGuiIndex++;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel("GGUChannel");
		network.registerMessage(GGUSortPacketHandler.class, GGUSortPacket.class, 0, Side.SERVER);
		network.registerMessage(GGUSyncPlayerPropertiesPacketHandler.class, GGUSyncPlayerPropsPacket.class, 1, Side.SERVER);
		network.registerMessage(GGUInventorySwitchHandler.class, GGUInventorySwitchPacket.class, 2, Side.SERVER);

		GGUConfigManager.init(event);
		GGUItems.init();
		GGUEntityTile.init();
		GGUBlocks.init();
		GGUEntityMob.init();
		GGUEnchantment.init();
		GGUAchievements.init();

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
		MinecraftForge.EVENT_BUS.register(new GGUToolTipHandler());
		MinecraftForge.EVENT_BUS.register(new GGUMobHandler());
		MinecraftForge.EVENT_BUS.register(new GGUBlockHandler());
		MinecraftForge.EVENT_BUS.register(new GGUEnchantmentHandler());
		MinecraftForge.EVENT_BUS.register(new ExtendedPropertiesHandler());
		MinecraftForge.EVENT_BUS.register(new GGUItemEffectHandler());
		MinecraftForge.EVENT_BUS.register(new GGUAchievementHandler());
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
		
		//THIS IS FUCKING WITH OUR RECIPES SOMEHOW!
//		GGURecipeManager.init(CraftingManager.getInstance().getRecipeList());
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent e)
	{
		GGUCommandHandler.init(e);

	}
}
