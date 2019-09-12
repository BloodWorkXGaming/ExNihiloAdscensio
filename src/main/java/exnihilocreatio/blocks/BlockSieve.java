package exnihilocreatio.blocks;

import java.util.*;

import javax.annotation.Nonnull;

import exnihilocreatio.ExNihiloCreatio;
import exnihilocreatio.compatibility.ITOPInfoProvider;
import exnihilocreatio.config.ModConfig;
import exnihilocreatio.items.ItemMesh;
import exnihilocreatio.tiles.TileSieve;
import exnihilocreatio.util.ItemStackItemHandler;
import exnihilocreatio.util.Util;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockSieve extends BlockBase implements ITileEntityProvider, ITOPInfoProvider {

	public static final PropertyEnum<MeshType> MESH = PropertyEnum.create("mesh", MeshType.class);

	public BlockSieve() {
		super(Material.WOOD, "block_sieve");
		setHardness(2.0f);
		setCreativeTab(ExNihiloCreatio.tabExNihilo);
		setDefaultState(blockState.getBaseState().withProperty(MESH, MeshType.NO_RENDER));
	}

	@Override
	public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing side, final float hitX, final float hitY, final float hitZ) {
		if (world.isRemote) {
			return true;
		}

		// I think this should work. Let's just go with it.
		if (player instanceof FakePlayer && !ModConfig.mechanics.fakePlayersCanSieve) {
			return false;
		}
		final TileSieve te = (TileSieve) world.getTileEntity(pos);

		if (te == null) {
			return true;
		}

		final ItemStack heldItem = player.getHeldItem(hand);

		// Removing a mesh
		if (heldItem.isEmpty() && !te.getMeshStack().isEmpty() && player.isSneaking() && te.setMesh(ItemStack.EMPTY, true)) {
			//Removing a mesh.
			Util.dropItemInWorld(te, player, te.getMeshStack(), 0.02f);
			te.setMesh(ItemStack.EMPTY, false);
			return true;
		}

		// Inserting blocks
		IItemHandler cap = null;
		if (ModConfig.compatibility.generalItemHandlerCompat) {
			cap = heldItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		}
		if (cap == null) {
			cap = new ItemStackItemHandler(heldItem);
		}

		int slot = 0;
		final int maxSlot = cap.getSlots();
		for (; slot < maxSlot; slot++) {
			final ItemStack stack = cap.getStackInSlot(slot);
			if (!stack.isEmpty() && stack.getItem() instanceof ItemMesh) {
				// Adding a mesh
				final boolean added = te.setMesh(cap.extractItem(slot, 1, true));
				if (added) {
					cap.extractItem(slot, 1, player.isCreative());
					return true;
				}
			}
			if (te.addBlock(cap.extractItem(slot, 1, true))) {
				// Adding a block
				cap.extractItem(slot, 1, player.isCreative());
				for (int dx = -ModConfig.sieve.sieveSimilarRadius; dx <= ModConfig.sieve.sieveSimilarRadius; dx++) {
					for (int dz = -ModConfig.sieve.sieveSimilarRadius; dz <= ModConfig.sieve.sieveSimilarRadius; dz++) {
						if (cap.getStackInSlot(slot).isEmpty()) {
							continue; // No more items
						}
						final TileEntity otherTE = world.getTileEntity(pos.add(dx, 0, dz));
						if (!(otherTE instanceof TileSieve)) {
							continue; // Not a sieve
						}
						final TileSieve sieve = (TileSieve) otherTE;
						if (!te.isSieveSimilarToInput(sieve)) {
							continue; // Not a similar sieve
						}
						if (sieve.addBlock(cap.extractItem(slot, 1, true))) {
							cap.extractItem(slot, 1, player.isCreative());
						}
					}
				}
				return true;
			}
		}

		final List<BlockPos> toSift = new ArrayList<>();
		for (int xOffset = -1 * ModConfig.sieve.sieveSimilarRadius; xOffset <= ModConfig.sieve.sieveSimilarRadius; xOffset++) {
			for (int zOffset = -1 * ModConfig.sieve.sieveSimilarRadius; zOffset <= ModConfig.sieve.sieveSimilarRadius; zOffset++) {
				final TileEntity entity = world.getTileEntity(pos.add(xOffset, 0, zOffset));
				if (entity instanceof TileSieve) {
					final TileSieve sieve = (TileSieve) entity;

					if (te.isSieveSimilar(sieve)) {
						toSift.add(pos.add(xOffset, 0, zOffset));
					}
				}
			}
		}
		for (final BlockPos posIter : toSift) {
			if (posIter != null) {
				final TileSieve sieve = (TileSieve) world.getTileEntity(posIter);
				if (sieve != null) {
					sieve.doSieving(player, false);
				}
			}
		}
		return true;
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MESH);
	}

	@Override
	@Nonnull
	@Deprecated
	public IBlockState getStateFromMeta(final int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(final IBlockState state) {
		return 0;
	}

	@Override
	public void breakBlock(@Nonnull final World world, @Nonnull final BlockPos pos, @Nonnull final IBlockState state) {
		final TileEntity te = world.getTileEntity(pos);
		if (te != null) {
			final TileSieve sieve = (TileSieve) te;
			if (!sieve.getMeshStack().isEmpty()) {
				Util.dropItemInWorld(sieve, null, sieve.getMeshStack(), 0.02f);
			}
		}

		super.breakBlock(world, pos, state);
	}

	@Override
	public TileEntity createNewTileEntity(@Nonnull final World worldIn, final int meta) {
		return new TileSieve();
	}

	//region >>>> RENDERING OPTIONS
	@Override
	@Deprecated
	public boolean isFullBlock(final IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(final IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(final IBlockState state) {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(final IBlockState blockState, @Nonnull final IBlockAccess blockAccess, @Nonnull final BlockPos pos, final EnumFacing side) {
		return false;
	}
	//endregion

	@Override
	public void addProbeInfo(final ProbeMode mode, final IProbeInfo probeInfo, final EntityPlayer player, final World world, final IBlockState blockState, final IProbeHitData data) {

		final TileSieve sieve = (TileSieve) world.getTileEntity(data.getPos());
		if (sieve == null) {
			return;
		}

		if (sieve.getMeshStack().isEmpty()) {
			probeInfo.text("Mesh: None");
			return;
		}

		probeInfo.text("Mesh: " + IProbeInfo.STARTLOC + sieve.getMeshStack().getTranslationKey() + ".name" + IProbeInfo.ENDLOC);

		if (mode == ProbeMode.EXTENDED) {
			final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(sieve.getMeshStack());
			for (final Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
				probeInfo.text(TextFormatting.BLUE + enchantment.getKey().getTranslatedName(enchantment.getValue()));
			}
		}

	}

	public enum MeshType implements IStringSerializable {
			NONE(0, "none"), STRING(1, "string"), FLINT(2, "flint"), IRON(3, "iron"), DIAMOND(4, "diamond"),
			NO_RENDER(5, "no_render");

		private final int id;
		private final String name;

		MeshType(final int id, final String name) {
			this.id = id;
			this.name = name;
		}

		public boolean isValid() {
			return id > 0 && id < 5;
		}

		public static MeshType getMeshTypeByID(final int meta) {
			switch (meta) {
			case 1:
				return STRING;
			case 2:
				return FLINT;
			case 3:
				return IRON;
			case 4:
				return DIAMOND;
			case 5:
				return NO_RENDER;
			}

			return NONE;
		}

		@Override
		@Nonnull
		public String getName() {
			return name;
		}

		public int getID() {
			return id;
		}

		@Override
		public String toString() {
			return getName();
		}
	}

}
