package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.DrainCard;
import Absorber.characters.TheDefault;
import Absorber.powers.DrainPower;
import Absorber.relics.DrainRelic;
import Absorber.relics.EKGRelic;
import basemod.abstracts.CustomPlayer;
//import basemod.patches.com.megacrit.cardcrawl.events.SensoryStone.CustomCharacterText;
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
import com.megacrit.cardcrawl.relics.Toolbox;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.powers.CommonPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DrainAction extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(DrainAction.class.getName());
    private int increaseHpAmount;
    private DamageInfo info;
//    private static final float DURATION = 0.1F;
    private int DRAIN_TOTAL;
    private static int TOTAL;
    public DrainAction(){

    }
    public DrainAction(AbstractCreature target, int amount) {
        this.DRAIN_TOTAL = amount;
        this.actionType = ActionType.HEAL;
        this.target=target;
        info = new DamageInfo(target,amount);
        TheDefault temp = (TheDefault)AbstractDungeon.player;
        info = on_drain(info);
        logger.info(amount);
        amount = info.base;
        if (amount>target.currentHealth){
            amount = target.currentHealth;
        }
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasRelic("Absorber:EKGRelic") && p.currentHealth < p.maxHealth*.5){
            info = new DamageInfo(info.owner,info.base*3);
            AbstractDungeon.actionManager.addToBottom( new LoseHPAction(target,temp,amount*3));
        }
        else{
            AbstractDungeon.actionManager.addToBottom( new LoseHPAction(target,temp,amount));
        }
        AbstractDungeon.actionManager.addToBottom( new HealAction(temp,temp,amount));
        DrainAction.add(new DamageInfo(info.owner,amount));
        after_drain(info);
    }

    @Override
    public void update() {
        isDone = true;
    }
    public DamageInfo on_drain(DamageInfo info){
        AbstractPlayer p = AbstractDungeon.player;
        for( AbstractPower o:p.powers){
            if(o instanceof DrainPower) {
                info = ((DrainPower) o).PreDrain(info);
            }
        }
        for(AbstractRelic o: p.relics){
            if(o instanceof DrainRelic) {
                info = ((DrainRelic) o).PreDrain(info);
            }
        }
//        if(p.hasRelic("Absorber:EKGRelic") && p.currentHealth < p.maxHealth*.5){
//            info = new DamageInfo(info.owner,info.base*2);
//        }
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
//        if(AbstractDungeon.player.hasRelic("EKGRelic")){
//            amount = amount*3;
//        }
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
    public static void add(DamageInfo info){
        TOTAL += info.base;
    }
    public static void add(int amount){
        TOTAL += amount;
    }
    public static int returnTotal(){
        return TOTAL;
    }
    public static void clear(){
        TOTAL = 0;
    }

}
