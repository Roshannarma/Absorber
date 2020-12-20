package Absorber.cards.Normal;

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
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class FinalAttack extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("FinalAttack");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 4;

    private static final int DAMAGE = 50;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 15;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int MINUS_DRAW_ENERGY  = 2;
    private static final int UPGRADE_PLUS_MINUS_DRAW_ENERGY = 1;

    public FinalAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MINUS_DRAW_ENERGY;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DrawReductionPower(p,magicNumber)));
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p,-1*magicNumber)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new EnergizedPower(p,-1*magicNumber)));
    }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MINUS_DRAW_ENERGY);
            initializeDescription();
        }
    }
}