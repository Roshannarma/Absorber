package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class SentrySlam extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("SentrySlam");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private boolean odd_turn = true;

    private static final int COST = 1;

    private static final int DAMAGE = 8;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    public SentrySlam() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void atTurnStart(){
        if (odd_turn) {
            this.baseDamage = this.baseDamage *2;
            odd_turn = false;
        }
        else {
            this.baseDamage = this.baseDamage / 2;
            this.upgradedDamage = false;
            odd_turn = true;
        }

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
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
