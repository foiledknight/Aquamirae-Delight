package womp.tinfoilknight.aquamirae_delight.items;

import com.obscuria.aquamirae.registry.AquamiraeItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class AquamiraeDelightTiers {
    public static final Tier SEPERATOR = new Tier() {
        public int getUses() {
            return 2200;
        }

        public float getSpeed() {
            return 9.0F;
        }

        public float getAttackDamageBonus() {
            return 1.0F;
        }

        public int getLevel() {
            return 3;
        }

        public int getEnchantmentValue() {
            return 14;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack[]{new ItemStack((ItemLike) AquamiraeItems.SHIP_GRAVEYARD_ECHO.get()), new ItemStack((ItemLike)AquamiraeItems.ABYSSAL_AMETHYST.get())});
        }
    };
    public static final Tier FIN_FILLETER = new Tier() {
        public int getUses() {
            return 1000;
        }

        public float getSpeed() {
            return 8.0F;
        }

        public float getAttackDamageBonus() {
            return 2.0F;
        }

        public int getLevel() {
            return 3;
        }

        public int getEnchantmentValue() {
            return 20;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack[]{new ItemStack(Items.DIAMOND), new ItemStack((ItemLike)AquamiraeItems.SHIP_GRAVEYARD_ECHO.get())});
        }
    };
    public static final Tier REMNANTS_KNIFE = new Tier() {
        public int getUses() {
            return 100;
        }

        public float getSpeed() {
            return 4.0F;
        }

        public float getAttackDamageBonus() {
            return 1.0F;
        }

        public int getLevel() {
            return 1;
        }

        public int getEnchantmentValue() {
            return 5;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack[]{new ItemStack((ItemLike)AquamiraeItems.SHARP_BONES.get()), new ItemStack((ItemLike)AquamiraeItems.SHIP_GRAVEYARD_ECHO.get())});
        }
    };
}
