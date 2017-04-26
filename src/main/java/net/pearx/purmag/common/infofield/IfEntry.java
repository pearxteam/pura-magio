package net.pearx.purmag.common.infofield;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.pages.IIfPage;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;

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
    private IGuiDrawable icon;
    private IIfPage[] pages;
    private List<IIfResearchStep> steps;
    private int stepsForDisplay;
    private int x;
    private int y;

    public IfEntry(String id, int tier, IGuiDrawable icon, List<String> parents, List<IIfResearchStep> steps, int stepsForDisplay, int x, int y, IIfPage... pages)
    {
        if(parents == null)
            parents = new ArrayList<>();
        if(steps == null)
            steps = new ArrayList<>();
        setId(id);
        setTier(tier);
        setParents(parents);
        setIcon(icon);
        setPages(pages);
        setSteps(steps);
        setStepsForDisplay(stepsForDisplay);
        setX(x);
        setY(y);
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

    public IGuiDrawable getIcon()
    {
        return icon;
    }

    public void setIcon(IGuiDrawable icon)
    {
        this.icon = icon;
    }

    public IIfPage[] getPages()
    {
        return pages;
    }

    public void setPages(IIfPage[] pages)
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

    public String getDisplayName()
    {
        return I18n.format("if_entry." + id + ".name");
    }

    //Just available to see, not read/continue researching/etc.
    public boolean isAvailable(EntityPlayer p, int tier)
    {
        IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null);
        //if tier is < needed.
        if (tier < getTier())
            return false;

        if(!isAllParentsUnlocked(p))
            return false;

        if(store.getSteps(getId()) < getStepsForDisplay())
            return false;

        return true;
    }

    public boolean isAllParentsUnlocked(EntityPlayer p)
    {
        for (String id : getParents())
            if (!p.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).isFullyUnlocked(id))
                return false;
        return true;
    }

    public int getStepsForDisplay()
    {
        return stepsForDisplay;
    }

    public void setStepsForDisplay(int stepsForDisplay)
    {
        this.stepsForDisplay = stepsForDisplay;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
