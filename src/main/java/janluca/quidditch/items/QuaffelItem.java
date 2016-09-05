package janluca.quidditch.items;

import janluca.quidditch.entities.EntityQuaffel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Laurin on 04.09.2016.
 */
public class QuaffelItem extends Item {
    public QuaffelItem(){
        setRegistryName("quidditch", "quaffel");
        setUnlocalizedName("quaffel");
        GameRegistry.register(this);
        initModel();
        setCreativeTab(CreativeTabs.MISC);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        if(!worldIn.isRemote) {
            EntityQuaffel quaffel = new EntityQuaffel(worldIn, playerIn);
            Vec3d lookDir = playerIn.getLookVec();
            quaffel.setPosition(playerIn.posX + lookDir.xCoord, playerIn.posY + (double)playerIn.getEyeHeight() - 0.10000000149011612D, playerIn.posZ + lookDir.zCoord);
            quaffel.setThrowableHeading(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 2.0F, 0.0F);
            worldIn.spawnEntityInWorld(quaffel);
        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
