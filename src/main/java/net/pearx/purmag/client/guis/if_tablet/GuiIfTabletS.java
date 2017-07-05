package net.pearx.purmag.client.guis.if_tablet;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * Created by mrAppleXZ on 28.04.17 11:27.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletS extends Control
{
    public int bit = 0;

    public GuiIfTabletS()
    {
        keyEventsRS = false;
    }

    @Override
    public void render()
    {
        bit = DrawingTools.drawStencil(getWidth(), getHeight());
    }

    @Override
    public void postRender()
    {
        DrawingTools.removeStencil(bit);
    }

    public GuiIfTablet getTablet()
    {
        if(getParent() instanceof GuiIfTablet)
            return (GuiIfTablet) getParent();
        return null;
    }

    @Override
    public int getWidth()
    {
        return getTablet().getWidth();
    }

    @Override
    public int getHeight()
    {
        return getTablet().getHeight();
    }

    public boolean isGlowing() { return false; }

    @Override
    public void keyUp(int keycode)
    {
        if(keycode == Keyboard.KEY_BACK || keycode == Keyboard.KEY_Q)
        {
            goBack();
        }
    }

    public void goBack()
    {
        getTablet().changeScreen(getTablet().se);
    }
}
