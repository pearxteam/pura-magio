package net.pearx.purmag.common.items.papyrus;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 30.05.17 9:23.
 */
public class PapyrusData
{
    private String papyrusId;

    public PapyrusData(String papyrusId)
    {
        this.papyrusId = papyrusId;
    }

    public String getPapyrusId()
    {
        return papyrusId;
    }

    public void setPapyrusId(String papyrusId)
    {
        this.papyrusId = papyrusId;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName()
    {
        return I18n.format("papyrus." + getPapyrusId() + ".name");
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayText()
    {
        return I18n.format("papyrus." + getPapyrusId() + ".text");
    }
}
