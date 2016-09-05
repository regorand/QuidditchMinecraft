package janluca.quidditch.proxies;

import janluca.quidditch.Quidditch;
import janluca.quidditch.entities.EntityQuaffel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

/**
 * Created by Laurin on 21.08.2016.
 */
public class ClientSide extends CommonSide {
    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityQuaffel.class, new IRenderFactory<EntityQuaffel>() {
            @Override
            public Render<? super EntityQuaffel> createRenderFor(RenderManager manager) {
                return new RenderSnowball<EntityQuaffel>(manager, Quidditch.quaffel, Minecraft.getMinecraft().getRenderItem());
            }
        });
    }

    @Override
    public void registerModels() {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Quidditch.quaffel, 0, new ModelResourceLocation(Quidditch.quaffel.getRegistryName(), "inventory"));
    }

}