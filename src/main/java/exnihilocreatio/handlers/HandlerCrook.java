package exnihilocreatio.handlers;

import exnihilocreatio.config.ModConfig;
import exnihilocreatio.items.tools.ICrook;
import exnihilocreatio.registries.manager.ExNihiloRegistryManager;
import exnihilocreatio.registries.types.CrookReward;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class HandlerCrook {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void crook(BlockEvent.HarvestDropsEvent event) {
        if (event.getWorld().isRemote)
            return;

        if (event.getHarvester() == null)
            return;

        if (event.isSilkTouching())
            return;

        ItemStack held = event.getHarvester().getHeldItemMainhand();
        if (!isCrook(held))
            return;

        List<CrookReward> rewards = ExNihiloRegistryManager.CROOK_REGISTRY.getRewards(event.getState());
        if (rewards != null && rewards.size() > 0) {
            event.getDrops().clear();
            event.setDropChance(1f);

            int fortune = event.getFortuneLevel();
            for (CrookReward reward : rewards) {
                if (event.getWorld().rand.nextFloat() <= reward.getChance() + (reward.getFortuneChance() * fortune)) {
                    event.getDrops().add(reward.getStack().copy());
                }

            }
        }

        if (event.getState().getBlock() instanceof BlockLeaves) //Simulate vanilla drops without firing event
        {
            for (int i = 0; i < ModConfig.crooking.numberOfTimesToTestVanillaDrops + 1; i++) {
                Block block = event.getState().getBlock();
                int fortune = event.getFortuneLevel();
                java.util.List<ItemStack> items = block.getDrops(event.getWorld(), event.getPos(), event.getState(), fortune);
                for (ItemStack item : items) {
                    if (event.getWorld().rand.nextFloat() <= event.getDropChance()) {
                        Block.spawnAsEntity(event.getWorld(), event.getPos(), item);
                    }
                }
            }
        }
    }


    public boolean isCrook(ItemStack stack) {
        if (stack == null)
            return false;

        if (stack.getItem() == Items.AIR)
            return false;

        if (stack.getItem() instanceof ICrook)
            return ((ICrook) stack.getItem()).isCrook(stack);

        // Inspirations compatibility
        if (stack.getItem().getToolClasses(stack).contains("crook"))
            return true;

        return false;
    }
}
