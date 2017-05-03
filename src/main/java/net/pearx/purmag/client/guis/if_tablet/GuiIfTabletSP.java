package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.common.Button;
import net.pearx.purmag.common.infofield.IfEntry;

import java.awt.*;

/**
 * Created by mrAppleXZ on 02.05.17 10:32.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSP extends GuiIfTabletS
{
    public IfEntry entry;
    public int index;

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
        Button btnBack = new Button(I18n.translateToLocal("button.back.name"), this::goBack);
        //borders (8 * 2), margins (4 * 2), btns (16 * 2)
        int backWidth = getWidth() - 16 - 8 - 32;
        int y = getHeight() - 16 - 8;
        btnBack.setWidth(backWidth);
        btnBack.setHeight(16);
        btnBack.setX(8);
        btnBack.setY(y);
        controls.add(btnBack);

        Button btnPrev = new Button(I18n.translateToLocal("button.prev.name"), () -> {});
        btnPrev.setWidth(16);
        btnPrev.setHeight(16);
        btnPrev.setX(backWidth + 8 + 4);
        btnPrev.setY(y);
        controls.add(btnPrev);

        Button btnNext = new Button(I18n.translateToLocal("button.next.name"), () -> {});
        btnNext.setWidth(16);
        btnNext.setHeight(16);
        btnNext.setX(backWidth + 8 + 8 + 16);
        btnNext.setY(y);
        controls.add(btnNext);
    }

    @Override
    public void render()
    {
        String s = entry.getDisplayName() + " [" + (index + 1) + "/" + entry.getPages().size() + "]";
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, DrawingTools.getFontHeight(), Color.WHITE);
    }
}
