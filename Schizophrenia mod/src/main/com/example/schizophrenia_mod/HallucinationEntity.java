package com.example.schizophrenia_mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HallucinationEntity extends ZombieEntity {

    public HallucinationEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
        this.setInvisible(true); // Make it appear as a shadow.
    }

    public static void spawnHallucination(ServerWorld world, BlockPos position) {
        HallucinationEntity hallucination = new HallucinationEntity(EntityType.ZOMBIE, world);
        hallucination.setPosition(position.getX(), position.getY(), position.getZ());
        world.spawnEntity(hallucination);

        // Schedule removal of the hallucination after a few seconds
        world.getServer().getScheduler().schedule(() -> hallucination.discard(), 100, java.util.concurrent.TimeUnit.MILLISECONDS);
    }
}
