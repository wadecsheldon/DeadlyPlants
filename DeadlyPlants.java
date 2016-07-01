package com.obsoleet37.deadlyplants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.obsoleet37.deadlyplants.block.BlockHemlock;
import com.obsoleet37.deadlyplants.block.ClickableBlock;
import com.obsoleet37.deadlyplants.item.SeedsHemlock;
import com.obsoleet37.deadlyplants.plant.Plants;
import com.obsoleet37.deadlyplants.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Constants.MODID, version = Constants.VERSION)
public class DeadlyPlants {
    
    @Mod.Instance(Constants.MODID)
    public static DeadlyPlants instance;
    
    
    public static Block test;
    
       
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
     
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	test = new ClickableBlock(Material.rock, MapColor.goldColor);
    	
    	Potion[] potionTypes = null;

        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[])f.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    f.set(null, newPotionTypes);
                }
            } catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }
    }
    
    public static RecipeHandler recipeHandler = new RecipeHandler();
    
    @EventHandler
    public void init(FMLInitializationEvent event) throws Exception
    {
    	proxy.load();
    	
    	recipeHandler.parseRecipe(new ResourceLocation("deadlyplants:recipe/test"));
		recipeHandler.parseRecipe(new ResourceLocation("deadlyplants:recipe/test2"));
		recipeHandler.parseRecipe(new ResourceLocation("deadlyplants:recipe/test3"));
		recipeHandler.parseRecipe(new ResourceLocation("deadlyplants:recipe/test4"));
    	
    	if(event.getSide() == Side.CLIENT)
    	{
    		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    		
    		//blocks
    		//renderItem.getItemModelMesher().register(Item.getItemFromBlock(Plants.hemlock), 0, new ModelResourceLocation(Constants.MODID + ":" + ((BlockHemlock) Plants.hemlock).getName(), "inventory"));

    		renderItem.getItemModelMesher().register(Plants.seedsHemlock, 0, new ModelResourceLocation(Constants.MODID + ":" + ((SeedsHemlock) Plants.seedsHemlock).getName(), "inventory"));
    	}
    }
    
    
    public static CreativeTabs tabDeadlyPlantsBase = new CreativeTabs("tabDeadlyPlantsBase") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return Items.bread;
        }
    };
    
}

