package ru.pearx.purmag.client.gui.controls;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.Colors;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.purmag.common.blocks.multiblock.PMMultiblock;
import ru.pearx.purmag.common.items.tinkering_kit.ItemTinkeringKit;

/*
 * Created by mrAppleXZ on 01.01.18 21:42.
 */
@SideOnly(Side.CLIENT)
public class PMMultiblockShowcase extends BlockArrayShowcase
{
    private PMMultiblock multiblock;

    public PMMultiblockShowcase(ResourceLocation buttonTex, PMMultiblock multiblock)
    {
        super(buttonTex, multiblock.getStructure(), true);
        this.multiblock = multiblock;
    }

    @Override
    public void render()
    {
        super.render();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 0);
        String s = I18n.format("misc.gui.multiblock_showcase.requiredTier", ItemTinkeringKit.getTierDisplayName(multiblock.getTier()));
        DrawingTools.drawString(s, getWidth() - DrawingTools.measureString(s), getHeight() - DrawingTools.getStringHeight(s), Colors.WHITE);
        GlStateManager.popMatrix();
    }
}
