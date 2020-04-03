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
            if(p.getItemInHand().getType() == Material.DIAMOND_HOE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&6Harvester Hoe"))){
                e.setCancelled(true);

                List<Block> blocks = getSugarCane(b);
                for(ListIterator iterator = blocks.listIterator(blocks.size()); iterator.hasPrevious();){
                    final Block listB = (Block) iterator.previous();
                    listB.setType(Material.AIR);
                }
                //invig
                int invigLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(2).charAt(18)));
                switch (invigLevel){
                    case 1:
                        int rangeOne = 2 - 1 + 1;
                        //rand == (math::random * range) + min
                        int randOne = (int) (Math.random() * rangeOne) + 1;
                        int newRandOneEssence = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId()) + randOne;
                        HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newRandOneEssence);
                        break;
                    case 2:
                        int rangeTwo = 3 - 1 + 1;
                        int randTwo = (int) (Math.random() * rangeTwo) + 1;
                        int newRandTwoEssence = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId()) + randTwo;
                        HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newRandTwoEssence);
                        break;
                    case 3:
                        int rangeThree = 5 - 2 +1;
                        int randThree = (int) (Math.random() * rangeThree) + 1;
                        int newRandThreeEssence = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId()) + randThree;
                        HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newRandThreeEssence);
                        break;
                    default:
                        int newEssence = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId()) + 1;
                        HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newEssence);
                }

                //fortune
                int fortuneLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(3).charAt(13)));
                switch (fortuneLevel){
                    case 1:
                        //range == max - min + 1
                        int rangeOne = 2 - 1 + 1;
                        //rand == (math::random * range) + min
                        int randOne = (int) (Math.random() * rangeOne) + 1;
                        p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size() * randOne));
                        break;
                    case 2:
                        int rangeTwo = 3 - 1 + 1;
                        int randTwo = (int) (Math.random() * rangeTwo) + 1;
                        p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size() *randTwo));
                        break;
                    case 3:
                        int rangeThree = 5 - 2 +1;
                        int randThree = (int) (Math.random() * rangeThree) + 1;
                        p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size() * randThree));
                        break;
                    default:
                        p.getInventory().addItem(new ItemStack(Material.SUGAR_CANE, blocks.size()));
                }

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
