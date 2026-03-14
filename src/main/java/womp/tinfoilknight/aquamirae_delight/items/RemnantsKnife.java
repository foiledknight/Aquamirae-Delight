package womp.tinfoilknight.aquamirae_delight.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.obscuria.obscureapi.api.common.classes.Ability;
import com.obscuria.obscureapi.api.common.classes.ClassAbility;
import com.obscuria.obscureapi.api.common.classes.ClassItem;
import com.obscuria.obscureapi.registry.ObscureAPIAttributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.item.KnifeItem;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

import java.util.UUID;

@ClassItem(
        clazz = "aquamirae:sea_wolf",
        type = "tool"
)
public class RemnantsKnife extends KnifeItem {
    @ClassAbility
    public final Ability ABILITY = Ability.create("aquamirae_delight", "remnants_knife").var(25, "%").build(RemnantsKnife.class);
    public RemnantsKnife() {
        super(AquamiraeDelightTiers.REMNANTS_KNIFE, 3, -2.0F, new Item.Properties().tab(AquamiraeDelight.TAB));
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> multimap = super.getDefaultAttributeModifiers(slot);
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(multimap);
            builder.put((Attribute) ObscureAPIAttributes.CRITICAL_HIT.get(), new AttributeModifier(UUID.fromString("AB3F55D3-644C-4F38-A497-9C13A33DB5CF"), "Weapon modifier", 0.1, AttributeModifier.Operation.MULTIPLY_BASE));
            return builder.build();
        } else {
            return multimap;
        }
    }
}
