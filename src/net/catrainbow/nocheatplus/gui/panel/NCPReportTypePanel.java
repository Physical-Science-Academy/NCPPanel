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
import cn.nukkit.Server;
import moe.him188.gui.window.FormSimple;
import net.catrainbow.nocheatplus.checks.CheckType;
import net.catrainbow.nocheatplus.gui.NCPPanel;
import net.catrainbow.nocheatplus.gui.ViolationBuffer;
import net.catrainbow.nocheatplus.staticbar.NCPStaticAPI;

public class NCPReportTypePanel extends FormSimple {


    private final String name;

    public NCPReportTypePanel(String name) {
        super(name + " hacking action", "");
        this.name = name;
        for (CheckType type : CheckType.values())
            if (type.isUsedCheck()) addButton(type.name());
    }

    @Override
    public void onClicked(int id, Player player) {
        Player target = Server.getInstance().getPlayer(name);
        String typeName = this.getButtons().get(id).getText();
        ViolationBuffer violationBuffer = new ViolationBuffer();
        violationBuffer.playerName = target.getName();
        violationBuffer.type = typeName;
        ViolationBuffer.violationBuffers.put(System.currentTimeMillis(), violationBuffer);
        player.sendMessage("Thanks for reporting! We will detect the player if he is a hacker.");
        if (NCPPanel.staticMode) {
            NCPStaticAPI.setPlayerCheckable(player);
        }
    }

}
