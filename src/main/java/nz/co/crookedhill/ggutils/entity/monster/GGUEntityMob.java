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

package nz.co.crookedhill.ggutils.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import nz.co.crookedhill.ggutils.GGUtils;
import nz.co.crookedhill.ggutils.helper.GGUConfigManager;
import cpw.mods.fml.common.registry.EntityRegistry;

public class GGUEntityMob {
	
	Entity creeperMite;
	
	public static void init() {
		
		/*if(GGUConfigManager.creeperMiteExist)
		creeperMite = new GGUEntityCreeperMite(null);*/
		
		if(GGUConfigManager.creeperMiteExist) {
			EntityRegistry.registerModEntity(GGUEntityCreeperMite.class, "creeper_mite", 0, GGUtils.instance, 64, 1, true);
			EntityRegistry.addSpawn(GGUEntityCreeperMite.class, 200, 1, 8, EnumCreatureType.monster);
			EntityList.addMapping(GGUEntityCreeperMite.class, "creeper_mite", 0, 113213, 3523523);
		}
		
	}
}
