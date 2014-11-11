/*
 * Copyright (c) 2014, William <w.cameron@crookedhill.co.nz>
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package nz.co.crookedhill.ggutils.achievements;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import nz.co.crookedhill.ggutils.block.GGUBlocks;

public class GGUAchievements {
	
	public static Achievement creepMiteExplosion;
	public static Achievement usedlazyCrafter;
	public static Achievement craftedSortivator;
	
	public static void init() 
	{
		creepMiteExplosion = (new Achievement("achievement.creepMiteExplosion", "creepMiteExplosion", 0, 0, new ItemStack(Blocks.tnt), (Achievement)null)).initIndependentStat().registerStat();
		usedlazyCrafter = (new Achievement("achievement.usedlazyCrafter", "usedlazyCrafter", 0, 1, new ItemStack(GGUBlocks.lazyCrafter), (Achievement)null)).initIndependentStat().registerStat();
		craftedSortivator = (new Achievement("achievement.craftedSortivator", "craftedSortivator", 1, 0, new ItemStack(GGUBlocks.sortivator), (Achievement)null)).initIndependentStat().registerStat();
		
		
		AchievementPage.registerAchievementPage(new GGUAchievementPage("GGUAchievements", new Achievement[] {creepMiteExplosion,usedlazyCrafter,craftedSortivator}));
	}

}
