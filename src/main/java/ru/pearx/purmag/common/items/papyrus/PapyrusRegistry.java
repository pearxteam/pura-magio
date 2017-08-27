package ru.pearx.purmag.common.items.papyrus;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetNBT;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.items.ItemRegistry;

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
        for (PapyrusData dat : getPapyruses())
            if (dat.getPapyrusId().equals(id))
                return dat;
        return null;
    }

    public void setup()
    {
        registerPapyrus(new PapyrusData("sip_knowledge", true));
        registerPapyrus(new PapyrusData("poem_1_1", false));
        registerPapyrus(new PapyrusData("poem_1_2", false));
    }

    public void addNotImportantToTable(LootTable table)
    {
        List<LootEntry> entrs = new ArrayList<>();
        for(PapyrusData p : getPapyruses())
        {
            if(!p.isImportant())
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setString("papyrus_id", p.getPapyrusId());
                entrs.add(new LootEntryItem(ItemRegistry.papyrus, 1, 1, new LootFunction[]{new SetNBT(new LootCondition[]{}, tag)}, new LootCondition[]{}, p.getPapyrusId()));
            }
        }
        table.addPool(new LootPool(entrs.toArray(new LootEntry[]{}), new LootCondition[]{}, new RandomValueRange(0, 1), new RandomValueRange(0), "papyruses"));
    }
}
