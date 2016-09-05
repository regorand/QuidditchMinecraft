package janluca.quidditch.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * Created by Laurin on 04.09.2016.
 */
public class EntityQuaffel extends EntityThrowable {


    public EntityQuaffel(World worldIn) {
        super(worldIn);
    }

    public EntityQuaffel(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
            if (!worldObj.isRemote) {
            Minecraft.getMinecraft().theWorld.spawnEntityInWorld(new EntityItem(Minecraft.getMinecraft().theWorld,
                    result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(),
                    new ItemStack(Item.getByNameOrId("quidditch:quaffel"))));
                setDead();
            }
        }
    }
}
