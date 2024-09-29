package symbolics.division.flopster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FoxTrotProgram implements FlopsterProgram {
    private List<Class<?>> deps = null;
    @Override
    public void configure(List<Class<?>> deps) {
        // dep 1: SinisterFox
        // dep 2: SinisterGoal
        this.deps = List.copyOf(deps);
    }

    @Override
    public void convoke(BlockPos source, PlayerEntity root) {
        if (deps == null) {
            throw new RuntimeException("Dependencies were not configured for this disk.");
        }
        var world = root.getWorld();
        Entity envoy;
        try {
            envoy = (Entity)deps.get(0).getConstructor(EntityType.class, World.class, Class.class)
                    .newInstance(EntityType.FOX, world, deps.get(1));
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        envoy.setPos(source.getX(), source.getY()+2, source.getZ());
        world.spawnEntity(envoy);
    }

    @Override
    public boolean remote() {
        return true;
    }
}
