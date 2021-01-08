package Absorber.cards.Normal;

import Absorber.actions.DrainAction;
import Absorber.cards.SyringeCard;
import Absorber.powers.EntangleThemPower;
import Absorber.powers.GremlinStabsPower;
import Absorber.powers.LousePower;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.RarePower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.DefaultMod;
import Absorber.actions.UncommonPowerAction;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;
@AutoAdd.Ignore
public class ForcedBloodTransfusion extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("ForcedBloodTransfusion");
    public static final String IMG = makeFinalCardPath("ForcedTransfusion");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_PLUS_COST = 0;

    private static final int HP_LOSS = 3;
    private static final int UPGRADE_PLUS_HP_LOSS = -1;

    private static final int TEMP_BUFF = 2;
//    private static final int UPGRADE_PLUS_TEMP_BUFF = 2;

    // /STAT DECLARATION/

    public ForcedBloodTransfusion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = HP_LOSS;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = TEMP_BUFF;
//        this.tags.add();
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new LoseHPAction(p, p, magicNumber));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, defaultSecondMagicNumber, false), defaultSecondMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, defaultSecondMagicNumber, false), defaultSecondMagicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        this.exhaust = true;
    }
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HP_LOSS);
//            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_TEMP_BUFF);
            upgradeBaseCost(UPGRADE_PLUS_COST);
            initializeDescription();
        }
    }
}
