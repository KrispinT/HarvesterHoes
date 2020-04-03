package me.krispin.hoes.upgrades;

import me.krispin.hoes.HarvesterHoes;
import me.krispin.hoes.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class UpgradeInventory implements Listener {
    private UpgradeInventory(){}
    private static UpgradeInventory instance = new UpgradeInventory();
    public static UpgradeInventory getInstance(){
        return instance;
    }

    private Inventory inv;

    public void resfreshInv(Player p){
        //title
        inv = Bukkit.createInventory(new UpgradeHolder(), 27, Utils.color("&eEssence: ❖" + HarvesterHoes.getInstance().getEssence().get(p.getUniqueId())));

        inv.clear();
        //filler
        for(int i = 0; i < 27; i++){
            inv.setItem(i, Utils.getinvFiller());
        }

        ItemStack hoe = HarvesterHoes.getInstance().getItemInHand().get(p.getUniqueId());

        //hustler
        int playerHustlerValue = Integer.valueOf(String.valueOf(hoe.getItemMeta().getLore().get(1).charAt(13))); //returns either 0,1,2,3
        List<String> hustlerLore = Utils.getHustlerLore();
        //current level
        hustlerLore.set(2, Utils.color("&8• &fLevel: &e" + playerHustlerValue + "/3"));
        //next level
        if(!(playerHustlerValue == 3)){
            int newHustlerLevel = playerHustlerValue + 1;
            hustlerLore.set(5, Utils.color("&8• &fLevel: &e " + newHustlerLevel + "/3"));
        }else{
            hustlerLore.set(5, Utils.color("&8• &fLevel: &cMAXED"));
        }
        //next cost
        switch (playerHustlerValue){
            case 0:
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(25000)));
                break;
            case 1:
                //1 to 2
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(50000)));
                break;
            case 2:
                //2 to 3
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(100000)));
                break;
            default:
                //
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖0"));
        }


        Utils.setHustlerLore(hustlerLore);

        //invig
        int playerInvigValue = Integer.valueOf(String.valueOf(hoe.getItemMeta().getLore().get(2).charAt(18)));
        List<String> invigLore = Utils.getInvigorationLore();
        //current level
        invigLore.set(2, Utils.color("&8• &fLevel: &e" + playerInvigValue+ "/3"));
        //next level
        if(!(playerInvigValue == 3)){
            int newInvigLevel = playerInvigValue + 1;
            invigLore.set(5, Utils.color("&8• &fLevel: &e" + newInvigLevel + "/3"));
        }else{
            invigLore.set(5, Utils.color("&8• &fLevel: &cMAXED"));
        }
        //next cost
        switch (playerInvigValue){
            case 0:
                invigLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(50000)));
                break;
            case 1:
                //1 to 2
                invigLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(75000)));
                break;
            case 2:
                //2 to 3
                invigLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(150000)));
                break;
            default:
                //
                invigLore.set(6, Utils.color("&8• &fCost: &e❖0"));
        }

        Utils.setInvigorationLore(invigLore);

        //fortune
        int playerFortuneValue = Integer.valueOf(String.valueOf(hoe.getItemMeta().getLore().get(3).charAt(13)));
        List<String> fortuneLore = Utils.getFortuneLore();
        //current level
        fortuneLore.set(2, Utils.color("&8• &fLevel: &e" + playerFortuneValue + "/3"));
        //next level
        if(!(playerFortuneValue == 3)){
            int newFortuneLevel = playerFortuneValue + 1;
            fortuneLore.set(5, Utils.color("&8• &fLevel: &e" + newFortuneLevel + "/3"));
        }else {
            fortuneLore.set(5, Utils.color("&8• &fLevel: &cMAXED"));
        }
        //next cost
        switch (playerFortuneValue){
            case 0:
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(50000)));
                break;
            case 1:
                //1 to 2
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(100000)));
                break;
            case 2:
                //2 to 3
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(200000)));
                break;
            default:
                //
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖0"));
        }

        Utils.setFortuneLore(fortuneLore);

        inv.setItem(11, Utils.getHustler());
        inv.setItem(13, Utils.getInvigoration());
        inv.setItem(15, Utils.getFortune());
    }

    public void openInventory(Player p){
        this.resfreshInv(p);
        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        //might be fix for npe
        Player p = (Player) e.getWhoClicked();
        if (!(e.getInventory().getHolder() instanceof UpgradeHolder))return;

        if(e.getInventory().getHolder() instanceof UpgradeHolder){
            e.setCancelled(true);

            //hustler
            if(e.getCurrentItem().getType() == Material.DIAMOND){
                p.sendMessage("hustler");
            }

            //invig
            if(e.getCurrentItem().getType() == Material.SULPHUR){
                p.sendMessage("invig");
            }

            //fortune
            if(e.getCurrentItem().getType() == Material.SUGAR_CANE){
                p.sendMessage("fortune");
            }
        }
    }

}
class UpgradeHolder implements InventoryHolder{

    @Override
    public Inventory getInventory() {
        return null;
    }
}

