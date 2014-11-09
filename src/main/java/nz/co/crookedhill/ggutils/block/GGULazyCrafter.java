package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GGULazyCrafter extends Block{

	protected GGULazyCrafter(Material material) {
		super(material);
		this.setBlockName("lazyCrafter");
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
		//side textures will the crafting table textures, top will be 
		//an edited one with an enderpearl crammed in.
		
		//when an item is crafted, ender particles will be spawned around
		//the block, preferably ontop where the enderpearl is.
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float float1, float float2, float float3) {
		//open the gui.
		//get the players inventory.
		//display the inventory and the items avalable 
		//to craft with current items.
		return true;
		
	}

}
