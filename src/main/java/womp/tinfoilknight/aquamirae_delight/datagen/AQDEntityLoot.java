package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditions;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class AQDEntityLoot extends EntityLootSubProvider {
    protected AQDEntityLoot() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(AquamiraeDelight.GOLDEN_MOTH.get(),  LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))).add(LootItem.lootTableItem(Items.GOLD_NUGGET).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3))))));
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return AquamiraeDelight.ENTITY_TYPES.getEntries().stream()
                .map(RegistryObject::get);
    }

}
