package ru.pearx.purmag.common.commands;


import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mrAppleXZ on 25.04.17 15:10.
 */
public class CommandIf extends CommandBase
{
    @Override
    public String getName()
    {
        return "pm_infofield";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "command.pm_if.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length != 3 && args.length != 2)
        {
            throw new WrongUsageException(getUsage(sender));
        }
        String act = args[0];
        String res = args[1];
        String player = args.length > 2 ? args[2] : sender.getName();
        if (!Arrays.asList(server.getPlayerList().getOnlinePlayerNames()).contains(player))
        {
            throw new CommandException("command.pm_if.playerNotFound");
        }
        if (!(PurMag.INSTANCE.getIfRegistry().containsEntry(res) || res.equals("all")))
        {
            throw new CommandException("command.pm_if.entryNotFound");
        }

        EntityPlayerMP p = server.getPlayerList().getPlayerByUsername(player);
        IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
        if(res.equals("all"))
        {
            for(IfEntry entr : PurMag.INSTANCE.getIfRegistry().entries)
            {
                setSteps(store, sender, act, entr.getId());
            }
        }
        else
        {
            setSteps(store, sender, act, res);
        }
        store.sync(p);
        sender.sendMessage(new TextComponentTranslation("command.pm_if.success." + act, "§5" + res, "§5" + player));
    }

    private void setSteps(IIfEntryStore store, ICommandSender sender, String act, String res) throws CommandException
    {
        int steps;
        switch (act)
        {
            case "unlock":
                steps = PurMag.INSTANCE.getIfRegistry().getEntry(res).getSteps().size();
                break;
            case "lock":
                steps = 0;
                break;
            default:
                throw new WrongUsageException(getUsage(sender));
        }
        store.setSteps(res, steps);
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 2;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
        if (args.length == 1)
            return Arrays.asList("unlock", "lock");
        if (args.length == 2)
        {
            ArrayList<String> arr = new ArrayList<>();
            for (IfEntry entr : PurMag.INSTANCE.getIfRegistry().entries)
                arr.add(entr.getId());
            return arr;
        }
        if (args.length == 3)
            return Arrays.asList(server.getPlayerList().getOnlinePlayerNames());
        return Collections.EMPTY_LIST;
    }
}
