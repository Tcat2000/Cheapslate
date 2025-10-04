package org.tcathebluecreper.cheapslate;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder().comment("Tools tagged with #cheapslate:fast_pickaxes will instant-mine blocks tagged with #cheapslate:fast_blocks when the following conditions are met (-1 = disabled condition)");

    public static final ModConfigSpec.IntValue EFFICIENCY_LEVEL = BUILDER.comment("Efficiency level required for #cheapslate:fast_tools tools to mine #cheapslate:fast_blocks").defineInRange("efficiency", 5, -1, 255);
    public static final ModConfigSpec.IntValue HASTE_LEVEL = BUILDER.comment("Haste level required for #cheapslate:fast_tools tools to mine #cheapslate:fast_blocks (haste 1 = level 0)").defineInRange("hast", 1, -1, 255);

    static final ModConfigSpec SPEC = BUILDER.build();
}
