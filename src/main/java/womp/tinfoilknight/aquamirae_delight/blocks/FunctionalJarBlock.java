package womp.tinfoilknight.aquamirae_delight.blocks;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FunctionalJarBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final Property<Integer> QUANTITY = IntegerProperty.create("quantity", 0, 3);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final Fluid content;
    private static final Logger LOGGER = LogUtils.getLogger();
    private final Supplier<? extends EntityType<?>> mobInJar;
    public FunctionalJarBlock() {
        super(Properties.of().mapColor(MapColor.GOLD).sound(SoundType.GLASS).strength(1.0F, 10.0F).lightLevel((s) -> 0).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(QUANTITY, 0));
        this.mobInJar = null;
        this.content = null;
    }
    public FunctionalJarBlock(Supplier<? extends EntityType<?>> entity) {
        super(Properties.of().mapColor(MapColor.GOLD).sound(SoundType.GLASS).strength(1.0F, 10.0F).lightLevel((s) -> 8).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(QUANTITY, 0));
        this.mobInJar = entity;
        this.content = null;
    }
    public FunctionalJarBlock(boolean containsFluid, Fluid fluid) {
        super(Properties.of().mapColor(MapColor.GOLD).sound(SoundType.GLASS).strength(1.0F, 10.0F).lightLevel((s) -> 0).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(QUANTITY, 2));
        this.mobInJar = null;
        this.content = fluid;
    }
    public FunctionalJarBlock(boolean containsFluid, Supplier<Fluid> fluid) {
        super(Properties.of().mapColor(MapColor.GOLD).sound(SoundType.GLASS).strength(1.0F, 10.0F).lightLevel((s) -> 0).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(QUANTITY, 2));
        this.mobInJar = null;
        this.content = fluid.get();
    }

    //Fluid Block Tut
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? null : (level0, pos0, state0, blockEntity) -> ((FunctionalJarBlockEntity)blockEntity).tick();
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FunctionalJarBlockEntity jarBlockEntity) {
            jarBlockEntity.use(blockState, level, pos, player, interactionHand, hitResult);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
    //End

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return (BlockState)this.defaultBlockState().setValue(WATERLOGGED, flag);
    }

    public IntegerProperty getContentsQuantity() {
        return (IntegerProperty) QUANTITY;
    }
    public Fluid fluid(){
        return this.content;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{WATERLOGGED});
        builder.add(new Property[]{QUANTITY});
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FunctionalJarBlockEntity(pos, state);
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
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    //JAR BASE LOGIC

    public boolean propagatesSkylightDown(BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return 0;
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.join(Shapes.or(box((double)3.0F, (double)0.0F, (double)3.0F, (double)13.0F, (double)12.0F, (double)13.0F), box((double)4.0F, (double)12.0F, (double)4.0F, (double)12.0F, (double)14.0F, (double)12.0F)), box(3.1, 0.1, 3.1, 12.9, (double)10.5F, 12.9), BooleanOp.ONLY_FIRST);
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
