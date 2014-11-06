package nz.co.crookedhill.ggutils.proxy;

import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import nz.co.crookedhill.ggutils.renderer.GGURenderCreeperMite;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(GGUEntityCreeperMite.class, new GGURenderCreeperMite());
	}
}
