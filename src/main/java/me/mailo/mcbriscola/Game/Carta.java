package me.mailo.mcbriscola.Game;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Carta extends ItemStack {
    private final int value;
    private final Material seme;
    private final int num;
    private ItemMeta meta;


    public Carta(Integer num, String seme) {
        Material material = Material.AIR;
        int value;
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
        assert cartaMeta != null;
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
        this.value = value;
        this.seme = material;
        this.setType(material);
        this.num = num;
        this.setAmount(1);
        this.setItemMeta(cartaMeta);
    }

    public Carta(ItemStack item) {
        this.seme = item.getType();

        String valueSemeArr = item.getItemMeta().getDisplayName().split(" di ")[0];

        switch (valueSemeArr) {
            case "Asso":
                this.num = 1;
                this.value = CartaValues.ASSO;
                break;
            case "3":
                this.num = 3;
                this.value = CartaValues.TRE;
                break;
            case "Fante":
                this.num = 8;
                this.value = CartaValues.FANTE;
                break;
            case "Cavallo":
                this.num = 9;
                this.value = CartaValues.CAVALLO;
                break;
            case "Re":
                this.num = 10;
                this.value = CartaValues.RE;
                break;
            default:
                this.num = Integer.parseInt(valueSemeArr);
                this.value = 0;
                break;
        }
    }

    public int getValue() {
        return value;
    }

    public Material getSeme() {
        return seme;
    }

    public int getNum() {
        return num;
    }

    public ItemMeta getMeta() {
        return meta;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }
}
