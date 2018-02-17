package ru.pearx.purmag.common.infofield;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.lib.Colors;
import ru.pearx.lib.Supplied;
import ru.pearx.libmc.client.gui.drawables.EntityDrawable;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.SimpleDrawable;
import ru.pearx.libmc.client.gui.drawables.item.ItemDrawable;
import ru.pearx.libmc.client.gui.drawables.item.MultiItemDrawable;
import ru.pearx.libmc.common.items.PXLItems;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.GuiDrawableRegistry;
import ru.pearx.purmag.client.gui.controls.CraftingControl;
import ru.pearx.purmag.client.infofield.pages.*;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.blocks.multiblock.MultiblockRegistry;
import ru.pearx.purmag.common.entities.EntityBeetle;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.*;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.recipes.ingredients.IngredientNBT;
import ru.pearx.purmag.common.sip.SipType;
import ru.pearx.purmag.common.sip.SipUtils;
import ru.pearx.purmag.common.tiles.TileMagibench;
import ru.pearx.purmag.common.tiles.TileSingleSip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mrAppleXZ on 15.04.17 9:42.
 */
public class IfRegistry
{
    public ArrayList<IfChannel> channels = new ArrayList<>();
    public ArrayList<IfEntry> entries = new ArrayList<>();
    public ArrayList<IfTier> tiers = new ArrayList<>();

    public IfRegistry()
    {
        registerTier(new IfTier(0)); //Only player's thoughts.
        registerTier(new IfTier(1)); //Player's thoughts and simple researches.
        registerTier(new IfTier(2)); //Player's thoughts and advanced researches.
    }

    public void registerChannel(IfChannel chan)
    {
        channels.add(chan);
    }

    public void registerEntry(IfEntry entr)
    {
        entries.add(entr);
    }

    public void registerTier(IfTier t)
    {
        tiers.add(t);
    }

    public IfChannel getChannel(String id)
    {
        for (IfChannel chan : channels)
            if (chan.getId().equals(id))
                return chan;
        return null;
    }

    public IfEntry getEntry(String id)
    {
        for (IfEntry entr : entries)
            if (entr.getId().equals(id))
                return entr;
        return null;
    }

    public boolean containsEntry(String id)
    {
        return getEntry(id) != null;
    }

    public boolean containsChannel(String id)
    {
        return getChannel(id) != null;
    }

    public IfTier getTier(int tier)
    {
        for (IfTier t : tiers)
        {
            if (t.getTier() == tier)
                return t;
        }
        return null;
    }

    public void attachEntry(String channel, IfEntryLocation entry)
    {
        getChannel(channel).addEntry(entry);
    }

