package me.krispin.hoes;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class HoeListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        if(b.getType() == Material.SUGAR_CANE_BLOCK){
            Player p = e.getPlayer();
            if(p.getItemInHand().isSimilar(Utils.getHoe())){
                e.setCancelled(true);
                List<Block> blocks = getSugarCane(b);
                for(ListIterator iterator = blocks.listIterator(blocks.size()); iterator.hasPrevious();){
                    final Block listB = (Block) iterator.previous();
                    listB.setType(Material.AIR);
                }
                 p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size()));
            }
        }
    }

    private List<Block> getSugarCane(Block b){
        List<Block> blocks = new ArrayList<>();
        blocks.add(b);
        Location loc = b.getLocation().clone().add(0, 1,0);
        while(loc.getBlock().getType() == Material.SUGAR_CANE_BLOCK){
            blocks.add(loc.getBlock());
            loc.add(0,1,0);
        }
        return blocks;
    }
}
