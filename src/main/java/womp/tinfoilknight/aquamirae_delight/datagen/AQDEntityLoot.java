package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class AQDEntityLoot extends LootTableS {
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
