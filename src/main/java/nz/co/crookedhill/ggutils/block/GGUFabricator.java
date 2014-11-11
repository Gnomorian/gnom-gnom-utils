package nz.co.crookedhill.ggutils.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GGUFabricator extends Block{

	protected GGUFabricator(Material material) {
		super(material);
		this.setBlockName("fabricator");
		this.setHardness(0.5f);
		this.setStepSound(Block.soundTypeWood);
	}

}
