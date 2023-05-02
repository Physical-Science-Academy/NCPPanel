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

import kotlin.Pair;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViolationBuffer {

    public static HashMap<Long, ViolationBuffer> violationBuffers = new HashMap<>();
    //缓存器
    public static HashMap<String, Pair<Integer, Long>> playerRecord = new HashMap<>();

    public static void addViolationBuffer(ViolationBuffer buffer) {
        //待优化
        if (hasSameValue(buffer)) {
            int ori = playerRecord.get(buffer.playerName).component1();
            long sys = playerRecord.get(buffer.playerName).component2();
            ViolationBuffer replace = violationBuffers.get(sys);
            replace.tick = ori + 1;
            playerRecord.put(buffer.playerName, new Pair<>(replace.tick, sys));
            violationBuffers.put(sys, replace);
        } else {
            long sys = System.currentTimeMillis();
            violationBuffers.put(sys, buffer);
            playerRecord.put(buffer.playerName, new Pair<>(1, sys));
        }
    }

    public static boolean hasSameValue(ViolationBuffer buffer) {
        AtomicBoolean result = new AtomicBoolean(false);
        violationBuffers.values().forEach(it -> {
            if (it.playerName.equals(buffer.playerName)) result.set(true);
        });
        return result.get();
    }

    public String playerName;
    public String type;
    public String info = "";
    public String level = "";

    public int tick = 1;

}
