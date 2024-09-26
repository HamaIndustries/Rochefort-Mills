package symbolics.division.flopster;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

@FunctionalInterface
public interface FlopsterProgram {
    void convoke(BlockPos source, PlayerEntity root);
    default boolean remote() { return false; }
    default void configure(List<Class<?>> deps) {}
}
