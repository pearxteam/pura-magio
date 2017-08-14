package ru.pearx.purmag.common.infofield;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.drawables.BigItemDrawable;
import ru.pearx.libmc.client.gui.drawables.EntityDrawable;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.SimpleDrawable;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.entities.EntityBeetle;
import ru.pearx.purmag.client.infofield.pages.IIfPage;
import ru.pearx.purmag.client.infofield.pages.IfPageEntity;
import ru.pearx.purmag.client.infofield.pages.IfPagePapyrus;
import ru.pearx.purmag.client.infofield.pages.IfPageText;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.*;
import ru.pearx.purmag.common.items.ItemRegistry;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public <T extends IIfResearchStep> List<Pair<IfEntry, T>> getAllResearchableSteps(Class<T> clazz, EntityPlayer p)
    {
        List<Pair<IfEntry, T>> lst = new ArrayList<>();
        IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
        for(IfEntry entr : entries)
        {
            int steps = store.getSteps(entr.getId());
            if(steps < entr.getSteps().size())
            {
                IIfResearchStep step = entr.getSteps().get(steps);
                if(clazz.isInstance(step))
                {
                    if(entr.isAvailableToResearch(p))
                    {
                        lst.add(Pair.of(entr, (T) step));
                    }
                }
            }
        }
        return lst;
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
        registerChannel(new IfChannel("information", 0));
        registerChannel(new IfChannel("exploration", 0));
        registerChannel(new IfChannel("sip", 1));

        registerEntry(new IfEntry("wooden_tablet", 0, null, Collections.emptyList(), 0));
        attachEntry("information", new IfEntryLocation("wooden_tablet", 0, 0));

        registerEntry(new IfEntry(
                "crysagnetite", 0,
                null,
                Arrays.asList(new IRSCollect(new ItemStack(ItemRegistry.ore_crysagnetite), "crysagnetite", true)),
                0));
        attachEntry("exploration", new IfEntryLocation("crysagnetite", 0, 0));

        registerEntry(new IfEntry(
                "sip_knowledge", 1,
                null,
                Arrays.asList(new IRSReadPapyrus("sip_knowledge"), new IRSTranslatePapyrus("sip_knowledge")),
                1));
        attachEntry("sip", new IfEntryLocation("sip_knowledge", 0, 0));

        registerEntry(new IfEntry(
                "verda_beetle", 0,
                null,
                Arrays.asList(new IRSKillEntity(EntityBeetle.class, "verda_beetle")),
                0));
        attachEntry("exploration", new IfEntryLocation("verda_beetle", 0, 5));
        //registerEntry(new IfEntry("crystals", 0, new BigItemDrawable(ItemUtils.getItemWithSip(SipTypeRegistry.DEFAULT, ItemRegistry.crystal)), null, Arrays.asList(new IRSCollect(new ItemStack(ItemRegistry.crystal_shard), "crystals.0", true)), 0, 0, 0, new IfPageText("crystals.0"), new IfPageText("crystals.1")));
    }

    @SideOnly(Side.CLIENT)
    public void setupClient()
    {
        registerChannelClient("information", new BigItemDrawable(new ItemStack(ItemRegistry.if_tablet, 1, 1)));
        registerChannelClient("exploration", new BigItemDrawable(new ItemStack(ItemRegistry.crystal)));
        registerChannelClient("sip", new SimpleDrawable(Utils.getRegistryName("textures/icons/sip.png"), 28, 28, 28, 28));

        registerEntryClient(
                "wooden_tablet", new BigItemDrawable(new ItemStack(ItemRegistry.if_tablet)),
                new IfPageText("wooden_tablet.0"),
                new IfPageText("wooden_tablet.1")
        );
        registerEntryClient(
                "crysagnetite", new BigItemDrawable(new ItemStack(ItemRegistry.ore_crysagnetite)),
                new IfPageText("crysagnetite.0"),
                new IfPageText("crysagnetite.1", Integer.toString(PurMag.INSTANCE.config.genCrysagnetite.minY), Integer.toString(PurMag.INSTANCE.config.genCrysagnetite.maxY)
                ));
        registerEntryClient(
                "sip_knowledge", new SimpleDrawable(Utils.getRegistryName("textures/icons/sip_text.png"), 28, 28, 28, 28),
                new IfPageText("sip_knowledge.0"),
                new IfPagePapyrus("sip_knowledge.1")
        );
        registerEntryClient(
                "verda_beetle", new EntityDrawable(EntityBeetle.class, 20, 5),
                new IfPageText("verda_beetle.0"),
                new IfPageEntity(EntityBeetle.class, "verda_beetle.1")
        );
    }
}



