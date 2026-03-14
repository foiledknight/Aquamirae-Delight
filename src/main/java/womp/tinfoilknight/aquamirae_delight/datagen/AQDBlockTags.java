package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.concurrent.CompletableFuture;

public class AQDBlockTags extends BlockTagsProvider {
    public AQDBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        this.tag(ModTags.MINEABLE_WITH_KNIFE).add(AquamiraeDelight.DEEPSEA_PIE.get(), AquamiraeDelight.FISHERMANS_DELICACY.get(), AquamiraeDelight.AQUATIC_FEAST.get());
    }

}
