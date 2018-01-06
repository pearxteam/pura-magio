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
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.IRSReadPapyrus;
import ru.pearx.purmag.common.items.base.ItemBase;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by mrAppleXZ on 30.05.17 9:19.
 */
public class ItemPapyrus extends ItemBase
{
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
        if (!worldIn.isRemote)
        {
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
            for (Pair<IfEntry, IRSReadPapyrus> pair : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSReadPapyrus.class, p, store))
            {
                if (pair.getRight().isSuitable(stack))
                {
                    store.unlockStepAndSync(pair.getLeft().getId(), (EntityPlayerMP)p);
                }
            }
        }
        if (stack.hasTagCompound())
        {
            if (stack.getTagCompound().hasKey("papyrus_id"))
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
        if (isInCreativeTab(tab))
        {
            for (PapyrusData data : PurMag.INSTANCE.getPapyrusRegistry().getPapyruses())
            {
                subItems.add(getPapyrusItem(data.getPapyrusId()));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (stack.hasTagCompound())
        {
            if (stack.getTagCompound().hasKey("papyrus_id"))
            {
                PapyrusData dat = PurMag.INSTANCE.getPapyrusRegistry().getPapyrus(stack.getTagCompound().getString("papyrus_id"));
                if (dat != null)
                    tooltip.add(TextFormatting.GOLD + dat.getDisplayName() + TextFormatting.RESET);
            }
        }
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
        if (stack.hasTagCompound())
        {
            if (stack.getTagCompound().hasKey("papyrus_id"))
            {
                return stack.getTagCompound().getString("papyrus_id");
            }
        }
        return "";
    }
}
