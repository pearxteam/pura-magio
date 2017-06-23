package net.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
    public static IGuiDrawable displayMessage;
    public static IGuiDrawable paperEntry;
    public static IGuiDrawable splitter;
    public static HashMap<Integer, IGuiDrawable> ifTabletEntryBgs = new HashMap<>();

    public static void setup()
    {
        runes = new AnimatedDrawable(Utils.getRegistryName("textures/other/runes.png"), 32, 38, 32, 38, 32, 380, 100, 0, -6);
        displayMessage = new AnimatedDrawable(Utils.getRegistryName("textures/other/message.png"), 192, 32, 192, 32, 192, 1024, 100);
        paperEntry = new SimpleDrawable(Utils.getRegistryName("textures/other/sticker.png"), 32, 32, 32, 32);
        splitter = new SimpleDrawable(Utils.getRegistryName("textures/other/splitter.png"), 276, 4, 276, 4);
        ifTabletEntryBgs.put(0, paperEntry);
        ifTabletEntryBgs.put(1, runes);
        ifTabletEntryBgs.put(2, runes);
    }
}