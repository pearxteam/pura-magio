package net.pearx.purmag.client.blockstates;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.common.blocks.BlockSingleSip;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 14.05.17 18:54.
 */
@SideOnly(Side.CLIENT)
public class ISMSingleSip implements IStateMapper
{
    public ModelResourceLocation loc;

    public ISMSingleSip(ModelResourceLocation loc)
    {
        this.loc = loc;
    }

    public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block block)
    {
        HashMap<IBlockState, ModelResourceLocation> map = new HashMap<>();
        for(String s : PurMag.instance.sip.allowedValues)
        {
            map.put(block.getDefaultState().withProperty(BlockSingleSip.SIPTYPE, s), loc);
        }
        return map;
    }
}
