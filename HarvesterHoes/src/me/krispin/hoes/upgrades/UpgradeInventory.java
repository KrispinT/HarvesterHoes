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

import java.util.ArrayList;
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
        inv = Bukkit.createInventory(new UpgradeHolder(), 27, Utils.color("&eEssence: ❖" + HarvesterHoes.getInstance().getNumberFormatted(HarvesterHoes.getInstance().getEssence().get(p.getUniqueId()))));

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
            hustlerLore.set(5, Utils.color("&8• &fLevel: &cMAX LEVEL"));
        }
        //next cost
        switch (playerHustlerValue){
            case 0:
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(50000)));
                break;
            case 1:
                //1 to 2
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(100000)));
                break;
            case 2:
                //2 to 3
                hustlerLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(200000)));
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
            invigLore.set(5, Utils.color("&8• &fLevel: &cMAX LEVEL"));
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
                invigLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(125000)));
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
            fortuneLore.set(5, Utils.color("&8• &fLevel: &cMAX LEVEL"));
        }
        //next cost
        switch (playerFortuneValue){
            case 0:
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(25000)));
                break;
            case 1:
                //1 to 2
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(50000)));
                break;
            case 2:
                //2 to 3
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖" + HarvesterHoes.getInstance().getNumberFormatted(100000)));
                break;
            default:
                //
                fortuneLore.set(6, Utils.color("&8• &fCost: &e❖0"));
        }

        Utils.setFortuneLore(fortuneLore);

        inv.setItem(11, Utils.getHustler());

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
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase((Utils.color("&bHustler")))){
                    //get level
                    int hustlerLevel = Integer.valueOf(String.valueOf(e.getCurrentItem().getItemMeta().getLore().get(2).charAt(15)));

                    int playerBalance = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId());
                    //hoe levels each
                    int hoeInivLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(2).charAt(18)));
                    int hoeFortuneLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(3).charAt(13)));

                    switch (hustlerLevel){
                        case 0:
                            int costZero = 50000;
                            if(playerBalance >= costZero){
                                int newBal = playerBalance - costZero;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBal);
                                p.setItemInHand(Utils.getHoe(1, hoeInivLevel, hoeFortuneLevel));
                                //1-2
                                List<String> levelZero = new ArrayList<>();
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Current Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e1/3"));
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Next Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelZero.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(100000)));
                                levelZero.add(Utils.color("&7&m----------------"));
                                Utils.setHustlerLore(levelZero);
                                inv.setItem(11, Utils.getHustler());

                                p.sendMessage(Utils.color("&aYou have upgraded your &6Hustler &alevel to &e1&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this!"));
                            }
                            break;
                        case 1:
                            //1-2
                            int costOne = 100000;
                            if(playerBalance >= costOne){
                                //buy
                                int newBalance = playerBalance - costOne;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBalance);
                                p.setItemInHand(Utils.getHoe(2, hoeInivLevel, hoeFortuneLevel));
                                //next level (2-3)
                                List<String> levelOne = new ArrayList<>();
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Current Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Next Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e3/3"));
                                levelOne.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(200000)));
                                levelOne.add(Utils.color("&7&m----------------"));
                                Utils.setHustlerLore(levelOne);
                                inv.setItem(11, Utils.getHustler());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Hustler &alevel to &e2&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this!"));
                            }
                            break;
                        case 2:
                            int costTwo = 200000;
                            if(playerBalance >= costTwo){
                                int newB = playerBalance - costTwo;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newB);
                                p.setItemInHand(Utils.getHoe(3, hoeInivLevel, hoeFortuneLevel));
                                //next level (max)
                                List<String> maxLore = new ArrayList<>();
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Current Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &e3/3"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Next Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &cMAX LEVEL"));
                                maxLore.add(Utils.color("&8• &fCost: &e❖0"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                Utils.setHustlerLore(maxLore);
                                inv.setItem(11, Utils.getHustler());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Hustler &alevel to &e3&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        default:
                            p.closeInventory();
                            p.sendMessage(Utils.color("&aYou already have &cMAXED &6Hustler&a!"));
                    }
                }
            }

            //invig
            if(e.getCurrentItem().getType() == Material.SULPHUR){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase((Utils.color("&cInvigoration")))){
                    int invigLevel = Integer.valueOf(String.valueOf(e.getCurrentItem().getItemMeta().getLore().get(2).charAt(15)));
                    int playerBalance = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId());
                    //other levels
                    int hustlerLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(1).charAt(13)));
                    int hoeFortuneLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(3).charAt(13)));

                    switch (invigLevel){
                        case 0:
                            int costZero = 50000;
                            if(playerBalance >= costZero){
                                int newBalance = playerBalance - costZero;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBalance);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, 1, hoeFortuneLevel));
                                List<String> levelZero = new ArrayList<>();
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Current Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e1/3"));
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Next Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelZero.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(75000)));
                                levelZero.add(Utils.color("&7&m----------------"));
                                Utils.setInvigorationLore(levelZero);
                                inv.setItem(13, Utils.getInvigoration());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Invigoration &alevel to &e1&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        case 1:
                            int costOne = 75000;
                            if(playerBalance >= costOne){
                                int newBal = playerBalance - costOne;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBal);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, 2, hoeFortuneLevel));
                                List<String> levelOne = new ArrayList<>();
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Current Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Next Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e3/3"));
                                levelOne.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(125000)));
                                levelOne.add(Utils.color("&7&m----------------"));
                                Utils.setInvigorationLore(levelOne);
                                inv.setItem(13, Utils.getInvigoration());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Invigoration &alevel to &e2&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        case 2:
                            int costTwo = 125000;
                            if(playerBalance >= costTwo){
                                int newB = playerBalance - costTwo;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newB);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, 3, hoeFortuneLevel));
                                List<String> maxLore = new ArrayList<>();
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Current Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &e3/3"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Next Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &cMAX LEVEL"));
                                maxLore.add(Utils.color("&8• &fCost: &e❖0"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                Utils.setInvigorationLore(maxLore);
                                inv.setItem(13, Utils.getInvigoration());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Invigoration &alevel to &e3&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        default:
                            p.closeInventory();
                            p.sendMessage(Utils.color("&aYou already have &cMAXED &6Invigoration&a!"));
                    }
                }
            }

            //fortune
            if(e.getCurrentItem().getType() == Material.SUGAR_CANE){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&aFortune"))){
                    int fortuneLevel = Integer.valueOf(String.valueOf(e.getCurrentItem().getItemMeta().getLore().get(2).charAt(15)));
                    int playerBalance = HarvesterHoes.getInstance().getEssence().get(p.getUniqueId());

                    //hoe levels
                    int hustlerLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(1).charAt(13)));
                    int hoeInivLevel = Integer.valueOf(String.valueOf(p.getItemInHand().getItemMeta().getLore().get(2).charAt(18)));

                    switch (fortuneLevel){
                        case 0:
                            int costZero = 25000;
                            if(playerBalance >= costZero){
                                int newBalance = playerBalance - costZero;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBalance);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, hoeInivLevel, 1));
                                List<String> levelZero = new ArrayList<>();
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Current Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e1/3"));
                                levelZero.add(Utils.color("&7&m----------------"));
                                levelZero.add(Utils.color("&6Next Upgrades:"));
                                levelZero.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelZero.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(50000)));
                                levelZero.add(Utils.color("&7&m----------------"));
                                Utils.setFortuneLore(levelZero);
                                inv.setItem(15, Utils.getFortune());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Fortune &alevel to &e1&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        case 1:
                            int costOne = 50000;
                            if(playerBalance >= costOne){
                                int newBal = playerBalance - costOne;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newBal);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, hoeInivLevel, 2));
                                List<String> levelOne = new ArrayList<>();
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Current Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e2/3"));
                                levelOne.add(Utils.color("&7&m----------------"));
                                levelOne.add(Utils.color("&6Next Upgrades:"));
                                levelOne.add(Utils.color("&8• &fLevel: &e3/3"));
                                levelOne.add(Utils.color("&8• &fCost: &e❖"+HarvesterHoes.getInstance().getNumberFormatted(100000)));
                                levelOne.add(Utils.color("&7&m----------------"));
                                Utils.setFortuneLore(levelOne);
                                inv.setItem(15, Utils.getFortune());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Fortune &alevel to &e2&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        case 2:
                            int costTwo = 100000;
                            if(playerBalance >= costTwo){
                                int newB = playerBalance - costTwo;
                                HarvesterHoes.getInstance().getEssence().put(p.getUniqueId(), newB);
                                p.setItemInHand(Utils.getHoe(hustlerLevel, hoeInivLevel, 3));
                                List<String> maxLore = new ArrayList<>();
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Current Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &e3/3"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                maxLore.add(Utils.color("&6Next Upgrades:"));
                                maxLore.add(Utils.color("&8• &fLevel: &cMAX LEVEL"));
                                maxLore.add(Utils.color("&8• &fCost: &e❖0"));
                                maxLore.add(Utils.color("&7&m----------------"));
                                Utils.setInvigorationLore(maxLore);
                                inv.setItem(15, Utils.getFortune());
                                p.sendMessage(Utils.color("&aYou have upgraded your &6Fortune &alevel to &e3&a!"));
                            }else{
                                p.closeInventory();
                                p.sendMessage(Utils.color("&cYou do not have enough &e❖Essence &cto afford this"));
                            }
                            break;
                        default:
                            p.closeInventory();
                            p.sendMessage(Utils.color("&aYou already have &cMAXED &6Fortune&a!"));
                    }
                }
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

