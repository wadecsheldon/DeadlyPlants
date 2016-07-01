package com.obsoleet37.deadlyplants.block;

import net.minecraft.block.BlockCrops;

import com.obsoleet37.deadlyplants.Constants;
import com.obsoleet37.deadlyplants.DeadlyPlants;
import com.obsoleet37.deadlyplants.item.Items;
import com.obsoleet37.deadlyplants.plant.Plants;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHemlock extends BlockCrops {
	
	public static final String name = "hemlock";
	
	public BlockHemlock() {
		super();
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(Constants.MODID + "_" + name);
	}
	
    protected Item getSeed()
    {
        return Plants.seedsHemlock;
    }

    protected Item getCrop()
    {
        return Plants.seedsHemlock;
    }

	public String getName() {
		return name;
	}
}