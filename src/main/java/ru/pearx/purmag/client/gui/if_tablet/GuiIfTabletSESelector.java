package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.carbide.mc.client.gui.TexturePart;
import ru.pearx.carbide.mc.client.gui.drawables.IGuiDrawable;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.infofield.IfChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 21.04.17 11:54.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSESelector extends GuiIfTabletSEPart
{
    public TexturePart texTab;

    public List<IfChannel> channels = new ArrayList<>();
    int selected = 0;

    @Override
    public void init()
    {
        texTab = new TexturePart(getTabletScreen().getTablet().data.getTexture(), getTabletScreen().getWidth(), 0, 32, 32, 512, 512);

        setWidth(texTab.width + 4);
        setHeight(texTab.height * 7);
        for (IfChannel chan : PurMag.INSTANCE.getIfRegistry().channels)
        {
            if (chan.isAvailable(Minecraft.getMinecraft().player, getTabletScreen().getTablet().tier))
                channels.add(chan);
        }
    }

    public void setSelected(int sel)
    {
        if (sel >= 0 && sel < channels.size())
        {
            Minecraft.getMinecraft().player.playSound(SoundRegistry.MAGICAL_CLICK, 1, 1);
            selected = sel;
            getTabletScreen().entries.reload();
        }
    }

    @Override
    public void postRender()
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 310);
        GlStateManager.enableBlend();
        int offset = 0;
        for (int i = selected - 3; i <= selected + 3; i++)
        {
            if (i >= 0 && i < channels.size())
            {
                int xoff = i == selected ? 0 : 4;
                texTab.draw(xoff, offset);
                IGuiDrawable draw = channels.get(i).getIcon();
                draw.draw(getGuiScreen(), xoff + ((texTab.width - draw.getWidth()) / 2), offset + ((texTab.height - draw.getHeight()) / 2));
            }
            offset += texTab.height;
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    @Override
    public void mouseWheel(int delta)
    {
        if (delta > 0)
            setSelected(selected - 1);
        if (delta < 0)
            setSelected(selected + 1);
    }

    @Override
    public void keyUp(int keycode)
    {
        if (keycode == Keyboard.KEY_W || keycode == Keyboard.KEY_UP)
            setSelected(selected - 1);
        if (keycode == Keyboard.KEY_S || keycode == Keyboard.KEY_DOWN)
            setSelected(selected + 1);
    }

    public IfChannel getSelectedChannel()
    {
        return channels.get(selected);
    }
}
