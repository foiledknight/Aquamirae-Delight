package womp.tinfoilknight.aquamirae_delight.datagen;

import com.obscuria.aquamirae.registry.AquamiraeItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;
import womp.tinfoilknight.aquamirae_delight.tag.InForgeTags;

import java.util.function.Consumer;

public class AQDRecipes extends RecipeProvider {
    public AQDRecipes(PackOutput output) {
        super(output);
    }

    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        craftingRecipes(consumer);
        smeltingRecipes(consumer);
        cuttingRecipes(consumer);
        cookingRecipes(consumer);
    }

    public static void cuttingRecipes(Consumer<FinishedRecipe> consumer) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{AquamiraeItems.SPINEFISH.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.SPINEFISH_SLICE.get(), 2).addResult(AquamiraeItems.SHARP_BONES.get()).build(consumer, rk("spinefish"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{AquamiraeItems.COOKED_SPINEFISH.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.COOKED_SPINEFISH_SLICE.get(), 2).addResult(AquamiraeItems.SHARP_BONES.get()).build(consumer, rk("cooked_spinefish"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{(ItemLike) AquamiraeDelight.DEEPSEA_PIE.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.DEEPSEA_PIE_SLICE.get(), 4).build(consumer, rk("deepsea_pie"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{(ItemLike) AquamiraeDelight.FISHERMANS_DELICACY.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.FISHERMANS_DELICACY_SLICE.get(), 6).build(consumer, rk("fishermans_delicacy"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{AquamiraeItems.OXYGELIUM.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.OXYGELIUM_BULB.get(), 2).addResult(AquamiraeItems.ELODEA.get()).build(consumer, rk("oxygelium"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{AquamiraeItems.WISTERIA_NIVEIS.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), AquamiraeDelight.WISTERIA_LEAVES.get()).addResult(AquamiraeDelight.GROUND_WISTERIA.get(), 2).addResult(Items.SNOWBALL).build(consumer, rk("wisteria_niveis"));
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(new ItemLike[]{AquamiraeDelight.SPINEFISH_KELP_ROLL.get()}), Ingredient.of(ForgeTags.TOOLS_KNIVES), (ItemLike) AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE.get(), 3).build(consumer, rk("spinefish_kelp_roll"));
    }

    public static void cookingRecipes(Consumer<FinishedRecipe> consumer) {
        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeDelight.ABYSSAL_RISOTTO.get(), 1, 200, 1.0F).addIngredient(AquamiraeItems.ABYSSAL_AMETHYST.get()).addIngredient(InForgeTags.RAW_FISHES_SPINEFISH).addIngredient(AquamiraeDelight.WISTERIA_LEAVES.get()).addIngredient(ForgeTags.CROPS_ONION).addIngredient(ForgeTags.CROPS_RICE).unlockedByAnyIngredient(new ItemLike[]{ModItems.RICE.get(), ModItems.ONION.get(), AquamiraeItems.SPINEFISH.get(), AquamiraeDelight.SPINEFISH_SLICE.get(), AquamiraeItems.ABYSSAL_AMETHYST.get(), AquamiraeDelight.WISTERIA_LEAVES.get()}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, rc("abyssal_risotto"));
        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeDelight.ANGLERS_SOUP.get(), 1, 200, 1.0F).addIngredient(AquamiraeItems.ANGLERS_FANG.get()).addIngredient(InForgeTags.COOKED_FISHES_SPINEFISH).addIngredient(ForgeTags.CROPS_TOMATO).addIngredient(ForgeTags.CROPS_ONION).addIngredient(ForgeTags.CROPS_RICE).unlockedByAnyIngredient(new ItemLike[]{AquamiraeItems.ANGLERS_FANG.get(), AquamiraeItems.COOKED_SPINEFISH.get(), AquamiraeDelight.COOKED_SPINEFISH_SLICE.get(), ModItems.TOMATO.get(), ModItems.ONION.get(), ModItems.RICE.get()}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, rc("anglers_soup"));
        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeDelight.ESCAGELIUM_SOUP.get(), 1, 200, 1.0F).addIngredient(AquamiraeItems.ESCA.get()).addIngredient(AquamiraeDelight.OXYGELIUM_BULB.get()).addIngredient(AquamiraeItems.FIN.get()).addIngredient(AquamiraeDelight.WISTERIA_LEAVES.get()).unlockedByAnyIngredient(new ItemLike[]{AquamiraeItems.ESCA.get(), AquamiraeItems.FIN.get(), AquamiraeDelight.OXYGELIUM_BULB.get(), AquamiraeDelight.WISTERIA_LEAVES.get()}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, rc("escagelium_soup"));
        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeDelight.GOLDEN_PUREE_PASTA.get(), 1, 200, 1.0F).addIngredient(AquamiraeDelight.GOLDEN_PUREE.get()).addIngredient(ForgeTags.PASTA).addIngredient(AquamiraeDelight.GROUND_WISTERIA.get()).unlockedByAnyIngredient(new ItemLike[]{AquamiraeDelight.GOLDEN_PUREE.get(), ModItems.RAW_PASTA.get(), AquamiraeDelight.GROUND_WISTERIA.get()}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, r("golden_puree_pasta"));
        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeDelight.SPINEFISH_ALFREDO.get(), 1, 200, 1.0F).addIngredient(InForgeTags.RAW_FISHES_SPINEFISH).addIngredient(ForgeTags.PASTA).addIngredient(ForgeTags.MILK).addIngredient(AquamiraeDelight.OXYGELIUM_BULB.get()).unlockedByAnyIngredient(new ItemLike[]{AquamiraeItems.SPINEFISH.get(), AquamiraeDelight.SPINEFISH_SLICE.get(), ModItems.RAW_PASTA.get(), Items.MILK_BUCKET, ModItems.MILK_BOTTLE.get(), AquamiraeDelight.OXYGELIUM_BULB.get()}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, rc("spinefish_alfredo"));

        CookingPotRecipeBuilder.cookingPotRecipe(AquamiraeItems.SEA_STEW.get(), 1, 200, 1.0F).addIngredient(Items.BAKED_POTATO).addIngredient(AquamiraeItems.ESCA.get()).addIngredient(InForgeTags.COOKED_FISHES_SPINEFISH).addIngredient(AquamiraeItems.OXYGELIUM.get()).addIngredient(AquamiraeItems.FIN.get()).addIngredient(Items.PUFFERFISH).unlockedByAnyIngredient(new ItemLike[]{Items.BAKED_POTATO, AquamiraeItems.ESCA.get(), AquamiraeItems.COOKED_SPINEFISH.get(), AquamiraeDelight.COOKED_SPINEFISH_SLICE.get(), AquamiraeItems.OXYGELIUM.get(), AquamiraeItems.FIN.get(), Items.PUFFERFISH}).setRecipeBookTab(CookingPotRecipeBookTab.MEALS).build(consumer, rc("sea_stew"));
    }

    public void craftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AquamiraeDelight.FIN_FILLETER.get(), 1).pattern("AB").pattern("CD").define('A', AquamiraeItems.SHIP_GRAVEYARD_ECHO.get()).define('B', Items.DIAMOND).define('C', Items.STICK).define('D', AquamiraeItems.FIN.get()).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.FIN.get(), Items.DIAMOND, Items.STICK, AquamiraeItems.SHIP_GRAVEYARD_ECHO.get()})).save(consumer, r("fin_filleter"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AquamiraeDelight.REMNANTS_KNIFE.get(), 1).pattern("AB").pattern("C ").define('A', Items.LEATHER).define('B', AquamiraeItems.SHARP_BONES.get()).define('C', Items.STICK).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.SHARP_BONES.get(), Items.LEATHER, Items.STICK})).save(consumer, r("remnants_knife"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, AquamiraeDelight.SEPARATOR.get(), 1).pattern("  A").pattern("BC ").pattern("DB ").define('A', AquamiraeItems.SHIP_GRAVEYARD_ECHO.get()).define('B', AquamiraeItems.ABYSSAL_AMETHYST.get()).define('C', Items.NETHERITE_INGOT).define('D', Items.STICK).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.ABYSSAL_AMETHYST.get(), AquamiraeItems.SHIP_GRAVEYARD_ECHO.get(), Items.NETHERITE_INGOT, Items.STICK})).save(consumer, r("separator"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AquamiraeDelight.ANGLED_KEBAB.get(), 1).requires(InForgeTags.RAW_FISHES_SPINEFISH).requires(AquamiraeDelight.OXYGELIUM_BULB.get()).requires(AquamiraeItems.ANGLERS_FANG.get()).requires(InForgeTags.RAW_FISHES_SPINEFISH).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeDelight.OXYGELIUM_BULB.get(), AquamiraeItems.ANGLERS_FANG.get(), AquamiraeItems.SPINEFISH.get()})).save(consumer, r("angled_kebab"));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AquamiraeDelight.AQUATIC_FEAST.get(), 1).pattern("AAA").pattern("BCB").pattern("CDC").define('A', AquamiraeDelight.SPINEFISH_ROLL.get()).define('B', AquamiraeDelight.FISHERMANS_DELICACY_SLICE.get()).define('C', AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE.get()).define('D', Items.BOWL).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeDelight.SPINEFISH_ROLL.get(), AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE.get(), AquamiraeDelight.FISHERMANS_DELICACY_SLICE.get()})).save(consumer, r("aquatic_feast"));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AquamiraeDelight.DEEPSEA_PIE.get(), 1).pattern("ABA").pattern("CDC").pattern("DED").define('A', AquamiraeDelight.GROUND_WISTERIA.get()).define('B', AquamiraeItems.FIN.get()).define('C', AquamiraeDelight.GOLDEN_PUREE.get()).define('D', InForgeTags.COOKED_FISHES_SPINEFISH).define('E', ModItems.PIE_CRUST.get()).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeDelight.GOLDEN_PUREE.get(), AquamiraeItems.FIN.get(), AquamiraeItems.COOKED_SPINEFISH.get(), ModItems.PIE_CRUST.get(), AquamiraeDelight.GROUND_WISTERIA.get(), AquamiraeDelight.COOKED_SPINEFISH_SLICE.get()})).group("aqd_deepsea_pie").save(consumer, r("deepsea_pie"));
        fromSlices4(AquamiraeDelight.DEEPSEA_PIE, AquamiraeDelight.DEEPSEA_PIE_SLICE, consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AquamiraeDelight.FISHERMANS_DELICACY.get(), 1).requires(ForgeTags.COOKED_CHICKEN).requires(ForgeTags.CROPS_TOMATO).requires(ForgeTags.CROPS_ONION).requires(InForgeTags.COOKED_FISHES_SPINEFISH).requires(AquamiraeItems.ANGLERS_FANG.get()).requires(InForgeTags.COOKED_FISHES_SPINEFISH).requires(AquamiraeDelight.WISTERIA_LEAVES.get()).requires(Items.BREAD).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.COOKED_SPINEFISH.get(), AquamiraeDelight.WISTERIA_LEAVES.get(), AquamiraeItems.ANGLERS_FANG.get(), ModItems.TOMATO.get(), ModItems.ONION.get(), Items.BREAD})).save(consumer, r("fishermans_delicacy"));
        fromSlices6(AquamiraeDelight.FISHERMANS_DELICACY, AquamiraeDelight.FISHERMANS_DELICACY_SLICE, consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AquamiraeDelight.GLAZED_GRILLED_SPINEFISH.get(), 1).requires(InForgeTags.COOKED_FISHES_SPINEFISH).requires(AquamiraeDelight.OXYGELIUM_BULB.get()).requires(Items.BOWL).requires(AquamiraeDelight.WISTERIA_LEAVES.get()).requires(AquamiraeDelight.GOLDEN_PUREE.get()).requires(ForgeTags.CROPS_ONION).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.COOKED_SPINEFISH.get(), AquamiraeDelight.OXYGELIUM_BULB.get(), AquamiraeDelight.WISTERIA_LEAVES.get(), AquamiraeDelight.GOLDEN_PUREE.get(), ModItems.ONION.get()})).save(consumer, r("glazed_grilled_spinefish"));
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, AquamiraeDelight.SPINEFISH_KELP_ROLL.get(), 1).pattern(" A ").pattern("BCB").pattern("DDD").define('A', ForgeTags.CROPS_CABBAGE).define('B', ModItems.COOKED_RICE.get()).define('C', InForgeTags.COOKED_FISHES_SPINEFISH).define('D', Items.DRIED_KELP).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeItems.COOKED_SPINEFISH.get(), ModItems.COOKED_RICE.get(), Items.DRIED_KELP, ModItems.CABBAGE.get()})).save(consumer, r("spinefish_kelp_roll"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AquamiraeDelight.SPINEFISH_ROLL.get(), 1).requires(AquamiraeDelight.SPINEFISH_SLICE.get()).requires(AquamiraeDelight.SPINEFISH_SLICE.get()).requires(ModItems.COOKED_RICE.get()).unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{AquamiraeDelight.SPINEFISH_SLICE.get(), ModItems.COOKED_RICE.get()})).save(consumer, r("spinefish_roll"));
    }

    public static void smeltingRecipes(Consumer<FinishedRecipe> consumer) {
        foodSmeltingRecipes ("spinefish_slice", AquamiraeDelight.SPINEFISH_SLICE.get(), AquamiraeDelight.COOKED_SPINEFISH_SLICE.get(), 0.35F, consumer);
        foodSmeltingRecipes("golden_puree", InForgeTags.GOLDEN_MOTH_IN_A_JAR, AquamiraeDelight.GOLDEN_PUREE.get(), 0.35F, consumer);
    }


    private static void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = (ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, name)).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemLike[]{ingredient}), RecipeCategory.FOOD, result, experience, 200).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ingredient})).save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(new ItemLike[]{ingredient}), RecipeCategory.FOOD, result, experience, 600).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ingredient})).save(consumer, namePrefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(new ItemLike[]{ingredient}), RecipeCategory.FOOD, result, experience, 100).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{ingredient})).save(consumer, namePrefix + "_from_smoking");
    }
    private static void foodSmeltingRecipes(String name, TagKey<Item> ingredient, ItemLike result, float experience, Consumer<FinishedRecipe> consumer) {
        String namePrefix = (ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, name)).toString();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ingredient).build())).save(consumer);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ingredient).build())).save(consumer, namePrefix + "_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100).unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ingredient).build())).save(consumer, namePrefix + "_from_smoking");
    }
    private static void fromSlices4(RegistryObject<Block> whole, RegistryObject<Item> slice, Consumer<FinishedRecipe> consumer) {
        String whole_string = whole.getId().getPath();
        String slice_string = slice.getId().getPath();
        ItemLike whole_item = whole.get();
        ItemLike slice_item = slice.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, whole_item, 1).pattern("SS").pattern("SS").define('S', slice_item).unlockedBy(("has_" + slice_string), InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{(ItemLike)slice_item})).group("aqd_" + whole_string).save(consumer, r (whole_string + "_from_slices"));
    }
    private static void fromSlices6(RegistryObject<Block> whole, RegistryObject<Item> slice, Consumer<FinishedRecipe> consumer) {
        String whole_string = whole.getId().getPath();
        String slice_string = slice.getId().getPath();
        ItemLike whole_item = whole.get();
        ItemLike slice_item = slice.get();
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, whole_item, 1).pattern("SSS").pattern("SSS").define('S', slice_item).unlockedBy(("has_" + slice_string), InventoryChangeTrigger.TriggerInstance.hasItems(new ItemLike[]{(ItemLike)slice_item})).group("aqd_" + whole_string).save(consumer, r (whole_string + "_from_slices"));
    }
    public static ResourceLocation r(String name) {
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, name);
    }

    public static ResourceLocation rc(String name) {
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, "cooking/" + name);
    }

    public static ResourceLocation rk(String name) {
        return ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, "cutting/" + name);
    }

}
