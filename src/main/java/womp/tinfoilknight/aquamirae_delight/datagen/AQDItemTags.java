package womp.tinfoilknight.aquamirae_delight.datagen;


import com.obscuria.aquamirae.Aquamirae;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.tag.InForgeTags;

import java.util.concurrent.CompletableFuture;

public class AQDItemTags extends ItemTagsProvider {
    public AQDItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> tagLookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, tagLookup, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.MEALS).add(AquamiraeDelight.GLAZED_GRILLED_SPINEFISH.get(), AquamiraeDelight.GOLDEN_PUREE_PASTA.get(), AquamiraeDelight.ABYSSAL_RISOTTO.get(), AquamiraeDelight.SPINEFISH_ALFREDO.get(), AquamiraeDelight.ESCAGELIUM_SOUP.get(), AquamiraeDelight.ANGLERS_SOUP.get());
        this.tag(ModTags.DRINKS).add(AquamiraeDelight.GOLDEN_PUREE.get());
        this.tag(ModTags.FEASTS).add(AquamiraeDelight.AQUATIC_FEAST_ITEM.get());
        this.tag(ModTags.KNIVES).add(AquamiraeDelight.FIN_FILLETER.get(), AquamiraeDelight.REMNANTS_KNIFE.get(), AquamiraeDelight.SEPARATOR.get());
        this.tag(ForgeTags.TOOLS_KNIVES).add(AquamiraeDelight.FIN_FILLETER.get(), AquamiraeDelight.REMNANTS_KNIFE.get(), AquamiraeDelight.SEPARATOR.get());

        this.tag(ForgeTags.RAW_FISHES).addTag(InForgeTags.RAW_FISHES_SPINEFISH);
        this.tag(InForgeTags.RAW_FISHES_SPINEFISH).add(AquamiraeItems.SPINEFISH.get(), AquamiraeDelight.COOKED_SPINEFISH_SLICE.get());
        this.tag(ForgeTags.COOKED_FISHES).addTag(InForgeTags.COOKED_FISHES_SPINEFISH);
        this.tag(InForgeTags.COOKED_FISHES_SPINEFISH).add(AquamiraeItems.COOKED_SPINEFISH.get() ,AquamiraeDelight.COOKED_SPINEFISH_SLICE.get());

        this.tag(InForgeTags.JAR_ITEMS).add(AquamiraeItems.GOLDEN_MOTH_IN_A_JAR.get(), AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR_BLOCK_ITEM.get());
    }
}
