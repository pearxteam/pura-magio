package net.pearx.purmag.infofield;

import net.minecraft.client.resources.I18n;
import net.pearx.purmag.client.guis.IGuiDrawable;

import java.util.ArrayList;

/**
 * Created by mrAppleXZ on 15.04.17 9:43.
 */
public class IFChannel
{
    private IGuiDrawable icon;
    private String id;

    private ArrayList<String> entries = new ArrayList<>();

    public IFChannel(String id, IGuiDrawable icon)
    {
        setId(id);
        setIcon(icon);
    }

    public IFChannel(String id, IGuiDrawable icon, String... entries)
    {
        this(id, icon);
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
}
