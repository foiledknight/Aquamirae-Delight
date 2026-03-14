package womp.tinfoilknight.aquamirae_delight.items;

import com.obscuria.obscureapi.api.common.classes.Ability;
import com.obscuria.obscureapi.api.common.classes.ClassAbility;
import com.obscuria.obscureapi.api.common.classes.ClassItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.KnifeItem;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

@ClassItem(
        clazz = "aquamirae:sea_wolf",
        type = "tool"
)
public class Separator extends KnifeItem {

    @ClassAbility
    public final Ability ABILITY = Ability.create("aquamirae_delight", "separator").var(10, "s").action((stack, entity, target, context, values) -> {
        if (target == null) {
            return false;
        } else {
            MobEffectInstance EFFECT = target.getEffect(AquamiraeDelight.SPEED_DECREASE.get());
            if (EFFECT != null) {
                target.addEffect(new MobEffectInstance(AquamiraeDelight.SPEED_DECREASE.get(), 20 * (Integer)values.get(0), Math.min(4, EFFECT.getAmplifier() + 1), false, false));
            } else {
                target.addEffect(new MobEffectInstance(AquamiraeDelight.SPEED_DECREASE.get(), 20 * (Integer)values.get(0), 0, false, false));
            }

            return true;
        }
    }).build(Separator.class);

    public Separator() {
        super(AquamiraeDelightTiers.SEPERATOR, 3, -2.6F, (new Item.Properties()).fireResistant().rarity(Rarity.EPIC).tab(AquamiraeDelight.TAB));
    }

    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity entity, @NotNull LivingEntity source) {
        boolean hurt = super.hurtEnemy(stack, entity, source);
        if (hurt) {
            this.ABILITY.use(stack, source, entity, (UseOnContext)null);
        }
        return hurt;
    }
}
