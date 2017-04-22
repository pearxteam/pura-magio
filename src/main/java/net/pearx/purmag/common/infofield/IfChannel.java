package net.pearx.purmag.common.infofield;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;

import java.util.ArrayList;

/**
 * Created by mrAppleXZ on 15.04.17 9:43.
 */
public class IfChannel
{
    private IGuiDrawable icon;
    private String id;
    private int tier;

    private ArrayList<String> entries = new ArrayList<>();

    public IfChannel(String id, IGuiDrawable icon, int tier)
    {
        setId(id);
        setIcon(icon);
        setTier(tier);
    }

    public IfChannel(String id, IGuiDrawable icon, int tier, String... entries)
    {
        this(id, icon, tier);
        for (String entr : entries)
            addEntry(entr);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public IGuiDrawable getIcon()
    {
        return icon;
    }

    public void setIcon(IGuiDrawable icon)
    {
        this.icon = icon;
    }

    public ArrayList<String> getEntries()
    {
        return entries;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    public void addEntry(String entr)
    {
        getEntries().add(entr);
    }

    public void removeEntry(String entr)
    {
        getEntries().remove(entr);
    }

    public String getDisplayName()
    {
        return I18n.format("if_channel." + id + ".name");
    }

    public boolean isAvailable(EntityPlayer p, int tier)
    {
        return tier >= getTier();
    }

    public boolean containsEntry(String id)
    {
        return entries.contains(id);
    }
}
