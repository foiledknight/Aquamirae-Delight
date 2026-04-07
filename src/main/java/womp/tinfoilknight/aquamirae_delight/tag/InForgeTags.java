package womp.tinfoilknight.aquamirae_delight.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class InForgeTags {
    public static final TagKey<Item> RAW_FISHES_SPINEFISH = forgeItemTag("raw_fishes/spinefish");
    public static final TagKey<Item> COOKED_FISHES_SPINEFISH = forgeItemTag("cooked_fishes/spinefish");
    public static final TagKey<Item> JAR_ITEMS = AQDItemTag("jars");
    public static final TagKey<Block> JAR_BLOCKS = AQDBlockTag("jars");

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
    }
    private static TagKey<Item> AQDItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, path));
    }
    private static TagKey<Block> AQDBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, path));
    }
}
