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
package net.catrainbow.nocheatplus.gui.panel;

import cn.nukkit.Player;
import moe.him188.gui.window.FormSimple;
import net.catrainbow.nocheatplus.components.registry.NCPComponent;
import net.catrainbow.nocheatplus.gui.NCPPanel;

public class NCPModulePanel extends FormSimple {

    public NCPModulePanel() {
        super("NCP Modules", "Switch to see the module's info");
        for (NCPComponent component : NCPPanel.provider.getAllComponents().values()) {
            this.addButton(component.getRegisterCom().getName());
        }
    }

    @Override
    public void onClicked(int id, Player player) {
        String name = this.getButtons().get(id).getText();
        if (NCPPanel.provider.getAllComponents().containsKey(name))
            player.showFormWindow(new NCPModuleInfoPanel(NCPPanel.provider.getNCPComponent(name)));
    }
}
