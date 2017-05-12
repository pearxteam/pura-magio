package net.pearx.purmag.common.infofield;

import net.minecraft.item.ItemStack;
import net.pearx.purmag.client.guis.drawables.BigItemDrawable;
import net.pearx.purmag.common.infofield.pages.IfPageText;
import net.pearx.purmag.common.infofield.steps.IRSCollect;
import net.pearx.purmag.common.items.ItemBlockCrystal;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mrAppleXZ on 15.04.17 9:42.
 */
public class IfRegistry
{
    public ArrayList<IfChannel> channels = new ArrayList<>();
    public ArrayList<IfEntry> entries = new ArrayList<>();
    public ArrayList<IfTier> tiers = new ArrayList<>();

    public void registerChannel(IfChannel chan)
    {
        channels.add(chan);
    }

    public void registerEntry(IfEntry entr)
    {
        entries.add(entr);
    }

    public void registerTier(IfTier t)
    {
        tiers.add(t);
    }

    public IfChannel getChannel(String id)
    {
        for(IfChannel chan : channels)
            if(chan.getId().equals(id))
                return chan;
        return null;
    }

    public IfEntry getEntry(String id)
    {
        for(IfEntry entr : entries)
            if(entr.getId().equals(id))
                return entr;
        return null;
    }

    public boolean containsEntry(String id)
    {
        return getEntry(id) != null;
    }

    public boolean containsChannel(String id)
    {
        return getChannel(id) != null;
    }

    public IfTier getTier(int tier)
    {
        for (IfTier t : tiers)
        {
            if(t.getTier() == tier)
                return t;
        }
        return null;
    }

    public void setup()
    {
        registerTier(new IfTier(0));
        registerTier(new IfTier(1));
        registerTier(new IfTier(2));

        registerChannel(new IfChannel("information_field", new BigItemDrawable(new ItemStack(ItemRegistry.if_tablet)), 0));
        registerChannel(new IfChannel("crystallography", new BigItemDrawable(new ItemStack(ItemRegistry.crystal)), 0, "crystals", "crystals1", "crystals2"));

        registerEntry(new IfEntry("crystals", 0, new BigItemDrawable(ItemUtils.getItemWithSip(SipTypeRegistry.def, ItemRegistry.crystal)), null, Arrays.asList(new IRSCollect(new ItemStack(ItemRegistry.crystal_shard), "crystals.0", true)), 0, 0, 0, new IfPageText("crystals.0")));
    }
}



