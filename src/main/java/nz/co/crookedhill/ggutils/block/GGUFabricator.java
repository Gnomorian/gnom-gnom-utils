/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GGUFabricator extends Block
{

	protected GGUFabricator(Material material)
	{
		super(material);
		this.setBlockName("fabricator");
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
	}

}
