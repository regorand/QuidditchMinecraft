package janluca.quidditch.items;


import janluca.quidditch.KeyBindings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


/**
 * Created by Jan-Luca on 16.08.2016.
 */
public class BroomItem extends Item {

    private Vec3d broomVec;
    private boolean updateLookVec = true;

    public BroomItem(){
        setRegistryName("quidditch", "broom");
        setUnlocalizedName("broom");
        GameRegistry.register(this);
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                Vec3d playerLookVec = player.getLookVec();
                if(updateLookVec) {
                    broomVec = new Vec3d(playerLookVec.xCoord, playerLookVec.yCoord, playerLookVec.zCoord);
                }
                if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof BroomItem) {
                    player.capabilities.isFlying = true;
                    if(KeyBindings.shift.isKeyDown()) {
                        updateLookVec = false;
                        shiftFly(player);
                    } else {
                        updateLookVec = true;
                        noShiftFly(player);
                    }
                }
            }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(!worldIn.isRemote) {
            Vec3d lookDir = playerIn.getLookVec();
            playerIn.addChatMessage(new TextComponentString(Double.toString(broomVec.xCoord)));
            playerIn.addChatMessage(new TextComponentString(Double.toString(lookDir.xCoord)));
        }

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        return EnumActionResult.PASS;
    }

    /*public void throwSnowball(World worldIn, EntityPlayer playerIn){
        if(!worldIn.isRemote) {
            EntityThrowable z = new EntitySnowball(worldIn);
            Vec3d lookDir = playerIn.getLookVec();
            z.setPosition(playerIn.posX, playerIn.posY + (double) playerIn.getEyeHeight() - 0.10000000149011612D, playerIn.posZ);
            z.setThrowableHeading(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 1, 0);
            worldIn.spawnEntityInWorld(z);
        }
    }*/

    private void noShiftFly(EntityPlayer playerIn) {
        Vec3d lookDir = playerIn.getLookVec();
        Vec3d moveDir = lookDir;

        if(KeyBindings.w.isKeyDown()) {
            if(KeyBindings.a.isKeyDown()) {
                moveDir = moveDir.rotateYaw(0.5f);
            }
            if(KeyBindings.d.isKeyDown()) {
                moveDir = moveDir.rotateYaw(-0.5f);
            }
            playerIn.setVelocity(moveDir.xCoord, moveDir.yCoord, moveDir.zCoord);
        }
        if(KeyBindings.s.isKeyDown()) {
            if(KeyBindings.a.isKeyDown()) {
                moveDir = moveDir.rotateYaw(-0.5f);
            }
            if(KeyBindings.d.isKeyDown()) {
                moveDir = moveDir.rotateYaw(0.5f);
            }
            playerIn.setVelocity(-moveDir.xCoord, -moveDir.yCoord, -moveDir.zCoord);
        }
    }

    private void shiftFly(EntityPlayer playerIn) {
        if(KeyBindings.w.isKeyDown()) {
            if(broomVec.yCoord >= -1.0) {
                broomVec = new Vec3d(broomVec.xCoord, broomVec.yCoord-0.05, broomVec.zCoord);
            }
        }
        if(KeyBindings.a.isKeyDown()) {
                broomVec = broomVec.rotateYaw(0.05f);
        }
        if(KeyBindings.s.isKeyDown()) {
            if(broomVec.yCoord <= 1.0) {
                broomVec = new Vec3d(broomVec.xCoord, broomVec.yCoord+0.05, broomVec.zCoord);
            }
        }
        if(KeyBindings.d.isKeyDown()) {
            broomVec = broomVec.rotateYaw(-0.05f);
        }
        playerIn.setVelocity(broomVec.xCoord, broomVec.yCoord, broomVec.zCoord);
    }


    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}