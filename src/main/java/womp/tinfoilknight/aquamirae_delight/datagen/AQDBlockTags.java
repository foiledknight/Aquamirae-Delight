package womp.tinfoilknight.aquamirae_delight.datagen;

import com.obscuria.aquamirae.registry.AquamiraeBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.tag.ExistingTag;

import java.util.concurrent.CompletableFuture;

public class AQDBlockTags extends BlockTagsProvider {
    public AQDBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.MINEABLE_WITH_KNIFE).add(AquamiraeDelight.DEEPSEA_PIE.get(), AquamiraeDelight.FISHERMANS_DELICACY.get(), AquamiraeDelight.AQUATIC_FEAST.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ExistingTag.JAR_BLOCKS);
        this.tag(ExistingTag.JAR_BLOCKS).add(AquamiraeBlocks.GOLDEN_MOTH_IN_A_JAR.get(), AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get(), AquamiraeDelight.JAR.get(), AquamiraeDelight.FUNCTIONAL_JAR.get());
    }

}
