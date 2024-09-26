package symbolics.division.flopster;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public record Message(byte[] subject, BlockPos location) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, Message> CODEC = CustomPayload.codecOf(Message::write, Message::new);
    public static final CustomPayload.Id<Message> ID = new CustomPayload.Id<>(Identifier.of(Flopster.MOD_ID, "message"));

    private Message(PacketByteBuf buf) {
        this(buf.readByteArray(), buf.readBlockPos());
    }

    private void write(PacketByteBuf buf) {
        buf.writeByteArray(subject);
        buf.writeBlockPos(location);
    }

    @Override
    public CustomPayload.Id<Message> getId() {
        return ID;
    }
}
