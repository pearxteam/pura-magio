package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import ru.pearx.lib.math.MathUtils;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.ModelSupplied;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.client.models.PXLModelRenderer;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.tiles.TileStoneCrusher;

/*
 * Created by mrAppleXZ on 12.12.17 19:22.
 */
@SideOnly(Side.CLIENT)
public class TESRStoneCrusher extends PXLFastTESR<TileStoneCrusher>
{
    public static final ModelSupplied MDL_MAIN = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/main"), "normal"));
    public static final ModelSupplied MDL_LEVER = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/lever"), "normal"));
    public static final ModelSupplied MDL_HANDLE = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/handle"), "normal"));
    public static final ModelSupplied MDL_COIL = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/coil"), "normal"));
    public static final ModelSupplied MDL_ANVIL = new ModelSupplied(new ModelResourceLocation(new ResourceLocation("minecraft", "anvil"), "damage=0,facing=north"));

    @Override
    public void renderPre(TileStoneCrusher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0, 0.5f);
        GlStateManager.rotate(PXLMC.getHorizontalRotation(te.getRotation()), 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);
    }

    @Override
    public void renderPost(TileStoneCrusher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.popMatrix();
    }

    @Override
    public void render(TileStoneCrusher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, BufferBuilder buffer, Tessellator tes)
    {
        float sin = MathHelper.sin(MathUtils.toRadians(System.currentTimeMillis() / 10 % 360));
        int deg = (int)(System.currentTimeMillis() / 2 % 360);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_MAIN.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();

        GlStateManager.pushMatrix();
        resetTrans(te);
        GlStateManager.translate(-0.23964f, -0.034445f, -0.211711f);
        GlStateManager.rotate(-deg, 0, 0, 1);
        GlStateManager.translate(0.23964f, 0.034445f, 0.211711f);
        setTrans(te);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_LEVER.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        resetTrans(te);
        GlStateManager.translate(0.5, 0.5, 0);
        GlStateManager.rotate(deg, 0, 0, 1);
        GlStateManager.translate(-0.5, -0.5, 0);
        setTrans(te);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_HANDLE.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        resetTrans(te);
        GlStateManager.translate(0.5, 0.5, -0.5);
        GlStateManager.rotate(deg, 0, 0, 1);
        GlStateManager.translate(-0.5, -0.5, 0.5);
        setTrans(te);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_COIL.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(1, 0, 0);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(te.getWorld(), MDL_ANVIL.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();
        GlStateManager.popMatrix();
    }
}
