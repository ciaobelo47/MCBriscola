package me.mailo.mcbriscola.Game;

import me.mailo.mcbriscola.MCBriscola;
import me.mailo.mcbriscola.langs.LangLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Toa implements CommandExecutor {

    public static LangLoader langLoader = new LangLoader(MCBriscola.plugin);
    public static Inventory ToaPl1 = Bukkit.createInventory(Bukkit.getPlayer(""), 45, "Ea Toa dea Briscola - Player 1");
    public static Inventory ToaPl2 = Bukkit.createInventory(Bukkit.getPlayer(""), 45, "Ea Toa dea Briscola - Player 2");
    private final Logger logger = MCBriscola.getInstance().getLogger();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("briscola")) {
            if (args[0].equalsIgnoreCase("start")) {
                if (args.length > 1) {
                    p.sendTitle(ChatColor.RED + "YOU ARE PLAYER 1", null, 3, 10, 3);
                    Bukkit.getPlayer(args[1]).sendTitle(ChatColor.RED + "YOU ARE PLAYER 2", null, 3, 10, 3);
                    logger.log(Level.WARNING, "Started Multiplayer Game...");
                    ToaPl1.clear();
                    ToaPl2.clear();
                    Gioco.singlePlayer = false;
                    p.openInventory(ToaPl1);
                    Bukkit.getPlayer(args[1]).openInventory(ToaPl2);

                } else {
                    p.sendTitle(ChatColor.RED + "YOU ARE PLAYER 1", null, 3, 10, 3);
                    logger.log(Level.WARNING, "Started Singleplayer Game...");
                    ToaPl1.clear();
                    Gioco.singlePlayer = true;
                    p.openInventory(ToaPl1);
                }
            } else if (args[0].equalsIgnoreCase("test")) {
                p.sendMessage(langLoader.get("welcome_mess"));
                logger.log(Level.SEVERE, langLoader.get("welcome_mess"));
                logger.log(Level.WARNING, langLoader.get("welcome_mess"));

            } else {
                p.sendMessage(ChatColor.RED + langLoader.get("arg_miss"));
            }
        }
        return false;
    }

    /**
     * Usata solo come funzione di debug.
     */
    @Deprecated
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
        ToaPl1.setItem(41, denari);
        ToaPl1.setItem(42, bastoni);
        ToaPl1.setItem(43, spade);
        ToaPl1.setItem(44, coppe);
        for (int i = 2; i < 11; i++) {
            metaDenari.setDisplayName(i + " di Denari");
            metaBastoni.setDisplayName(i + " di Bastoni");
            metaSpade.setDisplayName(i + " di Spade");
            metaCoppe.setDisplayName(i + " di Coppe");
            denari.setItemMeta(metaDenari);
            bastoni.setItemMeta(metaBastoni);
            spade.setItemMeta(metaSpade);
            coppe.setItemMeta(metaCoppe);
            ToaPl1.setItem(i, denari);
            ToaPl1.setItem(i + 10, bastoni);
            ToaPl1.setItem(i + 20, spade);
            ToaPl1.setItem(i + 30, coppe);
        }


    }
}
