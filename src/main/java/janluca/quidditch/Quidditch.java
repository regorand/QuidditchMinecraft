package janluca.quidditch;

import janluca.quidditch.items.BroomItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "quidditch")
public class Quidditch {
    public static final String MOD_ID = "quidditch";

    public static BroomItem broom;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        broom = new BroomItem();
        broom.initModel();




        ItemStack stick = new ItemStack(Item.getByNameOrId("stick")), wheat = new ItemStack(Item.getByNameOrId("wheat"));


        GameRegistry.addRecipe(new ItemStack(Item.getByNameOrId("quidditch:broom")),
                "A  ",
                " A ",
                "  B",
                'A', stick, 'B', wheat);


    }

    @EventHandler
    public void init(FMLInitializationEvent event){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }


}
