package org.tcathebluecreper.cheapslate;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Cheapslate.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder().comment("Tools tagged with #cheapslate:fast_pickaxes will instant-mine blocks tagged with #cheapslate:fast_blocks when the following conditions are met (-1 = disabled condition)");

    private static final ForgeConfigSpec.IntValue EFFICIENCY_LEVEL = BUILDER.comment("Efficiency level required for #cheapslate:fast_tools tools to mine #cheapslate:fast_blocks").defineInRange("efficiency", 5, -1, 255);
    private static final ForgeConfigSpec.IntValue HASTE_LEVEL = BUILDER.comment("Haste level required for #cheapslate:fast_tools tools to mine #cheapslate:fast_blocks (haste 1 = level 0)").defineInRange("hast", 1, -1, 255);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int efficiencyLevel;
    public static int hasteLevel;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        efficiencyLevel = EFFICIENCY_LEVEL.get();
        hasteLevel = HASTE_LEVEL.get();
    }
}
