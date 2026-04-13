package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AQDBlockLoot extends BlockLootSubProvider {
    protected AQDBlockLoot() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.add(AquamiraeDelight.JAR.get(),
                LootTable.lootTable().withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(AquamiraeDelight.JAR.get()))
                )
        );
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return AquamiraeDelight.BLOCKS.getEntries() // Get all registered entries
                .stream() // Stream the wrapped objects
                .map(RegistryObject::get)
                .filter(block -> !skip.contains(block))
                .toList(); // Create the iterable
    }
    List<Block> skip = List.of(
            AquamiraeDelight.AQUATIC_FEAST.get(),
            AquamiraeDelight.DEEPSEA_PIE.get(),
            AquamiraeDelight.FISHERMANS_DELICACY.get(),
            AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get(),
            AquamiraeDelight.FUNCTIONAL_JAR.get()
    );
}
