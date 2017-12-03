package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.RandomUtils;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.particle.ParticleEngine;
import ru.pearx.libmc.common.networking.ByteBufTools;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.particle.ParticleMultiblock;
import ru.pearx.purmag.common.SoundRegistry;

import java.util.Random;

/*
 * Created by mrAppleXZ on 03.12.17 14:16.
 */
public class CPacketSpawnMultiblockParticles implements IMessage
{
    public BlockPos pos;
    public ResourceLocation multiblockId;
    public Rotation rot;

    public CPacketSpawnMultiblockParticles()
    {
    }

    public CPacketSpawnMultiblockParticles(BlockPos pos, ResourceLocation multiblockId, Rotation rot)
    {
        this.pos = pos;
        this.multiblockId = multiblockId;
        this.rot = rot;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readBlockPos(buf);
        multiblockId = new ResourceLocation(ByteBufUtils.readUTF8String(buf));
        rot = Rotation.values()[buf.readInt()];
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeBlockPos(buf, pos);
        ByteBufUtils.writeUTF8String(buf, multiblockId.toString());
        buf.writeInt(rot.ordinal());
    }

    public static class Handler implements IMessageHandler<CPacketSpawnMultiblockParticles, IMessage>
    {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSpawnMultiblockParticles message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() ->
            {
                Minecraft.getMinecraft().world.playSound(message.pos, SoundRegistry.MULTIBLOCK_FORM, SoundCategory.BLOCKS, 1, 1, false);
                BlockArray struct = Multiblock.REGISTRY.getValue(message.multiblockId).getStructure();
                BlockPos min = new BlockPos(struct.getMinX(), struct.getMinY(), struct.getMinZ()).add(message.pos);
                BlockPos max = new BlockPos(struct.getMaxX(), struct.getMaxY(), struct.getMaxZ()).add(message.pos);
                for(int i = 0; i < 10; i++)
                {
                    Random rand = PurMag.INSTANCE.random;
                    float delta = RandomUtils.nextFloat(0.005f, 0.04f, rand);
                    float lightDelta = RandomUtils.nextFloat(-0.01f, 0.01f, rand);
                    float y = RandomUtils.nextFloat(min.getY(), max.getY() + 2, rand);
                    ParticleEngine.addParticle(new ParticleMultiblock(RandomUtils.nextFloat(min.getX(), max.getX() + 1, rand), y, min.getZ() - 0.2f, lightDelta, -delta, RandomUtils.nextColor(rand)));
                    ParticleEngine.addParticle(new ParticleMultiblock(RandomUtils.nextFloat(min.getX(), max.getX() + 1, rand), y, max.getZ() + 1 + 0.2f, lightDelta, delta, RandomUtils.nextColor(rand)));
                    ParticleEngine.addParticle(new ParticleMultiblock(min.getX() - 0.2f, y, RandomUtils.nextFloat(min.getZ(), max.getZ() + 1, rand), -delta, lightDelta, RandomUtils.nextColor(rand)));
                    ParticleEngine.addParticle(new ParticleMultiblock(max.getX() + 1 + 0.2f, y, RandomUtils.nextFloat(min.getZ(), max.getZ() + 1, rand), delta, lightDelta, RandomUtils.nextColor(rand)));
                }
            });
            return null;
        }
    }

}
