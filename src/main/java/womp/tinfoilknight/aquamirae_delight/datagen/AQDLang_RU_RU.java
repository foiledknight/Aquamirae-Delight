package womp.tinfoilknight.aquamirae_delight.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelight;

public class AQDLang_RU_RU extends LanguageProvider {
    public AQDLang_RU_RU(PackOutput output, String locale) {
        super(output, AquamiraeDelight.MODID, locale);
    }

    protected void addTranslations() {
        add(AquamiraeDelight.AQUAMIRAE_DELIGHT_CREATIVE_TAB_KEY, "Aquamirae Delight");
        addItem(AquamiraeDelight.SEPARATOR, "Отсекатель");
        addItem(AquamiraeDelight.FIN_FILLETER, "Плавниковый Филерез");
        addItem(AquamiraeDelight.REMNANTS_KNIFE, "Мёртвый нож");
        addItem(AquamiraeDelight.SPINEFISH_SLICE, "Ломтик Хребеточника");
        addItem(AquamiraeDelight.COOKED_SPINEFISH_SLICE, "Ломтик Приготовленного Хребеточника");
        addItem(AquamiraeDelight.WISTERIA_LEAVES, "Листья Вистерии");
        addItem(AquamiraeDelight.GROUND_WISTERIA, "Молотая Вистерия");
        addItem(AquamiraeDelight.GOLDEN_PUREE, "Золотое Пюре");
        addItem(AquamiraeDelight.OXYGELIUM_BULB, "Луковицы Кислородника");

        addItem(AquamiraeDelight.SPINEFISH_ROLL, "Ролл из Хребеточника");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL, "Ролл из Ламинарии с Хребеточником");
        addItem(AquamiraeDelight.SPINEFISH_KELP_ROLL_SLICE, "Ломтик Ролла из Ламинарии с Хребеточником");
        addBlock(AquamiraeDelight.DEEPSEA_PIE, "Глубоководный Пирог");
        addItem(AquamiraeDelight.DEEPSEA_PIE_SLICE, "Долька Глубоководного Пирога");
        addBlock(AquamiraeDelight.FISHERMANS_DELICACY, "Рыбацкий Деликатес");
        addItem(AquamiraeDelight.FISHERMANS_DELICACY_SLICE, "Ломтик Рыбацкого Деликатеса");
        addItem(AquamiraeDelight.ANGLED_KEBAB, "Шашлык из Удильщика");
        addBlock(AquamiraeDelight.AQUATIC_FEAST, "Подводное Ассорти");

        addItem(AquamiraeDelight.GLAZED_GRILLED_SPINEFISH, "Глазированный Жаренный Хребеточник");
        addItem(AquamiraeDelight.GOLDEN_PUREE_PASTA, "Паста с Золотым Пюре");
        addItem(AquamiraeDelight.ABYSSAL_RISOTTO, "Ризотто Бездны");
        addItem(AquamiraeDelight.SPINEFISH_ALFREDO, "Паста Альфредо с Хребеточником");
        addItem(AquamiraeDelight.ESCAGELIUM_SOUP, "Суп из Светящейся Приманки");
        addItem(AquamiraeDelight.ANGLERS_SOUP, "Суп из Удильщика");

        addEffect(AquamiraeDelight.SPEED_DECREASE, "Снижение скорости");

        addEntityType(AquamiraeDelight.GOLDEN_MOTH, "§eЗолотой мотылёк");

        addItem(AquamiraeDelight.GOLDEN_MOTH_SPAWN_EGG, "Призыв Золотого мотылька");
        addBlock(AquamiraeDelight.GOLDEN_MOTH_IN_A_JAR, "Золотой мотылёк в банке");
        addBlock(AquamiraeDelight.JAR, "Банка");
        addBlock(AquamiraeDelight.FUNCTIONAL_JAR, "Банка");

        add("class.aquamirae.sea_wolf_tool", "Инструмент морского волка");
        add("ability.aquamirae_delight.separator", "Ослабление > Снижает скорость цели на 10% на {#1}. Суммируется до 5 раз");
        add("ability.aquamirae_delight.fin_filleter", "Ненасытность > За каждую пустую единицу сытости вы наносите на {#1} ед. больше урона (макс. {#2}). Убийство моба восстанавливает пол-единицы сытости.");
        add("ability.aquamirae_delight.remnants_knife", "Быстрое течение > Находясь в воде, вы получаете эффект «Грация дельфина» и наносите на {#1} ед. больше урона");

        add("lore.aquamirae_delight.golden_moth_in_a_jar", "Некоторые сокровища могут иметь собственную душу, а не только пожирать чужие...");
    }
}
