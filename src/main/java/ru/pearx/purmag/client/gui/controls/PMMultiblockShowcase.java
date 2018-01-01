package ru.pearx.purmag.client.gui.controls;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.purmag.common.blocks.multiblock.PMMultiblock;
import ru.pearx.purmag.common.items.ItemTinkeringKit;

/*
 * Created by mrAppleXZ on 01.01.18 21:42.
 */
@SideOnly(Side.CLIENT)
public class PMMultiblockShowcase extends BlockArrayShowcase
{
    private PMMultiblock multiblock;

    public PMMultiblockShowcase(ResourceLocation buttonTex, PMMultiblock multiblock)
    {
        super(buttonTex, multiblock.getStructure());
        this.multiblock = multiblock;
    }

    @Override
    public void render()
    {
        super.render();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 500);
        String s = I18n.format("misc.gui.multiblock_showcase.requiredTier", ItemTinkeringKit.getTierDisplayName(multiblock.getTier()));
        DrawingTools.drawString(s, getWidth() - DrawingTools.measureString(s), getHeight() - DrawingTools.getStringHeight(s), Colors.WHITE);
        GlStateManager.popMatrix();
    }
}
