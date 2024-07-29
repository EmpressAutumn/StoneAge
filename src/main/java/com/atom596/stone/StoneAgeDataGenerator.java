package com.atom596.stone;

import com.atom596.stone.datagen.StoneAgeBlockLootTableProvider;
import com.atom596.stone.datagen.StoneAgeModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class StoneAgeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(StoneAgeBlockLootTableProvider::new);
		pack.addProvider(StoneAgeModelProvider::new);
	}
}
