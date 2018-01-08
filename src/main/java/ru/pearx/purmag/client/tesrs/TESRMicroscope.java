package ru.pearx.purmag.client.tesrs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.tiles.TileMicroscope;

/*
 * Created by mrAppleXZ on 18.08.17 16:16.
 */
public class TESRMicroscope extends PXLFastTESR<TileMicroscope>
{
    @Override
    public void render(TileMicroscope te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, BufferBuilder buffer, Tessellator tes)
    {
        IBlockState state = getWorld().getBlockState(te.getPos());
        if (state.getBlock() != BlockRegistry.microscope)
            return;
        ItemStack stack = te.handler.getStackInSlot(0);

        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();
            resetTrans(te);
            GlStateManager.translate(0.5f, 0, 0.5f);
            GlStateManager.rotate(PXLMC.getHorizontalRotation(state.getValue(HorizontalFacingController.FACING_H)), 0, 1, 0);
            GlStateManager.rotate(180, 0, 1, 0);
            GlStateManager.translate(-0.5f, 0, -0.5f);

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.25f, 0.25f, 0.25f);
            GlStateManager.translate(1.99f, 1.27f, 1.8f);
            if (!(stack.getItem() instanceof ItemBlock))
            {
                GlStateManager.translate(0, -0.22f, 0);
                GlStateManager.rotate(90, 1, 0, 0);
            }
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
            GlStateManager.popMatrix();
        }
    }
}
