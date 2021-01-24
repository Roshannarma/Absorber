package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.patches.MonsterRarityEnum;
import basemod.AutoAdd;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class SentrySlam extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("SentrySlam");
    public static final String IMG = makeFinalCardPath("Sentry"); // ConsumeDagger.png
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
//    public static final String NORMAL_DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private boolean odd_turn = true;

    private static final int COST = 1;

    private static final int DAMAGE = 8;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int ODD_TURNS = 2;

    public SentrySlam() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void atTurnStart(){
        if (odd_turn) {
            baseMagicNumber = magicNumber = ODD_TURNS;
            this.upgradedDamage = true;
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            odd_turn = false;
//            rawDescription = UPGRADE_DESCRIPTION;
        }
        else {
            baseMagicNumber = magicNumber = 1;
            this.upgradedDamage = false;
            odd_turn = true;
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
//            rawDescription = NORMAL_DESCRIPTION;
        }

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!odd_turn){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
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
