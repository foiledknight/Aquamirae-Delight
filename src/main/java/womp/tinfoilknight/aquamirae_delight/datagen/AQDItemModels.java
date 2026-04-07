package womp.tinfoilknight.aquamirae_delight.datagen;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class AQDItemModels extends ItemModelProvider {


    public AQDItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AquamiraeDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleTool(AquamiraeDelight.SEPARATOR);
        simpleTool(AquamiraeDelight.FIN_FILLETER);
        simpleTool(AquamiraeDelight.REMNANTS_KNIFE);

        simpleItem(AquamiraeDelight.SPINEFISH_SLICE);
        simpleItem(AquamiraeDelight.COOKED_SPINEFISH_SLICE);
        simpleItem(AquamiraeDelight.WISTERIA_LEAVES);
        simpleItem(AquamiraeDelight.GROUND_WISTERIA);
        simpleItem(AquamiraeDelight.GOLDEN_PUREE);
        simpleItem(AquamiraeDelight.OXYGELIUM_BULB);

        simpleItem(AquamiraeDelight.SPINEFISH_ROLL);
        simpleItem(AquamiraeDelight.SPINEFISH_KELP_ROLL);
        simpleItem(AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE);
        simpleItem(AquamiraeDelight.DEEPSEA_PIE_SLICE);
        simpleItem(AquamiraeDelight.DEEPSEA_PIE_ITEM);
        simpleItem(AquamiraeDelight.FISHERMANS_DELICACY_ITEM);
        simpleItem(AquamiraeDelight.FISHERMANS_DELICACY_SLICE);
        simpleItem(AquamiraeDelight.ANGLED_KEBAB);
        simpleItem(AquamiraeDelight.AQUATIC_FEAST_ITEM);

        simpleItem(AquamiraeDelight.GLAZED_GRILLED_SPINEFISH);
        simpleItem(AquamiraeDelight.GOLDEN_PUREE_PASTA);
        simpleItem(AquamiraeDelight.ABYSSAL_RISOTTO);
        simpleItem(AquamiraeDelight.SPINEFISH_ALFREDO);
        simpleItem(AquamiraeDelight.ESCAGELIUM_SOUP);
        simpleItem(AquamiraeDelight.ANGLERS_SOUP);

        spawnEgg(AquamiraeDelight.GOLDEN_MOTH_SPAWN_EGG);
        simpleItem(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR_BLOCK_ITEM);
    }

    private void simpleTool(RegistryObject<Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + name));
    }

    private void simpleItem(RegistryObject<Item> item) {
        String name = item.getId().getPath();
        withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    private void spawnEgg(RegistryObject<Item> item){
        String name = item.getId().getPath();
        withExistingParent(name, mcLoc("item/template_spawn_egg"));
    }
}