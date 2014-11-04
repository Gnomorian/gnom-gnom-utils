package nz.co.crookedhill.ggutils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import nz.co.crookedhill.ggutils.handlers.GGUEventHandler;
import nz.co.crookedhill.ggutils.item.GGUItems;
import nz.co.crookedhill.ggutils.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
    public static final String MODID = "ggutils";
    public static final String VERSION = "1.7.10_0.1.0";
    
    //Setting proxy for client and server side
	@SidedProxy(clientSide = "nz.co.crookedhill.ggutils.proxy.ClientProxy", serverSide = "nz.co.crookedhill.ggutils.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Mod instance
	@Instance(MODID)
	public static GGUtils instance;
	
	public static GGUConfigManager configs;
	
	//Set Creative Tabs
	public static CreativeTabs ggutilsCreativeTab = new GGUCreativeTabBlock(CreativeTabs.getNextID(), MODID);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
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
		
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	//compatability with other mod initialization here
    }
}
