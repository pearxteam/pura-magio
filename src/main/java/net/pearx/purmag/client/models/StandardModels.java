package net.pearx.purmag.client.models;

import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.processors.FacingProcessor;
import net.pearx.purmag.client.models.processors.IQuadProcessor;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.BlockWallIfTablet;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModelBase
    {
        public Crystal()
        {
            super(true);
            setBaseModel(new ResourceLocation(PurMag.MODID, "block/crystal.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), new Quat4f(0, -0.20944f, 0, 1)).getMatrix());
        }
    }

    public static class CrystalGlass extends ConnectedModel
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.MODID, "blocks/crystal_glass"));
        }
    }

    public static class Glove extends OvModelBase
    {
        public Glove()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("item/glove.obj"));
        }


        float xr, yr, zr;

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0f, 0.2f, -0.1f), new Quat4f(0, 0, 45, 1), null, new Quat4f(0, 0, 180, 1)).getMatrix());
            }
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0.15f, 0.15f, 0), null, new Vector3f(1.2f, 1.2f, 1.2f), new Quat4f(-1.64061f, -4.50295f, 0, 1)).getMatrix());
            }
            return Pair.of(this, new TRSRTransformation(null, null, null, null).getMatrix());
        }
    }

    public static class TranslationDesk extends OvModelBase
    {
        public TranslationDesk()
        {
            super(true);
            processors.add(new FacingProcessor());
            setBaseModel(Utils.getRegistryName("block/translation_desk.obj"));
        }


        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, new TRSRTransformation(null, null, new Vector3f(0.6f, 0.6f, 0.6f), ModelUtils.getRotation(30, 225, 90)).getMatrix());
            return Pair.of(this, new TRSRTransformation(null, null, new Vector3f(0.375f, 0.375f, 0.375f), null).getMatrix());
        }
    }

    public static class CrystalSmall extends OvModelBase
    {
        public CrystalSmall()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("block/crystal_small.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, new Vector3f(1.5f, 1.5f, 1.5f), new Quat4f(0.349066f, 0.20944f, 0, 1)).getMatrix());
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, null, null).getMatrix());
        }
    }

    public static class WallIfTablet extends OvModelBase
    {
        private WeakReference<ItemStack> stack;

        public WallIfTablet()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("block/wall_if_tablet.obj"));
            processors.add(new FacingProcessor());
            processors.add((quads, state, side, rand) ->
            {
                int tier = 0;
                if(state != null && state instanceof IExtendedBlockState)
                    tier = ((IExtendedBlockState)state).getValue(BlockWallIfTablet.IF_TIER);
                else if(stack != null && stack.get() != null)
                    tier = stack.get().getMetadata();

                for(int q = 0; q < quads.size(); q++)
                {
                    BakedQuad quad = quads.get(q);
                    quads.set(q, new BakedQuadWNT(quads.get(q), Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("purmag:models/wall_if_tablet." + tier)));
                }
            });
        }

        @Override
        public ItemOverrideList getOverrides()
        {
            return new ItemOverrideList(Collections.emptyList())
            {
                @Override
                public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity)
                {
                    WallIfTablet.this.stack = new WeakReference<>(stack);
                    return originalModel;
                }
            };
        }
    }
}
