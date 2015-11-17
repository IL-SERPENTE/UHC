package net.samagames.uhc;

import com.google.gson.JsonPrimitive;
import net.samagames.api.SamaGamesAPI;
import net.samagames.survivalapi.SurvivalAPI;
import net.samagames.survivalapi.game.SurvivalGame;
import net.samagames.survivalapi.game.SurvivalGameLoop;
import net.samagames.survivalapi.game.types.SurvivalSoloGame;
import net.samagames.survivalapi.game.types.SurvivalTeamGame;
import net.samagames.survivalapi.modules.craft.DisableLevelTwoPotionModule;
import net.samagames.survivalapi.modules.craft.DisableNotchAppleModule;
import net.samagames.survivalapi.modules.craft.DisableSpeckedMelonModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class UHC extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        SurvivalGame game;

        int nb = SamaGamesAPI.get().getGameManager().getGameProperties().getOption("playersPerTeam", new JsonPrimitive(1)).getAsInt();

        SurvivalAPI.get().loadModule(DisableLevelTwoPotionModule.class, new HashMap<String, Object>());
        SurvivalAPI.get().loadModule(DisableNotchAppleModule.class, new HashMap<String, Object>());
        SurvivalAPI.get().loadModule(DisableSpeckedMelonModule.class, new HashMap<String, Object>());

        if (nb > 1)
            game = new SurvivalTeamGame<SurvivalGameLoop>(this, "uhc", "UHC", "La survie en Ultra Hard Core", null, SurvivalGameLoop.class, nb);
        else
            game = new SurvivalSoloGame<SurvivalGameLoop>(this, "uhc", "UHC", "La survie en Ultra Hard Core", null, SurvivalGameLoop.class);

        SamaGamesAPI.get().getGameManager().registerGame(game);
    }
}