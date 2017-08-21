package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;

/**
 * Created by mrAppleXZ on 28.04.17 11:27.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletS extends Control
{
    protected int bit = 0;

    @Override
    public void render()
    {
        enableStencil();
    }

    public void enableStencil()
    {
        bit = DrawingTools.drawStencil(getWidth(), getHeight());
    }

    public void disableStencil()
    {
        DrawingTools.removeStencil(bit);
    }

    @Override
    public void postRender()
    {
        disableStencil();
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
