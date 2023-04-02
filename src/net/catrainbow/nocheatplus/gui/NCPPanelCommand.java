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
import cn.nukkit.command.CommandSender;
import net.catrainbow.nocheatplus.command.NCPSubCommand;
import net.catrainbow.nocheatplus.gui.panel.NCPBasePanel;
import org.jetbrains.annotations.NotNull;

/**
 * NCPPanel Command
 * use /ncp panel to open panel
 *
 * @author Catrainbow
 */
public class NCPPanelCommand extends NCPSubCommand {

    public NCPPanelCommand() {
        super("panel");
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) ((Player) commandSender).showFormWindow(new NCPBasePanel());

        }
        return true;
    }

    @NotNull
    @Override
    public String[] getAliases() {
        return new String[]{"admin", "manager"};
    }

    @NotNull
    @Override
    public String getDescription() {
        return "[Addons] NCPPanel Command";
    }
}
