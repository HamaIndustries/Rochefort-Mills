package symbolics.division.flopster;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.EnumSet;

public class SinisterGoal extends Goal implements STUPID.KILL {
    protected static final TargetPredicate TARGET_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(90).ignoreVisibility();
    protected final PathAwareEntity mob;
    protected final TargetPredicate predicate;
    protected PlayerEntity closestPlayer;
    protected double speed;
    protected int cooldown;

    public SinisterGoal(PathAwareEntity entity, double spd) {
        mob = entity;
        predicate = TARGET_PREDICATE.copy().setPredicate(STUPID.makeLookAwayPredicate(this));
        speed = spd;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    protected boolean isLookingAway(LivingEntity entity) {
        return entity.getRotationVector().dotProduct(this.mob.getPos().subtract(entity.getPos())) < 0;
    }

    protected boolean isSinisterTarget(LivingEntity entity) {
        return isLookingAway(entity);
    }

    @Override
    public boolean canStart() {
        this.closestPlayer = this.mob.getWorld().getClosestPlayer(this.predicate, this.mob);
        return this.closestPlayer != null;
    }

    @Override
    public boolean shouldContinue() {
        return canStart();
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        this.mob.getLookControl().lookAt(this.closestPlayer);
        this.cooldown = Math.max(this.cooldown - 1, 0);
        if (this.mob.distanceTo(this.closestPlayer) < 2 && this.cooldown == 0) {
            this.mob.tryAttack(this.closestPlayer);
            this.cooldown = 20;
        }
        if (this.mob.distanceTo(this.closestPlayer) < 0.4) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().startMovingTo(this.closestPlayer, this.speed);
        }
    }

    @Override
    public PathAwareEntity getMob() {
        return mob;
    }
}
