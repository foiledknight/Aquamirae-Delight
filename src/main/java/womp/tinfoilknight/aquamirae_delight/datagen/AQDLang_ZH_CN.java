package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class AQDLang_ZH_CN extends LanguageProvider {
    public AQDLang_ZH_CN(PackOutput output, String locale) {
        super(output, AquamiraeDelight.MODID, locale);
    }

    protected void addTranslations() {
        add("creative_tab.aquamirae_delight", "海灵乐事");
        addItem(AquamiraeDelight.SEPARATOR, "隔离器");
        addItem(AquamiraeDelight.FIN_FILLETER, "鳍片切片机");
        addItem(AquamiraeDelight.REMNANTS_KNIFE, "残刀");
        addItem(AquamiraeDelight.SPINEFISH_SLICE, "生棘鱼片");
        addItem(AquamiraeDelight.COOKED_SPINEFISH_SLICE, "熟棘鱼片");
        addItem(AquamiraeDelight.WISTERIA_LEAVES, "紫藤叶");
        addItem(AquamiraeDelight.GROUND_WISTERIA, "紫藤根末");
        addItem(AquamiraeDelight.GOLDEN_PUREE, "金蛾糊酱");
        addItem(AquamiraeDelight.OXYGELIUM_BULB, "氧素草球茎");

        addItem(AquamiraeDelight.SPINEFISH_ROLL, "棘鱼寿司");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL, "棘鱼海苔卷");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE, "棘鱼海苔卷切片");
        addBlock(AquamiraeDelight.DEEPSEA_PIE, "深海派");
        addItem(AquamiraeDelight.DEEPSEA_PIE_SLICE, "深海派片");
        addBlock(AquamiraeDelight.FISHERMANS_DELICACY, "海味盛宴");
        addItem(AquamiraeDelight.FISHERMANS_DELICACY_SLICE, "渔夫面包卷切片");
        addItem(AquamiraeDelight.ANGLED_KEBAB, "鮟鱇烤肉串");
        addBlock(AquamiraeDelight.AQUATIC_FEAST, "渔夫面包卷");

        addItem(AquamiraeDelight.GLAZED_GRILLED_SPINEFISH, "浇酱焙棘鱼");
        addItem(AquamiraeDelight.GOLDEN_PUREE_PASTA, "金蛾酱意面");
        addItem(AquamiraeDelight.ABYSSAL_RISOTTO, "深海烩饭");
        addItem(AquamiraeDelight.SPINEFISH_ALFREDO, "阿尔弗雷多棘鱼意面");
        addItem(AquamiraeDelight.ESCAGELIUM_SOUP, "鮟鱇鱼配氧素草汤");
        addItem(AquamiraeDelight.ANGLERS_SOUP, "垂钓者之汤");

        addEffect(AquamiraeDelight.SPEED_DECREASE, "减速");

        addEntityType(AquamiraeDelight.GOLDEN_MOTH, "§e金蛾");

        addItem(AquamiraeDelight.GOLDEN_MOTH_SPAWN_EGG, "金蛾刷怪蛋");
        addBlock(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR, "金蛾罐子");

        add("class.aquamirae.sea_wolf_tool", "海狼工具");
        add("ability.aquamirae_delight.separator", "虚弱 > 在 {#1} 内降低目标 10% 的移动速度。最高叠加 5 层");
        add("ability.aquamirae_delight.fin_filleter", "贪婪 > 每缺失一格饥饿值，造成的伤害增加 {#1} 点（最高 {#2} 点）。击杀生物可恢复半格饥饿值。");
        add("ability.aquamirae_delight.remnants_knife", "疾流 > 在水中获得海豚之恩，并造成 {#1} 点额外伤害");

    }
}
