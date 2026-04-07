package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AquamiraeDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();
        BlockTagsProvider blockTags = new AQDBlockTags(gen, efh);
        LootTableProvider.SubProviderEntry entityLootSubProvider = new LootTableProvider.SubProviderEntry(AQDEntityLoot::new, LootContextParamSets.EMPTY);

        gen.addProvider(
                event.includeClient(),
                new AQDBlockStates(gen, efh)
        );
        gen.addProvider(
                event.includeClient(),
                new AQDLang_EN_US(gen)
        );
        gen.addProvider(
                event.includeClient(),
                new AQDLang_RU_RU(gen)
        );
        gen.addProvider(
                event.includeClient(),
                new AQDLang_ZH_CN(gen)
        );
        gen.addProvider(
                event.includeServer(),
                new AQDRecipes(gen)
        );
        gen.addProvider(
                event.includeClient(),
                new AQDItemModels(gen, efh)
        );
        gen.addProvider(
                event.includeServer(),
                new AQDItemTags(gen, blockTags, efh)
        );
        gen.addProvider(
                event.includeServer(),
                blockTags
        );
        gen.addProvider(event.includeServer(), new AQDLootTables(output, Collections.emptySet(), List.of(entityLootSubProvider)));
    }
}

