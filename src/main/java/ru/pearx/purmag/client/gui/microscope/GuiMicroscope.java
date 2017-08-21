package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.GuiOnScreen;
import ru.pearx.purmag.client.PurMagClient;

/*
 * Created by mrAppleXZ on 18.08.17 20:48.
 */
@SideOnly(Side.CLIENT)
public class GuiMicroscope extends GuiOnScreen
{
    protected ItemStack stack;

    public GuiMicroscope(ItemStack stack)
    {
        this.stack = stack;
        setWidth(368);
        setHeight(240);
    }

    @Override
    public void render()
    {
        int y = 8;
        for(String s : PurMagClient.INSTANCE.getMicroscopeDataBuilder().build(stack))
        {
            DrawingTools.drawString(s, 8, y, Colors.GREY_500);
            y += DrawingTools.getFontHeight();
        }
    }
}
