package womp.tinfoilknight.aquamirae_delight.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SpeedDecreaseMobEffect extends MobEffect {
    public SpeedDecreaseMobEffect() {
        super(MobEffectCategory.NEUTRAL, -6750055);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    public @NotNull String getDescriptionId() {
        return "effect.aquamirae_delight.health_decrease";
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            public boolean isVisibleInGui(MobEffectInstance effect) {
                return false;
            }
        });
    }
}
