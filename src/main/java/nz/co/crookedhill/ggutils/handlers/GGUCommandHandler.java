package nz.co.crookedhill.ggutils.handlers;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class GGUCommandHandler
{
    public GGUCommandHandler(FMLServerStartingEvent e)
    {
	e.registerServerCommand(new GGUCommandHandler.SampleCommand());
    }

    public class SampleCommand implements ICommand
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
	    return "sample";
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
	    // TODO Auto-generated method stub
	    return "sample <text>";
	}

	@Override
	public List getCommandAliases()
	{
	    // TODO Auto-generated method stub
	    return null;
	}

	@Override
	public void processCommand(ICommandSender icommandsender,
		String[] astring)
	{
	    if (astring.length == 0)
	    {
		if (icommandsender instanceof EntityPlayer)
		    icommandsender.addChatMessage(new ChatComponentText(
			    "Invalid arguments"));
		return;
	    }
	    icommandsender.addChatMessage(new ChatComponentText("Sample: ["
		    + astring[0] + "]"));

	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
	{
	    // TODO Auto-generated method stub
	    return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_,
		String[] p_71516_2_)
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
