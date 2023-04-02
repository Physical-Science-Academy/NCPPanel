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
import net.catrainbow.nocheatplus.checks.ViolationData;
import net.catrainbow.nocheatplus.gui.NCPPanel;

public class NCPPlayerViolationsPanel extends FormSimple {

    private final String name;

    public NCPPlayerViolationsPanel(String name) {
        super(name + "'s violations", "");
        this.name = name;
        this.addButton("Kick");
        this.addButton("Banned");
        this.addButton("Teleport");
    }

    @Override
    public void onClicked(int id, Player player) {
        Player target = Server.getInstance().getPlayer(name);
        switch (id) {
            case 0:
                player.sendMessage("kicked the player successfully!");
                NCPPanel.provider.kickPlayer(target, CheckType.STAFF);
                break;
            case 1:
                player.sendMessage("Banned the player for 3 days successfully!");
                NCPPanel.provider.banPlayer(target, 3, 0, 0);
                break;
            case 2:
                player.sendMessage("teleport to the player successfully!");
                player.teleport(target);
                break;
        }
    }

    public void update() {
        Player player = Server.getInstance().getPlayer(name);
        StringBuilder stringBuilder = new StringBuilder("check->");
        for (CheckType type : CheckType.values()) {
            if (type.isUsedCheck()) {
                ViolationData data = NCPPanel.provider.getPlayerProvider(player).getViolationData(type);
                stringBuilder.append("\n").append("  ->").append(type.name()).append(": ").append(data.getVL());
            }
        }
        this.setContent(stringBuilder.toString());
    }

}
