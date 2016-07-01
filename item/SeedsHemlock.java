package com.obsoleet37.deadlyplants.item;

import com.obsoleet37.deadlyplants.Constants;
import com.obsoleet37.deadlyplants.DeadlyPlants;
import com.obsoleet37.deadlyplants.plant.Plants;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SeedsHemlock extends ItemSeedFood {
	
	public static final String name = "seedsHemlock";
	
	public SeedsHemlock() {
		super(0, 0, Plants.hemlock, Blocks.dirt);
		setAlwaysEdible();
		setPotionEffect(19, 10, 1, 1);
        GameRegistry.registerItem(this, name);
        setUnlocalizedName(Constants.MODID + "_" + name);
        setCreativeTab(DeadlyPlants.tabDeadlyPlantsBase);
	}
	
	@Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Plains;
    }

	public String getName() {
		return name;
	}

}
