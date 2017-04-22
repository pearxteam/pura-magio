package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.infofield.IfChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 21.04.17 11:54.
 */
public class GuiIfTabletSelector extends Control
{
    public List<IfChannel> channels = new ArrayList<>();
    GuiIfTablet g;
    int selected = 0;

    public GuiIfTabletSelector(GuiIfTablet g, EntityPlayer p)
    {
        this.g = g;

        setWidth(g.texTab.width + 4);
        setHeight(g.texTab.height * 7);
        for(IfChannel chan : PurMag.instance.if_registry.channels)
        {
            if(chan.isAvailable(p, g.tier))
                channels.add(chan);
        }
    }

    public void setSelected(int sel)
    {
        if(sel >= 0 && sel < channels.size())
        {
            Minecraft.getMinecraft().player.playSound(SoundRegistry.IfChannelChange, 1, 1);
            selected = sel;
            g.reloadResearches();
        }
    }

    @Override
    public void render()
    {
        int offset = 0;
        for(int i = selected - 3; i <= selected + 3; i++)
        {
            if(i >= 0 && i < channels.size())
            {
                int xoff = i == selected ? 0 : 4;
                g.texTab.draw(xoff, offset);
                IGuiDrawable draw = channels.get(i).getIcon();
                draw.draw(xoff + ((g.texTab.width - draw.getWidth()) / 2), offset + ((g.texTab.height - draw.getHeight()) / 2));
            }
            offset += g.texTab.height;
        }
    }

    @Override
    public void mouseWheel(int delta)
    {
        if(isFocused())
        {
            if (delta > 0)
                setSelected(selected - 1);
            if (delta < 0)
                setSelected(selected + 1);
        }
    }

    public IfChannel getSelectedChannel()
    {
        return channels.get(selected);
    }
}
