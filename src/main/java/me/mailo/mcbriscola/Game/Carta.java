package me.mailo.mcbriscola.Game;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class Carta extends ItemStack {
    private final int Value;
    private final Material seme;
    private final int num;
    private ItemMeta meta;


    public Carta(@Nullable Integer num, String seme) {
        Material material = Material.AIR;
        int value = 0;
        switch (seme) {
            case "Spade":
                material = Material.DIAMOND_SWORD;
                break;
            case "Coppe":
                material = Material.EXPERIENCE_BOTTLE;
                break;
            case "Bastoni":
                material = Material.STICK;
                break;
            case "Denari":
                material = Material.EMERALD;
                break;
        }
        ItemStack questo = new ItemStack(material);
        ItemMeta cartaMeta = questo.getItemMeta();
        switch (num) {
            case 1:
                value = 11;
                cartaMeta.setDisplayName("Asso di " + seme);
                break;
            case 3:
                value = 10;
                cartaMeta.setDisplayName("3 di " + seme);
                break;
            case 8:
                value = 2;
                cartaMeta.setDisplayName("Fante di " + seme);
                break;
            case 9:
                value = 3;
                cartaMeta.setDisplayName("Cavallo di " + seme);
                break;
            case 10:
                value = 4;
                cartaMeta.setDisplayName("Re di " + seme);
                break;
            default:
                value = 0;
                cartaMeta.setDisplayName(num + " di " + seme);
                break;
        }
        this.Value = value;
        this.seme = material;
        this.setType(material);
        this.num = num;
        this.setAmount(1);
        this.setItemMeta(cartaMeta);


    }
}
