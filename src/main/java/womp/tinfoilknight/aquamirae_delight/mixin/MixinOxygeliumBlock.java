package womp.tinfoilknight.aquamirae_delight.mixin;

import com.obscuria.aquamirae.common.blocks.OxygeliumBlock;
import com.obscuria.aquamirae.registry.AquamiraeBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Objects;

@Mixin(OxygeliumBlock.class)
public abstract class MixinOxygeliumBlock {

    /**
     * @author tinfoilknight_
     * @reason Allows harvesting from Oxygelium. Injecting caused Water Breathing to apply, even when harvesting with a knife.
     */
    @Overwrite(remap = false)
    private @NotNull InteractionResult useBud(BlockState state, Level world, BlockPos pos, LivingEntity entity) {
        world.setBlock(pos, (BlockState)((BlockState)((Block) AquamiraeBlocks.OXYGELIUM.get()).defaultBlockState().setValue(OxygeliumBlock.TYPE, OxygeliumBlock.Type.EMPTY_BUD)).setValue(OxygeliumBlock.WATERLOGGED, (Boolean)state.getValue(OxygeliumBlock.WATERLOGGED)), 3);
        SoundEvent sound;
        if (entity.getMainHandItem().is(ForgeTags.TOOLS_KNIVES)) {
            sound = SoundEvents.WET_GRASS_BREAK;
            ((Player)entity).addItem(new ItemStack(AquamiraeDelight.OXYGELIUM_BULB.get(), 2));
            entity.getMainHandItem().setDamageValue(entity.getMainHandItem().getDamageValue() + 1);
        }
        else {
            sound = SoundEvents.BUBBLE_COLUMN_BUBBLE_POP;
            entity.setAirSupply(0);
            entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, (entity.hasEffect(MobEffects.WATER_BREATHING) ? ((MobEffectInstance) Objects.requireNonNull(entity.getEffect(MobEffects.WATER_BREATHING))).getDuration() : 0) + 200, 0));
            if (!world.isClientSide() && entity.hasEffect(MobEffects.WATER_BREATHING) && ((MobEffectInstance) Objects.requireNonNull(entity.getEffect(MobEffects.WATER_BREATHING))).getDuration() > 1200) {
                world.playSound((Player) null, pos, (SoundEvent) SoundEvents.AMBIENT_CAVE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
            }
        }
        if (!world.isClientSide()) {
            world.playSound((Player)null, pos, sound, SoundSource.BLOCKS, 5.0F, 1.0F);
        }
        return InteractionResult.SUCCESS;
    }

    /**
     * @author tinfoilknight_
     * @reason Same as above, this time, yield is 3
     */
    @Overwrite(remap = false)
    private @NotNull InteractionResult useRareBud(BlockState state, Level world, BlockPos pos, LivingEntity entity) {
        if (entity.getMainHandItem().is(ForgeTags.TOOLS_KNIVES)) {
            ((Player)entity).addItem(new ItemStack(AquamiraeDelight.OXYGELIUM_BULB.get(), 3));
            entity.getMainHandItem().setDamageValue(entity.getMainHandItem().getDamageValue() + 1);
            if (!world.isClientSide()) {
                world.playSound((Player)null, pos, SoundEvents.WET_GRASS_BREAK, SoundSource.BLOCKS, 5.0F, 1.0F);
            }
        }
        else {
            if (!world.isClientSide()) {
                world.playSound((Player)null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 0.6F, 1.0F);
                world.addFreshEntity(new ExperienceOrb(world, (double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, 8));
            }
        }

        world.setBlock(pos, (BlockState)((BlockState)((Block)AquamiraeBlocks.OXYGELIUM.get()).defaultBlockState().setValue(OxygeliumBlock.TYPE, OxygeliumBlock.Type.EMPTY_BUD)).setValue(OxygeliumBlock.WATERLOGGED, (Boolean)state.getValue(OxygeliumBlock.WATERLOGGED)), 3);
        return InteractionResult.SUCCESS;
    }
}