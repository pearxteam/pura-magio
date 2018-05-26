package ru.pearx.purmag.common.infofield;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.client.gui.drawables.IGuiDrawable;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.infofield.pages.IIfPage;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.IIfResearchStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 15.04.17 21:07.
 */
public class IfEntry
{
    private String id;
    private int tier;
    private List<String> parents;
    @SideOnly(Side.CLIENT)
    private IGuiDrawable icon;
    @SideOnly(Side.CLIENT)
    private List<IIfPage> pages;
    private List<IIfResearchStep> steps;
    private int stepsForDisplay;

    public IfEntry(String id, int tier, List<String> parents, List<IIfResearchStep> steps, int stepsForDisplay)
    {
        if (parents == null)
            parents = new ArrayList<>();
        if (steps == null)
            steps = new ArrayList<>();
        setId(id);
        setTier(tier);
        setParents(parents);
        setSteps(steps);
        setStepsForDisplay(stepsForDisplay);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    public List<String> getParents()
    {
        return parents;
    }

    public void setParents(List<String> parents)
    {
        this.parents = parents;
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

    @SideOnly(Side.CLIENT)
    public List<IIfPage> getPages()
    {
        return pages;
    }

    @SideOnly(Side.CLIENT)
    public void setPages(List<IIfPage> pages)
    {
        this.pages = pages;
    }

    public List<IIfResearchStep> getSteps()
    {
        return steps;
    }

    public void setSteps(List<IIfResearchStep> steps)
    {
        this.steps = steps;
    }

    public int getStepsForDisplay()
    {
        return stepsForDisplay;
    }

    public void setStepsForDisplay(int stepsForDisplay)
    {
        this.stepsForDisplay = stepsForDisplay;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName()
    {
        if (Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).getSteps(getId()) < getStepsForDisplay())
            return "???";
        return I18n.format("if_entry." + id + ".name");
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayDescription()
    {
        return I18n.format("if_entry." + id + ".desc");
    }

    //Just available to see, not read/continue researching/etc.
    public boolean isAvailableToSee(EntityPlayer p, int tier)
    {
        //if tier is < needed.
        if (tier < getTier())
            return false;

        if (!isAvailable(p))
            return false;

        return true;
    }

    public boolean isAvailableToResearch(EntityPlayer p)
    {
        return isAvailable(p);
    }

    public boolean isAvailable(EntityPlayer p)
    {
        for (String id : getParents())
        {
            if(!PurMag.INSTANCE.getIfRegistry().getEntry(id).isUnlocked(p))
                return false;
        }
        return true;
    }

    public boolean isUnlocked(EntityPlayer p)
    {
        IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
        if(store.getSteps(getId()) < getSteps().size())
        {
            return false;
        }
        if(getSteps().size() == 0)
        {
            for(String parent : getParents())
            {
                IfEntry entr = PurMag.INSTANCE.getIfRegistry().getEntry(parent);
                if(!entr.isUnlocked(p))
                    return false;
            }
        }
        return true;
    }
}
