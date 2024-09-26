package symbolics.division.flopster;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;

import java.util.function.Predicate;

public class STUPID {
    // hidden classes can't lambda which is AWESOME and I LOVE IT.
    // its either this or write an entire metafactory for this one thing
    public static Predicate<LivingEntity> makeLookAwayPredicate(KILL k) {
        return entity -> entity.getRotationVector().dotProduct(k.getMob().getPos().subtract(entity.getPos())) < 0;
    }

    public interface KILL {
        PathAwareEntity getMob();
    }
}
