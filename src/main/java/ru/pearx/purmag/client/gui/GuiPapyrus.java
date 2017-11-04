package ru.pearx.purmag.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;


/**
 * Created by mrAppleXZ on 31.05.17 19:46.
 */
@SideOnly(Side.CLIENT)
public class GuiPapyrus extends GuiOnScreen
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/gui/papyrus.png");
    private String text;

    public GuiPapyrus(String papyrusId)
    {
        text = PurMag.INSTANCE.getPapyrusRegistry().getPapyrus(papyrusId).getDisplayText();
        setWidth(192);
        setHeight(240);
    }

    @Override
    public void render()
    {
        DrawingTools.drawTexture(TEXTURE, 0, 0, getWidth(), getHeight());
        boolean unic = Minecraft.getMinecraft().fontRenderer.getUnicodeFlag();
        Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(true);
        DrawingTools.drawString(text, 5, 5, Colors.GREY_500, getWidth() - 10);
        Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(unic);
    }
}
