package womp.tinfoilknight.aquamirae_delight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.registry.ModItems;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class AquaticFeastBlock extends FeastBlock {
    public static final IntegerProperty ROLL_SERVINGS = IntegerProperty.create("servings", 0, 8);
    protected static final VoxelShape PLATE_SHAPE = Block.box((double)1.0F, (double)0.0F, (double)1.0F, (double)15.0F, (double)2.0F, (double)15.0F);
    protected static final VoxelShape FOOD_SHAPE;
    public final List<Supplier<Item>> riceRollServings;
    public final Supplier<Item> fds = AquamiraeDelight.FISHERMANS_DELICACY_SLICE;
    public final Supplier<Item> spr = AquamiraeDelight.SPINEFISH_ROLL;
    public final Supplier<Item> skr = AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE;

    public AquaticFeastBlock(BlockBehaviour.Properties properties) {
        super(properties, ModItems.SALMON_ROLL, true);
        this.riceRollServings = Arrays.asList(skr, skr, skr, spr, spr, spr, fds, fds);
    }

    public IntegerProperty getServingsProperty() {
        return ROLL_SERVINGS;
    }

    public int getMaxServings() {
        return 8;
    }

    public ItemStack getServingItem(BlockState state) {
        return new ItemStack((ItemLike)((Supplier)this.riceRollServings.get((Integer)state.getValue(this.getServingsProperty()) - 1)).get());
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Integer)state.getValue(this.getServingsProperty()) == 0 ? PLATE_SHAPE : FOOD_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, ROLL_SERVINGS});
    }

    static {
        FOOD_SHAPE = Shapes.joinUnoptimized(PLATE_SHAPE, Block.box((double)2.0F, (double)2.0F, (double)2.0F, (double)14.0F, (double)4.0F, (double)14.0F), BooleanOp.OR);
    }
}
