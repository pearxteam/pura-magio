package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.infofield.IfChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 21.04.17 11:54.
 */
public class GuiIfTabletSelector extends ControlIfTabletPart
{
    public TexturePart texTab;

    public List<IfChannel> channels = new ArrayList<>();
    int selected = 0;

    @Override
    public void init()
    {
        texTab = new TexturePart(getTablet().textures, getTablet().w, 0, 32, 32, 512, 512);

        setWidth(texTab.width + 4);
        setHeight(texTab.height * 7);
        for(IfChannel chan : PurMag.instance.if_registry.channels)
        {
            if(chan.isAvailable(Minecraft.getMinecraft().player, getTablet().tier))
                channels.add(chan);
        }
    }

    public void setSelected(int sel)
    {
        if(sel >= 0 && sel < channels.size())
        {
            Minecraft.getMinecraft().player.playSound(SoundRegistry.IfChannelChange, 1, 1);
            selected = sel;
            getTablet().entries.reload();
        }
    }

    @Override
    public void postRender()
    {
        int offset = 0;
        for(int i = selected - 3; i <= selected + 3; i++)
        {
            if(i >= 0 && i < channels.size())
            {
                int xoff = i == selected ? 0 : 4;
                texTab.draw(xoff, offset);
                IGuiDrawable draw = channels.get(i).getIcon();
                draw.draw(xoff + ((texTab.width - draw.getWidth()) / 2), offset + ((texTab.height - draw.getHeight()) / 2));
            }
            offset += texTab.height;
        }
    }

    @Override
    public void mouseWheel(int delta)
    {
            if (delta > 0)
                setSelected(selected - 1);
            if (delta < 0)
                setSelected(selected + 1);
    }

    public IfChannel getSelectedChannel()
    {
        return channels.get(selected);
    }
}
