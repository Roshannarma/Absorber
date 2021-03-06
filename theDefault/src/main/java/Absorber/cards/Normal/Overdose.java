package Absorber.cards.Normal;

import Absorber.actions.DrainAction;
import Absorber.cards.SyringeCard;
import Absorber.powers.*;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.DefaultMod;
import Absorber.actions.UncommonPowerAction;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
@AutoAdd.Ignore
public class Overdose extends SyringeCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("Overdose");
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;

    private static final int HP_LOSS = 4;
//    private static final int UPGRADE_PLUS_HP_LOSS = 1;

    private static final int MANA_GAIN = 1;
    private static final int UPGRADE_PLUS_MANA_GAIN = 1;

    // /STAT DECLARATION/

    public Overdose() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = HP_LOSS;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = MANA_GAIN;
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainEnergyAction(defaultSecondMagicNumber)
        );
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p,new DamageNextTurnPower(p,p,magicNumber))
        );
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
//            upgradeMagicNumber(UPGRADE_PLUS_HP_LOSS);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_MANA_GAIN);
//            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
