package womp.tinfoilknight.aquamirae_delight.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class InForgeTags {
    public static final TagKey<Item> RAW_FISHES_SPINEFISH = forgeItemTag("raw_fishes/spinefish");
    public static final TagKey<Item> COOKED_FISHES_SPINEFISH = forgeItemTag("cooked_fishes/spinefish");

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
    }
}