    public <T extends IIfResearchStep> List<Pair<IfEntry, T>> getAllResearchableSteps(Class<T> clazz, EntityPlayer p)
    {
        return getAllResearchableSteps(clazz, p, p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null));
    }

    public <T extends IIfResearchStep> List<Pair<IfEntry, T>> getAllResearchableSteps(Class<T> clazz, EntityPlayer p, IIfEntryStore store)
    {
        List<Pair<IfEntry, T>> lst = new ArrayList<>();
        for (IfEntry entr : entries)
        {
            int steps = store.getSteps(entr.getId());
            if (steps < entr.getSteps().size())
            {
                IIfResearchStep step = entr.getSteps().get(steps);
                if (clazz.isInstance(step))
                {
                    if (entr.isAvailableToResearch(p))
                    {
                        lst.add(Pair.of(entr, (T) step));
                    }
                }
            }
        }
        return lst;
    }

    @SideOnly(Side.CLIENT)
    public void registerEntryClient(String name, IGuiDrawable icon, IIfPage... pages)
    {
        IfEntry entr = getEntry(name);
        entr.setIcon(icon);
        entr.setPages(Arrays.asList(pages));
    }

    @SideOnly(Side.CLIENT)
    public void registerChannelClient(String name, IGuiDrawable icon)
    {
        getChannel(name).setIcon(icon);
    }

    @SideOnly(Side.CLIENT)
    public void registerTierClient(int tier, IfTier.TabletData tabletData, ResourceLocation wallTabletTexture, ResourceLocation tabletItemModel)
    {
        IfTier t = getTier(tier);
        t.setTabletData(tabletData);
        t.setWallTabletTexture(wallTabletTexture);
        t.setItemModel(tabletItemModel);
    }

    @SideOnly(Side.CLIENT)
    public void setupIfTiersClient()
    {
        registerTierClient(0, new IfTier.TabletData(GuiDrawableRegistry.paperEntry, false, Utils.gRL("textures/gui/if_tablet/0.png"), Colors.GREY_100, Colors.GREY_700)
        {
            IGuiDrawable done = GuiDrawableRegistry.paperEntryCompleted;
            @Override
            @SideOnly(Side.CLIENT)
            public IGuiDrawable getEntryBackground(IfEntry entry, int currentSteps)
            {
                if(currentSteps < entry.getSteps().size())
                    return super.getEntryBackground(entry, currentSteps);
                else
                    return done;
            }
        }, Utils.gRL("models/wall_if_tablet/0"), Utils.gRL("if_tablet/0"));
        registerTierClient(1, new IfTier.TabletData(GuiDrawableRegistry.runes, true, Utils.gRL("textures/gui/if_tablet/1.png"), Colors.LIGHTGREEN_200, Colors.GREEN_800), Utils.gRL("models/wall_if_tablet/1"), Utils.gRL("if_tablet/1"));
        registerTierClient(2, new IfTier.TabletData(GuiDrawableRegistry.runes, true, Utils.gRL("textures/gui/if_tablet/2.png"), Colors.LIGHTGREEN_200, Colors.GREEN_900), Utils.gRL("models/wall_if_tablet/2"), Utils.gRL("if_tablet/2"));
    }

    public void setup()
    {
        registerChannel(new IfChannel("information", 0));
        registerChannel(new IfChannel("exploration", 0));
        registerChannel(new IfChannel("machinery", 1));
        registerChannel(new IfChannel("sip", 1));

        //INFORMATION
        registerEntry(new IfEntry("wooden_tablet", 0, null, Collections.emptyList(), 0));
        attachEntry("information", new IfEntryLocation("wooden_tablet", 0, 0));

        //SIP
        registerEntry(new IfEntry(
                "sip_knowledge", 1,
                null,
                Arrays.asList(new IRSReadPapyrus("sip_knowledge"), new IRSTranslatePapyrus("sip_knowledge")),
                1));
        attachEntry("sip", new IfEntryLocation("sip_knowledge", 0, 0));
        //EXPLORATION
        /*registerEntry(new IfEntry(
                "crysagnetite", 0,
                null,
                Arrays.asList(
                        new IRSCollect(Ingredient.fromItem(ItemRegistry.ore_crysagnetite), "crysagnetite", true),
                        new IRSMicroscopeResearch(Ingredient.fromItem(ItemRegistry.ore_crysagnetite), new boolean[][]
                                {
                                        {true, true, true, true, true, false},
                                        {true, true, false, false, true, true},
                                        {true, false, false, false, false, true},
                                        {true, false, false, false, false, true},
                                        {true, true, false, false, true, true},
                                        {true, true, false, false, true, true}
                                })),
                0));
        attachEntry("exploration", new IfEntryLocation("crysagnetite", 0, 0));







        */

        registerEntry(new IfEntry(
                "crystals", 0,
                null,
                Arrays.asList(new IRSCollect(Ingredient.fromItem(ItemRegistry.crystal_shard), "crystals", true)), 0));
        attachEntry("exploration", new IfEntryLocation("crystals", -2, 3));

        registerEntry(new IfEntry(
                "crystals_2", 0,
                Arrays.asList("crystals", "microscope"),
                Arrays.asList(new IRSMicroscopeResearch(Ingredient.fromItem(ItemRegistry.crystal_shard), new boolean[][]
                        {
                                {false, false, false, true, false, false, false},
                                {false, false, false, true, false, false, false},
                                {true, false, true, true, true, false, false},
                                {true, true, true, true, true, false, true},
                                {false, true, true, false, true, true, true},
                                {false, false, true, false, true, true, true},
                                {false, false, true, true, true, true, false},
                                {false, false, true, true, true, false, false},
                        })), 0));
        attachEntry("exploration", new IfEntryLocation("crystals_2", 0, 3));

        registerEntry(new IfEntry(
                "flame_crystal", 0,
                Arrays.asList("crystals_2"),
                Arrays.asList(new IRSMicroscopeResearch(new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "flame")), new boolean[][]
                        {
                                {false, false, true, false, false},
                                {true, false, true, false, true},
                                {true, true, true, true, true},
                                {true, true, false, true, true},
                                {false, true, true, true, false},
                        })),
                0));
        attachEntry("exploration", new IfEntryLocation("flame_crystal", 3, 5));

        registerEntry(new IfEntry(
                "rock_crystal", 0,
                Arrays.asList("crystals_2"),
                Arrays.asList(new IRSMicroscopeResearch(new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "rock")), new boolean[][]
                        {
                                {false, true, true, true, false},
                                {true, false, false, false, true},
                                {true, false, false, false, true},
                                {true, true, true, true, true},
                        })),
                0));
        attachEntry("exploration", new IfEntryLocation("rock_crystal", 1, 5));

        registerEntry(new IfEntry(
                "air_crystal", 0,
                Arrays.asList("crystals_2"),
                Arrays.asList(new IRSMicroscopeResearch(new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "air")), new boolean[][]
                        {
                                {false, true, true, true, true, false},
                                {true, false, false, true, false, true},
                                {true, false, true, false, false, true},
                                {true, false, false, true, true, false},
                                {true, false, false, false, false, false},
                                {false, true, true, true, true, true},
                        })),
                0));
        attachEntry("exploration", new IfEntryLocation("air_crystal", -1, 5));

        registerEntry(new IfEntry(
                "sea_crystal", 0,
                Arrays.asList("crystals_2"),
                Arrays.asList(new IRSMicroscopeResearch(new IngredientNBT(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal_shard), "sea")), new boolean[][]
                        {
                                {false, true, true, false, false, true},
                                {true, false, false, true, true, false},
                                {false, true, true, false, false, true},
                                {true, false, false, true, true, false},
                        })),
                0));
        attachEntry("exploration", new IfEntryLocation("sea_crystal", -3, 5));


        registerEntry(new IfEntry(
                "laboratories", 0,
                null,
                Collections.emptyList(),
                0
        ));
        attachEntry("exploration", new IfEntryLocation("laboratories", 0, -2));

        registerEntry(new IfEntry(
                "microscope", 0,
                Arrays.asList("laboratories"),
                Arrays.asList(new IRSBlockInteract(Arrays.asList(new BlockArrayEntry(BlockRegistry.microscope.getDefaultState(), new ItemStack(ItemRegistry.microscope))))),
                0
        ));
        attachEntry("exploration", new IfEntryLocation("microscope", 0, 0));

        registerEntry(new IfEntry(
                "magibench", 0,
                Arrays.asList("microscope"),
                Arrays.asList(new IRSMicroscopeResearch(Ingredient.fromStacks(new ItemStack(Blocks.CRAFTING_TABLE)), new boolean[][]
                        {
                                {true, true, true, false, true, true, true, false},
                                {true, false, true, false, true, true, true, true},
                                {true, true, true, false, false, true, false, false},
                                {true, true, true, false, false, true, false, false},
                                {false, true, true, false, false, true, false, false},
                                {false, false, true, false, false, true, false, false}
                        })),
                0
        ));
        attachEntry("exploration", new IfEntryLocation("magibench", 2, 0));

        registerEntry(new IfEntry(
                "brulanta_flower", 0,
                Arrays.asList("magibench"),
                Arrays.asList(
                        new IRSCollect(Ingredient.fromItem(ItemRegistry.brulanta_flower), "brulanta_flower", true),
                        new IRSMicroscopeResearch(Ingredient.fromItem(ItemRegistry.brulanta_flower), new boolean[][]
                                {
                                        {true, false, false, false, true},
                                        {true, true, true, true, true},
                                        {false, true, true, true, false},
                                        {false, false, true, false, false},
                                        {true, false, true, false, false},
                                        {false, true, true, false, false},
                                        {false, false, true, false, false},
                                })),
                0
        ));
        attachEntry("exploration", new IfEntryLocation("brulanta_flower", 4, 0));

        registerEntry(new IfEntry(
                "mortar_and_pestle", 0,
                Arrays.asList("magibench"),
                Arrays.asList(new IRSMicroscopeResearch(Ingredient.fromItem(Items.CLAY_BALL), new boolean[][]
                        {
                                {false, false, true, true, false, false},
                                {false, true, true, true, true, false},
                                {true, true, false, false, true, true},
                                {true, true, false, false, true, true},
                                {false, true, true, true, true, false},
                                {false, false, true, true, false, false},
                        })),
                0
        ));
        attachEntry("exploration", new IfEntryLocation("mortar_and_pestle", 4, 2));

        registerEntry(new IfEntry(
                "verda_beetle", 0,
                Arrays.asList("magibench"),
                Arrays.asList(
                        new IRSKillEntity(EntityBeetle.class, "verda_beetle"),
                        new IRSMicroscopeResearch(Ingredient.fromStacks(new ItemStack(ItemRegistry.beetle_meat, 1, 0)), new boolean[][]
                                {
                                        {true, false, true, true, false, true},
                                        {true, true, false, false, true, true},
                                        {false, false, true, true, false, false},
                                        {false, false, true, true, false, false},
                                        {false, true, false, false, true, false},
                                        {true, true, false, false, true, true},
                                })),
                0));
        attachEntry("exploration", new IfEntryLocation("verda_beetle", 4, -2));

        //MACHINERY
        attachEntry("machinery", new IfEntryLocation("magibench", 0, 2));

        registerEntry(new IfEntry(
                "multiblocks", 0,
                Arrays.asList("magibench"),
                Collections.emptyList(),
                0));
        attachEntry("machinery", new IfEntryLocation("multiblocks", 0, 0));

        registerEntry(new IfEntry(
                "stone_crusher", 0,
                Arrays.asList("multiblocks"),
                Arrays.asList(
                        new IRSMicroscopeResearch(Ingredient.fromItem(Item.getItemFromBlock(Blocks.ANVIL)), new boolean[][]
                                {
                                        {false, false, true, true, true, true, true, true},
                                        {true, true, true, true, true, true, true, true},
                                        {false, false, true, true, true, true, true, true},
                                        {false, false, false, true, true, false, false, false},
                                        {false, false, false, true, true, false, false, false},
                                        {false, false, false, true, true, false, false, false},
                                        {false, false, true, true, true, true, false, false},
                                        {true, true, true, false, false, true, true, true},
                                })),
                0));
        attachEntry("machinery", new IfEntryLocation("stone_crusher", 2, 1));

        MinecraftForge.EVENT_BUS.post(new IfRegistryEvent.Setup(this));
    }

    @SideOnly(Side.CLIENT)
    public void setupClient()
    {
        registerChannelClient("information", new ItemDrawable(new ItemStack(ItemRegistry.if_tablet, 1, 1), 1.5f));
        registerChannelClient("exploration", new ItemDrawable(new ItemStack(ItemRegistry.crystal), 1.5f));
        registerChannelClient("machinery", new SimpleDrawable(Utils.gRL("textures/gui/icons/machinery.png"), 28, 28));
        registerChannelClient("sip", new SimpleDrawable(Utils.gRL("textures/gui/icons/sip.png"), 28, 28));

        //INFORMATION
        registerEntryClient(
                "wooden_tablet", new ItemDrawable(new ItemStack(ItemRegistry.if_tablet), 1.5f),
                new IfPageText("wooden_tablet.0"),
                new IfPageText("wooden_tablet.1"),
                new IfPageCrafting(CraftingControl.fromVanillaCrafting(Utils.gRL("painting_kit")), CraftingControl.fromVanillaCrafting(Utils.gRL("gray_paper_pack")), CraftingControl.fromVanillaCrafting(Utils.gRL("wooden_tablet")))
        );

        //SIP
        registerEntryClient(
                "sip_knowledge", new SimpleDrawable(Utils.gRL("textures/gui/icons/sip_text.png"), 28, 28, 28, 28),
                new IfPageText("sip_knowledge.0"),
                new IfPagePapyrus("sip_knowledge.1")
        );

        /*registerEntryClient(
                "crysagnetite", new BigItemDrawable(new ItemStack(ItemRegistry.ore_crysagnetite)),
                new IfPageText("crysagnetite.0"),
                new IfPageText("crysagnetite.1", Integer.toString(PurMag.INSTANCE.config.genCrysagnetite.minY), Integer.toString(PurMag.INSTANCE.config.genCrysagnetite.maxY)
                ));






        */

        //EXPLORATION
        {
            List<ItemStack> l = new ArrayList<>();
            for (SipType t : PurMag.INSTANCE.getSipRegistry().getTypes())
                l.add(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), t.getName()));
            MultiItemDrawable draw = new MultiItemDrawable(l, 1.5f, 500);

            {
                BlockArray arr = new BlockArray();
                int x = 0;
                IBlockState stone = Blocks.STONE.getDefaultState();
                for (SipType t : PurMag.INSTANCE.getSipRegistry().getTypes())
                {
                    TileSingleSip tile = new TileSingleSip();
                    tile.setType(t.getName(), false);
                    arr.getMap().put(new BlockPos(x, 0, 0), new BlockArrayEntry(BlockRegistry.crystal.getDefaultState(), SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), t.getName()), tile));
                    x += 2;
                }
                for (int i = 0; i < PurMag.INSTANCE.getSipRegistry().getTypes().size() * 2 - 1; i++)
                {
                    arr.getMap().put(new BlockPos(i, -1, 0), new BlockArrayEntry(stone, ItemStack.EMPTY));
                }
                registerEntryClient(
                        "crystals", draw,
                        new IfPageText("crystals.0"),
                        new IfPageBlocks(arr)
                );
            }

            registerEntryClient(
                    "crystals_2", draw,
                    new IfPageText("crystals_2.0")
            );
        }

        registerEntryClient(
                "flame_crystal", new ItemDrawable(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), "flame"), 1.5f),
                new IfPageText("flame_crystal")
        );
        registerEntryClient(
                "rock_crystal", new ItemDrawable(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), "rock"), 1.5f),
                new IfPageText("rock_crystal")
        );
        registerEntryClient(
                "air_crystal", new ItemDrawable(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), "air"), 1.5f),
                new IfPageText("air_crystal")
        );

        registerEntryClient(
                "sea_crystal", new ItemDrawable(SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), "sea"), 1.5f),
                new IfPageText("sea_crystal")
        );

        registerEntryClient(
                "laboratories", new SimpleDrawable(Utils.gRL("textures/gui/icons/laboratory.png"), 28, 28),
                new IfPageText("laboratories.0"),
                new IfPagePicture(GuiDrawableRegistry.labSmall, "laboratories.1"),
                new IfPagePicture(GuiDrawableRegistry.labMedium, "laboratories.2"),
                new IfPageText("laboratories.3")
        );
        registerEntryClient(
                "microscope", new ItemDrawable(new ItemStack(ItemRegistry.microscope), 1.5f),
                new IfPageText("microscope.0"),
                new IfPageText("microscope.1"),
                new IfPagePicture(new SimpleDrawable(Utils.gRL("textures/gui/pictures/microscope_research.png"), 344, 185), "microscope.2"),
                new IfPageBlocks(new BlockArray()
                {{
                    getMap().put(new BlockPos(0, 0, 0), new BlockArrayEntry(BlockRegistry.microscope.getDefaultState(), new ItemStack(ItemRegistry.microscope)));
                }})
        );
        {
            TileMagibench mag = new TileMagibench();
            mag.setTier(0, false);
            registerEntryClient(
                    "magibench", new ItemDrawable(new ItemStack(ItemRegistry.magibench), 1.5f),
                    new IfPageText("magibench.0"),
                    new IfPageCrafting(CraftingControl.fromVanillaCrafting(Utils.gRL("simple_magibench"))),
                    new IfPageBlocks(BlockArray.fromSingleBlock(BlockRegistry.magibench.getDefaultState(), new ItemStack(ItemRegistry.magibench, 1, 0), mag))
            );
        }
        registerEntryClient(
                "brulanta_flower", new ItemDrawable(new ItemStack(ItemRegistry.brulanta_flower), 1.5f),
                new IfPageText("brulanta_flower.0"),
                new IfPageBlocks(new BlockArray()
                {{
                    getMap().put(new BlockPos(0, 0, 0), new BlockArrayEntry(BlockRegistry.brulanta_flower.getDefaultState(), new ItemStack(ItemRegistry.brulanta_flower)));
                    getMap().put(new BlockPos(0, -1, 0), new BlockArrayEntry(Blocks.SAND.getDefaultState(), ItemStack.EMPTY));
                }})
        );
        registerEntryClient(
                "mortar_and_pestle", new ItemDrawable(new ItemStack(ItemRegistry.mortar_and_pestle), 1.5f),
                new IfPageText("mortar_and_pestle.0"),
                new IfPageCrafting(CraftingControl.fromMagibench(Utils.gRL("unf_mortar_and_pestle"))),
                new IfPageCrafting(CraftingControl.fromSmelting(new ItemStack(ItemRegistry.unfinished_mortar_and_pestle)))
        );
        registerEntryClient(
                "verda_beetle", new EntityDrawable(EntityBeetle.class, 20, 5),
                new IfPageText("verda_beetle.0"),
                new IfPageEntity(EntityBeetle.class, "verda_beetle.1"),
                new IfPageCrafting(CraftingControl.fromSmelting(new ItemStack(ItemRegistry.beetle_meat)))
        );

        //MACHINERY
        registerEntryClient(
                "multiblocks", new ItemDrawable(new ItemStack(ItemRegistry.iron_tinkering_kit), 1.5f),
                new IfPageText("multiblocks.0"),
                new IfPageCrafting(CraftingControl.fromMagibench(Utils.gRL("iron_tinkering_kit_0")), CraftingControl.fromSmelting(new ItemStack(ItemRegistry.iron_tinkering_kit_mold, 1, 0))),
                new IfPageCrafting(CraftingControl.fromMagibench(Utils.gRL("iron_tinkering_kit_2")), CraftingControl.fromSmelting(new ItemStack(ItemRegistry.iron_tinkering_kit_mold, 1, 2))),
                new IfPageCrafting(CraftingControl.fromMagibench(Utils.gRL("iron_tinkering_kit")))
        );
        registerEntryClient(
                "stone_crusher", new ItemDrawable(PXLItems.multiblock.newStack(MultiblockRegistry.STONE_CRUSHER.getRegistryName()), 1.5f),
                new IfPageText("stone_crusher.0"),
                new IfPageCrafting(CraftingControl.fromMagibench(Utils.gRL("rope_coil")), CraftingControl.fromMagibench(Utils.gRL("cog_rope_coil"))),
                new IfPageMultiblock(MultiblockRegistry.STONE_CRUSHER)
        );

        MinecraftForge.EVENT_BUS.post(new IfRegistryEvent.SetupClient(this));
    }

    @SideOnly(Side.CLIENT)
    public void setupJei()
    {
        for(IfEntry entr : entries)
        {
            for(IIfPage p : entr.getPages())
            {
                if(p instanceof IfPageCrafting)
                {
                    for(Supplied<CraftingControl> sup : ((IfPageCrafting) p).getCrafts())
                        sup.supply();
                }
            }
        }
    }
}



