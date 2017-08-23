package ru.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.DisplayMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 26.04.17 22:49.
 */
@SideOnly(Side.CLIENT)
public class DisplayMessageQuery
{
    public static List<DisplayMessage> query = new ArrayList<>();

    public static DisplayMessage getMessage()
    {
        if (query.size() < 1)
            return null;
        return query.get(0);
    }

    public static void removeMessage()
    {
        query.remove(0);
    }

    public static void addMessage(DisplayMessage msg)
    {
        query.add(msg);
    }
}
