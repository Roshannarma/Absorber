package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.DrainCard;
import Absorber.characters.TheDefault;
import Absorber.powers.DrainPower;
import Absorber.relics.DrainRelic;
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
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.powers.CommonPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class DrainAllAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(DrainAction.class.getName());
    private DamageInfo info;
    private int healing_total;
    public DrainAllAction(){

    }
    public DrainAllAction(int amount) {
        this.actionType = ActionType.HEAL;
        info = new DamageInfo(target,amount);
        TheDefault p = (TheDefault)AbstractDungeon.player;
        info = on_drain(info);
        logger.info(amount);
        healing_total = 0;
        ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
        for(AbstractMonster o: m) {
            amount = info.base;
            if (amount>o.currentHealth) {
                amount = o.currentHealth;
            }
            healing_total += amount;
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(o, p, amount));
            DrainAction.add(new DamageInfo(o,amount));
        }
        AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,healing_total));
        after_drain(info);
    }

    @Override
    public void update() {
        isDone = true;
    }
    public DamageInfo on_drain(DamageInfo info){
        for( AbstractPower o:AbstractDungeon.player.powers){
            if(o instanceof DrainPower) {
                info = ((DrainPower) o).PreDrain(info);
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                info = ((DrainRelic) o).PreDrain(info);
            }
        }
        if(AbstractDungeon.player.hasRelic("EKGRelic")){
            info = new DamageInfo(info.owner,info.base*2);

        }
        DrainAction.add(info);
        return info;
    }
    public static int on_drain(int amount){
        if(AbstractDungeon.player==null){
            return amount;
        }
        for( AbstractPower o:AbstractDungeon.player.powers){
            if(o instanceof DrainPower) {
                amount = ((DrainPower) o).PreDrainCheck(amount);
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                amount = ((DrainRelic) o).PreDrainCheck(amount);
            }
        }
        if(AbstractDungeon.player.hasRelic("EKGRelic")){
            amount = amount*2;

        }
//        DrainAction.add(info);
        return amount;
    }
    public static void after_drain(DamageInfo info){
        for( AbstractPower o:AbstractDungeon.player.powers){
            if(o instanceof DrainPower) {
                info = ((DrainPower) o).AfterDrain(info);
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                info = ((DrainRelic) o).AfterDrain(info);
            }
        }
    }

}
