package Absorber.actions;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Stimulated {
    private boolean is_stimulated;
    public static boolean update(){
        if (AbstractDungeon.player.hasRelic("Absorber:Catalyst")){
            return EnergyPanel.totalCount - 1>AbstractDungeon.player.energy.energy;
        }
        else{
            return EnergyPanel.totalCount>AbstractDungeon.player.energy.energy;
        }
    }
//    public boolean getBoolean(){
//        return is_stimulated;
//    }
}
