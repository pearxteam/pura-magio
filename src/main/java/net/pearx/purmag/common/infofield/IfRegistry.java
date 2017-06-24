package net.pearx.purmag.common.infofield;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.drawables.BigItemDrawable;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.drawables.SimpleDrawable;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.infofield.pages.IIfPage;
import net.pearx.purmag.common.infofield.pages.IfPagePapyrus;
import net.pearx.purmag.common.infofield.pages.IfPageText;
import net.pearx.purmag.common.infofield.steps.IRSCollect;
import net.pearx.purmag.common.infofield.steps.IRSReadPapyrus;
import net.pearx.purmag.common.infofield.steps.IRSTranslatePapyrus;
import net.pearx.purmag.common.items.ItemRegistry;

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

    public IfRegistry()
    {
        registerTier(new IfTier(0)); //Only player's thoughts.
        registerTier(new IfTier(1)); //Player's thoughts and simple researches.
        registerTier(new IfTier(2)); //Player's thoughts and advanced researches.
        registerTier(new IfTier(3)); //Ancients' knowledges.
        registerTier(new IfTier(4)); //The WHITE CATACLYSM knowledge.
    }

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

    public void attachEntry(String channel, IfEntryLocation entry)
    {
        getChannel(channel).addEntry(entry);
    }

    @SideOnly(Side.CLIENT)
    public void registerEntryClient(String name, IGuiDrawable icon, IIfPage... pages)
    {
        IfEntry entr = getEntry(name);
        entr.setIcon(icon);
        entr.setPages(Arrays.asList(pages));
    }

    @SideOnly(Side.CLIENT)
    public void registerChannelClient(String name, IGuiDrawable icon)
    {
        getChannel(name).setIcon(icon);
    }

    public void setup()
    {
        registerChannel(new IfChannel("infofield", 0));
        registerChannel(new IfChannel("geology", 0));
        registerChannel(new IfChannel("sip", 1));
        registerChannel(new IfChannel("sif", 2));
        //todo icon
        registerChannel(new IfChannel("ancients", 3));
        //todo icon
        registerChannel(new IfChannel("white", 4));

        registerEntry(new IfEntry(
                "crysagnetite", 0,
                null,
                Arrays.asList(new IRSCollect(new ItemStack(ItemRegistry.ore_crysagnetite), "crysagnetite", true)),
                0));
        attachEntry("geology", new IfEntryLocation("crysagnetite", 0, 0));

        registerEntry(new IfEntry(
                "sip_knowledge", 1,
                null,
                Arrays.asList(new IRSReadPapyrus("sip_knowledge"), new IRSTranslatePapyrus("sip_knowledge")),
                1));
        attachEntry("sip", new IfEntryLocation("sip_knowledge", 0, 0));
        //registerEntry(new IfEntry("crystals", 0, new BigItemDrawable(ItemUtils.getItemWithSip(SipTypeRegistry.DEFAULT, ItemRegistry.crystal)), null, Arrays.asList(new IRSCollect(new ItemStack(ItemRegistry.crystal_shard), "crystals.0", true)), 0, 0, 0, new IfPageText("crystals.0"), new IfPageText("crystals.1")));
    }

    @SideOnly(Side.CLIENT)
    public void setupClient()
    {
        registerChannelClient("infofield", new BigItemDrawable(new ItemStack(ItemRegistry.if_tablet, 1, 1)));
        registerChannelClient("geology", new BigItemDrawable(new ItemStack(ItemRegistry.crystal)));
        registerChannelClient("sip", new SimpleDrawable(Utils.getRegistryName("textures/icons/sip.png"), 28, 28, 28, 28));
        registerChannelClient("sif", new SimpleDrawable(Utils.getRegistryName("textures/icons/sif.png"), 28, 28, 28, 28));
        registerChannelClient("ancients", new SimpleDrawable(Utils.getRegistryName("todo"), 28, 28, 28, 28));
        registerChannelClient("white", new SimpleDrawable(Utils.getRegistryName("todo"), 28, 28, 28, 28));

        registerEntryClient(
                "crysagnetite", new BigItemDrawable(new ItemStack(ItemRegistry.ore_crysagnetite)),
                new IfPageText("crysagnetite.0"), new IfPageText("crysagnetite.1", Integer.toString(PurMag.instance.config.genCrysagnetite.minY), Integer.toString(PurMag.instance.config.genCrysagnetite.maxY)
                ));
        registerEntryClient(
                "sip_knowledge", new SimpleDrawable(Utils.getRegistryName("textures/icons/sip_text.png"), 28, 28, 28, 28),
                new IfPageText("sip_knowledge.0"),
                new IfPagePapyrus("sip_knowledge.1")
        );
    }
}



