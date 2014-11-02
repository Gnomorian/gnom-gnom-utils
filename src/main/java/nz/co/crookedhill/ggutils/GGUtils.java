package nz.co.crookedhill.ggutils;

import net.minecraft.creativetab.CreativeTabs;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import nz.co.crookedhill.ggutils.creativetabs.GGUCreativeTabBlock;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
    public static final String MODID = "ggutils";
    public static final String VERSION = "0.0.1";
    
	//Set Creative Tabs
	public static CreativeTabs fcraftTabBlocks = new GGUCreativeTabBlock(CreativeTabs.getNextID(), MODID);
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		GGUBlocks.init();
    }
}
