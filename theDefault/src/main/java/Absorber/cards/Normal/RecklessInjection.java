package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class RecklessInjection extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("RecklessInjection");
    public static final String IMG = makeFinalCardPath("RecklessInjection"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 14;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 6;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int HEALTH_LOSS = 3;

    public RecklessInjection() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = HEALTH_LOSS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p,magicNumber));
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
