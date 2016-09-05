package janluca.quidditch;

import janluca.quidditch.entities.RegisterEntities;
import janluca.quidditch.items.QuaffelItem;
import janluca.quidditch.items.BroomItem;
import janluca.quidditch.proxies.CommonSide;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Quidditch.MOD_ID)
public class Quidditch {
    public static final String MOD_ID = "quidditch";


    @Mod.Instance(MOD_ID)
    public static Quidditch instance = new Quidditch();

    public static BroomItem broom;
    public static QuaffelItem quaffel;

    private RegisterEntities entities;

    @SidedProxy(clientSide = "janluca.quidditch.proxies.ClientSide", serverSide = "janluca.quidditch.proxies.CommonSide")
    public static CommonSide proxy = new CommonSide();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        broom = new BroomItem();
        quaffel = new QuaffelItem();
        proxy.registerRenderThings();
        KeyBindings.init();

        ItemStack stick = new ItemStack(Item.getByNameOrId("stick")), wheat = new ItemStack(Item.getByNameOrId("wheat"));

        GameRegistry.addRecipe(new ItemStack(Item.getByNameOrId("quidditch:broom")),
                "A  ",
                " A ",
                "  B",
                'A', stick, 'B', wheat);


    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        entities = new RegisterEntities();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.registerModels();
    }


}
