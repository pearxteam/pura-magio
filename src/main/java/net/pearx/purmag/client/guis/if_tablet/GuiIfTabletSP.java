package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.common.Button;
import net.pearx.purmag.client.guis.if_tablet.pages.IPRenderer;
import net.pearx.purmag.common.infofield.IfEntry;
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
        Button btnBack = new Button(I18n.translateToLocal("button.back.name"), this::goBack);
        //borders (8 * 2), margins (4 * 2), btns (16 * 2)
        int backWidth = getWidth() - 16 - 8 - 32;
        int y = getHeight() - 16 - 8;
        btnBack.setWidth(backWidth);
        btnBack.setHeight(16);
        btnBack.setX(8);
        btnBack.setY(y);
        controls.add(btnBack);

        Button btnPrev = new Button(I18n.translateToLocal("button.prev.name"), () -> update(false));
        btnPrev.setWidth(16);
        btnPrev.setHeight(16);
        btnPrev.setX(backWidth + 8 + 4);
        btnPrev.setY(y);
        controls.add(btnPrev);

        Button btnNext = new Button(I18n.translateToLocal("button.next.name"), () -> update(true));
        btnNext.setWidth(16);
        btnNext.setHeight(16);
        btnNext.setX(backWidth + 8 + 8 + 16);
        btnNext.setY(y);
        controls.add(btnNext);

        index = index - 1;
        update(true);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        if(Mouse.isButtonDown(0))
            if(dx > 2 || dx < -2)
            update(dx > 0);
    }

    @Override
    public void render()
    {
        super.render();
        String s = entry.getDisplayName() + " [" + (index + 1) + "/" + entry.getPages().size() + "]";
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, DrawingTools.getFontHeight(), Color.WHITE);
    }

    public void update(boolean next)
    {
        if(sensitive)
        {
            int ind = index + (next ? 1 : -1);
            if(ind >= 0 && ind < entry.getPages().size())
            {
                this.index = ind;
                sensitive = false;
                newRend = entry.getPages().get(index).getRenderer();
                controls.add(newRend);
                new Thread(() ->
                {
                    newRend.setX(next ? newRend.getX() - newRend.getWidth() : newRend.getX() + newRend.getWidth());
                    for (int i = 0; i <= newRend.getWidth(); i++)
                    {
                        try
                        {
                            Thread.sleep(1);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        newRend.setX(newRend.getX() + (next ? 1 : -1));
                        if (rend != null)
                            rend.setX(rend.getX() + (next ? 1 : -1));
                    }
                    if (rend != null)
                        controls.remove(rend);
                    rend = newRend;
                    sensitive = true;
                }).start();
            }
        }
    }
}
