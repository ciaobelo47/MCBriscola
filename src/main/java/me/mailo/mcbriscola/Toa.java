package me.mailo.mcbriscola;

import me.mailo.mcbriscola.langs.LangLoader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Toa implements CommandExecutor {
    public static LangLoader langLoader = new LangLoader(MCBriscola.plugin);
    public static Inventory Toa = Bukkit.createInventory(Bukkit.getPlayer(""), 45, "Ea Toa dea Briscola");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("briscola")){
            if (args[0].equalsIgnoreCase("start")){
                ToaCreate();
                p.openInventory(Toa);
            } else if (args[0].equalsIgnoreCase("test")){
                p.sendMessage(langLoader.get("welcome_mess"));
                System.out.println(langLoader.get("welcome_mess"));
                p.sendMessage(String.valueOf(MCBriscola.getInstance().getConfig().getBoolean("settings.easteregg")));
                System.out.println(MCBriscola.getInstance().getConfig().getBoolean("settings.easteregg"));

            }
        }
        return false;
    }

    private void ToaCreate() {
        ItemStack denari = new ItemStack(Material.EMERALD);
        ItemStack bastoni = new ItemStack(Material.STICK);
        ItemStack spade = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack coppe = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta metaDenari = denari.getItemMeta();
        ItemMeta metaBastoni = bastoni.getItemMeta();
        ItemMeta metaSpade = spade.getItemMeta();
        ItemMeta metaCoppe = coppe.getItemMeta();
        metaDenari.setDisplayName("Asso di Denari");
        metaBastoni.setDisplayName("Asso di Bastoni");
        metaSpade.setDisplayName("Asso di Spade");
        metaCoppe.setDisplayName("Asso di Coppe");
        denari.setItemMeta(metaDenari);
        bastoni.setItemMeta(metaBastoni);
        spade.setItemMeta(metaSpade);
        coppe.setItemMeta(metaCoppe);
        Toa.setItem(41,denari);
        Toa.setItem(42,bastoni);
        Toa.setItem(43,spade);
        Toa.setItem(44,coppe);
        for (int i = 2; i < 11; i++) {
            metaDenari.setDisplayName(i + " di Denari");
            metaBastoni.setDisplayName(i + " di Bastoni");
            metaSpade.setDisplayName(i + " di Spade");
            metaCoppe.setDisplayName(i + " di Coppe");
            denari.setItemMeta(metaDenari);
            bastoni.setItemMeta(metaBastoni);
            spade.setItemMeta(metaSpade);
            coppe.setItemMeta(metaCoppe);
            Toa.setItem(i,denari);
            Toa.setItem(i+10,bastoni);
            Toa.setItem(i+20,spade);
            Toa.setItem(i+30,coppe);
        }


    }
}
