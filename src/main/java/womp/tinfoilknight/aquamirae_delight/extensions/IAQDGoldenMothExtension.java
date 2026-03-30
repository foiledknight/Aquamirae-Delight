package womp.tinfoilknight.aquamirae_delight.extensions;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;

public abstract class IAQDGoldenMothExtension extends Animal implements FlyingAnimal {
    protected IAQDGoldenMothExtension(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
}
