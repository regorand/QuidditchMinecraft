package janluca.quidditch.items;


import janluca.quidditch.KeyBindings;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
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
    private boolean hasLanded = true;

    public BroomItem(){
        setRegistryName("quidditch", "broom");
        setUnlocalizedName("broom");
        GameRegistry.register(this);
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
            if (entityIn instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entityIn;
                Vec3d playerVec = player.getLookVec();
                if(hasLanded) {
                    hasLanded = false;
                    broomVec = new Vec3d(playerVec.xCoord, playerVec.yCoord, playerVec.zCoord);
                }
                if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof BroomItem) {
                    player.moveRelative(0.5F, 0.0F, 0.1F);
                    player.capabilities.isFlying = true;
                    if(KeyBindings.w.isKeyDown()) {
                    }
                    if(KeyBindings.a.isKeyDown()) {
                    }
                    if(KeyBindings.s.isKeyDown()) {
                    }
                    if(KeyBindings.d.isKeyDown()) {
                    }
                } else {
                    if(player.onGround) {
                        hasLanded = true;
                    }
                }
            }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if(!worldIn.isRemote) {
            Vec3d lookDir = playerIn.getLookVec();
            playerIn.addChatMessage(new TextComponentString(Double.toString(lookDir.xCoord)));
            playerIn.addChatMessage(new TextComponentString(Double.toString(lookDir.yCoord)));
            playerIn.addChatMessage(new TextComponentString(Double.toString(lookDir.zCoord)));
        }

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        return EnumActionResult.PASS;
    }

    public void throwSnowball(World worldIn, EntityPlayer playerIn){
        if(!worldIn.isRemote) {
            EntityThrowable z = new EntitySnowball(worldIn);
            Vec3d lookDir = playerIn.getLookVec();
            z.setPosition(playerIn.posX, playerIn.posY + (double) playerIn.getEyeHeight() - 0.10000000149011612D, playerIn.posZ);
            z.setThrowableHeading(lookDir.xCoord, lookDir.yCoord, lookDir.zCoord, 1, 0);
            worldIn.spawnEntityInWorld(z);
        }
    }

    private void noShiftFly(EntityPlayer playerIn) {
        Vec3d lookDir = playerIn.getLookVec();
        double xzCoord = Math.sqrt((lookDir.xCoord*lookDir.xCoord) + (lookDir.zCoord*lookDir.zCoord));
    }

    private void shiftFly(EntityPlayer playerIn) {

    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}