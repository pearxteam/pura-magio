package ru.pearx.purmag.common.structure;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.TextBox;
import ru.pearx.libmc.client.gui.structure.ControlStructureProcessor;
import ru.pearx.libmc.common.structure.processors.StructureProcessor;
import ru.pearx.libmc.common.structure.processors.StructureProcessorData;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.config.PMConfig;
import ru.pearx.purmag.common.expressions.IExpression;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

import javax.annotation.Nullable;
import java.util.Random;

/*
 * Created by mrAppleXZ on 29.09.17 19:50.
 */
public class CodeStorageProcessor extends StructureProcessor
{
    public static final ResourceLocation ID = Utils.gRL("code_storage");

    public CodeStorageProcessor()
    {
        setRegistryName(ID);
    }

    public static class Data extends StructureProcessorData
    {
        public ResourceLocation table;

        public Data(BlockPos pos)
        {
            setAbsolutePos(pos);
        }

        public Data(BlockPos pos, ResourceLocation table)
        {
            setAbsolutePos(pos);
            this.table = table;
        }

        @Override
        public NBTTagCompound serialize()
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("table", table.toString());
            return tag;
        }

        @Override
        public void deserialize(NBTTagCompound tag)
        {
            table = new ResourceLocation(tag.getString("table"));
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Control extends ControlStructureProcessor
    {
        public TextBox table = new TextBox(Utils.gRL("textures/gui/textbox.png"));

        public Control()
        {
            table.setWidth(getWidth() - pos.getWidth());
            table.setPos(pos.getX() + pos.getWidth(), pos.getY());
            table.setMaxRenderLength(48);
        }

        public Control(Data data)
        {
            this();
            setPosText(data.getAbsolutePos());
            table.setText(data.table.toString());
        }

        @Override
        public void init()
        {
            super.init();
            getControls().add(table);
        }

        @Override
        public void render()
        {
            super.render();
            DrawingTools.drawString(I18n.format("misc.structure_processor.code_storage.table"), table.getX(), 0, Colors.WHITE);
        }

        @Override
        public Pair<ResourceLocation, StructureProcessorData> getData()
        {
            return Pair.of(ID, new Data(PXLMC.parseCoords(pos.getBuffer().toString()), new ResourceLocation(table.getBuffer().toString())));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ControlStructureProcessor getControl(@Nullable StructureProcessorData data)
    {
        if(data != null)
        {
            Data dat = (Data) data;
            return new Control(dat);
        }
        return new Control();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getName()
    {
        return I18n.format("misc.structure_processor.code_storage.name");
    }

    @Override
    public void process(StructureProcessorData data, WorldServer world, Random rand)
    {
        Data d = (Data) data;
        PMConfig cfg = PurMag.INSTANCE.config;
        IExpression.ExpressionData expr = PurMag.INSTANCE.getExpressionRegistry().getRegistry().getValue(cfg.enabledExpressions.get(rand.nextInt(cfg.enabledExpressions.size()))).generateExpression(rand);
        TileEntity te = world.getTileEntity(d.getAbsolutePos());
        if(te != null && te instanceof TileCodeStorage)
        {
            TileCodeStorage storage = (TileCodeStorage) te;
            storage.tryLock(null, true, expr.getText(), expr.getResult());
            PXLMC.fillBlockWithLoot(world, rand, d.getAbsolutePos(), EnumFacing.NORTH, d.table, expr.getLuck());
        }
    }

    @Override
    public StructureProcessorData loadData(NBTTagCompound tag, BlockPos pos)
    {
        Data dat = new Data(pos);
        dat.deserialize(tag);
        return dat;
    }
}
