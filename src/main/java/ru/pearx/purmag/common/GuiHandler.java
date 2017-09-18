package ru.pearx.purmag.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.code_storage.GuiContainerCodeStorage;
import ru.pearx.purmag.common.blocks.BlockCodeStorage;
import ru.pearx.purmag.common.inventory.ContainerCodeStorage;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 17.09.17 15:32.
 */
public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (id)
        {
            case BlockCodeStorage.GUI_ID:
                BlockPos pos = new BlockPos(x, y, z);
                TileEntity te = world.getTileEntity(pos);
                if(te != null && te instanceof TileCodeStorage)
                {
                    return new ContainerCodeStorage((TileCodeStorage) te, player.inventory);
                }
                break;
        }
        return null;
    }

    @Nullable
    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (id)
        {
            case BlockCodeStorage.GUI_ID:
                BlockPos pos = new BlockPos(x, y, z);
                TileEntity te = world.getTileEntity(pos);
                if(te != null && te instanceof TileCodeStorage)
                {
                    return new GuiContainerCodeStorage(new ContainerCodeStorage((TileCodeStorage) te, player.inventory));
                }
                break;
        }
        return null;
    }
}
