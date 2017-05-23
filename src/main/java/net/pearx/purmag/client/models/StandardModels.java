package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModelBase implements IPerspectiveAwareModel
    {
        public Crystal()
        {
            setBaseModel(new ResourceLocation(PurMag.ModId, "block/crystal.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), null).getMatrix());
        }
    }

    public static class CrystalGlass extends CTMModelBase
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.ModId, "blocks/crystal_glass"));
        }
    }

    public static class Glove extends OvModelBase implements IPerspectiveAwareModel
    {
        public Glove() { setBaseModel(Utils.getRegistryName("item/glove.obj")); }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0f, 0.2f, -0.1f), new Quat4f(0, 0, 45, 1), null, new Quat4f(0, 0, 180, 1)).getMatrix());
            }
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, new TRSRTransformation(null, new Quat4f(180, 180, 180, 1), null, null).getMatrix());
            }
            return Pair.of(this, new TRSRTransformation(null, null, null, null).getMatrix());
        }
    }
}
