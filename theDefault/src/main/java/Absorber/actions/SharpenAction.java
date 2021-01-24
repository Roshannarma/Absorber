package Absorber.actions;

import Absorber.cards.SpecialScalingCards;
import Absorber.relics.FreshSamplesRelic;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SharpenAction extends AbstractGameAction {
    private int increaseHpAmount;
    private static final float DURATION = 0.1F;
    private static final Logger logger = LogManager.getLogger(SharpenAction.class.getName());

    public SharpenAction(AbstractCreature target,AbstractCreature source, int amount) {
        this.setValues(target, source,amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.1F;
    }

    public void update() {
        for(AbstractCard o: AbstractDungeon.player.hand.group){
            if(o instanceof SpecialScalingCards){
                ((SpecialScalingCards) o).extra_damage += amount;
            }
            else {
                o.baseDamage += amount;
            }
            o.upgradedDamage = true;
            isDone = true;
        }
        this.tickDuration();
    }
}
