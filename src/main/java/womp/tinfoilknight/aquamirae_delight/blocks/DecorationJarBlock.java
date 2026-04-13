package womp.tinfoilknight.aquamirae_delight.blocks;

import com.obscuria.aquamirae.common.blocks.JarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.entity.GoldenMothAnimal;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class DecorationJarBlock extends Block implements SimpleWaterloggedBlock {
    private final @Nullable Supplier<? extends EntityType<?>> mobInJar;
    public static final IntegerProperty LIGHT = IntegerProperty.create("light", 0, 15);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty REPLACE = BooleanProperty.create("replace");
    private static final BlockBehaviour.Properties props = Properties.of().mapColor(MapColor.GOLD).sound(SoundType.GLASS).strength(1.0F, 10.0F).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false);


    public DecorationJarBlock(BlockBehaviour.Properties props, @Nullable Supplier<? extends EntityType<?>> entity, int light, boolean replace) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIGHT, light).setValue(WATERLOGGED, false).setValue(REPLACE, replace));
        this.mobInJar = entity;
    }
    public DecorationJarBlock() {
        this(props, null, 0, true);
    }
    public DecorationJarBlock(int light) {
        this(props, null, light, true);
    }
    public DecorationJarBlock(Supplier<? extends EntityType<?>> entity) {
        this(props, entity, 0, true);
    }
    public DecorationJarBlock(EntityType<?> entity) {
        this(() -> entity);
    }
    public DecorationJarBlock(EntityType<?> entity, int light) {
        this(props, () -> entity, light, true);
    }
    public DecorationJarBlock(RegistryObject<EntityType<GoldenMothAnimal>> entity, int light) {
        this(props, entity, light, true);
    }
    public DecorationJarBlock(RegistryObject<EntityType<GoldenMothAnimal>> entity) {
        this(entity::get);
    }

    @Override
    public void setPlacedBy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState blockstate, LivingEntity entity, @NotNull ItemStack itemstack) {
        if (world instanceof ServerLevel server) {
            if (mobInJar != null){
                Mob mob = (Mob) mobInJar.get().create(world);
                mob.moveTo((double)pos.getX() + (double)0.5F, (double)pos.getY() + 0.3, (double)pos.getZ() + (double)0.5F, 0.0F, 0.0F);
                mob.setPersistenceRequired();
                mob.finalizeSpawn(server, world.getCurrentDifficultyAt(mob.blockPosition()), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                world.addFreshEntity(mob);
            }
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getValue(LIGHT);
    }
    public BlockState setLightEmission(BlockState state, int light) {
        return state.setValue(LIGHT, light);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
        builder.add(LIGHT);
        builder.add(REPLACE);
    }
    //Base Jar Stuff
    public boolean propagatesSkylightDown(BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return 0;
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.join(Shapes.or(box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)12.0F, (double)13.0F), box((double)4.0F, (double)12.0F, (double)4.0F, (double)12.0F, (double)14.0F, (double)12.0F)), box(3.1, 0.1, 3.1, 12.9, (double)10.5F, 12.9), BooleanOp.ONLY_FIRST);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return (BlockState)this.defaultBlockState().setValue(WATERLOGGED, flag);
    }

    public @NotNull FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor world, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if ((Boolean)state.getValue(WATERLOGGED)) {
            world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }

        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

}
