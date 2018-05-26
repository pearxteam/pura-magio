package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.carbide.Colors;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.gui.controls.common.Button;
import ru.pearx.purmag.PurMag;
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
        int margin = 8;
        int w = (getWidth() - margin * 4) / 3;
        int h = 16;
        int y = getHeight() - margin - h;

        Button btnLeft = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.if_tablet.left"), () -> update(false, true));
        btnLeft.setSize(w, h);
        btnLeft.setPos(margin, y);
        getControls().add(btnLeft);

        Button btnBack = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.if_tablet.back"), this::goBack);
        btnBack.setSize(w, h);
        btnBack.setPos(margin + w + margin, y);
        getControls().add(btnBack);

        Button btnRight = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.if_tablet.right"), () -> update(true, true));
        btnRight.setSize(w, h);
        btnRight.setPos(margin + w + margin + w + margin, y);
        getControls().add(btnRight);

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

    private boolean playingAnim;
    private boolean next;
    private long startTime = -1;
    private IPRenderer newRend;
    private int nX;
    private int x;
    private int pos;

    @Override
    public void update()
    {
        if(playingAnim)
        {
            if(startTime == -1)
            {
                //init
                newRend = getRenderer(index);
                getControls().add(newRend);
                newRend.setX(next ? newRend.getX() + newRend.getWidth() : newRend.getX() - newRend.getWidth());
                startTime = System.currentTimeMillis();
                nX = newRend.getX();
                x = rend.getX();
                pos = 0;
            }
            {
                //process
                int t = (int) ((System.currentTimeMillis() - startTime) * 2f);

                pos = t > newRend.getWidth() ? newRend.getWidth() : t;
                newRend.setX(nX + (next ? -pos : pos));
                rend.setX(x + (next ? -pos : pos));
            }
            if(pos >= newRend.getWidth())
            {
                //end
                getControls().remove(rend);
                rend = newRend;
                playingAnim = false;
                startTime = -1;
                newRend = null;
                nX = 0;
                x = 0;
                pos = 0;
            }
        }
    }

    public void update(boolean next, boolean playAnim)
    {
        if (!playingAnim)
        {
            int ind = index + (next ? 1 : -1);
            if (ind >= 0 && ind < entry.getPages().size())
            {
                this.index = ind;
                if (playAnim && PurMag.INSTANCE.config.ifTabletPageAnimationSpeed > 0)
                {
                    this.playingAnim = true;
                    this.next = next;
                }
                else
                {
                    getControls().remove(rend);
                    rend = getRenderer(index);
                    getControls().add(rend);
                }
            }
        }
    }

    private IPRenderer getRenderer(int index)
    {
        IPRenderer rend = entry.getPages().get(index).getRenderer();
        rend.setX(8);
        rend.setY(8 + DrawingTools.getFontHeight() + GuiDrawableRegistry.splitter.getHeight() + 4);
        return rend;
    }
}
