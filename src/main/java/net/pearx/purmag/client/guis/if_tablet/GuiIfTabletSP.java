package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.GuiDrawableRegistry;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.common.Button;
import net.pearx.purmag.client.guis.if_tablet.pages.IPRenderer;
import net.pearx.purmag.common.infofield.IfEntry;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;

/**
 * Created by mrAppleXZ on 02.05.17 10:32.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSP extends GuiIfTabletS
{
    public IfEntry entry;
    public int index;

    private IPRenderer rend;
    private IPRenderer newRend;
    private boolean sensitive = true;

    public GuiIfTabletSP(IfEntry entr, int index)
    {
        super();
        this.entry = entr;
        this.index = index;
    }

    @Override
    public void init()
    {
        super.init();
        Button btnBack = new Button(I18n.format("button.back.name"), this::goBack);
        //borders (8 * 2)
        int backWidth = getWidth() - 16;
        int y = getHeight() - 16 - 8;
        btnBack.setWidth(backWidth);
        btnBack.setHeight(16);
        btnBack.setX(8);
        btnBack.setY(y);
        controls.add(btnBack);

        index = index - 1;
        update(true, false);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        if(Mouse.isButtonDown(0))
            if(dx > 2 || dx < -2)
                update(dx < 0, true);
    }

    @Override
    public void keyUp(int keycode)
    {
        super.keyUp(keycode);
        if(keycode == Keyboard.KEY_RIGHT)
            update(true, true);
        else if(keycode == Keyboard.KEY_LEFT)
            update(false, true);
    }

    @Override
    public void render()
    {
        super.render();
        String s = entry.getDisplayName() + " [" + (index + 1) + "/" + entry.getPages().size() + "]";
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, 8, Color.WHITE);
        GuiDrawableRegistry.splitter.draw((getWidth() - GuiDrawableRegistry.splitter.getWidth()) / 2, DrawingTools.getFontHeight() + 8 + 2);
    }

    public void update(boolean next, boolean playAnim)
    {
        if(sensitive)
        {
            int ind = index + (next ? 1 : -1);
            if(ind >= 0 && ind < entry.getPages().size())
            {
                this.index = ind;
                if(playAnim)
                {
                    sensitive = false;
                    newRend = entry.getPages().get(index).getRenderer();
                    controls.add(newRend);
                    new Thread(() ->
                    {
                        newRend.setX(next ? newRend.getX() + newRend.getWidth() : newRend.getX() - newRend.getWidth());
                        for (int i = 0; i <= newRend.getWidth(); i++)
                        {
                            try
                            {
                                Thread.sleep(1);
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                            newRend.setX(newRend.getX() + (next ? -1 : 1));
                            if (rend != null)
                                rend.setX(rend.getX() + (next ? -1 : 1));
                        }
                        if (rend != null)
                            controls.remove(rend);
                        rend = newRend;
                        sensitive = true;
                    }).start();
                }
                else
                {
                    controls.remove(rend);
                    rend = entry.getPages().get(index).getRenderer();
                    controls.add(rend);
                }
            }
        }
    }
}
