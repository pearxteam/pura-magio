package net.pearx.purmag.infofield;

import net.minecraft.item.ItemStack;
import net.pearx.purmag.client.guis.ItemDrawable;
import net.pearx.purmag.items.ItemRegistry;

import java.util.ArrayList;

/**
 * Created by mrAppleXZ on 15.04.17 9:42.
 */
public class IFRegistry
{
    public ArrayList<IFChannel> channels = new ArrayList<>();
    public ArrayList<IFEntry> entries = new ArrayList<>();

    public void registerChannel(IFChannel chan)
    {
        channels.add(chan);
    }

    public void registerEntry(IFEntry entr)
    {
        entries.add(entr);
    }

    public IFChannel getChannel(String id)
    {
        for(IFChannel chan : channels)
            if(chan.getId().equals(id))
                return chan;
        return null;
    }

    public IFEntry getEntry(String id)
    {
        for(IFEntry entr : entries)
            if(entr.getId().equals(id))
                return entr;
        return null;
    }

    public void setup()
    {
        registerChannel(new IFChannel("crystallogy", new ItemDrawable(new ItemStack(ItemRegistry.crystal))));
    }
}
