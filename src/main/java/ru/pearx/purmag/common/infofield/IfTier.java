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
    @SideOnly(Side.CLIENT)
    public static class TabletData
    {
        private IGuiDrawable entryBackground;
        private boolean shouldGlow;
        private ResourceLocation texture;
        private Color lineColorStart;
        private Color lineColorEnd;

        public TabletData(IGuiDrawable entryBackground, boolean shouldGlow, ResourceLocation texture, Color lineColorStart, Color lineColorEnd)
        {
            this.entryBackground = entryBackground;
            this.shouldGlow = shouldGlow;
            this.texture = texture;
            this.lineColorStart = lineColorStart;
            this.lineColorEnd = lineColorEnd;
        }

        public IGuiDrawable getEntryBackground()
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

        public Color getLineColorStart()
        {
            return lineColorStart;
        }

        public void setLineColorStart(Color lineColorStart)
        {
            this.lineColorStart = lineColorStart;
        }

        public Color getLineColorEnd()
        {
            return lineColorEnd;
        }

        public void setLineColorEnd(Color lineColorEnd)
        {
            this.lineColorEnd = lineColorEnd;
        }
    }

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
}
