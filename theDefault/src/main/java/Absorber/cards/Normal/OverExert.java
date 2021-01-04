package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.DrawLessNextTurnPower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class OverExert extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("OverExert");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;

    private static final int DAMAGE = 15;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 5;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int MINUS_DRAW_ENERGY  = 1;
//    private static final int UPGRADE_PLUS_MINUS_DRAW_ENERGY = 1;

    public OverExert() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MINUS_DRAW_ENERGY;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DrawReduce(p,magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DrawLessNextTurnPower(p,p,magicNumber)));
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new EnergizedPower(p,-1*magicNumber)));
    }
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if(c.type==CardType.ATTACK){
            int temp;
            if(c.costForTurn== -1){
                temp = costForTurn - AbstractDungeon.player.energy.energyMaster;
            }
            else {
                temp = costForTurn - c.costForTurn;
            }
            if(temp<0){
                temp = 0;
            }
            setCostForTurn(temp);
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
//            upgradeMagicNumber(UPGRADE_PLUS_MINUS_DRAW_ENERGY);
            initializeDescription();
        }
    }
}