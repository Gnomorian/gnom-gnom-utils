/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.entity.GGUEntityWockyJab;
import nz.co.crookedhill.ggutils.entity.monster.GGUEntityCreeperMite;
import nz.co.crookedhill.ggutils.entity.tile.GGUEntityModularLimb;
import nz.co.crookedhill.ggutils.handlers.GGUKeybindHandler;
import nz.co.crookedhill.ggutils.renderer.GGURenderCreeperMite;
import nz.co.crookedhill.ggutils.renderer.GGURenderModularLimb;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy
{
	@Override
	public void postInit(){}

	@Override
	public void init()
	{
		GGUtils.arseTardis = new KeyBinding("Arse Tardis", Keyboard.KEY_Z, "GG Utils");
		ClientRegistry.registerKeyBinding((KeyBinding) GGUtils.arseTardis);
		FMLCommonHandler.instance().bus().register(new GGUKeybindHandler());
	}

	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}

	@Override
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(GGUEntityModularLimb.class, new GGURenderModularLimb());
		RenderingRegistry.registerEntityRenderingHandler(GGUEntityCreeperMite.class, new GGURenderCreeperMite());
		RenderingRegistry.registerEntityRenderingHandler(GGUEntityWockyJab.class, new RenderSnowball(Items.apple));
	}
}
