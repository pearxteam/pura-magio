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
import ru.pearx.carbide.RandomUtils;
import ru.pearx.carbide.mc.CarbideMC;
import ru.pearx.carbide.mc.client.particle.ParticleEngine;
import ru.pearx.carbide.mc.common.misc.CoordUtils;
import ru.pearx.carbide.mc.common.networking.ByteBufTools;
import ru.pearx.carbide.mc.common.structure.blockarray.BlockArray;
import ru.pearx.carbide.mc.common.structure.multiblock.Multiblock;
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
                BlockPos f = CoordUtils.transformPos(new BlockPos(struct.getMinX(), struct.getMinY(), struct.getMinZ()), null, message.rot).add(message.pos);
                BlockPos t = CoordUtils.transformPos(new BlockPos(struct.getMaxX(), struct.getMaxY(), struct.getMaxZ()), null, message.rot).add(message.pos);
                int minX = Math.min(f.getX(), t.getX());
                int maxX = Math.max(f.getX(), t.getX());
                int minY = Math.min(f.getY(), t.getY());
                int maxY = Math.max(f.getY(), t.getY());
                int minZ = Math.min(f.getZ(), t.getZ());
                int maxZ = Math.max(f.getZ(), t.getZ());
                for(int i = 0; i < 10; i++)
                {
                    Random rand = PurMag.INSTANCE.random;
                    float delta = RandomUtils.nextFloat(0.001f, 0.002f, rand);
                    float lightDelta = RandomUtils.nextFloat(-0.001f, 0.001f, rand);
                    float y = RandomUtils.nextFloat(minY, maxY + 2, rand);
                    ParticleEngine.addParticle(new ParticleMultiblock(RandomUtils.nextFloat(minX, maxX + 1, rand), y, minZ - 0.2f, lightDelta, -delta, rand));
                    ParticleEngine.addParticle(new ParticleMultiblock(RandomUtils.nextFloat(minX, maxX + 1, rand), y, maxZ + 1 + 0.2f, lightDelta, delta, rand));
                    ParticleEngine.addParticle(new ParticleMultiblock(minX - 0.2f, y, RandomUtils.nextFloat(minZ, maxZ + 1, rand), -delta, lightDelta, rand));
                    ParticleEngine.addParticle(new ParticleMultiblock(maxX + 1 + 0.2f, y, RandomUtils.nextFloat(minZ, maxZ + 1, rand), delta, lightDelta, rand));
                }
            });
            return null;
        }
    }

}
