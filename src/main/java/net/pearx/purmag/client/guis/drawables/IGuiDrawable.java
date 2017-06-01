package net.pearx.purmag.client.guis.drawables;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 15.04.17 9:48.
 */
@SideOnly(Side.CLIENT)
public interface IGuiDrawable
{
    int getWidth();
    int getHeight();

    void draw(int x, int y);
}
