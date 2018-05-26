package ru.pearx.purmag.common.infofield;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.client.gui.drawables.IGuiDrawable;

import java.util.ArrayList;

/**
 * Created by mrAppleXZ on 15.04.17 9:43.
 */
public class IfChannel
{
    @SideOnly(Side.CLIENT)
    private IGuiDrawable icon;
    private String id;
    private int tier;

    private ArrayList<IfEntryLocation> entries = new ArrayList<>();

    public IfChannel(String id, int tier)
    {
        setId(id);
        setTier(tier);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @SideOnly(Side.CLIENT)
    public IGuiDrawable getIcon()
    {
        return icon;
    }

    @SideOnly(Side.CLIENT)
    public void setIcon(IGuiDrawable icon)
    {
        this.icon = icon;
    }

    public ArrayList<IfEntryLocation> getEntries()
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

    public void addEntry(IfEntryLocation entr)
    {
        getEntries().add(entr);
    }

    public void removeEntry(IfEntryLocation entr)
    {
        getEntries().remove(entr);
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName()
    {
        return I18n.format("if_channel." + id + ".name");
    }

    public boolean isAvailable(EntityPlayer p, int tier)
    {
        return tier >= getTier();
    }

    public boolean containsEntry(IfEntryLocation id)
    {
        return entries.contains(id);
    }
}
