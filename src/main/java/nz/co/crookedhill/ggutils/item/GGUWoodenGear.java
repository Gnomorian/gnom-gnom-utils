/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.item;

import net.minecraft.item.Item;
import nz.co.crookedhill.ggutils.GGUtils;

public class GGUWoodenGear extends Item
{
	public GGUWoodenGear() 
	{
		this.setMaxStackSize(64);
		this.setTextureName(GGUtils.MODID + ":woodenGear_texture");
	}
}
