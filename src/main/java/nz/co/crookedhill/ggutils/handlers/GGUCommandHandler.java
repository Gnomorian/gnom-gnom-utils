/* 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package nz.co.crookedhill.ggutils.handlers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class GGUCommandHandler
{
	public static void init(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new GGUCommandHandler.GGUCreateChannel());
		e.registerServerCommand(new GGUCommandHandler.GGUDeleteChannel());
		e.registerServerCommand(new GGUCommandHandler.GGUAddToChannel());
		e.registerServerCommand(new GGUCommandHandler.GGUSetChannelpassword());
		e.registerServerCommand(new GGUCommandHandler.GGUSetChannelTextColour());
	}

	public static class GGUCreateChannel implements ICommand
	{
		@Override
		public int compareTo(Object arg0)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName()
		{
			// TODO Auto-generated method stub
			return "GGUCreateChannel";
		}

		@Override
		public String getCommandUsage(ICommandSender p_71518_1_)
		{
			// TODO Auto-generated method stub
			return "GGUCreateChannel <channelName>";
		}

		@Override
		public List getCommandAliases()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void processCommand(ICommandSender icommandsender, String[] astring)
		{
			if (astring.length < 1 || astring.length > 1)
			{
				if (icommandsender instanceof EntityPlayer)
					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments, usage: " + EnumChatFormatting.GREEN + this.getCommandUsage(icommandsender)));
				return;
			}
			icommandsender.addChatMessage(new ChatComponentText("Creating Channel: " + astring[0]));

		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		{
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		{
			return false;
		}
	}

	public static class GGUDeleteChannel implements ICommand
	{
		@Override
		public int compareTo(Object arg0)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName()
		{
			// TODO Auto-generated method stub
			return "GGUDeleteChannel";
		}

		@Override
		public String getCommandUsage(ICommandSender p_71518_1_)
		{
			// TODO Auto-generated method stub
			return EnumChatFormatting.GREEN + "GGUDeleteChannel" + EnumChatFormatting.GREEN + " <channelName>";
		}

		@Override
		public List getCommandAliases()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void processCommand(ICommandSender icommandsender, String[] astring)
		{
			if (astring.length < 1 || astring.length > 1)
			{
				if (icommandsender instanceof EntityPlayer)
					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments, usage: " + EnumChatFormatting.GREEN + this.getCommandUsage(icommandsender)));
				return;
			}
			icommandsender.addChatMessage(new ChatComponentText("Deleting Channel: " + astring[0]));

		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		{
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		{
			// TODO Auto-generated method stub
			return false;
		}
	}

	public static class GGUSetChannelTextColour implements ICommand
	{
		@Override
		public int compareTo(Object arg0)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName()
		{
			// TODO Auto-generated method stub
			return "GGUSetChannelTextColour";
		}

		@Override
		public String getCommandUsage(ICommandSender p_71518_1_)
		{
			// TODO Auto-generated method stub
			return EnumChatFormatting.GREEN + "GGUSetChannelTextColour " + EnumChatFormatting.GREEN + "<channelName> <Colour>";
		}

		@Override
		public List getCommandAliases()
		{
			return null;
		}

		@Override
		public void processCommand(ICommandSender icommandsender, String[] astring)
		{
			if (astring.length < 1 || astring.length > 1)
			{
				if (icommandsender instanceof EntityPlayer)
					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments, usage: " + this.getCommandUsage(icommandsender) + EnumChatFormatting.RESET));
				return;
			}
			icommandsender.addChatMessage(new ChatComponentText("Setting Colour of " + astring[0] + " to " + astring[1]));

		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		{
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		{
			// TODO Auto-generated method stub
			return false;
		}
	}

	public static class GGUSetChannelpassword implements ICommand
	{
		@Override
		public int compareTo(Object arg0)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName()
		{
			// TODO Auto-generated method stub
			return "GGUSetChannelpassword";
		}

		@Override
		public String getCommandUsage(ICommandSender p_71518_1_)
		{
			// have to add lots of green characters cos minecraft chat is stupid
			return EnumChatFormatting.GREEN + "GGUSetChannelpassword" + EnumChatFormatting.GREEN + " <channelName>" + EnumChatFormatting.GREEN + " <password>";
		}

		@Override
		public List getCommandAliases()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void processCommand(ICommandSender icommandsender, String[] astring)
		{
			if (astring.length < 2 || astring.length > 2)
			{
				if (icommandsender instanceof EntityPlayer)
					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments, usage: " + EnumChatFormatting.GREEN + this.getCommandUsage(icommandsender)));
				return;
			}
			icommandsender.addChatMessage(new ChatComponentText("Setting Password of " + astring[0] + " to " + astring[1]));

		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		{
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		{
			// TODO Auto-generated method stub
			return false;
		}
	}

	public static class GGUAddToChannel implements ICommand
	{
		@Override
		public int compareTo(Object arg0)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getCommandName()
		{
			// TODO Auto-generated method stub
			return "GGUMoveToChannel";
		}

		@Override
		public String getCommandUsage(ICommandSender p_71518_1_)
		{
			// TODO Auto-generated method stub
			return EnumChatFormatting.GREEN + "GGUMoveToChannel " + EnumChatFormatting.GREEN + "<playerName>" + EnumChatFormatting.GREEN + " <channelName>";
		}

		@Override
		public List getCommandAliases()
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void processCommand(ICommandSender icommandsender, String[] astring)
		{
			if (astring.length < 2 || astring.length > 2)
			{
				if (icommandsender instanceof EntityPlayer)
					icommandsender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Invalid arguments, usage: " + EnumChatFormatting.GREEN + this.getCommandUsage(icommandsender)));
				return;
			}
			icommandsender.addChatMessage(new ChatComponentText("Player " + astring[0] + " is now a member of " + astring[1]));

		}

		@Override
		public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
		{
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
		{
			// TODO Auto-generated method stub
			return false;
		}
	}
}
