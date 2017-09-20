package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.MinecraftForgeClient;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 20.09.17 20:20.
 */
public class TESRCodeStorage extends PXLFastTESR<TileCodeStorage>
{
    public static final ModelResourceLocation MDL_BODY = new ModelResourceLocation(Utils.getResourceLocation("code_storage/body"), "normal");
    public static final ModelResourceLocation MDL_TOP = new ModelResourceLocation(Utils.getResourceLocation("code_storage/top"), "normal");

    @Override
    public void renderPre(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.translate(0.5f, 0, 0.5f);
        EnumFacing face = te.getWorld().getBlockState(te.getPos()).getValue(HorizontalFacingController.FACING_H);
        int angle = -10;
        switch (face)
        {
            case NORTH:
                angle = 0;
                break;
            case WEST:
                angle = 90;
                break;
            case SOUTH:
                angle = 180;
                break;
            case EAST:
                angle = 270;
                break;
        }
        GlStateManager.rotate(angle, 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);
    }

    @Override
    public void renderTileEntityFast(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer)
    {
        BlockRendererDispatcher dis = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel body = dis.getBlockModelShapes().getModelManager().getModel(MDL_BODY);
        IBakedModel top = dis.getBlockModelShapes().getModelManager().getModel(MDL_TOP);

        dis.getBlockModelRenderer().renderModel(te.getWorld(), body, te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
    }
}
