package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
public class Cycle extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("Cycle");
    public static final String IMG = makeFinalCardPath("Cycle");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private boolean odd_turn = true;

    private static final int COST = 1;

    private static final int BLOCK = 9;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_BLOCK = 4;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}\

    private static final int DRAW = 1;


//    private static final int ODD_TURNS = 2;

    public Cycle() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = DRAW;
    }

    @Override
    public void atTurnStart(){
        if (odd_turn) {
            this.upgradedBlock = true;
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            odd_turn = false;
        }
        else {
            this.upgradedDamage = false;
            odd_turn = true;
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!odd_turn){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,block));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}

