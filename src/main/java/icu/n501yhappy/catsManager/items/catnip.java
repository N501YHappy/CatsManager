package icu.n501yhappy.catsManager.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class catnip {
    public static ItemStack get(){
        ItemStack is = new ItemStack(Material.OAK_LEAVES);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§a§l猫薄荷");
        List<String> lores = new ArrayList<>();
        lores.add("§7右键对其他cat使用");
        im.setLore(lores);
        is.setItemMeta(im);
        return is;
    }
}
