package net.pearx.purmag.infofield;

import net.minecraft.item.ItemStack;
import net.pearx.purmag.client.guis.drawables.ItemDrawable;
import net.pearx.purmag.items.ItemRegistry;

import java.util.ArrayList;

/**
 * Created by mrAppleXZ on 15.04.17 9:42.
 */
public class IFRegistry
{
    public ArrayList<IFChannel> channels = new ArrayList<>();
    public ArrayList<IFEntry> entries = new ArrayList<>();
    public ArrayList<IFTier> tiers = new ArrayList<>();

    public void registerChannel(IFChannel chan)
    {
        channels.add(chan);
    }

    public void registerEntry(IFEntry entr)
    {
        entries.add(entr);
    }

    public void registerTier(IFTier t)
    {
        tiers.add(t);
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

    public IFTier getTier(int tier)
    {
        for (IFTier t : tiers)
        {
            if(t.getTier() == tier)
                return t;
        }
        return null;
    }

    public void setup()
    {
        registerTier(new IFTier(0));
        registerTier(new IFTier(1));
        registerTier(new IFTier(2));

        registerChannel(new IFChannel("information_field", new ItemDrawable(new ItemStack(ItemRegistry.if_tablet), 1.5f), 0));
        registerChannel(new IFChannel("crystallogy", new ItemDrawable(new ItemStack(ItemRegistry.crystal), 1.5f), 0));
    }
}
