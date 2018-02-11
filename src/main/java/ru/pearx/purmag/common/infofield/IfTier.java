package ru.pearx.purmag.common.infofield;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Color;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;

/*
 * Created by mrAppleXZ on 15.08.17 23:31.
 */
public class IfTier
{
    private int tier;
    @SideOnly(Side.CLIENT)
    private TabletData tabletData;
    @SideOnly(Side.CLIENT)
    private ResourceLocation wallTabletTexture;
    @SideOnly(Side.CLIENT)
    private ResourceLocation itemModel;

    public IfTier(int tier)
    {
        this.tier = tier;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    @SideOnly(Side.CLIENT)
    public TabletData getTabletData()
    {
        return tabletData;
    }

    @SideOnly(Side.CLIENT)
    public void setTabletData(TabletData tabletData)
    {
        this.tabletData = tabletData;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getWallTabletTexture()
    {
        return wallTabletTexture;
    }

    @SideOnly(Side.CLIENT)
    public void setWallTabletTexture(ResourceLocation wallTabletTexture)
    {
        this.wallTabletTexture = wallTabletTexture;
    }

    public String getUnlocalizedName()
    {
        return "if_tier." + getTier() + ".name";
    }

    @SideOnly(Side.CLIENT)
    public String getName()
    {
        return I18n.format(getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getItemModel()
    {
        return itemModel;
    }

    @SideOnly(Side.CLIENT)
    public void setItemModel(ResourceLocation itemModel)
    {
        this.itemModel = itemModel;
    }

    @SideOnly(Side.CLIENT)
    public static class TabletData
    {
        private IGuiDrawable entryBackground;
        private boolean shouldGlow;
        private ResourceLocation texture;
        private Color lineColor;
        private Color lineColorActive;

        public TabletData(IGuiDrawable entryBackground, boolean shouldGlow, ResourceLocation texture, Color lineColor, Color lineColorActive)
        {
            this.entryBackground = entryBackground;
            this.shouldGlow = shouldGlow;
            this.texture = texture;
            this.lineColor = lineColor;
            this.lineColorActive = lineColorActive;
        }

        public IGuiDrawable getEntryBackground(IfEntry entry, int currentSteps)
        {
            return entryBackground;
        }

        public void setEntryBackground(IGuiDrawable entryBackground)
        {
            this.entryBackground = entryBackground;
        }

        public boolean isShouldGlow()
        {
            return shouldGlow;
        }

        public void setShouldGlow(boolean shouldGlow)
        {
            this.shouldGlow = shouldGlow;
        }

        public ResourceLocation getTexture()
        {
            return texture;
        }

        public void setTexture(ResourceLocation texture)
        {
            this.texture = texture;
        }

        public Color getLineColor()
        {
            return lineColor;
        }

        public void setLineColor(Color lineColor)
        {
            this.lineColor = lineColor;
        }

        public Color getLineColorActive()
        {
            return lineColorActive;
        }

        public void setLineColorActive(Color lineColorActive)
        {
            this.lineColorActive = lineColorActive;
        }
    }
}
