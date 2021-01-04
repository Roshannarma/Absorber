package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.Stimulated;
import Absorber.cards.AbstractDynamicCard;
import Absorber.cards.StimulatedCards;
import basemod.AutoAdd;
import basemod.interfaces.PostEnergyRechargeSubscriber;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class Draw_Attack extends StimulatedCards {


    public static final String ID = DefaultMod.makeID("Draw_Attack");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 8;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int DRAW  = 1;

    private static final int STIM_DRAW = 2;
//    private static final int UPGRADE_PLUS_STIM_DRAW = 1;

    public Draw_Attack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DRAW;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = STIM_DRAW;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(!Stimulated.update()){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,magicNumber));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,defaultBaseSecondMagicNumber));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
//            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_STIM_DRAW);
            initializeDescription();
        }
    }

}
