package mc.clorofile.org;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.chat.Chat;

/**
 * Main Class
 * @author Febriansyah
 * @version 1.1
 *
 */
public class Main extends JavaPlugin implements Listener {
	
	/**
	 * Prefix Text
	 * @return string
	 */
	public static String PIP_Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Player Inventory Profile" + ChatColor.DARK_GRAY + "]";
	
	/**
	 * @return string
	 */
	public static String Bukkit_Version = Bukkit.getVersion();
	
	/**
	 * Activated Text
	 * @return string
	 */
	public static String PIP_Enable = ChatColor.GREEN + " Activated successfully.";
	
	/**
	 * Deactivated
	 * @return string
	 */
	public static String PIP_Disable = ChatColor.RED + " Deactivated successfully.";
	
	/**
	 * @return string
	 */
	public static String PlayerCommand = "profile";
	
	/**
	 * @return string
	 */
	public static String InventoryTitle = "Player Inventory Profile";
	
	/**
	 * TextFormatter
	 * @param str
	 * @return string
	 */
	public static String TextFormatter(String str)
	{
		
		return ChatColor.translateAlternateColorCodes('&', str);
		
	}
	
	public static Economy economy = null;
	public static Chat chat = null;
	
	/**
	 * activate the plugin when the server is running
	 * @return void
	 */
	public void onEnable()
	{
		
		SetupEconomy();
		SetupChat();
		getServer().getConsoleSender().sendMessage(PIP_Prefix + PIP_Enable);
		getServer().getConsoleSender().sendMessage("Bukkit Version: " + Bukkit_Version);
		Bukkit.getPluginManager().registerEvents(this, (Plugin)this);
		
	}
	
	/**
	 * deactivate the plugin when the server is stopped
	 * @return void
	 */
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(PIP_Prefix + PIP_Disable);
	}
	
	/**
	 * SetupEconomy
	 * @return boolean
	 */
	private boolean SetupEconomy()
	{
		
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null)
			economy = (Economy)economyProvider.getProvider(); 
		return (economy != null);
		
	}
	
	/**
	 * SetupChat
	 * @return boolean
	 */
	private boolean SetupChat()
	{
		RegisteredServiceProvider<Chat> rsp = Bukkit.getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return (chat != null);
	}
	
	
	/**
	 * This function is when the player executes a command accompanied by the target player
	 * @param isPlayerTarget
	 */
	@EventHandler
	public void onClick(InventoryClickEvent isPlayerTarget) {
		
	    Player isPlayerExecutor = (Player)isPlayerTarget.getWhoClicked();
	    if (isPlayerTarget.getInventory().getName().equals(InventoryTitle)) {
	    	
	    	isPlayerTarget.setCancelled(true);
	    	
	    } 
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		
		if(!(sender instanceof Player)) {
			
			sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.GRAY + "Use this command only in-game!");
			return true;
			
		}
		
		Player isPlayerExecutor = (Player) sender;
		
		if(command.getName().equalsIgnoreCase(PlayerCommand) && sender.hasPermission("profile.use.player")) {
			
			if(args.length > 0) {
				
				Player isPlayerTarget = Bukkit.getPlayer(args[0]);
				if(isPlayerTarget != null) {
					
					// Getting player target
					
					/**
					 * Armors
					 */
					ItemStack Helmet = isPlayerTarget.getInventory().getHelmet();
					ItemStack Chestplate = isPlayerTarget.getInventory().getChestplate();
					ItemStack Leggings = isPlayerTarget.getInventory().getLeggings();
					ItemStack Boots = isPlayerTarget.getInventory().getBoots();
					
					/**
					 * Head Player (Target)
					 */
					ItemStack PlayerHead = new ItemStack(397, 1, (short) 3);
					ItemMeta PlayerHead_Meta  = PlayerHead.getItemMeta();
					PlayerHead_Meta.setDisplayName(TextFormatter(chat.getPlayerPrefix("null", isPlayerTarget.getName()) + isPlayerTarget.getName()));
					PlayerHead.setItemMeta(PlayerHead_Meta);
					
					/**
					 * Emerald (For Economy Info)
					 */
					ItemStack Emerald = new ItemStack(Material.EMERALD);
					ItemMeta Emerald_Meta = Emerald.getItemMeta();
					Emerald_Meta.setDisplayName(TextFormatter("&cMoney : &7$") + economy.getBalance(isPlayerTarget.getName()));
					Emerald.setItemMeta(Emerald_Meta);
					
					/**
					 * Barrier
					 */
					ItemStack Barrier = new ItemStack(Material.BARRIER);
					ItemMeta Barrier_Meta = Barrier.getItemMeta();
					Barrier_Meta.setDisplayName(TextFormatter("&4&l/"));
					Barrier.setItemMeta(Barrier_Meta);
					
					/**
					 * Inventory on Bottom
					 */
					ItemStack Inventory_1 = isPlayerTarget.getInventory().getItem(0);
					ItemStack Inventory_2 = isPlayerTarget.getInventory().getItem(1);
					ItemStack Inventory_3 = isPlayerTarget.getInventory().getItem(2);
					ItemStack Inventory_4 = isPlayerTarget.getInventory().getItem(3);
					ItemStack Inventory_5 = isPlayerTarget.getInventory().getItem(4);
					ItemStack Inventory_6 = isPlayerTarget.getInventory().getItem(5);
					ItemStack Inventory_7 = isPlayerTarget.getInventory().getItem(6);
					ItemStack Inventory_8 = isPlayerTarget.getInventory().getItem(7);
					ItemStack Inventory_9 = isPlayerTarget.getInventory().getItem(8);
					
					/**
					 * Render
					 */
					Inventory InventoryTarget = Bukkit.getServer().createInventory(null, 36, InventoryTitle);
					// armors
					InventoryTarget.setItem(4, Helmet);
					InventoryTarget.setItem(13, Chestplate);
					InventoryTarget.setItem(22, Leggings);
					InventoryTarget.setItem(31, Boots);
					
					// inventory on bottom
					InventoryTarget.setItem(0, Inventory_1);
					InventoryTarget.setItem(1, Inventory_2);
					InventoryTarget.setItem(2, Inventory_3);
					InventoryTarget.setItem(9, Inventory_4);
					InventoryTarget.setItem(10, Inventory_5);
					InventoryTarget.setItem(11, Inventory_6);
					InventoryTarget.setItem(18, Inventory_7);
					InventoryTarget.setItem(19, Inventory_8);
					InventoryTarget.setItem(20, Inventory_9);
					
					// player info
					InventoryTarget.setItem(28, PlayerHead);
					InventoryTarget.setItem(34, Emerald);
					
					// barrier
					InventoryTarget.setItem(27, Barrier);
					InventoryTarget.setItem(29, Barrier);
					InventoryTarget.setItem(35, Barrier);
					InventoryTarget.setItem(33, Barrier);
					
					isPlayerExecutor.openInventory(InventoryTarget);
					
				}
				else {
					
					// Player not found
					isPlayerExecutor.sendMessage(TextFormatter("&cError: &7Player not found."));
					
				}
								
			}
			else {
				
				// Execute command without args
				isPlayerExecutor.sendMessage(TextFormatter("&fTo see other players' inventory."));
				isPlayerExecutor.sendMessage(TextFormatter("&f/profile <player>"));
				
			}
			
		}
		else {
			
			// not have permissions
			isPlayerExecutor.sendMessage(TextFormatter("&cNo Permission."));
			
		}
		
		return false;
	}
	
}
