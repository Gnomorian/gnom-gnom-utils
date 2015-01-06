package nz.co.crookedhill.ggutils.helper;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;

public class GGUHelper {

	private static Random rand = new Random();

	public static Block getRandomBlock()
	{
		int maxBlocks = Block.blockRegistry.getKeys().size();
		int randomItem = rand.nextInt(maxBlocks);
		Block randomBlock = Block.getBlockById(randomItem);

		//to make sure random item is never null
		while(randomBlock == null)
		{
			randomItem = rand.nextInt(maxBlocks);
			randomBlock = Block.getBlockById(randomItem);
		}

		return randomBlock;
	}

	public static Item getRandomItem()
	{
		int maxItems = Item.itemRegistry.getKeys().size();
		int randomItem = rand.nextInt(maxItems);
		Item item = Item.getItemById(randomItem);

		//to make sure random item is never null
		while(item == null)
		{
			randomItem = rand.nextInt(maxItems);
			item = Item.getItemById(randomItem);
		}

		return item;
	}
	
	public static void sendMessageToPlayer(EntityPlayer player, String message)
	{
		if(!player.worldObj.isRemote)
		{
			player.addChatComponentMessage(new ChatComponentText(message));
		}
	}
	
	public static void spawnRandomTree(World world, int x, int y, int z)
	{
		WorldGenAbstractTree tree;
		int random = rand.nextInt(3);
		
		if(random == 0)
		{
			tree = new WorldGenBigTree(true);
			tree.generate(world, rand, x, y, z);
		}
		else if(random == 1)
		{
			tree = new WorldGenCanopyTree(true);
			tree.generate(world, rand, x, y, z);
		}
		else if(random == 2)
		{
			WorldGenBigMushroom mushroom = new WorldGenBigMushroom();
			mushroom.generate(world, rand, x, y, z);
		}
	}
	
	public static void spawnRandomEntity(World world, double x, double y, double z)
	{
		int entityListSize = EntityList.IDtoClassMapping.size();
		int randomEntityIndex = rand.nextInt(entityListSize);
		int randomEntityId = (Integer) EntityList.IDtoClassMapping.keySet().toArray()[randomEntityIndex];
		Entity entity = EntityList.createEntityByID(randomEntityId, world);
		
		//to make sure random item is never null
		while(entity == null || !(entity instanceof EntityLiving))
		{
			randomEntityIndex = rand.nextInt(entityListSize);
			randomEntityId = (Integer) EntityList.IDtoClassMapping.keySet().toArray()[randomEntityIndex];
			entity = EntityList.createEntityByID(randomEntityId, world);
		}
		
		entity.setLocationAndAngles(x, y+1, z, 0, 0);
		world.spawnEntityInWorld(entity);
	}
}
