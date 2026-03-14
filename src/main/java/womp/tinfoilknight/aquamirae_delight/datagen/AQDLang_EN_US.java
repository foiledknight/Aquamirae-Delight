package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class AQDLang_EN_US extends LanguageProvider {
    public AQDLang_EN_US(PackOutput output, String locale) {
        super(output, AquamiraeDelight.MODID, locale);
    }

    protected void addTranslations() {
        add("creative_tab.aquamirae_delight", "Aquamirae Delight");
        addItem(AquamiraeDelight.SEPARATOR, "Separator");
        addItem(AquamiraeDelight.FIN_FILLETER, "Fin Filleter");
        addItem(AquamiraeDelight.REMNANTS_KNIFE, "Remnants Knife");
        addItem(AquamiraeDelight.SPINEFISH_SLICE, "Spinefish Slice");
        addItem(AquamiraeDelight.COOKED_SPINEFISH_SLICE, "Cooked Spinefish Slice");
        addItem(AquamiraeDelight.WISTERIA_LEAVES, "Wisteria Leaves");
        addItem(AquamiraeDelight.GROUND_WISTERIA, "Ground Wisteria");
        addItem(AquamiraeDelight.GOLDEN_PUREE, "Golden Puree");
        addItem(AquamiraeDelight.OXYGELIUM_BULB, "Oxygelium Bulb");

        addItem(AquamiraeDelight.SPINEFISH_ROLL, "Spinefish Roll");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL, "Spinefish Kelp Roll");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE, "Spinefish Kelp Roll Slice");
        addBlock(AquamiraeDelight.DEEPSEA_PIE, "Deepsea Pie");
        addItem(AquamiraeDelight.DEEPSEA_PIE_SLICE, "Deepsea Pie Slice");
        addBlock(AquamiraeDelight.FISHERMANS_DELICACY, "Fisherman's Delicacy");
        addItem(AquamiraeDelight.FISHERMANS_DELICACY_SLICE, "Fisherman's Delicacy Slice");
        addItem(AquamiraeDelight.ANGLED_KEBAB, "Angled Kebab");
        addBlock(AquamiraeDelight.AQUATIC_FEAST, "Aquatic Feast");

        addItem(AquamiraeDelight.GLAZED_GRILLED_SPINEFISH, "Glazed Grilled Spinefish");
        addItem(AquamiraeDelight.GOLDEN_PUREE_PASTA, "Golden Puree Pasta");
        addItem(AquamiraeDelight.ABYSSAL_RISOTTO, "Abyssal Risotto");
        addItem(AquamiraeDelight.SPINEFISH_ALFREDO, "Spinefish Alfredo");
        addItem(AquamiraeDelight.ESCAGELIUM_SOUP, "Escagelium Soup");
        addItem(AquamiraeDelight.ANGLERS_SOUP, "Anglers Soup");

        addEffect(AquamiraeDelight.SPEED_DECREASE, "Speed Decrease");

        addEntityType(AquamiraeDelight.GOLDEN_MOTH, "Golden Moth");

        add("class.aquamirae.sea_wolf_tool", "Sea Wolf Tool");
        add("ability.aquamirae_delight.separator", "Enervation > Reduces the target's speed by 10% for {#1}. Stacks up to 5 times");
        add("ability.aquamirae_delight.fin_filleter", "Ravenous > For each empty hunger shank, you deal {#1} more damage (max {#2}). Killing a mob restores half a hunger shank.");
        add("ability.aquamirae_delight.remnants_knife", "Swift Current > Gain Dolphin's Grace and deal {#1} more damage while in water");

    }
}
