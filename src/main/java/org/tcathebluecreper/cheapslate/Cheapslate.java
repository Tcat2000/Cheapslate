package org.tcathebluecreper.cheapslate;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;

@Mod(Cheapslate.MODID)
public class Cheapslate {
    public static final String MODID = "cheapslate";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Cheapslate(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void changeBreakSpeed(PlayerEvent.BreakSpeed event) {
        if(event.getState().getTags().anyMatch(tag -> tag.location().equals(ResourceLocation.tryParse("cheapslate:fast_blocks")))) {
            ItemStack tool = event.getEntity().getMainHandItem();
            if(
                    tool.getTags().anyMatch(tag -> tag.location().equals(ResourceLocation.tryParse("cheapslate:fast_pickaxes"))) &&
                    tool.getEnchantments().getLevel(Minecraft.getInstance().level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.EFFICIENCY)) >= Config.EFFICIENCY_LEVEL.get() &&
                        (event.getEntity().hasEffect(MobEffects.DIG_SPEED) &&
                        event.getEntity().getEffect(MobEffects.DIG_SPEED).getAmplifier() >= Config.HASTE_LEVEL.get()) || Config.HASTE_LEVEL.get() == -1
            ) event.setNewSpeed(10000);
        }
    }
}
