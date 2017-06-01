package net.pearx.purmag.common.items.papyrus;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSReadPapyrus;
import net.pearx.purmag.common.items.ItemBase;
import net.pearx.purmag.common.items.ItemRegistry;

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
        setUnlocalizedName("papyrus");
        setRegistryName("papyrus");
        setHasSubtypes(true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer p, EnumHand handIn)
    {
        ItemStack stack = p.getHeldItem(handIn);
        if(!worldIn.isRemote)
        {
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
            for (IfEntry entr : PurMag.instance.if_registry.entries)
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
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        for(PapyrusData data : papyruses)
        {
            ItemStack stack = new ItemStack(this);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("papyrus_id", data.getPapyrusId());
            stack.setTagCompound(tag);
            subItems.add(stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
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

    public static void setup()
    {
        ItemRegistry.papyrus.papyruses.add(new PapyrusData("sip_knowledge"));
    }
}
