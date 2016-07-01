package com.obsoleet37.deadlyplants;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.util.JsonUtils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeHandler {
	
	private class CraftingRecipe {
		private boolean isShapeless;
		private String outputIdentifier;
		private boolean outputIsItem;
		private int outputQuantity;
		private int outputMeta;
		private String[] pattern;
	}
	
	
	private ItemStack parseItemStack(JsonObject json) {
		Item item = null;
		if (json.has("isBlock") && json.get("isBlock").getAsBoolean()) {
			item = Item.getItemFromBlock(Block.getBlockFromName(json.get("name").getAsString()));
		} else {
			item = Item.getByNameOrId(json.get("name").getAsString());
		}
		return new ItemStack(item, JsonUtils.getIntOr("quantity", json, 1), JsonUtils.getIntOr("meta",  json,  0));
	}
	
	
	public boolean parseRecipe(ResourceLocation file) throws Exception{
		JsonObject raw = new JsonParser().parse(new InputStreamReader(getClass().getClassLoader()
				.getResourceAsStream("assets/" + file.getResourceDomain() + "/" + file.getResourcePath() + ".json"))).getAsJsonObject();
		
		try {
			//Parse the output information
			ItemStack output = parseItemStack(raw.get("output").getAsJsonObject());
			System.out.println(output);
			
			switch (raw.get("type").getAsString()) {
			
				case "shapeless":
					//Parse inputs for shapeless recipe
					ArrayList<ItemStack> inputList = new ArrayList<ItemStack>();
					JsonArray inputJson = raw.get("inputs").getAsJsonArray();
					for (JsonElement input: inputJson) {
						inputList.add(parseItemStack(input.getAsJsonObject()));
					}
					
					GameRegistry.addShapelessRecipe(output, inputList.toArray());
					break;
					
					
				case "shaped":
					//Parse pattern and key for shaped recipe
					ArrayList<Object> inputs = new ArrayList<Object>();
					JsonArray patternJson = raw.get("pattern").getAsJsonArray();
					for (JsonElement line: patternJson) {
						inputs.add(line.getAsString());
					}
					
					JsonArray keyJson = raw.get("key").getAsJsonArray();
					for (JsonElement entry: keyJson) {
						inputs.add(entry.getAsJsonObject().get("symbol").getAsCharacter());
						inputs.add(parseItemStack(entry.getAsJsonObject().get("item").getAsJsonObject()));
					}
					
					GameRegistry.addShapedRecipe(output, inputs.toArray());
					break;
					
					
				case "brewing":
					ItemStack ingredient = parseItemStack(raw.get("ingredient").getAsJsonObject());
					ItemStack base = parseItemStack(raw.get("base").getAsJsonObject());
					
					BrewingRecipeRegistry.addRecipe(base, ingredient, output);
					break;
					
					
				default:
					System.out.println("Invalid recipe type: " + raw.get("type").getAsString() + " in " + file);
			}
			
		} catch (Exception e) {
			System.out.println("Parse failed.");
			throw e;
			//return false;
		}
		return true;
	}

}