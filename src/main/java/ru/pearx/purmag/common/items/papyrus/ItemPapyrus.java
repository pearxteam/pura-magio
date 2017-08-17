package ru.pearx.purmag.common.items.papyrus;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.IIfResearchStep;
import ru.pearx.purmag.common.infofield.steps.IRSReadPapyrus;
import ru.pearx.purmag.common.items.ItemBase;
import ru.pearx.purmag.common.items.ItemRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 30.05.17 9:19.
 */
public class ItemPapyrus extends ItemBase
{
    public List<PapyrusData> papyruses = new ArrayList<>();

    public ItemPapyrus()
    {
        super("papyrus");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer p, EnumHand handIn)
    {
        ItemStack stack = p.getHeldItem(handIn);
        if(!worldIn.isRemote)
        {
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
            for (IfEntry entr : PurMag.INSTANCE.if_registry.entries)
            {
                int steps = store.getSteps(entr.getId());
                if (steps < entr.getSteps().size())
                {
                    IIfResearchStep step = entr.getSteps().get(steps);
                    if (step instanceof IRSReadPapyrus)
                    {
                        if (entr.isAvailableToResearch(p))
                        {
                            IRSReadPapyrus rp = (IRSReadPapyrus) step;
                            if (rp.isSuitable(stack))
                            {
                                store.unlockStepAndSync(entr.getId(), (EntityPlayerMP)p);
                            }
                        }
                    }
                }
            }
        }
        if(stack.hasTagCompound())
        {
            if(stack.getTagCompound().hasKey("papyrus_id"))
            {
                PurMag.proxy.openPapyrus(stack.getTagCompound().getString("papyrus_id"));
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        if(isInCreativeTab(tab))
        {
            for (PapyrusData data : papyruses)
            {
                subItems.add(getPapyrusItem(data.getPapyrusId()));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if(stack.hasTagCompound())
        {
            if(stack.getTagCompound().hasKey("papyrus_id"))
            {
                PapyrusData dat = getPapyrus(stack.getTagCompound().getString("papyrus_id"));
                if(dat != null)
                    tooltip.add(TextFormatting.GOLD + dat.getDisplayName() + TextFormatting.RESET);
            }
        }
    }

    public PapyrusData getPapyrus(String id)
    {
        for(PapyrusData dat : papyruses)
            if(dat.getPapyrusId().equals(id))
                return dat;
        return null;
    }

    public ItemStack getPapyrusItem(String id)
    {
        ItemStack stack = new ItemStack(this);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("papyrus_id", id);
        stack.setTagCompound(tag);
        return stack;
    }

    public String getId(ItemStack stack)
    {
        if(stack.hasTagCompound())
        {
            if(stack.getTagCompound().hasKey("papyrus_id"))
            {
                return stack.getTagCompound().getString("papyrus_id");
            }
        }
        return "";
    }

    public static void setup()
    {
        ItemRegistry.papyrus.papyruses.add(new PapyrusData("sip_knowledge"));
    }
}
