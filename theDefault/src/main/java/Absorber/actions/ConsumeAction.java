package Absorber.actions;

import Absorber.cards.starter.LivingDagger;
import Absorber.relics.FreshSamplesRelic;
import Absorber.relics.LinkedSoulsRelic;
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
import Absorber.DefaultMod;

import static Absorber.DefaultMod.refresh;
import Absorber.TopPanel.FreshSamplesPanel;


public class ConsumeAction extends AbstractGameAction {
    private int increaseHpAmount;
//    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private AbstractRelic FreshSamplesRelic;
    private static final Logger logger = LogManager.getLogger(ConsumeAction.class.getName());

    public ConsumeAction(AbstractCreature target, DamageInfo info) {
//        this.info = info;
        this.setValues(target, info);
//        this.increaseHpAmount = maxHPAmount;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
//            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AddCardFromConsume temp = new AddCardFromConsume(target);
                Absorber.DefaultMod.DidConsume = true;
                logger.info("consumed");
//                ((FreshSamplesPanel) refresh).charged = true;
//                ((FreshSamplesPanel) refresh).ChangeImage();
                for(AbstractRelic a: AbstractDungeon.player.relics){
                    if(a instanceof LinkedSoulsRelic) {
                        ((LinkedSoulsRelic) a).refresh();
                    }
                }
                for(AbstractCard c: AbstractDungeon.player.masterDeck.group){
                    if(c instanceof LivingDagger){
                        ((LivingDagger) c).buff(c.magicNumber);
                    }
                }
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
