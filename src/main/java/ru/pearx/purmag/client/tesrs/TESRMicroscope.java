package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.tiles.TileMicroscope;

/*
 * Created by mrAppleXZ on 18.08.17 16:16.
 */
public class TESRMicroscope extends PXLFastTESR<TileMicroscope>
{
    @Override
    public void renderPre(TileMicroscope te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        ItemStack stack = te.handler.getStackInSlot(0);
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.25f, 0.25f, 0.25f);
            switch (getWorld().getBlockState(te.getPos()).getValue(HorizontalFacingController.FACING_H))
            {
                case NORTH:
                    GlStateManager.translate(1.99f, 1.27f, 2.12f);
                    break;
                case SOUTH:
                    GlStateManager.translate(1.99f, 1.27f, 1.82f);
                    break;
                case EAST:
                    GlStateManager.translate(1.87f, 1.27f, 2.02f);
                    break;
                case WEST:
                    GlStateManager.translate(2.12f, 1.27f, 2.02f);
                    break;
            }
            if (!(stack.getItem() instanceof ItemBlock))
            {
                GlStateManager.translate(0, -0.22f, 0);
                GlStateManager.rotate(90, 1, 0, 0);
            }
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void renderPost(TileMicroscope te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {

    }
}
