/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nz.co.crookedhill.ggutils.block.GGUBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GGUCreativeTabBlock extends CreativeTabs
{
    public GGUCreativeTabBlock(int tabID, String label)
    {
	super(tabID, label);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
	return "GnomGnom's Utils Blocks";
    }

    @Override
    public Item getTabIconItem()
    {
	return Item.getItemFromBlock(GGUBlocks.sortivator);
    }
}
