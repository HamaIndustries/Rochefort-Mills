package symbolics.division.flopster;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/*
inertia.

    foreign silt with cosmic soil
    dredged from distant lands
    together come with common dirt
    and ever shifting sands

    to stem the tidal bore inland
    our treasured far-flung guest
    for it to lap against the wall
    as futile as the rest

    deep ocean spirals follow paths
    as fated by the stars
    their motion follows naturally
    the rules of moon and mars

    inevitable inertia drives
    countless quants in gyre
    the standing waves stand in detente
    with entropic desire

 */

public class Siltware {
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    public static void dredge(BlockPos source, List<Path> paths, int invalidFilesCount, PlayerEntity root) {
        if (invalidFilesCount > 0) {
            Flopster.LOGGER.error(invalidFilesCount + " invalid");
        }
        for (var path : paths) {
            Flopster.LOGGER.info("loading " + path);

            byte[] disk;
            try {
                disk = IOUtils.toByteArray(path.toUri());
            } catch (IOException e) {
                Flopster.LOGGER.error("wtf: " + path);
                return;
            }

            // pray to god that statistics treats you well
            final byte[] end = {(byte)0xca, (byte)0xfe, (byte)0xba, (byte)0xbe};
            int skip = 0;
            for (int i = 0; i < disk.length; i++) {
                boolean eq = true;
                for (int j = 0; j < end.length; j++) {
                    if (disk[i+j] != end[j]) {
                        eq = false;
                        break;
                    }
                }
                if (eq) {
                    skip = i - 1;
                    break;
                }
            }

            // -52 -2 -70 -66
            if (skip == 0) {
                Flopster.LOGGER.error("mud without substance");
                return;
            }

            int rank = disk[skip];
            byte[] sediment = Arrays.copyOfRange(disk, skip-2*rank-1, disk.length);
            zodameranu_pujo_ooaona(sediment, source, true, root);
        }
    }

    private static Class<?> conjure(byte[] silt) throws IllegalAccessException {
        return lookup.defineHiddenClass(silt, true, MethodHandles.Lookup.ClassOption.NESTMATE).lookupClass();
    }

    private static int sizeof(byte[] mags, int ix) {
        int u = Byte.toUnsignedInt(mags[2*ix]) << 8;
        int l = Byte.toUnsignedInt(mags[2*ix+1]);
        return u + l;
    }

    public static void zodameranu_pujo_ooaona(byte[] silt, BlockPos source, boolean localized, PlayerEntity root) {
        int ranks = silt[0];
        byte[] magnitudes = Arrays.copyOfRange(silt, 1, ranks*2+1);
        Class<?>[] categorias = new Class<?>[ranks];
        try {
            int offset = magnitudes.length + 2;
            for (int i = 0; i < ranks; i++) {
                int size = sizeof(magnitudes, i);
                var body = Arrays.copyOfRange(silt, offset, offset + size);
                categorias[i] = conjure(body);
                offset += size;
            }
        } catch (IllegalAccessException e) {
            Flopster.LOGGER.error("program is a wicked child and wishes to steal divine mysteries");
            return;
        }

        Object child;
        try {
            child = categorias[0].getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            Flopster.LOGGER.error("program is a greedy child and demands more than it is owed");
            return;
        } catch (IllegalAccessException e) {
            Flopster.LOGGER.error("program is a quiet child who hides its nature");
            return;
        } catch (InstantiationException e) {
            Flopster.LOGGER.error("program is an ephemeral child who was not properly tethered to the plane");
            return;
        } catch (InvocationTargetException e) {
            Flopster.LOGGER.error("the child failed to be born in its own right");
            return;
        }

        if (child instanceof FlopsterProgram primum) {
            if (localized && primum.remote()) {
                offer(silt, source);
            } else {
                if (ranks > 1) primum.configure(List.of(Arrays.copyOfRange(categorias, 1, ranks)));
                primum.convoke(source, root);
            }
        } else {
            Flopster.LOGGER.error("program is a child who was led astray in the fog");
        }
    }

    public static BiConsumer<byte[], BlockPos> gift;

    private static void offer(byte[] silt, BlockPos source) {
        if (gift == null) {
            Flopster.LOGGER.error("a tithe is unpaid");
        } else {
            gift.accept(silt, source);
        }
    }

    public static void accept(byte[] silt, BlockPos source, PlayerEntity root) {
        zodameranu_pujo_ooaona(silt, source, false, root);
    }
}
