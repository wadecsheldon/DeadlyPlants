package com.obsoleet37.deadlyplants.block;

//import com.obsoleet37.deadlyplants.Constants;
//import com.obsoleet37.deadlyplants.DeadlyPlants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
//import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
//import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClickableBlock extends Block {
	
	//private String name = "test";
	
	
	public ClickableBlock(Material material) {
		super(material);
	}
	
	
	public ClickableBlock(Material material, MapColor color) {
		super(material, color);
		//setHardness(1);
        //GameRegistry.registerBlock(this, name);
        //setUnlocalizedName(Constants.MODID + "_" + name);
        //setCreativeTab(DeadlyPlants.tabDeadlyPlantsBase);
	}
	
	
	//public String getName() {
		//return name;
	//}
    
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
    	if (player.isSneaking()) {
            onShiftRightClick(world, pos, state, player, side, hitX, hitY, hitZ);
        } else {
            onRightClick(world, pos, state, player, side, hitX, hitY, hitZ);
        }
        return true;
    }
    
    
    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
        if (player.isSneaking()) {
            onShiftLeftClick(world, pos, player);
        } else {
            onLeftClick(world, pos, player);
        }
    }
    
    
    public void onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
    	return;
    }
    
    
    public void onShiftRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
    	return;
    }
    
    
    public void onLeftClick(World world, BlockPos pos, EntityPlayer player) {
    	return;
    }
    
    
    public void onShiftLeftClick(World world, BlockPos pos, EntityPlayer player) {
    	return;
    }

}
