package nz.co.crookedhill.ggutils;

import net.minecraft.creativetab.CreativeTabs;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import nz.co.crookedhill.ggutils.item.GGUItems;
import nz.co.crookedhill.ggutils.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
    public static final String MODID = "ggutils";
    public static final String VERSION = "0.0.1";
    
    //Setting proxy for client and server side
	@SidedProxy(clientSide = "nz.co.crookedhill.ggutils.proxy.ClientProxy", serverSide = "nz.co.crookedhill.ggutils.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//Mod instance
	@Instance(MODID)
	public static GGUtils instance;
	
	//Set Creative Tabs
	public static CreativeTabs ggutilsCreativeTab = new GGUCreativeTabBlock(CreativeTabs.getNextID(), MODID);
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		GGUBlocks.init();
		GGUItems.init();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
		//Registering the gui and renderer handlers
		proxy.registerRenderers();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
    }
}
