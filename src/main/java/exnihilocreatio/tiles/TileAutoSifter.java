package exnihilocreatio.tiles;

import exnihilocreatio.config.ModConfig;
import exnihilocreatio.rotationalPower.CapabilityRotationalMember;
import exnihilocreatio.rotationalPower.IRotationalPowerConsumer;
import exnihilocreatio.util.BlockInfo;
import exnihilocreatio.util.Util;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.vecmath.Point3f;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TileAutoSifter extends BaseTileEntity implements ITickable, IRotationalPowerConsumer {
    public final List<Tuple<Point3f, EnumFacing.Axis>> connectionPieces = new ArrayList<>();
    public final ItemHandlerAutoSifter itemHandlerAutoSifter;
    public TileSieve[][] toSift = null;
    public EnumFacing facing = EnumFacing.NORTH;
    private int tickCounter = 0;
    public float rotationValue = 0;
    public float perTickRotation = 0;
    public float storedRotationalPower = 0;
    public float offsetX = 0;
    public float offsetY = 0;
    public float offsetZ = 0;

    public TileAutoSifter() {
        itemHandlerAutoSifter = new ItemHandlerAutoSifter();
        itemHandlerAutoSifter.setTe(this);
    }

    @Override
    public void update() {
        tickCounter++;

        int rotationSpeed = 10;
        if (world.isRemote && perTickRotation != 0) {
            offsetX = ModConfig.client.clientAutoSieveDisplacement * (float) Math.cos(tickCounter * 2f * Math.PI / rotationSpeed);
            offsetZ = ModConfig.client.clientAutoSieveDisplacement * (float) Math.sin(tickCounter * 2f * Math.PI / rotationSpeed);
        }

        if (tickCounter > 0 && tickCounter % rotationSpeed == 0) {
            perTickRotation = calcEffectivePerTickRotation(world, pos, facing);

            updateToSift();

            tickCounter = 0;
        }

        storedRotationalPower += perTickRotation;

        if (Math.abs(storedRotationalPower) > 100 && toSift != null) {
            storedRotationalPower += storedRotationalPower > 0 ? -100 : 100;
            if (!world.isRemote) {
                doAutoSieving(toSift);
            }
        }


        if (world.isRemote) {
            rotationValue += perTickRotation;

            if (tickCounter % rotationSpeed == 0) {
                calculateConnectionPieces();
            }
        }
    }

    private void updateToSift() {
        BlockPos posOther = pos.up();
        TileEntity te = world.getTileEntity(posOther);

        if (te instanceof TileSieve)
            toSift = collectPossibleSieves((TileSieve) te);
        else
            toSift = null;
    }

    private void calculateConnectionPieces() {
        connectionPieces.clear();

        if (toSift != null) {
            for (int x = 0; x < toSift.length; x++) {
                for (int z = 0; z < toSift.length; z++) {
                    if (toSift[x][z] != null) {
                        if (x + 1 < toSift.length) {
                            if (toSift[x + 1][z] != null) {
                                connectionPieces.add(new Tuple<>(new Point3f(x + 0.5F - ModConfig.sieve.autoSieveRadius, 0.3F, z - ModConfig.sieve.autoSieveRadius), EnumFacing.Axis.X));
                            }
                        }
                        if (z + 1 < toSift.length) {
                            if (toSift[x][z + 1] != null) {
                                connectionPieces.add(new Tuple<>(new Point3f(x - ModConfig.sieve.autoSieveRadius, 0.3F, z + 0.5F - ModConfig.sieve.autoSieveRadius), EnumFacing.Axis.Z));
                            }
                        }

                    }
                }
            }
        }
    }


    private TileSieve[][] collectPossibleSieves(TileSieve thisSieve) {
        BlockPos sievePos = thisSieve.getPos();

        TileSieve[][] sieveMap = new TileSieve[ModConfig.sieve.autoSieveRadius * 2 + 1][ModConfig.sieve.autoSieveRadius * 2 + 1];

        Queue<BlockPos> toCheck = new LinkedList<>();

        toCheck.add(sievePos);
        while(!toCheck.isEmpty()) {
            final BlockPos curPos = toCheck.poll();
            if(curPos == null)
                break;
            final int iX = curPos.getX() - sievePos.getX() + ModConfig.sieve.autoSieveRadius;
            final int iZ = curPos.getZ() - sievePos.getZ() + ModConfig.sieve.autoSieveRadius;
            if(!validIndex(iX, iZ))
                continue;
            if(sieveMap[iX][iZ] == null) {
                if(isValidPartnerSieve(world.getTileEntity(curPos))) {
                    sieveMap[iX][iZ] = (TileSieve)world.getTileEntity(curPos);
                    for(EnumFacing face : EnumFacing.HORIZONTALS)
                        toCheck.add(curPos.offset(face));
                }
            }
        }

        return sieveMap;
    }

    public boolean isConnected(TileSieve sieve) {
        final int iX = sieve.getPos().getX() - pos.getX() + ModConfig.sieve.autoSieveRadius;
        final int iZ = sieve.getPos().getZ() - pos.getZ() + ModConfig.sieve.autoSieveRadius;
        final int dy = sieve.getPos().getY() - pos.getY();
        return !(dy != 1 || toSift[iX][iZ] == null);
    }

    private boolean validIndex(int x, int z) {
        return x >= 0 && x < 2*ModConfig.sieve.autoSieveRadius+1 &&
                z >= 0 && z < 2*ModConfig.sieve.autoSieveRadius+1;
    }

    private boolean isValidPartnerSieve(TileEntity tileOther) {
        if (tileOther instanceof TileSieve) {

            TileSieve sieve = (TileSieve) tileOther;
            sieve.validateAutoSieve();

            if (sieve.autoSifter == this) {
                return true;
            } else if (sieve.autoSifter == null) {
                sieve.autoSifter = this;
                return true;
            }
        }
        return false;
    }

    private void doAutoSieving(TileSieve[][] sieveMap) {
        for (TileSieve[] tileSieves : sieveMap) {
            for (TileSieve tileSieve : tileSieves) {
                if (tileSieve != null) {
                    if (!itemHandlerAutoSifter.getStackInSlot(0).isEmpty() && tileSieve.addBlock(itemHandlerAutoSifter.getStackInSlot(0))) {
                        itemHandlerAutoSifter.getStackInSlot(0).shrink(1);
                    }
                    tileSieve.doSieving(null, true);
                }
            }
        }
    }

    @Override
    public float getMachineRotationPerTick() {
        return perTickRotation;
    }

    @Override
    public void setEffectivePerTickRotation(float rotation) {
        perTickRotation = rotation;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandlerAutoSifter);
        if (capability == CapabilityRotationalMember.ROTIONAL_MEMBER)
            return CapabilityRotationalMember.ROTIONAL_MEMBER.cast(this);
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ||
                (capability == CapabilityRotationalMember.ROTIONAL_MEMBER && facing == this.facing) ||
                super.hasCapability(capability, facing);
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        // if (currentItem != null) {
        //     NBTTagCompound currentItemTag = currentItem.writeToNBT(new NBTTagCompound());
        //     tag.setTag("currentItem", currentItemTag);
        // } TODO: implement


        NBTTagCompound itemHandlerTag = itemHandlerAutoSifter.serializeNBT();
        tag.setTag("itemHandler", itemHandlerTag);

        if (facing != null)
            tag.setString("facing", facing.getName());

        tag.setFloat("rot", rotationValue);
        tag.setFloat("sRot", storedRotationalPower);

        return super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // if (tag.hasKey("currentItem")) {
        //     currentItem = ItemInfo.readFromNBT(tag.getCompoundTag("currentItem"));
        // } else {
        //     currentItem = null;
        // } TODO: see above

        if (tag.hasKey("itemHandler")) {
            itemHandlerAutoSifter.deserializeNBT((NBTTagCompound) tag.getTag("itemHandler"));
        }

        if (tag.hasKey("facing"))
            facing = EnumFacing.byName(tag.getString("facing"));

        if (tag.hasKey("rot"))
            rotationValue = tag.getFloat("rot");

        if (tag.hasKey("sRot"))
            storedRotationalPower = tag.getFloat("sRot");


        super.readFromNBT(tag);
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture() {
        if (!itemHandlerAutoSifter.getStackInSlot(0).isEmpty()) {
            return Util.getTextureFromBlockState(new BlockInfo(itemHandlerAutoSifter.getStackInSlot(0)).getBlockState());
            //TODO: MOVE BLOCKINFO OUT -> should not get recreated each frame
        }

        return null;
    }
}
