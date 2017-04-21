package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.pearx.purmag.PurMag;
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

    @Override
    public void render()
    {
    }
}
