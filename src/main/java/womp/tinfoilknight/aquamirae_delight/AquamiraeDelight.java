package womp.tinfoilknight.aquamirae_delight;

import com.mojang.logging.LogUtils;
import com.obscuria.aquamirae.Aquamirae;
import com.obscuria.aquamirae.common.blocks.JarBlock;
import com.obscuria.obscureapi.util.ItemUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.item.KelpRollItem;
import vectorwing.farmersdelight.common.registry.ModEffects;
import womp.tinfoilknight.aquamirae_delight.blocks.*;
import womp.tinfoilknight.aquamirae_delight.effects.SpeedDecreaseMobEffect;
import womp.tinfoilknight.aquamirae_delight.entity.GoldenMothAnimal;
import womp.tinfoilknight.aquamirae_delight.items.FinFilleter;
import womp.tinfoilknight.aquamirae_delight.items.RemnantsKnife;
import womp.tinfoilknight.aquamirae_delight.items.Separator;
import womp.tinfoilknight.aquamirae_delight.tag.ExistingTag;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.common.registry.ModItems.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AquamiraeDelight.MODID)
public class AquamiraeDelight {
    public static final String MODID = "aquamirae_delight";
    public static final String AQID = Aquamirae.MODID;
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final int TINY_DURATION = 100; //5 seconds
    public static final int BRIEF_DURATION = 600; //30 seconds
    public static final int SHORT_DURATION = 1200; //1 minute
    public static final int MIDSIZE_DURATION = 2400; //2 minutes
    public static final int MEDIUM_DURATION = 3600; //3 minutes
    public static final int LONG_DURATION = 6000; //5 minutes
    public static final int EXTREME_DURATION = 9600; //8 minutes

