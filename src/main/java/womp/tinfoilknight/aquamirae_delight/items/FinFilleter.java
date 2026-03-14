package womp.tinfoilknight.aquamirae_delight.items;

import com.obscuria.obscureapi.api.common.classes.Ability;
import com.obscuria.obscureapi.api.common.classes.ClassAbility;
import com.obscuria.obscureapi.api.common.classes.ClassItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import vectorwing.farmersdelight.common.item.KnifeItem;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

@ClassItem(
        clazz = "aquamirae:sea_wolf",
        type = "tool"
)
public class FinFilleter extends KnifeItem {
    @ClassAbility
    public final Ability ABILITY = Ability.create("aquamirae_delight", "fin_filleter").var(10, "%").var(100, "%").build(FinFilleter.class);
    public FinFilleter() {
        super(AquamiraeDelightTiers.FIN_FILLETER, 3, -2.0F, new Item.Properties().tab(AquamiraeDelight.TAB));
    }


}
