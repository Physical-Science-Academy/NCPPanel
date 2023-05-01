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
import cn.nukkit.scheduler.Task;
import net.catrainbow.nocheatplus.checks.CheckType;

public class NCPPanelTask extends Task {
    @Override
    public void onRun(int i) {
        if (ViolationBuffer.violationBuffers.size() >= NCPPanel.getInstance().getConfig().getInt("maxBuffers")) {
            if (NCPPanel.getInstance().getConfig().getBoolean("autoDelete")) ViolationBuffer.violationBuffers.clear();
        }
        if (NCPPanel.getInstance().getConfig().getBoolean("autoDelete")) {
            for (Long timeMill : ViolationBuffer.violationBuffers.keySet()) {
                if (System.currentTimeMillis() - timeMill > NCPPanel.getInstance().getConfig().getInt("scanMinute") * 60 * 1000L)
                    ViolationBuffer.violationBuffers.remove(timeMill);
            }
        }
        for (Player player : NCPPanel.getInstance().getServer().getOnlinePlayers().values()) {
            for (CheckType type : CheckType.values())
                if (type.isUsedCheck()) {
                    double vl = NCPPanel.provider.getAllPlayerData().get(player.getName()).getViolationData(type).getVL();
                    if (vl > NCPPanel.getInstance().getConfig().getDouble("violations.low")) {
                        ViolationBuffer violationBuffer = new ViolationBuffer();
                        violationBuffer.playerName = player.getName();
                        violationBuffer.type = type.name();
                        violationBuffer.info = NCPPanel.getInstance().getConfig().getString("autoCheckInfo");
                        if (vl > NCPPanel.getInstance().getConfig().getDouble("violations.high"))
                            violationBuffer.level = NCPPanel.getInstance().getConfig().getString("violation.highTitle");
                        ViolationBuffer.violationBuffers.put(System.currentTimeMillis(), violationBuffer);
                    }
                }
        }
    }
}
