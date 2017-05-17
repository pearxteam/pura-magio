package net.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 10.04.17 8:55.
 */
@SideOnly(Side.CLIENT)
public class OvModelBase implements IModelBase
{
    private ResourceLocation baseModel;

    public void setBaseModel(ResourceLocation loc)
    {
        baseModel = loc;
    }

    public ResourceLocation getBaseModel()
    {
        return baseModel;
    }

    private IBakedModel baked;

    protected IBakedModel getBaked()
    {
        return baked;
    }

    @Override
    public void bake()
    {
        IModel mdl = null;
        try
        {
            mdl = ModelLoaderRegistry.getModel(getBaseModel());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        baked = mdl.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
                location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        List<BakedQuad> l = new ArrayList<>();
        for(BakedQuad q : getBaked().getQuads(state, side, rand))
        {
            l.add(new BakedQuad(q.getVertexData(), 1, q.getFace(), q.getSprite()));
        }
        return l;
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return getBaked().isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return getBaked().isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return getBaked().isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return getBaked().getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return getBaked().getItemCameraTransforms();
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return getBaked().getOverrides();
    }
}
