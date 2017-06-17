package net.pearx.purmag.client.guis.translation_desk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.GuiOnScreen;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.controls.common.Button;
import net.pearx.purmag.client.guis.controls.common.ProgressBar;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.IfRegistry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.playerdata.IfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSTranslatePapyrus;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.papyrus.ItemPapyrus;
import net.pearx.purmag.common.tiles.TileTranslationDesk;

import java.awt.*;


/**
 * Created by mrAppleXZ on 07.06.17 20:12.
 */
@SideOnly(Side.CLIENT)
public class GuiTranslationDesk extends GuiOnScreen
{
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

    public int texW, texH;
    public ResourceLocation textures;
    public TexturePart bg;
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
        texW = 384;
        texH = 240;
        setWidth(384);
        setHeight(240);

        textures = Utils.getRegistryName("textures/gui/translation_desk/translation_desk.png");
        bg = new TexturePart(textures, 0, 0, getWidth(), getHeight(), texW, texH);
        btnStart = new BtnStart(Utils.getRegistryName("textures/gui/button_wooden.png"), null, () ->
        {
            if(panel.translating)
                panel.stop();
            else
                panel.start();
        });
        btnStart.setWidth(128);
        btnStart.setHeight(24);
        btnStart.setX(3);
        btnStart.setY(getHeight() - 24 - 5);

        panel = new GuiTranslationDeskPanel();

        barRate = new BarRate();
        barRate.setColor(Color.GREEN);
        barRate.setTextColor(Color.WHITE);
        barRate.setText(net.minecraft.util.text.translation.I18n.translateToLocal("translation_desk.rate"));
        barRate.setPos(3, 5 + (DrawingTools.getFontHeight() * 2));
        barRate.setSize(128, 16);
    }

    @Override
    public void render()
    {
        GlStateManager.enableBlend();
        bg.draw(0, 0);
        GlStateManager.disableBlend();
        DrawingTools.drawString(I18n.format("translation_desk.status", I18n.format("translation_desk.status." + status.toString())), 3, 5, Color.WHITE);
        if(panel.translating)
        {
            DrawingTools.drawString(I18n.format("translation_desk.remains", panel.entries.size()), 3, 5 + DrawingTools.getFontHeight(), Color.WHITE);
        }
    }

    @Override
    public void init()
    {
        updateStatus();
        controls.add(btnStart);
        controls.add(panel);
        controls.add(barRate);
    }

    public void updateStatus()
    {
        TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileTranslationDesk)
        {
            TileTranslationDesk ttd = (TileTranslationDesk) te;
            ItemStack stack = ttd.handler.getStackInSlot(0);
            if(panel.translating)
            {
                status = Status.TRANSLATING;
                return;
            }
            entryName = null;
            if(!stack.isEmpty())
            {
                IIfEntryStore store = Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
                for(IfEntry entr : PurMag.instance.if_registry.entries)
                {
                    int steps = store.getSteps(entr.getId());
                    if(steps < entr.getSteps().size())
                    {
                        IIfResearchStep step = entr.getSteps().get(steps);
                        if(step instanceof IRSTranslatePapyrus)
                        {
                            if(((IRSTranslatePapyrus) step).isSuitable(stack))
                            {
                                this.entryName = entr.getId();
                                this.stack = stack;
                                status = Status.CAN_TRANSLATE;
                                return;
                            }
                        }
                    }
                }
                status = Status.CANT_TRANSLATE;
                return;
            }
        }
        status = Status.NO_PAPYRUS;
    }
}
