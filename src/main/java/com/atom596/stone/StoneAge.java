package com.atom596.stone;

import com.atom596.stone.block.StoneAgeBlocks;
import com.atom596.stone.item.StoneAgeItemGroup;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoneAge implements ModInitializer {
	public static final String MOD_ID = "stone_age";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		StoneAgeBlocks.register();
		StoneAgeItemGroup.register();

		LOGGER.info("Initialized Stone Age by EmpressAutumn");
	}
}
