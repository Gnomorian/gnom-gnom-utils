/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.achievements;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import nz.co.crookedhill.ggutils.block.GGUBlocks;

public class GGUAchievements
{

    public static Achievement creepMiteExplosion;
    public static Achievement usedlazyCrafter;
    public static Achievement craftedSortivator;

    public static void init()
    {
	creepMiteExplosion = (new Achievement("achievement.creepMiteExplosion", "creepMiteExplosion", 0, 0, new ItemStack(Blocks.tnt), (Achievement) null)).initIndependentStat().registerStat();
	usedlazyCrafter = (new Achievement("achievement.usedlazyCrafter", "usedlazyCrafter", 0, 1, new ItemStack(GGUBlocks.lazyCrafter), (Achievement) null)).initIndependentStat().registerStat();
	craftedSortivator = (new Achievement("achievement.craftedSortivator", "craftedSortivator", 1, 0, new ItemStack(GGUBlocks.sortivator), (Achievement) null)).initIndependentStat().registerStat();

	AchievementPage.registerAchievementPage(new GGUAchievementPage("GGUAchievements", new Achievement[] { creepMiteExplosion, usedlazyCrafter, craftedSortivator }));
    }

}
