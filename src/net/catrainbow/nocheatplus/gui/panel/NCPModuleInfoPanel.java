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

public class NCPModuleInfoPanel extends FormSimple {

    private final NCPComponent component;

    public NCPModuleInfoPanel(NCPComponent component) {
        super(component.getRegisterCom().getName(), "Module Name: " + component.getRegisterCom().getName() + "\nModule Author: " + component.getRegisterCom().getAuthor() + "\nModule Version: " + component.getRegisterCom().getVersion());
        this.component = component;
        this.addButton("Enabled");
        this.addButton("Disabled");
    }

    @Override
    public void onClicked(int id, Player player) {
        if (id == 0) {
            this.component.onEnabled();
            player.sendMessage("Module " + component.getRegisterCom().getName() + " now is enabled");
        } else {
            this.component.onDisabled();
            player.sendMessage("Module " + component.getRegisterCom().getName() + " now is disabled");
        }
    }
}
