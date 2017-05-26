package net.pearx.purmag.client.guis.if_tablet;

import net.minecraftforge.client.MinecraftForgeClient;
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
        bit = MinecraftForgeClient.reserveStencilBit();
        int flag = 1 << bit;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilFunc(GL11.GL_ALWAYS, flag, flag);
        GL11.glStencilOp(GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_REPLACE);
        GL11.glStencilMask(flag);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthMask(false);
        GL11.glClearStencil(0);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        DrawingTools.drawRectangle(0, 0, getWidth(), getHeight());
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glStencilFunc(GL11.GL_EQUAL, flag, flag);
        GL11.glStencilMask(0);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(true);
    }

    @Override
    public void postRender()
    {
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        MinecraftForgeClient.releaseStencilBit(bit);
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
