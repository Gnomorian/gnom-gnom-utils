package nz.co.crookedhill.ggutils;

import net.minecraft.init.Blocks;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = GGUtils.MODID, version = GGUtils.VERSION)
public class GGUtils
{
    public static final String MODID = "ggutils";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		GGUBlocks.init();
    }
}
