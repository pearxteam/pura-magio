package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.SoundRegistry;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.infofield.IFChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 21.04.17 11:54.
 */
public class GuiIfTabletSelector extends Control
{
    public List<IFChannel> channels = new ArrayList<>();
    TexturePart tab;
    int selected = 0;

    public GuiIfTabletSelector(TexturePart tab, EntityPlayer p, int tier)
    {
        this.tab = tab;

        setWidth(tab.width + 4);
        setHeight(tab.height * 7);
        for(IFChannel chan : PurMag.instance.if_registry.channels)
        {
            if(chan.isAvailable(p, tier))
                channels.add(chan);
        }
    }

    public void setSelected(int sel)
    {
        if(sel >= 0 && sel < channels.size())
        {
            Minecraft.getMinecraft().player.playSound(SoundRegistry.IfChannelChange, 1, 1);
            selected = sel;
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
                tab.draw(xoff, offset);
                IGuiDrawable draw = channels.get(i).getIcon();
                draw.draw(xoff + ((tab.width - draw.getWidth()) / 2), offset + ((tab.height - draw.getHeight()) / 2));
            }
            offset += tab.height;
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
}
