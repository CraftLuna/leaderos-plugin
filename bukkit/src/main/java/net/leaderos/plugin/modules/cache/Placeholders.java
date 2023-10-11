package net.leaderos.plugin.modules.cache;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.leaderos.plugin.Main;
import net.leaderos.plugin.modules.cache.model.User;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

/**
 * @author poyrazinan
 * @since 1.0
 */
public class Placeholders extends PlaceholderExpansion {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            Main.getShared().getConfigFile().getSettings().getTimeFormat());

    /**
     * identifier of placeholder
     * @return
     */
    public String getIdentifier() {
        return "leaderos-cache";
    }

    /**
     The author of the Placeholder
     This cannot be null
     */
    public String getAuthor() {
        return "Geik";
    }

    /**
     Same with #getAuthor() but for versioon
     This cannot be null
     */

    public String getVersion() {
        return "1.0";
    }

    /**
     * PlaceholderRequest method
     * @param p player
     * @param identifier placeholder identifier
     * @return
     */
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {

        // Checks if player authed
        if (!User.isPlayerAuthed(p))
            return "";

        // Loads cache
        User user = User.getUser(p.getName());

        // Identifier matcher
        if (identifier.equals("email"))
            return user.getEmail();
        else if (identifier.equals("credit"))
            return user.getCredit() +"";
        else if (identifier.equals("creation_ip"))
            return user.getCreationIp();
        else if (identifier.equals("creation_date"))
            return dateFormat.format(user.getCreationDate());
        else return "";
    }

}