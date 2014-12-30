package nz.co.crookedhill.ggutils.helper;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentText;

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
}
