package janluca.quidditch.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;


/**
 * Created by Jan-Luca on 16.08.2016.
 */
public class BroomItem extends Item {

    public BroomItem(){
        setRegistryName("quidditch", "broom");
        setUnlocalizedName("broom");
        GameRegistry.register(this);

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(entityIn instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entityIn;
            if(!worldIn.isRemote && player.inventory.getStackInSlot(40).getUnlocalizedName().equals(this.getUnlocalizedName())){
                throwSnowball(worldIn, (EntityPlayer) entityIn);
            }
        }


    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(!worldIn.isRemote) {
            throwSnowball(worldIn, playerIn);
        }

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    public void throwSnowball(World worldIn, EntityPlayer playerIn){

        EntityThrowable z = new EntitySnowball(worldIn);
        Vec3d lookDir = playerIn.getLookVec();
        z.setPosition(playerIn.posX+lookDir.xCoord, playerIn.posY + (double)playerIn.getEyeHeight() - 0.10000000149011612D, playerIn.posZ+lookDir.zCoord);
        z.setThrowableHeading(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 1, 0);
        worldIn.spawnEntityInWorld(z);

    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
