package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.purmag.client.GuiDrawableRegistry;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;
import ru.pearx.purmag.common.infofield.IfEntry;


/**
 * Created by mrAppleXZ on 02.05.17 10:32.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSP extends GuiIfTabletS
{
    public IfEntry entry;
    public int index;

    private IPRenderer rend;
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
        Button btnBack = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.if_tablet.back"), this::goBack);
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
    public void mouseWheel(int delta)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
        {
            update(delta > 0, true);
        }
    }

    @Override
    public void keyUp(int keycode)
    {
        super.keyUp(keycode);
        if (keycode == Keyboard.KEY_RIGHT || keycode == Keyboard.KEY_D)
            update(true, true);
        if (keycode == Keyboard.KEY_LEFT || keycode == Keyboard.KEY_A)
            update(false, true);
    }


    @Override
    public void render()
    {
        super.render();
        String s = entry.getDisplayName() + " [" + (index + 1) + "/" + entry.getPages().size() + "]";
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, 8, Colors.WHITE);
        GuiDrawableRegistry.splitter.draw(getGuiScreen(), (getWidth() - GuiDrawableRegistry.splitter.getWidth()) / 2, DrawingTools.getFontHeight() + 8 + 2);
    }

    public void update(boolean next, boolean playAnim)
    {
        if (sensitive)
        {
            int ind = index + (next ? 1 : -1);
            if (ind >= 0 && ind < entry.getPages().size())
            {
                this.index = ind;
                if (playAnim)
                {
                    sensitive = false;
                    IPRenderer newRend = entry.getPages().get(index).getRenderer();
                    controls.add(newRend);
                    //newRend.setX(next ? newRend.getX() + newRend.getWidth() : newRend.getX() - newRend.getWidth());
                //    long startTime = System.currentTimeMillis();
                //    new Thread(() ->
               //     {
               //         int nX = newRend.getX();
               //         int x = rend.getX();
                //        int pos = 0;
               //         while(pos < newRend.getWidth())
               //         {
              //              int t = (int)(System.currentTimeMillis() - startTime);
              //              pos = t > newRend.getWidth() ? newRend.getWidth() : t;
              //              newRend.setX(nX + (next ? -pos : pos));
                 //           if (rend != null)
               //                 rend.setX(x + (next ? -pos : pos));
                //        }
                        //if (rend != null)
                            controls.remove(rend);
                        rend = newRend;
                        sensitive = true;
                    //}).start();
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
