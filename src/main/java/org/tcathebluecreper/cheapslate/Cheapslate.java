package org.tcathebluecreper.cheapslate;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(Cheapslate.MODID)
public class Cheapslate {
    public static final String MODID = "cheapslate";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Cheapslate() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void changeBreakSpeed(PlayerEvent.BreakSpeed event) {
        if(event.getState().getTags().anyMatch(tag -> tag.location().equals(ResourceLocation.parse("cheapslate:fast_blocks")))) {
            ItemStack tool = event.getEntity().getMainHandItem();
            if(
                    tool.getTags().anyMatch(tag -> tag.location().equals(ResourceLocation.parse("cheapslate:fast_pickaxes"))) &&
                    tool.getAllEnchantments().getOrDefault(Enchantments.BLOCK_EFFICIENCY,0) >= Config.efficiencyLevel &&
                        (event.getEntity().hasEffect(MobEffects.DIG_SPEED) &&
                        event.getEntity().getEffect(MobEffects.DIG_SPEED).getAmplifier() >= Config.hasteLevel) || Config.hasteLevel == -1
            ) event.setNewSpeed(10000);
        }
    }
}
