package net.pearx.purmag.common.items.papyrus;

import net.minecraft.util.text.translation.I18n;

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

    public String getDisplayName()
    {
        return I18n.translateToLocal("papyrus." + getPapyrusId() + ".name");
    }

    public String getDisplayText()
    {
        return I18n.translateToLocal("papyrus." + getPapyrusId() + ".text");
    }
}
