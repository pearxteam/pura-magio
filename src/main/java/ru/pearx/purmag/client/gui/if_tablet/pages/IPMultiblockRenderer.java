package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.client.gui.controls.PMMultiblockShowcase;
import ru.pearx.purmag.client.infofield.pages.IfPageBlocks;
import ru.pearx.purmag.client.infofield.pages.IfPageMultiblock;

/*
 * Created by mrAppleXZ on 01.01.18 21:57.
 */
@SideOnly(Side.CLIENT)
public class IPMultiblockRenderer extends IPAbstractBlocksRenderer<IfPageMultiblock>
{
    public IPMultiblockRenderer(IfPageMultiblock page)
    {
        super(page, new PMMultiblockShowcase(PurMagClient.BUTTON_TEXTURE, page.getMultiblock()));
    }
}