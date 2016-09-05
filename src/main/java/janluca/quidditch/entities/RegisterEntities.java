package janluca.quidditch.entities;

import janluca.quidditch.Quidditch;
import net.minecraftforge.fml.common.registry.EntityRegistry;

/**
 * Created by Laurin on 04.09.2016.
 */
public class RegisterEntities {
    public static int quaffel = 0;

    public RegisterEntities() {
        register();
    }

    private void register() {
        EntityRegistry.registerModEntity(EntityQuaffel.class, "quaffel", quaffel, Quidditch.instance, 64, 1, true);
    }
}
