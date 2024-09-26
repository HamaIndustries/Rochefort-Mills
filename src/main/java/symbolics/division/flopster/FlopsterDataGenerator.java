package symbolics.division.flopster;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.TextureMap;

public class FlopsterDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(FlopsterModels::new);
	}

	private static class FlopsterModels extends FabricModelProvider {
		public FlopsterModels(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			var tex = TextureMap.sideFrontTop(Flopster.FLOPSTER_DRIVE);
			blockStateModelGenerator.registerNorthDefaultHorizontalRotatable(Flopster.FLOPSTER_DRIVE, tex);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {

		}
	}
}
