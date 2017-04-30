package net.pearx.purmag.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;

/**
 * Created by mrAppleXZ on 28.04.17 12:21.
 */
@SideOnly(Side.CLIENT)
public class GuiDrawableRegistry
{
    public static IGuiDrawable runes;
    public static IGuiDrawable stepCollect;
    public static IGuiDrawable displayMessage;

    public static void setup()
    {
        runes = new AnimatedDrawable(new ResourceLocation(PurMag.ModId, "textures/runes.png"), 32, 38, 32, 38, 32, 380, 100);
        displayMessage = new AnimatedDrawable(new ResourceLocation(PurMag.ModId, "textures/message.png"), 192, 32, 192, 32, 192, 1024, 100);
    }
}