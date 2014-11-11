package nz.co.crookedhill.ggutils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import nz.co.crookedhill.ggutils.enchantment.GGUEnchantment;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityMob;
import nz.co.crookedhill.ggutils.handlers.GGUBlockHandler;
import nz.co.crookedhill.ggutils.handlers.GGUEnchantmentHandler;
import nz.co.crookedhill.ggutils.handlers.GGUToolTipHandler;
import nz.co.crookedhill.ggutils.handlers.GGUMobHandler;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import nz.co.crookedhill.ggutils.item.GGUItems;
import nz.co.crookedhill.ggutils.network.GGUSortPacket;
import nz.co.crookedhill.ggutils.network.GGUSortPacketHandler;
import nz.co.crookedhill.ggutils.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
	/*
	 * add a function at the start that loops through the recipes and stores 
	 * them all in lists sorted out, eg List<FurnaceRecipes>, List<ShapedRecipes> 
	 * to reduce the time it takes to filter out all the recipes.
	 */
	
	public static final String MODID = "ggutils";
	/**
	 * 0.0.0.0
	 * first 0=
	 * 	the number of Minecraft versions supported since making the mod.
	 * second 0=
	 * 	the number of milestones reached.
	 * third 0=
	 * 	the number of features added (blocks, items etc.)
	 * forth 0=
	 * 	the number of bug fixes/sub features added since last feature added.
	 */
	public static final String VERSION = "0.0.4.2";

	//Setting proxy for client and server side
	@SidedProxy(clientSide = "nz.co.crookedhill.ggutils.proxy.ClientProxy", serverSide = "nz.co.crookedhill.ggutils.proxy.CommonProxy")
	public static CommonProxy proxy;

	//Mod instance
	@Instance(MODID)
	public static GGUtils instance;

	public static SimpleNetworkWrapper network;

	public static GGUConfigManager configs;

	//Set Creative Tabs
	public static CreativeTabs ggutilsCreativeTab = new GGUCreativeTabBlock(CreativeTabs.getNextID(), MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("GGUChannel");
		network.registerMessage(GGUSortPacketHandler.class, GGUSortPacket.class, 0, Side.SERVER);
		GGUConfigManager.init(event);
		GGUItems.init();
		GGUBlocks.init();
		GGUEntityMob.init();
		GGUEnchantment.init();
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

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//compatability with other mod initialization here
	}
}
