package nz.co.crookedhill.ggutils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.MinecraftForge;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import nz.co.crookedhill.ggutils.handlers.GGUEventHandler;
import nz.co.crookedhill.ggutils.handlers.GGUMobHandler;
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
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
    public static final String MODID = "ggutils";
    public static final String VERSION = "0.0.3.0";
    
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
		GGUBlocks.init();
		GGUItems.init();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
		MinecraftForge.EVENT_BUS.register(new GGUEventHandler());
		MinecraftForge.EVENT_BUS.register(new GGUMobHandler());
		
		EntityRegistry.registerModEntity(GGUEntityCreeperMite.class, "creeper_mite", 0, this, 64, 1, true);
		EntityRegistry.addSpawn(GGUEntityCreeperMite.class, 200, 1, 8, EnumCreatureType.monster);
		EntityList.addMapping(GGUEntityCreeperMite.class, "creeper_mite", 0, 113213, 3523523);
		
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//compatability with other mod initialization here
    }
}
