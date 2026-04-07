package womp.tinfoilknight.aquamirae_delight.mixin;

import com.obscuria.aquamirae.common.entities.GoldenMoth;
import com.obscuria.aquamirae.registry.AquamiraeBlocks;
import com.obscuria.aquamirae.registry.AquamiraeParticleTypes;
import com.obscuria.aquamirae.registry.AquamiraeSounds;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

@Mixin(GoldenMoth.class)
public class MixinGoldenMoth extends PathfinderMob {
    protected MixinGoldenMoth(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    /**
     * @author tinfoilknight_
     * @reason Makes it so Golden Moths have a taming sort of system
     */
    @Overwrite(remap = false)
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        super.mobInteract(player, hand);
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() == Items.GLASS_BOTTLE) {
            stack.shrink(1);
            if (!this.level.isClientSide()) {
                this.level.playSound((Player)null, this.blockPosition(), (SoundEvent) AquamiraeSounds.ENTITY_GOLDEN_MOTH_CATCH.get(), SoundSource.AMBIENT, 1.0F, 1.0F);
                ItemEntity item = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack((ItemLike) AquamiraeBlocks.GOLDEN_MOTH_IN_A_JAR.get()));
                item.setPickUpDelay(10);
                this.level.addFreshEntity(item);
                this.discard();
            }
        }
        if (stack.getItem() == AquamiraeDelight.WISTERIA_LEAVES.get()) {
            stack.shrink(1);
            if (!this.level.isClientSide()) {
                double x = this.getX();
                double y = this.getY();
                double z = this.getZ();
                Vec2 rot = this.getRotationVector();
                this.discard();
                Entity newMoth = AquamiraeDelight.GOLDEN_MOTH.get().create(level);
                newMoth.moveTo(x, y, z, rot.y, rot.x);
                level.addFreshEntity(newMoth);
            }
            if (this.level instanceof ServerLevel) {
                ServerLevel server = (ServerLevel)level;
                RandomSource random = this.getRandom();
                double min = 0.1;
                double max = 0.5;
                for (int i = 0; i < 8; i++) {
                    double offsetX = this.getX() + (min + random.nextDouble() * (max - min)) * (random.nextBoolean() ? 1 : -1);
                    double offsetY = this.getY() + (min + random.nextDouble() * (max - min)) * (random.nextBoolean() ? 1 : -1);
                    double offsetZ = this.getZ() + (min + random.nextDouble() * (max - min)) * (random.nextBoolean() ? 1 : -1);
                    server.sendParticles((SimpleParticleType)AquamiraeDelight.SHINE_GLINT.get(), offsetX, offsetY, offsetZ, 1, 0.05, 0.05, 0.05, 0.1);
                }
            }
        }

        return InteractionResult.sidedSuccess(this.level.isClientSide());
    }
}
