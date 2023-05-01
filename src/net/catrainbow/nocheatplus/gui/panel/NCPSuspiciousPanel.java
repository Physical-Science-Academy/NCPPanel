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
import net.catrainbow.nocheatplus.gui.NCPPanel;
import net.catrainbow.nocheatplus.gui.ViolationBuffer;

import java.util.ArrayList;

/**
 * 可疑玩家和举报收集面板
 *
 * @author Catrainbow
 */
public class NCPSuspiciousPanel extends FormSimple {

    public ArrayList<ViolationBuffer> list = new ArrayList<>();

    public NCPSuspiciousPanel() {
        super(NCPPanel.getInstance().formatLang("suspicious.title"), NCPPanel.getInstance().formatLang("suspicious.content"));
        for (Long sinceTimeMillis : ViolationBuffer.violationBuffers.keySet()) {
            int diff = (int) ((System.currentTimeMillis() - sinceTimeMillis) / 1000L);
            ViolationBuffer buffer = ViolationBuffer.violationBuffers.get(sinceTimeMillis);
            list.add(buffer);
            this.addButton(NCPPanel.getInstance().formatLang("suspicious.button").replace("@tick", String.valueOf(diff)).replace("@level", buffer.level).replace("@player", buffer.playerName).replace("@next", "\n").replace("@reason", buffer.type).replace("@info", buffer.info));
        }
    }

    @Override
    public void onClicked(int id, Player player) {
        ViolationBuffer buffer = list.get(id);
        player.showFormWindow(new NCPPlayerViolationsPanel(buffer.playerName));
    }
}