    private static final Supplier<? extends EntityType<GoldenMothAnimal>> goldenMothEntity = () -> EntityType.Builder.<GoldenMothAnimal>of(GoldenMothAnimal::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(GoldenMothAnimal::new).fireImmune().sized(0.5F, 0.2F).build("golden_moth");
    public static final Item.Properties common = new Item.Properties().rarity(Rarity.COMMON).stacksTo(64);
    public static final FoodProperties spinefish_slice = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.2F).meat().build();
    public static final FoodProperties cooked_spinefish_slice = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F).meat().build();
    public static final FoodProperties spinefish_roll = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).build();
    public static final FoodProperties golden_puree = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.3F).build();
    public static final FoodProperties oxygelium_bulb = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).effect(() -> new MobEffectInstance((MobEffect)MobEffects.WATER_BREATHING, 100, 0), 1.0F).build();
    public static final FoodProperties glazed_grilled_spinefish = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.75F).effect(() -> new MobEffectInstance((MobEffect) ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F) .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0F) .build();
    public static final FoodProperties golden_puree_pasta = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.75F).effect(() -> new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 6000, 0), 1.0F) .build();
    public static final FoodProperties abyssal_risotto = (new FoodProperties.Builder()).nutrition(16).saturationMod(0.75F).effect(() -> new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 9600, 0), 1.0F) .build();
    public static final FoodProperties spinefish_alfredo = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.80F).effect(() -> new MobEffectInstance((MobEffect)ModEffects.NOURISHMENT.get(), 3600, 0), 1.0F) .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0), 1.0F) .build();
    public static final FoodProperties escagelium_soup = (new FoodProperties.Builder()).nutrition(14).saturationMod(0.75F).effect(() -> new MobEffectInstance((MobEffect) ModEffects.COMFORT.get(), 2400, 0), 1.0F) .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, 2400, 0), 1.0F) .build();
    public static final FoodProperties anglers_soup = (new FoodProperties.Builder()).nutrition(16).saturationMod(0.65F).effect(() -> new MobEffectInstance((MobEffect) ModEffects.COMFORT.get(), 9600, 0), 1.0F) .build();
    public static final FoodProperties BREAD_SLICE = (new FoodProperties.Builder()).nutrition(2).saturationMod(1.2F).effect(() -> new MobEffectInstance((MobEffect) ModEffects.COMFORT.get(), 1200, 0), 1.0F) .build();
    public static final FoodProperties angled_kebab = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.9F).effect(() -> new MobEffectInstance((MobEffect) MobEffects.WATER_BREATHING, 600, 0), 1.0F) .build();

    public static final VoxelShape CAKE_SHAPE = Block.box((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)4.0F, (double)14.0F);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);

    public static Item.Properties drinkableFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }
    public static final RegistryObject<SimpleParticleType> SHINE_HEART = PARTICLES.register("shine_heart", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SHINE_GLINT = PARTICLES.register("shine_glint", () -> new SimpleParticleType(true));

    public static final RegistryObject<EntityType<GoldenMothAnimal>> GOLDEN_MOTH = ENTITY_TYPES.register("golden_moth", goldenMothEntity);


    public static final RegistryObject<MobEffect> SPEED_DECREASE = EFFECTS.register("speed_decrease", SpeedDecreaseMobEffect::new);

    public static final RegistryObject<Item> SEPARATOR = ITEMS.register("separator", Separator::new);
    public static final RegistryObject<Item> FIN_FILLETER = ITEMS.register("fin_filleter", FinFilleter::new);
    public static final RegistryObject<Item> REMNANTS_KNIFE = ITEMS.register("remnants_knife", RemnantsKnife::new);
    public static final RegistryObject<Item> SPINEFISH_SLICE = ITEMS.register("spinefish_slice", () -> new Item(foodItem(spinefish_slice)));
    public static final RegistryObject<Item> COOKED_SPINEFISH_SLICE = ITEMS.register("cooked_spinefish_slice", () -> new Item(foodItem(cooked_spinefish_slice)));
    public static final RegistryObject<Item> WISTERIA_LEAVES = ITEMS.register("wisteria_leaves", () -> new Item(common));
    public static final RegistryObject<Item> GROUND_WISTERIA = ITEMS.register("ground_wisteria", () -> new Item(common));
    public static final RegistryObject<Item> GOLDEN_PUREE = ITEMS.register("golden_puree", () -> new DrinkableItem(drinkableFoodItem(golden_puree)));
    public static final RegistryObject<Item> OXYGELIUM_BULB = ITEMS.register("oxygelium_bulbs", () -> new Item(foodItem(oxygelium_bulb)));

    public static final RegistryObject<Item> SPINEFISH_ROLL = ITEMS.register("spinefish_roll", () -> new Item(foodItem(spinefish_roll)));
    public static final RegistryObject<Item> SPINEFISH_KELP_ROLL = ITEMS.register("spinefish_kelp_roll", () -> new KelpRollItem(foodItem(FoodValues.KELP_ROLL)));
    public static final RegistryObject<Item> SPINEFISH_KELP_ROLL_SLICE = ITEMS.register("spinefish_kelp_roll_slice", () -> new Item(foodItem(FoodValues.KELP_ROLL_SLICE)));

    public static final RegistryObject<Block> AQUATIC_FEAST = BLOCKS.register("aquatic_feast", () -> new AquaticFeastBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Item> AQUATIC_FEAST_ITEM = ITEMS.register("aquatic_feast", () -> new BlockItem((Block) AQUATIC_FEAST.get(), basicItem()));

    public static final RegistryObject<Item> DEEPSEA_PIE_SLICE = ITEMS.register("deepsea_pie_slice", () -> new Item(foodItem(FoodValues.PIE_SLICE)));
    public static final RegistryObject<Block> DEEPSEA_PIE = BLOCKS.register("deepsea_pie", () -> new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), DEEPSEA_PIE_SLICE));
    public static final RegistryObject<Item> DEEPSEA_PIE_ITEM = ITEMS.register("deepsea_pie", () -> new BlockItem((Block) DEEPSEA_PIE.get(), basicItem()));
    public static final RegistryObject<Item> FISHERMANS_DELICACY_SLICE = ITEMS.register("fishermans_delicacy_slice", () -> new Item(foodItem(BREAD_SLICE)));
    public static final RegistryObject<Block> FISHERMANS_DELICACY = BLOCKS.register("fishermans_delicacy", () -> new BreadFoodBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), FISHERMANS_DELICACY_SLICE));
    public static final RegistryObject<Item> FISHERMANS_DELICACY_ITEM = ITEMS.register("fishermans_delicacy", () -> new BlockItem((Block) FISHERMANS_DELICACY.get(), basicItem()));
    public static final RegistryObject<Item> ANGLED_KEBAB = ITEMS.register("angled_kebab", () -> new Item(foodItem(angled_kebab)));

    public static final RegistryObject<Item> GLAZED_GRILLED_SPINEFISH = ITEMS.register("glazed_grilled_spinefish", () -> new Item(foodItem(glazed_grilled_spinefish)));
    public static final RegistryObject<Item> GOLDEN_PUREE_PASTA = ITEMS.register("golden_puree_pasta", () -> new Item(foodItem(golden_puree_pasta)));
    public static final RegistryObject<Item> ABYSSAL_RISOTTO = ITEMS.register("abyssal_risotto", () -> new Item(foodItem(abyssal_risotto)));
    public static final RegistryObject<Item> SPINEFISH_ALFREDO = ITEMS.register("spinefish_alfredo", () -> new Item(foodItem(spinefish_alfredo)));
    public static final RegistryObject<Item> ESCAGELIUM_SOUP = ITEMS.register("escagelium_soup", () -> new ConsumableItem(bowlFoodItem(escagelium_soup)));
    public static final RegistryObject<Item> ANGLERS_SOUP = ITEMS.register("anglers_soup", () -> new ConsumableItem(bowlFoodItem(anglers_soup)));
    public static final RegistryObject<Item> GOLDEN_MOTH_SPAWN_EGG = ITEMS.register("golden_moth_spawn_egg", () -> new ForgeSpawnEggItem(GOLDEN_MOTH, 0xd44e00, 0xffbe33, new Item.Properties().rarity(Rarity.UNCOMMON)));


    public static final RegistryObject<Block> GOLDEN_MOTH_IN_A_JAR = BLOCKS.register("golden_moth_in_a_jar", () -> new DecorationJarBlock(GOLDEN_MOTH, 8));
    public static final RegistryObject<Item> GOLDEN_MOTH_IN_A_JAR_ITEM = ITEMS.register("golden_moth_in_a_jar", () -> new BlockItem((Block) GOLDEN_MOTH_IN_A_JAR.get(), basicItem()));

    public static final RegistryObject<Block> JAR = BLOCKS.register("jar", () -> new DecorationJarBlock(0));
    public static final RegistryObject<Item> JAR_ITEM = ITEMS.register("jar", () -> new BlockItem((Block) JAR.get(), basicItem()));

    public static final RegistryObject<Block> FUNCTIONAL_JAR = BLOCKS.register("functional_jar", FunctionalJarBlock::new);
    public static final RegistryObject<Item> FUNCTIONAL_JAR_ITEM = ITEMS.register("functional_jar", () -> new BlockItem((Block) FUNCTIONAL_JAR.get(), basicItem()));

    public static final RegistryObject<BlockEntityType<FunctionalJarBlockEntity>> JAR_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("jar_block_entity", () -> BlockEntityType.Builder.of(FunctionalJarBlockEntity::new, FUNCTIONAL_JAR.get()).build(null));

    public static final String AQUAMIRAE_DELIGHT_CREATIVE_TAB_KEY = "itemGroup." + MODID;
    public static final RegistryObject<CreativeModeTab> AQUAMIRAE_DELIGHT_CREATIVE_TAB = CREATIVE_TABS.register("example", () -> CreativeModeTab.builder()
            .title(Component.translatable(AQUAMIRAE_DELIGHT_CREATIVE_TAB_KEY))
            .icon(() -> new ItemStack(GLAZED_GRILLED_SPINEFISH.get()))
            .displayItems((params, output) -> {
                output.accept(SEPARATOR.get());
                output.accept(FIN_FILLETER.get());
                output.accept(REMNANTS_KNIFE.get());
                output.accept(SPINEFISH_SLICE.get());
                output.accept(COOKED_SPINEFISH_SLICE.get());
                output.accept(GROUND_WISTERIA.get());
                output.accept(WISTERIA_LEAVES.get());
                output.accept(ANGLED_KEBAB.get());
                output.accept(OXYGELIUM_BULB.get());

                output.accept(SPINEFISH_ROLL.get());
                output.accept(SPINEFISH_KELP_ROLL.get());
                output.accept(SPINEFISH_KELP_ROLL_SLICE.get());
                output.accept(AQUATIC_FEAST_ITEM.get());
                output.accept(DEEPSEA_PIE_ITEM.get());
                output.accept(DEEPSEA_PIE_SLICE.get());
                output.accept(FISHERMANS_DELICACY_ITEM.get());
                output.accept(FISHERMANS_DELICACY_SLICE.get());


                output.accept(GLAZED_GRILLED_SPINEFISH.get());
                output.accept(GOLDEN_PUREE_PASTA.get());
                output.accept(ABYSSAL_RISOTTO.get());
                output.accept(SPINEFISH_ALFREDO.get());
                output.accept(ESCAGELIUM_SOUP.get());
                output.accept(ANGLERS_SOUP.get());

                output.accept(JAR.get());
                output.accept(GOLDEN_PUREE.get());
                output.accept(GOLDEN_MOTH_IN_A_JAR_ITEM.get());
                output.accept(GOLDEN_MOTH_SPAWN_EGG.get());
            })
            .build()
    );

    public AquamiraeDelight(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        EFFECTS.register(modEventBus);
        CREATIVE_TABS.register(modEventBus);
        PARTICLES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::inWater);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityAttacked);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityHurt);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityKilled);
        MinecraftForge.EVENT_BUS.addListener(this::replaceJar);
        modEventBus.addListener(this::registerAttributes);
        modEventBus.addListener(this::commonSetup);
    }

    @SubscribeEvent
    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put((ItemLike) GROUND_WISTERIA.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) WISTERIA_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) SPINEFISH_KELP_ROLL_SLICE.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) SPINEFISH_KELP_ROLL.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) FISHERMANS_DELICACY_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) DEEPSEA_PIE_SLICE.get(), 0.85F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) FISHERMANS_DELICACY_ITEM.get(), 1.0F);
        ComposterBlock.COMPOSTABLES.put((ItemLike) DEEPSEA_PIE.get(), 1.0F);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        ItemUtils.addLore("aquamirae_delight:golden_moth_in_a_jar");
    }

    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GOLDEN_MOTH.get(), GoldenMothAnimal.createAttributes().build());
    }

    private void onEntityHurt(@NotNull LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof LivingEntity source) {
            if (source.isInWater()) {
                Item mainHand = source.getMainHandItem().getItem();
                if (mainHand instanceof RemnantsKnife) {
                    RemnantsKnife item = (RemnantsKnife) mainHand;
                    event.setAmount(event.getAmount() * (1.0F + (float) item.ABILITY.getVariable(source, 1) * 0.01F));
                }
            }
        }
    }
    private void replaceJar(BlockEvent.EntityPlaceEvent event) {
        BlockState state = event.getPlacedBlock();

        if (!state.is(ExistingTag.JAR_BLOCKS)) return;
        if (!(state.getBlock() instanceof JarBlock || state.getBlock() instanceof DecorationJarBlock)) return;
        if ((state.is(JAR.get()))) return;
        if (state.getBlock() instanceof DecorationJarBlock) {
            if (!state.getValue(DecorationJarBlock.REPLACE)) {
                return;
            }
        }
        int light = state.getLightEmission(event.getLevel(), event.getPos());
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        level.setBlock(pos, JAR.get().defaultBlockState().setValue(DecorationJarBlock.LIGHT, light), 2);

    }
    private void inWater(@NotNull LivingEvent.LivingTickEvent event) {
        Entity sourceEntity = event.getEntity();
        if (sourceEntity instanceof LivingEntity source) {
            if (source.isInWater()) {
                Item mainHand = source.getMainHandItem().getItem();
                if (mainHand instanceof RemnantsKnife) {
                    source.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 40, 0));
                }
            }
        }
    }
    private void onEntityAttacked(@NotNull LivingHurtEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof Player source) {
            Item mainHand = source.getMainHandItem().getItem();
            if (mainHand instanceof FinFilleter item) {
                int emptyHunger = (int) Math.floor((double) (((20 - source.getFoodData().getFoodLevel()) / 2.0F)));
                event.setAmount(event.getAmount() + event.getAmount() * Math.min((float) item.ABILITY.getVariable(source, 2) * 0.01F, (float) (emptyHunger * item.ABILITY.getVariable(source, 1)) * 0.01F));
            }
        }
    }
    private void onEntityKilled(@NotNull LivingDeathEvent event) {
        Entity sourceEntity = event.getSource().getEntity();
        if (sourceEntity instanceof Player source) {
            Item mainHand = source.getMainHandItem().getItem();
            if (mainHand instanceof FinFilleter item) {
                if (source.getFoodData().getFoodLevel() < 20) {
                    source.getFoodData().setFoodLevel(1 + source.getFoodData().getFoodLevel());
                }
            }
        }
    }
}
