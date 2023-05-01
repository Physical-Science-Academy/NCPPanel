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
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.catrainbow.nocheatplus.gui.panel.NCPReportPanel;

/**
 * provide a command to report hackers
 * work better if you install NCPStaticBar
 *
 * @author Catrainbow
 */
public class NCPPanelReportCommand extends Command {

    public NCPPanelReportCommand() {
        super(NCPPanel.getInstance().getConfig().getString("reportCommand"), "NCP Report Command");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {

        if (commandSender instanceof Player) {
            ((Player) commandSender).showFormWindow(new NCPReportPanel());
        }
        return true;
    }
}
