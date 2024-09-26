package symbolics.division.flopster.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import symbolics.division.flopster.Threshold;

import java.nio.file.Path;
import java.util.List;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onFilesDropped", at = @At("HEAD"), cancellable = true)
    private void onFilesDropped(long window, List<Path> paths, int invalidFilesCount, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (Threshold.tryInsert(client, paths, invalidFilesCount)) ci.cancel();
    }
}
