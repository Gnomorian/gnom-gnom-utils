/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.entity.tile;

import cpw.mods.fml.common.registry.GameRegistry;

public class GGUEntityTile
{

	/**
	 * Init the tile entities
	 */
	public static void init()
	{
		GameRegistry.registerTileEntity(GGUEntityEggTimer.class, "eggTimer");
		GameRegistry.registerTileEntity(GGUEntityModularLimb.class, "modularLimb");
		GameRegistry.registerTileEntity(GGUEntityModularCore.class, "modularCore");
	}
}
