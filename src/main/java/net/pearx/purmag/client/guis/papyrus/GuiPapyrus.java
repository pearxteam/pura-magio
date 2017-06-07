package net.pearx.purmag.client.guis.papyrus;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.GuiOnScreen;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;

import java.awt.*;

/**
 * Created by mrAppleXZ on 31.05.17 19:46.
 */
@SideOnly(Side.CLIENT)
public class GuiPapyrus extends GuiOnScreen
{
    private String text;

    public GuiPapyrus(String papyrusId)
    {
        text = ItemRegistry.papyrus.getPapyrus(papyrusId).getDisplayText();
        setWidth(192);
        setHeight(240);
    }

    @Override
    public void render()
    {
        DrawingTools.drawTexture(Utils.getRegistryName("textures/gui/papyrus.png"), 0, 0, getWidth(), getHeight());
        boolean unic = Minecraft.getMinecraft().fontRenderer.getUnicodeFlag();
        Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(true);
        DrawingTools.drawString(text, 5, 5, Color.LIGHT_GRAY, getWidth() - 10);
        Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(unic);
    }
}
