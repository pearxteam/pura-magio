package ru.pearx.purmag.client.gui.translation_desk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.carbide.Colors;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.gui.controls.GuiOnScreen;
import ru.pearx.carbide.mc.client.gui.controls.common.Button;
import ru.pearx.carbide.mc.client.gui.controls.common.ProgressBar;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.steps.IRSTranslatePapyrus;
import ru.pearx.purmag.common.tiles.TileTranslationDesk;


/**
 * Created by mrAppleXZ on 07.06.17 20:12.
 */
@SideOnly(Side.CLIENT)
public class GuiTranslationDesk extends GuiOnScreen
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/gui/translation_desk/translation_desk.png");
    public int texW = 384, texH = 240;
    public BlockPos pos;
    public World world;
    public Status status;
    public String entryName;
    public ItemStack stack;
    public BtnStart btnStart;
    public GuiTranslationDeskPanel panel;
    public BarRate barRate;

    public GuiTranslationDesk(BlockPos pos, World world)
    {
        this.pos = pos;
        this.world = world;
        setWidth(384);
        setHeight(240);
        btnStart = new BtnStart(Utils.gRL("textures/gui/button_wooden.png"), null, () ->
        {
            if (panel.translating)
                panel.stop();
            else
                panel.start();
        });
        btnStart.setWidth(128);
        btnStart.setHeight(24);
        btnStart.setX(8);
        btnStart.setY(getHeight() - 24 - 8);

        panel = new GuiTranslationDeskPanel();

        barRate = new BarRate();
        barRate.setColor(Colors.GREEN_500);
        barRate.setTextColor(Colors.WHITE);
        barRate.setText(net.minecraft.util.text.translation.I18n.translateToLocal("translation_desk.rate"));
        barRate.setPos(15, 15 + (DrawingTools.getFontHeight() * 2));
        barRate.setSize(128, 16);
    }

    @Override
    public void render()
    {
        GlStateManager.enableBlend();
        DrawingTools.drawTexture(TEXTURE, 0, 0, getWidth(), getHeight());
        GlStateManager.disableBlend();
        DrawingTools.drawString(I18n.format("translation_desk.status", I18n.format("translation_desk.status." + status.toString())), 15, 15, Colors.WHITE);
        if (panel.translating)
        {
            DrawingTools.drawString(I18n.format("translation_desk.remains", panel.entries.size()), 15, 15 + DrawingTools.getFontHeight(), Colors.WHITE);
        }
    }

    @Override
    public void init()
    {
        updateStatus();
        getControls().add(btnStart);
        getControls().add(panel);
        getControls().add(barRate);
    }

    public void updateStatus()
    {
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileTranslationDesk)
        {
            TileTranslationDesk ttd = (TileTranslationDesk) te;
            ItemStack stack = ttd.getHandler().getStackInSlot(0);
            if (panel.translating)
            {
                status = Status.TRANSLATING;
                return;
            }
            entryName = null;
            if (!stack.isEmpty())
            {
                for (Pair<IfEntry, IRSTranslatePapyrus> p : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSTranslatePapyrus.class, Minecraft.getMinecraft().player))
                {
                    if (p.getRight().isSuitable(stack))
                    {
                        this.entryName = p.getLeft().getId();
                        this.stack = stack;
                        status = Status.CAN_TRANSLATE;
                        return;
                    }
                }
                status = Status.CANT_TRANSLATE;
                return;
            }
        }
        status = Status.NO_PAPYRUS;
    }

    public enum Status
    {
        NO_PAPYRUS,
        CANT_TRANSLATE,
        CAN_TRANSLATE,
        TRANSLATING;


        @Override
        public String toString()
        {
            return super.toString().toLowerCase();
        }
    }

    public class BtnStart extends Button
    {
        public BtnStart(ResourceLocation textures, String str, Runnable run)
        {
            super(textures, str, run);
        }

        @Override
        public String getText()
        {
            return panel.translating ? I18n.format("translation_desk.button.stop") : I18n.format("translation_desk.button.start");
        }
    }

    public class BarRate extends ProgressBar
    {
        @Override
        public boolean isVisible()
        {
            return panel.translating;
        }

        @Override
        public int getValue()
        {
            return panel.rate;
        }

        @Override
        public int getMaxValue()
        {
            return panel.totalEntries;
        }
    }
}
