package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.DrainCard;
import Absorber.characters.TheDefault;
import Absorber.powers.DrainPower;
import basemod.abstracts.CustomPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.powers.CommonPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WallDefendAction extends AbstractGameAction {
    private int increaseHpAmount;
    private DamageInfo info;
    private static int TOTAL;
    public WallDefendAction(){

    }

    @Override
    public void update() {
        isDone=true;
    }

    public WallDefendAction( int amount) {
        this.actionType = ActionType.BLOCK;
        add(amount);
    }
    public static void add(int increase){
        TOTAL += increase;
    }
    public static int returnTotal(){
        return TOTAL;
    }
    public static void clear(){
        TOTAL = 0;
    }

}