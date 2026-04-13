package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.blocks.FunctionalJarBlock;

public class AQDBlockModels extends BlockModelProvider {
    private ResourceLocation AquamiraeID(String path){
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.AQID, path);
    }
    private ResourceLocation AquamiraeDelightID(String path){
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, path);
    }
    public AQDBlockModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        jarBlock(AquamiraeDelight.JAR.get());
        jarBlock(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get());
        jarBlock(AquamiraeDelight.FUNCTIONAL_JAR.get());
    }
    private void jarBlock(Block block){
        String name = ForgeRegistries.BLOCKS.getKey(block).getPath();
        withExistingParent(name, AquamiraeDelightID("block/base/jar")).texture("particle", AquamiraeID("block/jar")).texture("jar", AquamiraeID("block/jar"));
    }
}
