package net.pearx.purmag.client.guis.drawables;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 15.04.17 9:48.
 */
public interface IGuiDrawable
{
    int getWidth();
    int getHeight();

    @SideOnly(Side.CLIENT)
    void draw(int x, int y);
}
