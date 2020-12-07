package Absorber.actions;

import Absorber.DefaultMod;
import Absorber.cards.DrainCard;
import Absorber.characters.TheDefault;
import Absorber.powers.DrainPower;
import Absorber.relics.DrainRelic;
import Absorber.relics.EKGRelic;
import basemod.abstracts.CustomPlayer;
import basemod.patches.com.megacrit.cardcrawl.events.SensoryStone.CustomCharacterText;
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
        AbstractDungeon.actionManager.addToBottom( new HealAction(temp,temp,amount));
        AbstractDungeon.actionManager.addToBottom( new LoseHPAction(target,temp,amount));
    }

    @Override
    public void update() {
//        logger.info("Made it this far1");
//        TheDefault temp = (TheDefault)AbstractDungeon.player;
//        on_drain(info);
//        logger.info("Made it this far2");
//        if (amount>target.currentHealth){
//            amount = target.currentHealth;
//        }
//        logger.info("Made it this far3  ");
//        AbstractDungeon.actionManager.addToBottom( new HealAction(temp,temp,amount));
//        AbstractDungeon.actionManager.addToBottom( new LoseHPAction(target,temp,amount));
//        logger.info(amount);
//        target.healthBarUpdatedEvent();
        isDone = true;
    }
    public DamageInfo on_drain(DamageInfo info){
        for( AbstractPower o:AbstractDungeon.player.powers){
            if(o instanceof DrainPower) {
                info = ((DrainPower) o).activate(info);
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                if (!(o instanceof EKGRelic)) {
                    info = ((DrainRelic) o).activate(info);
                }
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                if (o instanceof EKGRelic) {
                    info = ((DrainRelic) o).activate(info);
                }
            }

        }
        DrainAction.add(info);
        return info;
    }
    public static int on_drain(int amount){
        if(AbstractDungeon.player==null){
            return amount;
        }
        boolean have_double = false;
        for( AbstractPower o:AbstractDungeon.player.powers){
            if(o instanceof DrainPower) {
                amount = ((DrainPower) o).damage_check(amount);
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                if (!(o instanceof EKGRelic)) {
                    amount = ((DrainRelic) o).damage_check(amount);
                }
            }
        }
        for(AbstractRelic o: AbstractDungeon.player.relics){
            if(o instanceof DrainRelic) {
                if (o instanceof EKGRelic) {
                    amount = ((DrainRelic) o).damage_check(amount);
                }
            }

        }

//        DrainAction.add(amount);
        return amount;
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
