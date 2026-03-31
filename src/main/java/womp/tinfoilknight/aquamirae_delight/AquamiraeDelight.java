package womp.tinfoilknight.aquamirae_delight;

import com.mojang.logging.LogUtils;
import com.obscuria.aquamirae.registry.AquamiraeItems;
import com.obscuria.aquamirae.registry.AquamiraeEntities;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
import womp.tinfoilknight.aquamirae_delight.blocks.AquaticFeastBlock;
import womp.tinfoilknight.aquamirae_delight.blocks.BreadFoodBlock;
import womp.tinfoilknight.aquamirae_delight.effects.SpeedDecreaseMobEffect;
import womp.tinfoilknight.aquamirae_delight.entities.GoldenMothAnimal;
import womp.tinfoilknight.aquamirae_delight.items.FinFilleter;
import womp.tinfoilknight.aquamirae_delight.items.RemnantsKnife;
import womp.tinfoilknight.aquamirae_delight.items.Separator;
import womp.tinfoilknight.aquamirae_delight.particle.ShineHeartParticle;

import static vectorwing.farmersdelight.common.registry.ModItems.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AquamiraeDelight.MODID)
public class AquamiraeDelight {
    public static final String MODID = "aquamirae_delight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final int TINY_DURATION = 100; //5 seconds
    public static final int BRIEF_DURATION = 600; //30 seconds
    public static final int SHORT_DURATION = 1200; //1 minute
    public static final int MIDSIZE_DURATION = 2400; //2 minutes
    public static final int MEDIUM_DURATION = 3600; //3 minutes
    public static final int LONG_DURATION = 6000; //5 minutes
    public static final int EXTREME_DURATION = 9600; //8 minutes

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        public @NotNull ItemStack makeIcon() {
            return ((Item) GLAZED_GRILLED_SPINEFISH.get()).getDefaultInstance();
        }
    };

    public static final Item.Properties common = new Item.Properties().rarity(Rarity.COMMON).stacksTo(64).tab(TAB);
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

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static Item.Properties drinkableFoodItem(FoodProperties food) {
        return (new Item.Properties()).food(food).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }
    public static final RegistryObject<SimpleParticleType> SHINE_HEART = PARTICLES.register("shine_heart", () -> new SimpleParticleType(true));

    public static final RegistryObject<EntityType<GoldenMothAnimal>> GOLDEN_MOTH =
            ENTITY_TYPES.register("golden_moth", () -> EntityType.Builder.<GoldenMothAnimal>of(GoldenMothAnimal::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(GoldenMothAnimal::new).fireImmune().sized(0.5F, 0.2F).build("golden_moth"));

    public static final RegistryObject<MobEffect> SPEED_DECREASE = EFFECTS.register("speed_decrease", SpeedDecreaseMobEffect::new);

    public static final RegistryObject<Item> SEPARATOR = ITEMS.register("separator", Separator::new);
    public static final RegistryObject<Item> FIN_FILLETER = ITEMS.register("fin_filleter", FinFilleter::new);
    public static final RegistryObject<Item> REMNANTS_KNIFE = ITEMS.register("remnants_knife", RemnantsKnife::new);
    public static final RegistryObject<Item> SPINEFISH_SLICE = ITEMS.register("spinefish_slice", () -> new Item(foodItem(spinefish_slice).tab(TAB)));
    public static final RegistryObject<Item> COOKED_SPINEFISH_SLICE = ITEMS.register("cooked_spinefish_slice", () -> new Item(foodItem(cooked_spinefish_slice).tab(TAB)));
    public static final RegistryObject<Item> WISTERIA_LEAVES = ITEMS.register("wisteria_leaves", () -> new Item(common));
    public static final RegistryObject<Item> GROUND_WISTERIA = ITEMS.register("ground_wisteria", () -> new Item(common));
    public static final RegistryObject<Item> GOLDEN_PUREE = ITEMS.register("golden_puree", () -> new DrinkableItem(drinkableFoodItem(golden_puree).tab(TAB)));
    public static final RegistryObject<Item> OXYGELIUM_BULB = ITEMS.register("oxygelium_bulbs", () -> new Item(foodItem(oxygelium_bulb).tab(TAB)));

    public static final RegistryObject<Item> SPINEFISH_ROLL = ITEMS.register("spinefish_roll", () -> new Item(foodItem(spinefish_roll).tab(TAB)));
    public static final RegistryObject<Item> SPINEFISH_KELP_ROLL = ITEMS.register("spinefish_kelp_roll", () -> new KelpRollItem(foodItem(FoodValues.KELP_ROLL).tab(TAB)));
    public static final RegistryObject<Item> SPINEFISH_KELP_ROLL_SLICE = ITEMS.register("spinefish_kelp_roll_slice", () -> new Item(foodItem(FoodValues.KELP_ROLL_SLICE).tab(TAB)));

    public static final RegistryObject<Block> AQUATIC_FEAST = BLOCKS.register("aquatic_feast", () -> new AquaticFeastBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));
    public static final RegistryObject<Item> AQUATIC_FEAST_ITEM = ITEMS.register("aquatic_feast", () -> new BlockItem((Block) AQUATIC_FEAST.get(), basicItem().tab(TAB)));

    public static final RegistryObject<Item> DEEPSEA_PIE_SLICE = ITEMS.register("deepsea_pie_slice", () -> new Item(foodItem(FoodValues.PIE_SLICE).tab(TAB)));
    public static final RegistryObject<Block> DEEPSEA_PIE = BLOCKS.register("deepsea_pie", () -> new PieBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), DEEPSEA_PIE_SLICE));
    public static final RegistryObject<Item> DEEPSEA_PIE_ITEM = ITEMS.register("deepsea_pie", () -> new BlockItem((Block) DEEPSEA_PIE.get(), basicItem().tab(TAB)));
    public static final RegistryObject<Item> FISHERMANS_DELICACY_SLICE = ITEMS.register("fishermans_delicacy_slice", () -> new Item(foodItem(BREAD_SLICE).tab(TAB)));
    public static final RegistryObject<Block> FISHERMANS_DELICACY = BLOCKS.register("fishermans_delicacy", () -> new BreadFoodBlock(BlockBehaviour.Properties.copy(Blocks.CAKE), FISHERMANS_DELICACY_SLICE));
    public static final RegistryObject<Item> FISHERMANS_DELICACY_ITEM = ITEMS.register("fishermans_delicacy", () -> new BlockItem((Block) FISHERMANS_DELICACY.get(), basicItem().tab(TAB)));
    public static final RegistryObject<Item> ANGLED_KEBAB = ITEMS.register("angled_kebab", () -> new Item(foodItem(angled_kebab).tab(TAB)));

    public static final RegistryObject<Item> GLAZED_GRILLED_SPINEFISH = ITEMS.register("glazed_grilled_spinefish", () -> new Item(foodItem(glazed_grilled_spinefish).tab(TAB)));
    public static final RegistryObject<Item> GOLDEN_PUREE_PASTA = ITEMS.register("golden_puree_pasta", () -> new Item(foodItem(golden_puree_pasta).tab(TAB)));
    public static final RegistryObject<Item> ABYSSAL_RISOTTO = ITEMS.register("abyssal_risotto", () -> new Item(foodItem(abyssal_risotto).tab(TAB)));
    public static final RegistryObject<Item> SPINEFISH_ALFREDO = ITEMS.register("spinefish_alfredo", () -> new Item(foodItem(spinefish_alfredo).tab(TAB)));
    public static final RegistryObject<Item> ESCAGELIUM_SOUP = ITEMS.register("escagelium_soup", () -> new ConsumableItem(bowlFoodItem(escagelium_soup).tab(TAB)));
    public static final RegistryObject<Item> ANGLERS_SOUP = ITEMS.register("anglers_soup", () -> new ConsumableItem(bowlFoodItem(anglers_soup).tab(TAB)));


    public AquamiraeDelight(FMLJavaModLoadingContext context) {
        var modEventBus = context.getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        EFFECTS.register(modEventBus);
        PARTICLES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::inWater);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityAttacked);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityHurt);
        MinecraftForge.EVENT_BUS.addListener(this::onEntityKilled);
        MinecraftForge.EVENT_BUS.addListener(this::registerSpawns);
        MinecraftForge.EVENT_BUS.addListener(this::registerAttributes);
        MinecraftForge.EVENT_BUS.addListener(this::replaceMoth);
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

    public void replaceMoth(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level level = event.getLevel();
        if (entity.getType() == AquamiraeEntities.GOLDEN_MOTH.get()) {
            event.setCanceled(true);
            if (!level.isClientSide()) {
                Entity newMoth = GOLDEN_MOTH.get().create(level);
                if (newMoth != null) {
                    newMoth.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                    level.addFreshEntity(newMoth);
                }
            }
        }
    }

    public void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(AquamiraeDelight.GOLDEN_MOTH.get(), GoldenMothAnimal.createAttributes().build());
    }

    public void registerSpawns(SpawnPlacementRegisterEvent event) {
        event.register(AquamiraeDelight.GOLDEN_MOTH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, GoldenMothAnimal.getSpawnRules(), SpawnPlacementRegisterEvent.Operation.REPLACE);
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
