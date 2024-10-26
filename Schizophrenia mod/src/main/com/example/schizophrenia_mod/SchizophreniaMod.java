package com.example.schizophrenia_mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SchizophreniaMod implements ModInitializer {

    public static final StatusEffect PARANOIA = new ParanoiaEffect();

    @Override
    public void onInitialize() {
        // Register the paranoia effect
        Registry.register(Registry.STATUS_EFFECT, new Identifier("schizophrenia_mod", "paranoia"), PARANOIA);

        // Apply paranoia effect to a player under certain conditions (demo)
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                // Apply the effect when player health is low, for demonstration
                if (player.getHealth() < 5.0f && !player.hasStatusEffect(PARANOIA)) {
                    player.addStatusEffect(new StatusEffectInstance(PARANOIA, 600, 0)); // 30 seconds
                }
            });
        });
    }
}
