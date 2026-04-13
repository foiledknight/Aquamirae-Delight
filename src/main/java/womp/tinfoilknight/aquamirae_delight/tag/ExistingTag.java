package womp.tinfoilknight.aquamirae_delight.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class ExistingTag {
    public static final TagKey<Item> RAW_FISHES_SPINEFISH = forgeItemTag("raw_fishes/spinefish");
    public static final TagKey<Item> COOKED_FISHES_SPINEFISH = forgeItemTag("cooked_fishes/spinefish");
    public static final TagKey<Item> JAR_ITEMS = AQDItemTag("jars");
    public static final TagKey<Block> JAR_BLOCKS = AQDBlockTag("jars");
    public static final TagKey<Item> GOLDEN_MOTH_IN_A_JAR = AQDItemTag("golden_moth_in_a_jar");

    private static TagKey<Item> forgeItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
    }
    private static TagKey<Block> forgeBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", path));
    }
    private static TagKey<Item> mcItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", path));
    }
    private static TagKey<Block> mcBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", path));
    }
    private static TagKey<Item> AQDItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, path));
    }
    private static TagKey<Block> AQDBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, path));
    }
    private static TagKey<Item> AQItemTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.AQID, path));
    }
    private static TagKey<Block> AQBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.AQID, path));
    }
}
