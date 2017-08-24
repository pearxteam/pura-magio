package ru.pearx.purmag.client.events;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.models.IPXModel;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.models.StandardModels;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.infofield.IfTier;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class ModelEvents
{
    /*resources todo:
     - microscope texture
     - furnace texture
     - tablet number two
    */
    @SubscribeEvent
    public static void onBake(ModelBakeEvent e)
    {
        putModel(e, PurMag.INSTANCE.config.useSmallerCrystalModel ? new StandardModels.CrystalOptionalSmaller() : new StandardModels.Crystal(), Utils.getRegistryName("crystal"));
        putModel(e, new StandardModels.CrystalGlass(), Utils.getRegistryName("crystal_glass"));
        putModel(e, new StandardModels.Glove(), Utils.getRegistryName("glove"));
        putModel(e, new StandardModels.TranslationDesk(), Utils.getRegistryName("translation_desk"));
        putModel(e, new StandardModels.CrystalSmall(), Utils.getRegistryName("crystal_small"));
        StandardModels.WallIfTablet wit = new StandardModels.WallIfTablet();
        putModel(e, wit, Utils.getRegistryName("wall_if_tablet"));
        putModel(e, wit, Utils.getRegistryName("broken_wall_if_tablet"));
        putModel(e, new StandardModels.Microscope(), Utils.getRegistryName("microscope"));
        putModel(e, new StandardModels.LuminousCrystalGlass(), Utils.getRegistryName("luminous_crystal_glass"));
    }

    private static void putModel(ModelBakeEvent e, IPXModel model, ResourceLocation loc)
    {
        model.bake();
        e.getModelRegistry().putObject(new ModelResourceLocation(loc, "normal"), model);
    }

    @SubscribeEvent
    public static void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "models/crystal"));
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "blocks/crystal_glass/" + i + j + k + l));
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "blocks/luminous_crystal_glass/" + i + j + k + l));
        e.getMap().registerSprite(Utils.getRegistryName("models/glove"));
        e.getMap().registerSprite(Utils.getRegistryName("particle/sip"));
        e.getMap().registerSprite(Utils.getRegistryName("models/crystal_small"));
        e.getMap().registerSprite(Utils.getRegistryName("models/translation_desk"));
        for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
            e.getMap().registerSprite(t.getWallTabletTexture());
        e.getMap().registerSprite(Utils.getRegistryName("models/microscope"));
    }
}
