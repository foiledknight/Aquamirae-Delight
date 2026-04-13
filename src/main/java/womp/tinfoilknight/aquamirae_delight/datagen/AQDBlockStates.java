package womp.tinfoilknight.aquamirae_delight.datagen;

import com.obscuria.aquamirae.Aquamirae;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.blocks.BreadFoodBlock;
import womp.tinfoilknight.aquamirae_delight.blocks.FunctionalJarBlock;

public class AQDBlockStates extends BlockStateProvider {
    public AQDBlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AquamiraeDelight.MODID, existingFileHelper);
    }

    private String blockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    public ResourceLocation resourceBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, "block/" + path);
    }
    public ResourceLocation resourceAquamiraeBlock(String path) {
        return ResourceLocation.fromNamespaceAndPath(Aquamirae.MODID, "block/" + path);
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(this.resourceBlock(path), this.models().existingFileHelper);
    }
    public ModelFile existingAquamiraeModel(String path) {
        return new ModelFile.ExistingModelFile(this.resourceAquamiraeBlock(path), this.models().existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.pieBlock((PieBlock) AquamiraeDelight.DEEPSEA_PIE.get());
        this.feastBlock((FeastBlock) AquamiraeDelight.AQUATIC_FEAST.get());
        this.breadBlock((BreadFoodBlock) AquamiraeDelight.FISHERMANS_DELICACY.get());
        jarBlock(AquamiraeDelight.JAR.get());
        jarBlock(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR.get());
        jarBlock(AquamiraeDelight.FUNCTIONAL_JAR.get());
    }
    public void jarBlock(Block block){
        ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
        String fileName = this.blockName(block);
        getVariantBuilder(block).partialState().setModels(builder.modelFile(this.existingModel(fileName)).build());
    }
    public void filledJarBlock(FunctionalJarBlock block){
        getVariantBuilder(block).forAllStates(state -> {
            IntegerProperty fillState = block.getContentsQuantity();
            int quantity = state.getValue(fillState);
            String suffix = "_" + quantity;

            ConfiguredModel.Builder<?> builder = ConfiguredModel.builder();
            String blockName = this.blockName(block);
            String fileName = blockName + suffix;
            return builder.modelFile(this.existingModel(fileName)).build();
        });
    }
    public void feastBlock(FeastBlock block) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            IntegerProperty servingsProperty = block.getServingsProperty();
            int servings = (Integer)state.getValue(servingsProperty);
            String suffix = "_stage" + (block.getMaxServings() - servings);
            if (servings == 0) {
                suffix = block.hasLeftovers ? "_leftover" : "_stage" + (servingsProperty.getPossibleValues().toArray().length - 2);
            }

            ConfiguredModel.Builder var10000 = ConfiguredModel.builder();
            String var10002 = this.blockName(block);
            return var10000.modelFile(this.existingModel(var10002 + suffix)).rotationY(((int)((Direction)state.getValue(FeastBlock.FACING)).toYRot() + 180) % 360).build();
        });
    }
    public void pieBlock(Block block) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            int bites = (Integer)state.getValue(PieBlock.BITES);
            String suffix = bites > 0 ? "_slice" + bites : "";
            ConfiguredModel.Builder var10000 = ConfiguredModel.builder();
            String var10002 = this.blockName(block);
            return var10000.modelFile(this.existingModel(var10002 + suffix)).rotationY(((int)((Direction)state.getValue(PieBlock.FACING)).toYRot() + 180) % 360).build();
        });
    }
    public void breadBlock(Block block) {
        this.getVariantBuilder(block).forAllStates((state) -> {
            int bites = (Integer)state.getValue(BreadFoodBlock.BITES);
            String suffix = bites > 0 ? "_slice" + bites : "";
            ConfiguredModel.Builder var10000 = ConfiguredModel.builder();
            String var10002 = this.blockName(block);
            return var10000.modelFile(this.existingModel(var10002 + suffix)).rotationY(((int)((Direction)state.getValue(BreadFoodBlock.FACING)).toYRot() + 180) % 360).build();
        });
    }
}
