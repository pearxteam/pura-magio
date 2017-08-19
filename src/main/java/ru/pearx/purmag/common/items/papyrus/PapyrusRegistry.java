package ru.pearx.purmag.common.items.papyrus;

import ru.pearx.purmag.PurMag;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 18.08.17 21:05.
 */
public class PapyrusRegistry
{
    private List<PapyrusData> papyruses = new ArrayList<>();

    public List<PapyrusData> getPapyruses()
    {
        return papyruses;
    }

    public void registerPapyrus(PapyrusData data)
    {
        getPapyruses().add(data);
    }

    public PapyrusData getPapyrus(String id)
    {
        for(PapyrusData dat : getPapyruses())
            if(dat.getPapyrusId().equals(id))
                return dat;
        return null;
    }

    public void setup()
    {
        registerPapyrus(new PapyrusData("sip_knowledge"));
    }
}
