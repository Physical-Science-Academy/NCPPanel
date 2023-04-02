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
import cn.nukkit.form.element.ElementButton;
import moe.him188.gui.window.FormSimple;

public class NCPBasePanel extends FormSimple {

    public NCPBasePanel() {
        super("NoCheatPlus Manager", "Switch button to continue.");
        this.addButton(new ElementButton("Modules"));
        this.addButton(new ElementButton("Violations"));
    }

    @Override
    public void onClicked(int id, Player player) {
        switch (id) {
            case 0:
                player.showFormWindow(new NCPModulePanel());
                break;
            case 1:
                player.showFormWindow(new NCPViolationsPanel());
                break;
            default:
                break;
        }
    }
}
