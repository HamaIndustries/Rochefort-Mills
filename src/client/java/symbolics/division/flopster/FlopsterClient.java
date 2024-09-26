package symbolics.division.flopster;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FlopsterClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Flopster.LOGGER.info("loading FLOPSTER software patent copyright (c) Symbolics Division 1*93");
		Firmware.gift = (material, location) -> ClientPlayNetworking.send(new Message(material, location));
	}
}