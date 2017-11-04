package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import ru.pearx.lib.RandomUtils;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.SimpleDrawable;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 17.10.17 19:48.
 */
public abstract class GuiAbstractMicroscope extends GuiOnScreen
{
    protected int margin = 9;
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/gui/microscope.png");
    private static final IGuiDrawable bg = new SimpleDrawable(TEXTURE, 368 * 2, 240 * 2, 368, 240, 0, 0);
    private static final IGuiDrawable frame = new SimpleDrawable(TEXTURE, 368 * 2, 240 * 2, 368, 240, 368, 0);
    private static final IGuiDrawable lcd = new SimpleDrawable(TEXTURE, 368 * 2, 240 * 2, 368, 240, 0, 240);

    public GuiAbstractMicroscope()
    {
        setWidth(368);
        setHeight(240);
    }

    private int yOff;

    @Override
    public void render()
    {
        bg.draw(null, 0, 0);
        renderMainPart();
    }

    @Override
    public void postRender()
    {
        GlStateManager.enableBlend();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 100);
        GlStateManager.pushMatrix();
        if(PurMag.INSTANCE.random.nextInt(5) == 0)
            yOff = RandomUtils.nextInt(-8, 9, PurMag.INSTANCE.random);
        int bit = DrawingTools.drawStencil(getWidth(), getHeight());
        GlStateManager.translate(0, yOff, 0);
        lcd.draw(null, 0, 0);
        GlStateManager.popMatrix();
        frame.draw(null, 0, 0);
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
        DrawingTools.removeStencil(bit);
    }

    public abstract void renderMainPart();
}
