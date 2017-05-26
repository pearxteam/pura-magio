package net.pearx.purmag.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.drawables.SimpleDrawable;
import net.pearx.purmag.common.Utils;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 28.04.17 12:21.
 */
@SideOnly(Side.CLIENT)
public class GuiDrawableRegistry
{
    public static IGuiDrawable runes;
    public static IGuiDrawable stepCollect;
    public static IGuiDrawable displayMessage;
    public static IGuiDrawable paperEntry;
    public static HashMap<Integer, IGuiDrawable> ifTabletEntryBgs = new HashMap<>();

    public static void setup()
    {
        runes = new AnimatedDrawable(Utils.getRegistryName("textures/runes.png"), 32, 38, 32, 38, 32, 380, 100, 0, -4);
        displayMessage = new AnimatedDrawable(Utils.getRegistryName("textures/message.png"), 192, 32, 192, 32, 192, 1024, 100);
        paperEntry = new SimpleDrawable(Utils.getRegistryName("textures/sticker.png"), 32, 32, 32, 32);
        ifTabletEntryBgs.put(0, paperEntry);
        ifTabletEntryBgs.put(1, runes);
        ifTabletEntryBgs.put(2, runes);
    }
}