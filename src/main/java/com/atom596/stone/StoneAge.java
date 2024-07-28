package com.atom596.stone;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoneAge implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("stone_age");

	@Override
	public void onInitialize() {
		LOGGER.info("Initialized Stone Age by EmpressAutumn");
	}
}
