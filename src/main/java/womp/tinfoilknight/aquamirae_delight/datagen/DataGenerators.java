package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vectorwing.farmersdelight.data.BlockTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AquamiraeDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        AQDBlockTags blockTags = new AQDBlockTags(output, lookupProvider,existingFileHelper);
        gen.addProvider(event.includeClient(), new AQDItemModels(output, existingFileHelper));
        gen.addProvider(event.includeClient(), new AQDBlockStates(output, existingFileHelper));
        gen.addProvider(event.includeClient(), new AQDLang_EN_US(output, "en_us"));
        gen.addProvider(event.includeClient(), new AQDLang_RU_RU(output, "ru_ru"));
        gen.addProvider(event.includeClient(), new AQDLang_ZH_CN(output, "zh_cn"));
        gen.addProvider(event.includeServer(), new AQDRecipes(output));
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new AQDItemTags(output, lookupProvider, blockTags.contentsGetter(),existingFileHelper));
    }
}

