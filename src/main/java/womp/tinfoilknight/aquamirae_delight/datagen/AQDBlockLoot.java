package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;

import java.util.Set;

public abstract class AQDBlockLoot extends BlockLootSubProvider {
    protected AQDBlockLoot(){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


}
