package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class StabbingBook extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("StabbingBook");
    public static final String IMG = makeFinalCardPath("BookOfStabbing"); // CorrosiveDagger_S.png


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static int DAMAGE = 6;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

//    private static int ACTUAL_DAMAGE;


    private static final int TURNS = 0;

    public StabbingBook() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 0;
    }

//    @Override
//    public void triggerAtStartOfTurn() {
//        magicNumber+=defaultBaseSecondMagicNumber;
//    }

    @Override
    public void atTurnStart(){
        upgradeMagicNumber(1);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i=0; i<magicNumber;i++){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
//        this.exhaust = true;
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
