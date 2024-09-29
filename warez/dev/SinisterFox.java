package symbolics.division.flopster;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;

class SinisterFox extends FoxEntity {
    public SinisterFox(EntityType<? extends FoxEntity> entityType, World world, Class<?> sinisterGoal) {
        super(entityType, world);
        Goal goal;
        try {
            goal = ((Class<? extends Goal>)sinisterGoal).getConstructor(PathAwareEntity.class, Double.TYPE).newInstance(this, 1.0);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        this.goalSelector.add(-99, goal);
    }

    @Override
    public boolean tryAttack(Entity target) {
        var funny = FallingBlockEntity.spawnFromBlock(target.getWorld(), target.getBlockPos().up(), Blocks.ANVIL.getDefaultState());
        funny.fallDistance = 100;
        funny.setHurtEntities(100, 100);
        funny.setDestroyedOnLanding();
        return true;
    }
}
