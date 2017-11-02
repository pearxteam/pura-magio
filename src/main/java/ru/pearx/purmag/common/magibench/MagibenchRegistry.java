package ru.pearx.purmag.common.magibench;

import net.minecraft.util.ResourceLocation;
import ru.pearx.purmag.common.Utils;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 30.10.17 18:19.
 */
public class MagibenchRegistry
{
    public static class Tier
    {
        private int tier;
        private int width;
        private int height;
        private int guiGridX;
        private int guiGridY;
        private int guiResultX;
        private int guiResultY;
        private ResourceLocation guiTexture;
        private int guiJeiStartX;
        private int guiJeiStartY;

        public Tier(int tier, int width, int height, int guiGridX, int guiGridY, int guiResultX, int guiResultY, ResourceLocation guiTexture, int guiJeiStartX, int guiJeiStartY)
        {
            this.tier = tier;
            this.width = width;
            this.height = height;
            this.guiGridX = guiGridX;
            this.guiGridY = guiGridY;
            this.guiResultX = guiResultX;
            this.guiResultY = guiResultY;
            this.guiTexture = guiTexture;
            this.guiJeiStartX = guiJeiStartX;
            this.guiJeiStartY = guiJeiStartY;
        }

        public int getTier()
        {
            return tier;
        }

        public int getWidth()
        {
            return width;
        }

        public int getHeight()
        {
            return height;
        }

        public int getGuiGridX()
        {
            return guiGridX;
        }

        public int getGuiGridY()
        {
            return guiGridY;
        }

        public int getGuiResultX()
        {
            return guiResultX;
        }

        public int getGuiResultY()
        {
            return guiResultY;
        }

        public ResourceLocation getGuiTexture()
        {
            return guiTexture;
        }

        public int getGuiJeiStartX()
        {
            return guiJeiStartX;
        }

        public int getGuiJeiStartY()
        {
            return guiJeiStartY;
        }
    }

    private List<Tier> tiers = new ArrayList<>();

    public List<Tier> getTiers()
    {
        return tiers;
    }

    public void registerTier(Tier t)
    {
        getTiers().add(t);
    }

    public Tier getTier(int tier)
    {
        for(Tier t : getTiers())
            if(t.getTier() == tier)
                return t;
        return null;
    }

    public void setup()
    {
        //jei: (144 - 104) / 2 = 20;  (94 - 54) / 2 = 20;
        registerTier(new Tier(0, 3, 3, 48, 29, 133, 46, Utils.getResourceLocation("textures/gui/inventory/magibench/0.png"), 20, 10));
    }
}
