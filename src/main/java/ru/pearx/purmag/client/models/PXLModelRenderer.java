package ru.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/*
 * Created by mrAppleXZ on 17.12.17 16:11.
 */
@SideOnly(Side.CLIENT)
public class PXLModelRenderer
{
    public static boolean renderModelTESR(IBlockAccess worldIn, IBakedModel modelIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, boolean checkSides, long rand)
    {
        boolean flag = false;
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            List<BakedQuad> list = modelIn.getQuads(stateIn, enumfacing, rand);

            if (!list.isEmpty() && (!checkSides || stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing)))
            {
                renderQuadsFlat(worldIn, stateIn, posIn, buffer, list);
                flag = true;
            }
        }

        List<BakedQuad> list1 = modelIn.getQuads(stateIn, null, rand);

        if (!list1.isEmpty())
        {
            renderQuadsFlat(worldIn, stateIn, posIn, buffer, list1);
            flag = true;
        }

        return flag;
    }

    private static void renderQuadsFlat(IBlockAccess blockAccessIn, IBlockState stateIn, BlockPos posIn, BufferBuilder buffer, List<BakedQuad> list)
    {
        Vec3d vec3d = stateIn.getOffset(blockAccessIn, posIn);
        double d0 = (double)posIn.getX() + vec3d.x;
        double d1 = (double)posIn.getY() + vec3d.y;
        double d2 = (double)posIn.getZ() + vec3d.z;
        int i = 0;

        for (int j = list.size(); i < j; ++i)
        {
            BakedQuad bakedquad = list.get(i);

            int brightness = stateIn.getPackedLightmapCoords(blockAccessIn, posIn);

            buffer.addVertexData(bakedquad.getVertexData());
            buffer.putBrightness4(brightness, brightness, brightness, brightness);

            if (bakedquad.hasTintIndex())
            {
                int k = Minecraft.getMinecraft().getBlockColors().colorMultiplier(stateIn, blockAccessIn, posIn, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    k = TextureUtil.anaglyphColor(k);
                }

                float f = (float)(k >> 16 & 255) / 255.0F;
                float f1 = (float)(k >> 8 & 255) / 255.0F;
                float f2 = (float)(k & 255) / 255.0F;
                buffer.putColorMultiplier(f, f1, f2, 4);
                buffer.putColorMultiplier(f, f1, f2, 3);
                buffer.putColorMultiplier(f, f1, f2, 2);
                buffer.putColorMultiplier(f, f1, f2, 1);
            }

            buffer.putPosition(d0, d1, d2);
        }
    }
}
