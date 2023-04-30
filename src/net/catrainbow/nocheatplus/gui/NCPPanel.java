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

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import net.catrainbow.nocheatplus.NoCheatPlus;
import net.catrainbow.nocheatplus.command.NCPCommand;
import net.catrainbow.nocheatplus.components.NoCheatPlusAPI;

/**
 * NoCheatPlus Panel
 *
 * @author Catrainbow
 */
public class NCPPanel extends PluginBase {

    public static NoCheatPlusAPI provider;
    public static boolean staticMode = Server.getInstance().getPluginManager().getPlugin("NCPStaticBar") != null;

    @Override
    public void onEnable() {
        if (this.getServer().getPluginManager().getPlugin("NoCheatPlus") == null) return;
        provider = NoCheatPlus.instance;
        this.getLogger().info("Hook NoCheatPlus successfully!");
        NCPCommand.Companion.getSubCommands().add(new NCPPanelCommand());
        this.getServer().getCommandMap().register("NCPPanel", new NCPPanelReportCommand());
        this.getLogger().info("NCPPanel Command inject into NCP successfully!");
    }
}
