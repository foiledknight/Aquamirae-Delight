package womp.tinfoilknight.aquamirae_delight.blocks;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import javax.annotation.Nullable;

public class FunctionalJarBlockEntity extends BlockEntity {
    public FunctionalJarBlockEntity(BlockPos pos, BlockState state) {
        super(AquamiraeDelight.JAR_BLOCK_ENTITY.get(), pos, state);
    }

    //Fluid Block Entity Tut
    public void tick() {
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult){
        if (this.level == null || this.level.isClientSide()){
            return InteractionResult.FAIL;
        }
        ItemStack stack = player.getItemInHand(interactionHand);
        if(stack.isEmpty()){
            return InteractionResult.FAIL;
        }

        boolean success = FluidUtil.interactWithFluidHandler(player, interactionHand, this.fluidTank);

        if (success) {
            this.setChanged();
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private final FluidTank fluidTank = new FluidTank(1000){
        @Override
        protected void onContentsChanged(){
            super.onContentsChanged();
            FunctionalJarBlockEntity.this.sendUpdate();
        }
    };

    private void sendUpdate() {
        setChanged();

        if (this.level != null)
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
    }

    private final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> this.fluidTank);


    public FluidTank getFluidTank(){
        return this.fluidTank;
    }
    public LazyOptional<FluidTank> getFluidOptional(){
        return this.fluidOptional;
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("FluidTank", this.fluidTank.writeToNBT(new CompoundTag()));
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);
        this.fluidTank.readFromNBT(pTag.getCompound("FluidTank"));
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.FLUID_HANDLER){
            return this.fluidOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps(){
        super.invalidateCaps();
        this.fluidOptional.invalidate();
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    //End
}
