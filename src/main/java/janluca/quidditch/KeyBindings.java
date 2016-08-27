package janluca.quidditch;


import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by Laurin on 22.08.2016.
 */
public class KeyBindings {

    public static KeyBinding w;
    public static KeyBinding a;
    public static KeyBinding s;
    public static KeyBinding d;
    public static KeyBinding shift;

    public static void init() {
        w = new KeyBinding("key.w", Keyboard.KEY_W, "key.categories.quidditch");
        a = new KeyBinding("key.a", Keyboard.KEY_A, "key.categories.quidditch");
        s = new KeyBinding("key.s", Keyboard.KEY_S, "key.categories.quidditch");
        d = new KeyBinding("key.d", Keyboard.KEY_D, "key.categories.quidditch");
        shift = new KeyBinding("key.shift", Keyboard.KEY_LMENU, "key.categories.quidditch");

        ClientRegistry.registerKeyBinding(w);
        ClientRegistry.registerKeyBinding(a);
        ClientRegistry.registerKeyBinding(s);
        ClientRegistry.registerKeyBinding(d);
    }
}
