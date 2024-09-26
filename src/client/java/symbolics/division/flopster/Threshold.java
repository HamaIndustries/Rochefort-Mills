package symbolics.division.flopster;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

import java.nio.file.Path;
import java.util.List;

public class Threshold {
    public static boolean tryInsert(MinecraftClient client, List<Path> paths, int invalidFilesCount) {
        if (!client.isPaused()
                && client.player != null
                && client.world != null
                && client.player.isAlive()
                && client.crosshairTarget != null
                && client.crosshairTarget.getType().equals(HitResult.Type.BLOCK)
                && client.crosshairTarget instanceof BlockHitResult bhr
                && client.world.getBlockState(bhr.getBlockPos()).isOf(Flopster.FLOPSTER_DRIVE)
        ) {
            client.getSoundManager().play(new PositionedSoundInstance(
                    Flopster.CLICK, SoundCategory.BLOCKS, 1, 1, client.player.getRandom(), bhr.getBlockPos()
            ));
            Siltware.dredge(bhr.getBlockPos(), paths, invalidFilesCount, client.player);
            return true;
        }
        return false;
    }
}
