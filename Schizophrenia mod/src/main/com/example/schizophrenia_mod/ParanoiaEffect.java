package com.example.schizophrenia_mod;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.particle.ParticleTypes;

public class ParanoiaEffect extends StatusEffect {

    public ParanoiaEffect() {
        super(StatusEffectCategory.HARMFUL, 0x98D982); // Effect color.
    }

    // Called every tick while the effect is active.
    @Override
    public void applyUpdateEffect(PlayerEntity player, int amplifier) {
        ServerWorld world = (ServerWorld) player.world;

        // Randomly trigger sound hallucinations
        if (world.getRandom().nextFloat() < 0.05) { // 5% chance per tick
            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }

        // Randomly trigger visual hallucinations
        if (world.getRandom().nextFloat() < 0.02) { // 2% chance per tick
            HallucinationEntity.spawnHallucination(world, player.getBlockPos().add(world.getRandom().nextInt(5) - 2, 1, world.getRandom().nextInt(5) - 2));
        }

        // Apply random particle effects around the player
        if (world.getRandom().nextFloat() < 0.1) { // 10% chance per tick
            world.spawnParticles(ParticleTypes.SMOKE, player.getX(), player.getY() + 1, player.getZ(), 10, 0.5, 0.5, 0.5, 0.01);
        }

        // Random item drop
        if (world.getRandom().nextFloat() < 0.02) { // 2% chance per tick
            player.dropItem(player.getMainHandStack().copy(), true, false);
            player.getMainHandStack().decrement(1); // Reduce the count if not empty
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Ensures the effect is applied every tick.
    }
}
