/*
 * This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in thCut even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.catrainbow.nocheatplus.gui;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.Config;
import net.catrainbow.nocheatplus.NoCheatPlus;
import net.catrainbow.nocheatplus.command.NCPCommand;
import net.catrainbow.nocheatplus.components.NoCheatPlusAPI;
import net.catrainbow.nocheatplus.gui.helper.HelperPanelCommand;

import java.util.ArrayList;

/**
 * NoCheatPlus Panel
 *
 * @author Catrainbow
 */
public class NCPPanel extends PluginBase {

    private static NCPPanel instance;

    public static NCPPanel getInstance() {
        return instance;
    }

    public static NoCheatPlusAPI provider;
    public static boolean staticMode = Server.getInstance().getPluginManager().getPlugin("NCPStaticBar") != null;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        if (this.getServer().getPluginManager().getPlugin("NoCheatPlus") == null) return;
        this.initLanguage();
        staticMode = Server.getInstance().getPluginManager().getPlugin("NCPStaticBar") != null;
        provider = NoCheatPlus.instance;
        this.getLogger().info("Hook NoCheatPlus successfully!");
        NCPCommand.Companion.getSubCommands().add(new NCPPanelCommand());
        this.getServer().getCommandMap().register("NCPPanel", new NCPPanelReportCommand());
        this.getLogger().info("NCPPanel Command inject into NCP successfully!");
        this.getServer().getScheduler().scheduleRepeatingTask(new NCPPanelTask(), this.getConfig().getInt("scanTick") * 20);
        if (this.getConfig().getBoolean("staffBroadcast")) {
            this.getServer().getScheduler().scheduleRepeatingTask(new Task() {
                @Override
                public void onRun(int i) {
                    for (Player player : getServer().getOnlinePlayers().values()) {
                        if (player.isOp() || provider.hasPermission(player, getConfig().getString("panelCommand"))) {
                            player.sendMessage(getConfig().getString("staffBroadcastMessage").replace("@size", String.valueOf(ViolationBuffer.violationBuffers.size())));
                        }
                    }
                }
            }, this.getConfig().getInt("staffBroadcastTick") * 20);
        }
        if (this.getConfig().getBoolean("helper.enabled"))
            this.getServer().getCommandMap().register("NCPPanel", new HelperPanelCommand());
    }

    public String formatLang(String path) {
        return this.getConfig().getString("language." + path);
    }


    private void initLanguage() {
        Config config = this.getConfig();
        if (!config.exists("maxBuffers")) {
            config.set("panelCommand", "panel");
            config.set("reportCommand", "report");
            config.set("maxBuffers", 20);
            config.set("autoDelete", true);
            config.set("scanTick", 10);
            config.set("scanMinute", 2);
            config.set("reportInfo", "Player Report");
            config.set("autoCheckInfo", "NCP Check");
            config.set("staffBroadcast", true);
            config.set("staffBroadcastTick", 200);
            config.set("staffBroadcastMessage", "NCP has collected @size suspicious players in recent minute. Please open panel to check them.");
            config.set("punishment.days", 3);
            config.set("punishment.hours", 0);
            config.set("punishment.minutes", 0);
            config.set("violations.high", 20d);
            config.set("violation.highTitle", "Danger");
            config.set("violations.low", 15d);
            config.set("language.base.suspicious", "Suspicious Player");
            config.set("language.base.modules", "Modules");
            config.set("language.base.violations", "Violations");
            config.set("language.module.title", "NCP Modules");
            config.set("language.module.content", "Switch to see the module's info");
            config.set("language.module.info", "Module Name:@name@nextAuthor:@author@nextVersion:@version");
            config.set("language.module.buttonEnable", "Enable");
            config.set("language.module.buttonDisable", "Disable");
            config.set("language.module.enable", "@module now is enabled");
            config.set("language.module.disable", "@module now is disabled");
            config.set("language.violation.title", "Violations");
            config.set("language.violation.content", "Switch to see player's violation");
            config.set("language.violation.subtitle", "@player's violations");
            config.set("language.violation.subContent", "");
            config.set("language.violation.kickButton", "Kick");
            config.set("language.violation.banButton", "Ban");
            config.set("language.violation.teleportButton", "Teleport");
            config.set("language.violation.kick", "Kick @player successfully!");
            config.set("language.violation.ban", "Ban @player successfully!");
            config.set("language.violation.teleport", "Teleport you to @player Successfully!");
            config.set("language.report.title", "Report");
            config.set("language.report.content", "Switch to report a hacking player");
            config.set("language.report.subtitle", "chose a reason");
            config.set("language.report.subContent", "");
            config.set("language.report.typeTranslate", new ArrayList<String>() {
                {
                    add("MOVING_SURVIVAL_FLY:Fly");
                }
            });
            config.set("language.report.feedback", "Thanks for reporting! We will detect the player if he is a hacker. @hack @reason");
            config.set("language.suspicious.title", "Suspicious Player");
            config.set("language.suspicious.content", "NCP will show you recent player who are seemed to be hack");
            config.set("language.suspicious.button", "§8@tick before @level §r@player@next§8Type:@reason Info:@info");
            config.set("helper.enabled", false);
            config.set("helper.list", new ArrayList<>());
            config.save(true);
        }
    }

}
