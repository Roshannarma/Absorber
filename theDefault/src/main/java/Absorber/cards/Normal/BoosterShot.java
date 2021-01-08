package Absorber.cards.Normal;

import Absorber.actions.DrainAction;
import Absorber.cards.SyringeCard;
import Absorber.powers.*;
import Absorber.cards.AbstractDynamicCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
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

public class BoosterShot extends SyringeCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("BoosterShot");
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int HP_GAIN = 3;
    private static final int UPGRADE_PLUS_HP_GAIN = 1;

    private static final int PROTECTED = 1;
    private static final int UPGRADE_PLUS_PROTECTED = 1;

    // /STAT DECLARATION/

    public BoosterShot() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = HP_GAIN;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = PROTECTED;
        this.tags.add(CardTags.HEALING);

    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new HealAction(p, p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ProtectedPower(AbstractDungeon.player, defaultSecondMagicNumber,false), defaultSecondMagicNumber));
        this.exhaust = true;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HP_GAIN);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_PROTECTED);
//            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
