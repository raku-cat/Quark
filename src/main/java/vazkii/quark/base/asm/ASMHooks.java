package vazkii.quark.base.asm;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.quark.automation.client.render.PistonTileEntityRenderer;
import vazkii.quark.automation.feature.PistonSpikes;
import vazkii.quark.automation.feature.PistonsMoveTEs;
import vazkii.quark.management.feature.BetterCraftShifting;
import vazkii.quark.misc.feature.ColorRunes;
import vazkii.quark.tweaks.feature.ImprovedMountHUD;
import vazkii.quark.tweaks.feature.ImprovedSleeping;
import vazkii.quark.vanity.client.emotes.base.EmoteHandler;
import vazkii.quark.vanity.client.render.BoatBannerRenderer;
import vazkii.quark.vanity.feature.BoatSails;

public final class ASMHooks {

	// ===== EMOTES ===== //
	
	public static void updateEmotes(Entity e) {
		EmoteHandler.updateEmotes(e);
	}

	// ===== COLOR RUNES ===== //
	
	public static void setColorRuneTargetStack(ItemStack stack) {
		ColorRunes.setTargetStack(stack);
	}
	
	public static int getRuneColor() {
		return ColorRunes.getColor();
	}
	
	public static void applyRuneColor(float f1, float f2, float f3, float f4) {
		ColorRunes.applyColor(f1, f2, f3, f4);
	}
	
	// ===== BOAT SAILS ===== //
	
	public static void onBoatUpdate(EntityBoat boat) {
		BoatSails.onBoatUpdate(boat);
	}
	
	public static void dropBoatBanner(EntityBoat boat) {
		BoatSails.dropBoatBanner(boat);
	}
	
	@SideOnly(Side.CLIENT)
	public static void renderBannerOnBoat(EntityBoat boat, float pticks) {
		BoatBannerRenderer.renderBanner(boat, pticks);
	}

	// ===== PISTON BLOCK BREAKERS & PISTONS MOVE TES ===== //
	
	public static boolean breakStuffWithSpikes(World world, BlockPos sourcePos, List<BlockPos> moveList, List<BlockPos> destroyList, EnumFacing facing, boolean extending) {
		boolean res = PistonSpikes.breakStuffWithSpikes(world, sourcePos, moveList, destroyList, facing, extending); 
		PistonsMoveTEs.detachTileEntities(world, sourcePos, moveList, destroyList, facing, extending);
		return res;
	}	
	
	// ===== BETTER CRAFT SHIFTING ===== //
	
	public static int getInventoryBoundary(int curr) {
		return BetterCraftShifting.getInventoryBoundary(curr);
	}
	
	// ===== PISTONS MOVE TES ===== //
	
	public static boolean shouldPistonMoveTE(boolean te, IBlockState state) {
		return PistonsMoveTEs.shouldMoveTE(te, state);
	}
	
	public static boolean setPistonBlock(World world, BlockPos pos, IBlockState state, int flags) {
		return PistonsMoveTEs.setPistonBlock(world, pos, state, flags);
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean renderPistonBlock(BlockPos pos, IBlockState state, BufferBuilder buffer, World world, boolean checkSides) {
		return PistonTileEntityRenderer.renderPistonBlock(pos, state, buffer, world, checkSides);
	}
	
	// ===== IMPROVED SLEEPING ===== //

	public static boolean isEveryoneAsleep(World world) {
		return ImprovedSleeping.isEveryoneAsleep(world);
	}
	
}

	