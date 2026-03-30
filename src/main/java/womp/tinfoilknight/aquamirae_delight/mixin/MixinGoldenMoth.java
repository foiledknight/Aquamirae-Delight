package womp.tinfoilknight.aquamirae_delight.mixin;

import com.obscuria.aquamirae.common.entities.GoldenMoth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(GoldenMoth.class)
public abstract class MixinGoldenMoth extends Animal implements FlyingAnimal {
    protected MixinGoldenMoth(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Inject()
}
