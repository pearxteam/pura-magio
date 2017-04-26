package net.pearx.purmag.client;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;

/**
 * Created by mrAppleXZ on 26.04.17 22:51.
 */
public class DisplayMessage
{
    private String subject;
    private String description;

    public DisplayMessage()
    {

    }

    public DisplayMessage(String subj, String desc)
    {
        setDescription(desc);
        setSubject(subj);
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void writeToByteBuf(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, subject);
        ByteBufUtils.writeUTF8String(buf, description);
    }

    public static DisplayMessage readFromByteBuf(ByteBuf buf)
    {
        DisplayMessage msg = new DisplayMessage();
        msg.setSubject(ByteBufUtils.readUTF8String(buf));
        msg.setDescription(ByteBufUtils.readUTF8String(buf));
        return msg;
    }
}
