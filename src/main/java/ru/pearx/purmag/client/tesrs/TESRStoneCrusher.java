package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import ru.pearx.lib.Color;
import ru.pearx.lib.Colors;
import ru.pearx.lib.math.MathUtils;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.ModelSupplied;
import ru.pearx.libmc.client.TESRMultiblock;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.libmc.client.models.PXLModelRenderer;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.tiles.TileStoneCrusher;

/*
 * Created by mrAppleXZ on 12.12.17 19:22.
 */
@SideOnly(Side.CLIENT)
public class TESRStoneCrusher extends TESRMultiblock<TileStoneCrusher>
{
    public static final ModelSupplied MDL_MAIN = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/main"), "normal"));
    public static final ModelSupplied MDL_LEVER = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/lever"), "normal"));
    public static final ModelSupplied MDL_HANDLE = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/handle"), "normal"));
    public static final ModelSupplied MDL_COIL = new ModelSupplied(new ModelResourceLocation(Utils.gRL("stone_crusher/coil"), "normal"));
    public static final ModelSupplied MDL_ANVIL = new ModelSupplied(new ModelResourceLocation(new ResourceLocation("minecraft", "anvil"), "damage=0,facing=north"));

    @Override
    public void render(TileStoneCrusher te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, BufferBuilder buffer, Tessellator tes)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0, 0.5f);
        GlStateManager.rotate(PXLMC.getHorizontalRotation(te.getRotation()), 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);

        //main part
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_MAIN.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();

        //lever
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.23964f, -0.034445f, -0.211711f);
        GlStateManager.rotate(-29, 0, 0, 1);
        GlStateManager.translate(0.23964f, 0.034445f, 0.211711f);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_LEVER.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tes.draw();
        GlStateManager.popMatrix();

        long timeDelta = te.getWorld().getTotalWorldTime() - te.getPreviousSpin();
        float deltaCooldown = (float) timeDelta / te.getCooldownBetweenSpins();
        {
            float rot = te.getSpins() * 90 + (timeDelta > te.getCooldownBetweenSpins() ? 0 : -90 + 90 * deltaCooldown);

            //handle
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.5, 0.5, 0);
            GlStateManager.rotate(rot, 0, 0, 1);
            GlStateManager.translate(-0.5, -0.5, 0);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_HANDLE.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
            tes.draw();
            GlStateManager.popMatrix();

            //coil
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.5, 0.5, -0.5);
            GlStateManager.rotate(rot, 0, 0, 1);
            GlStateManager.translate(-0.5, -0.5, 0.5);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            PXLModelRenderer.renderModelTESR(te.getWorld(), MDL_COIL.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
            tes.draw();
            GlStateManager.popMatrix();
        }

        {
            float os = 1f / te.getMaxSpins();
            boolean onGround = te.getSpins() <= 0;

            float anvilX = 1;
            float anvilY = te.getSpins() * os + (timeDelta > te.getCooldownBetweenSpins() ? 0 : -os + os * deltaCooldown);

            if (!onGround)
            {
                float sin = MathHelper.sin(MathUtils.toRadians((System.currentTimeMillis() / 8) % 360));
                anvilX += sin * 0.2f * (1 - anvilY);
                anvilY += Math.abs(sin) * 0.05f * (1 - anvilY);
            }

            //anvil
            GlStateManager.pushMatrix();
            GlStateManager.translate(anvilX, anvilY, 0);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            setTrans(buffer, te);
            Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(te.getWorld(), MDL_ANVIL.get(), te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
            buffer.setTranslation(0, 0, 0);
            tes.draw();
            GlStateManager.popMatrix();

            {
                //rope
                float width = 0.05f;
                Color c = Colors.BROWN_800;
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();
                float x0 = 1.5f + width / 2, x1 = 1.5f - width / 2, x2 = anvilX + 0.5f - width / 2, x3 = anvilX + 0.5f + width / 2;
                float y0 = 2.74f, y1 = anvilY + 1f;
                float z0 = 0.5f - width / 2, z1 = 0.5f + width / 2;
                GlStateManager.disableTexture2D();
                buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
                buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
                buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
                buffer.pos(x2, y1, z0).color(r, g, b, a).endVertex();
                buffer.pos(x3, y1, z0).color(r, g, b, a).endVertex();

                buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x2, y1, z1).color(r, g, b, a).endVertex();
                buffer.pos(x3, y1, z1).color(r, g, b, a).endVertex();

                buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
                buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x2, y1, z1).color(r, g, b, a).endVertex();
                buffer.pos(x2, y1, z0).color(r, g, b, a).endVertex();

                buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
                buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x3, y1, z1).color(r, g, b, a).endVertex();
                buffer.pos(x3, y1, z0).color(r, g, b, a).endVertex();

                buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
                buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
                buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
                tes.draw();
                GlStateManager.enableTexture2D();
            }
        }

        //item
        {
            ItemStack stack = te.handler.getStackInSlot(0);
            if(!stack.isEmpty())
            {
                GlStateManager.pushMatrix();
                GlStateManager.translate(1.5f, 0.13f, 0.5f);
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                if (!(stack.getItem() instanceof ItemBlock))
                {
                    GlStateManager.translate(0, -0.22f, 0);
                    GlStateManager.rotate(90, 1, 0, 0);
                }
                Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
                GlStateManager.popMatrix();
            }
        }

        GlStateManager.popMatrix();
    }
}
