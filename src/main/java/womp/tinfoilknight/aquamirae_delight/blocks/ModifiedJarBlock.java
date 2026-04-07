package womp.tinfoilknight.aquamirae_delight.blocks;

import com.obscuria.aquamirae.common.blocks.JarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModifiedJarBlock extends JarBlock {
    private final Supplier<? extends EntityType<?>> inJar;
    public ModifiedJarBlock(Supplier<? extends EntityType<?>> entity) {
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
        this.inJar = entity;
    }
    public ModifiedJarBlock() {
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
        this.inJar = null;
    }

    @Override
    public void setPlacedBy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState blockstate, LivingEntity entity, @NotNull ItemStack itemstack) {
        if (world instanceof ServerLevel server) {
            if (inJar != null){
                Mob mob = (Mob) inJar.get().create(world);
                mob.moveTo((double)pos.getX() + (double)0.5F, (double)pos.getY() + 0.3, (double)pos.getZ() + (double)0.5F, 0.0F, 0.0F);
                mob.setPersistenceRequired();
                mob.finalizeSpawn(server, world.getCurrentDifficultyAt(mob.blockPosition()), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                world.addFreshEntity(mob);
            }
        }

    }
}
