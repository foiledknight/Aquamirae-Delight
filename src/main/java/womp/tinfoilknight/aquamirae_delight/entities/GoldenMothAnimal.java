package womp.tinfoilknight.aquamirae_delight.entities;

import com.obscuria.aquamirae.common.entities.ShipGraveyardEntity;
import com.obscuria.aquamirae.registry.AquamiraeBlocks;
import com.obscuria.aquamirae.registry.AquamiraeParticleTypes;
import com.obscuria.aquamirae.registry.AquamiraeSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.EnumSet;

@ShipGraveyardEntity
public class GoldenMothAnimal extends Animal implements FlyingAnimal {
    public static Item FOOD_ITEM = AquamiraeDelight.WISTERIA_LEAVES.get();

    public GoldenMothAnimal(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(AquamiraeDelight.GOLDEN_MOTH.get(), level);
    }

    public GoldenMothAnimal(EntityType<GoldenMothAnimal> type, Level world) {
        super(type, world);
        this.xpReward = 10;
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    /// NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(FOOD_ITEM);
    }

    @Override
    public @Nullable GoldenMothAnimal getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return AquamiraeDelight.GOLDEN_MOTH.get().create(level);
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    /// END END END END END END END END END END

    protected @NotNull PathNavigation createNavigation(@NotNull Level world) {
        return new FlyingPathNavigation(this, world);
    }

    /// Some of these are changed too I'm sure
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(FOOD_ITEM), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0F, 20) {
            protected Vec3 getPosition() {
                RandomSource random = this.mob.getRandom();
                double dir_x = this.mob.getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double dir_y = this.mob.getY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                double dir_z = this.mob.getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
                return new Vec3(dir_x, dir_y, dir_z);
            }
        });
    }

    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
    }

    public SoundEvent getAmbientSound() {
        return (SoundEvent) AquamiraeSounds.ENTITY_GOLDEN_MOTH_AMBIENT.get();
    }

    public SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.GENERIC_HURT;
    }

    public SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_DEATH;
    }

    public boolean causeFallDamage(float l, float d, @NotNull DamageSource source) {
        return false;
    }

    public boolean hurt(DamageSource source, float amount) {
        if (!(source.getDirectEntity() instanceof ThrownPotion) && !(source.getDirectEntity() instanceof AreaEffectCloud)) {
            if (source.is(DamageTypes.FALL)) {
                return false;
            } else if (source.is(DamageTypes.CACTUS)) {
                return false;
            } else if (source.is(DamageTypes.DROWN)) {
                return false;
            } else if (source.is(DamageTypes.LIGHTNING_BOLT)) {
                return false;
            } else {
                Level var4 = this.level();
                if (var4 instanceof ServerLevel) {
                    ServerLevel server = (ServerLevel) var4;
                    server.sendParticles((SimpleParticleType) AquamiraeParticleTypes.SHINE.get(), this.getX(), this.getY(), this.getZ(), 6, 0.05, 0.05, 0.05, 0.8);
                }

                return super.hurt(source, amount);
            }
        } else {
            return false;
        }
    }

    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        super.mobInteract(player, hand);
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() == Items.GLASS_BOTTLE && !this.isBaby()) {
            stack.shrink(1);
            if (!this.level().isClientSide()) {
                this.level().playSound((Player) null, this.blockPosition(), (SoundEvent) AquamiraeSounds.ENTITY_GOLDEN_MOTH_CATCH.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
                ItemEntity item = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), new ItemStack((ItemLike) AquamiraeBlocks.GOLDEN_MOTH_IN_A_JAR.get()));
                item.setPickUpDelay(10);
                this.level().addFreshEntity(item);
                this.discard();
            }
        }

        return InteractionResult.sidedSuccess(this.level().isClientSide());
    }

    public void baseTick() {
        this.getPersistentData().putDouble("shine", this.getPersistentData().getDouble("shine") + (double) 1.0F);
        if (this.getPersistentData().getDouble("shine") > (double) 2.0F) {
            this.getPersistentData().putDouble("shine", (double) 0.0F);
            Level var2 = this.level();
            if (var2 instanceof ServerLevel) {
                if (this.isInLove()) {
                    ServerLevel server = (ServerLevel) var2;
                    server.sendParticles((SimpleParticleType) AquamiraeDelight.SHINE_HEART.get(), this.getX(), this.getY(), this.getZ(), 1, 0.1, 0.1, 0.1, 0.1);
                } else {
                    ServerLevel server = (ServerLevel) var2;
                    server.sendParticles((SimpleParticleType) AquamiraeParticleTypes.SHINE.get(), this.getX(), this.getY(), this.getZ(), 1, 0.1, 0.1, 0.1, 0.1);
                }
            }
        }

        if (this.isInWaterOrBubble()) {
            this.setDeltaMovement(this.getDeltaMovement().add((double) 0.0F, (double) 0.05F, (double) 0.0F));
        }

        super.baseTick();
    }

    protected void checkFallDamage(double y, boolean onGroundIn, @NotNull BlockState state, @NotNull BlockPos pos) {
    }

    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }

    public static SpawnPlacements.SpawnPredicate<GoldenMothAnimal> getSpawnRules() {
        return (entityType, level, spawnType, pos, random) -> {
            boolean var10000;
            if (level instanceof Level world) {
                if (!world.isDay()) {
                    var10000 = true;
                    return var10000;
                }
            }

            var10000 = false;
            return var10000;
        };
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.FLYING_SPEED, 0.4).add(Attributes.MOVEMENT_SPEED, 0.4).add(Attributes.MAX_HEALTH, (double) 3.0F);
    }
}
