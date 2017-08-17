package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.GuiOnScreen;
import ru.pearx.libmc.client.gui.TexturePart;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.infofield.IfTier;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTablet extends GuiOnScreen
{
    public TexturePart texBg;
    public TexturePart texFrame;

    public int tier;
    public IfTier.TabletData data;

    public GuiIfTabletSE se = new GuiIfTabletSE();
    public GuiIfTabletS if_screen;

    public GuiIfTablet(int tier)
    {
        this.tier = tier;
        setWidth(384);
        setHeight(256);
    }

    @Override
    public void init()
    {
        data = PurMag.INSTANCE.if_registry.getTier(tier).getTabletData();
        texBg = new TexturePart(data.getTexture(), 0, 0, getWidth(), getHeight(), 512, 512);
        texFrame = new TexturePart(data.getTexture(), 0, getHeight(), getWidth(), getHeight(), 512, 512);

        changeScreen(se);
    }

    @Override
    public void render()
    {
        float sin = data.isShouldGlow() ? MathHelper.sin((float) Math.toRadians(System.currentTimeMillis() / 10 % 360)) * 0.15f : 0.15f;
        GlStateManager.color(0.85f + sin, 0.85f + sin, 0.85f + sin);
        texBg.draw(0, 0);
        GlStateManager.color(1, 1, 1);
    }

    @Override
    public void postRender()
    {
        GlStateManager.enableBlend();
        texFrame.draw(0, 0);
        GlStateManager.disableBlend();
    }

    public void changeScreen(GuiIfTabletS screen)
    {
        controls.remove(if_screen);
        if_screen = screen;
        controls.add(screen);
    }
}
