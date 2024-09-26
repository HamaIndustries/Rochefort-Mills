package symbolics.division.flopster;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flopster implements ModInitializer {
	public static final String MOD_ID = "flopster";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Block FLOPSTER_DRIVE = Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "flopster_drive"), new FlopsterDrive(AbstractBlock.Settings.copy(Blocks.STONE)));

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.playC2S().register(Message.ID, Message.CODEC);
		ServerPlayNetworking.registerGlobalReceiver(Message.ID, (m, c) -> Firmware.accept(m.subject(), m.location(), c.player()));
	}
}