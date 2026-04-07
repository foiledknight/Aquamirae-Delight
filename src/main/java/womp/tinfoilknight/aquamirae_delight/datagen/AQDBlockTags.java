package womp.tinfoilknight.aquamirae_delight.datagen;

import com.obscuria.aquamirae.registry.AquamiraeBlocks;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.tag.InForgeTags;

import java.util.concurrent.CompletableFuture;

public class AQDBlockTags extends BlockTagsProvider {
    public AQDBlockTags(DataGenerator gen, @Nullable ExistingFileHelper existingFileHelper) {
        super(gen, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ModTags.MINEABLE_WITH_KNIFE).add(AquamiraeDelight.DEEPSEA_PIE.get(), AquamiraeDelight.FISHERMANS_DELICACY.get(), AquamiraeDelight.AQUATIC_FEAST.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get());
        this.tag(InForgeTags.JAR_BLOCKS).add(AquamiraeBlocks.GOLDEN_MOTH_IN_A_JAR.get(), AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get());
    }

}
