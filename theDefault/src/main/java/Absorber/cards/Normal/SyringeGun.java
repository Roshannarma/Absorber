package Absorber.cards.Normal;

import Absorber.actions.DrainAction;
import Absorber.cards.SyringeCard;
import Absorber.cards.SyringeDrainCard;
import Absorber.powers.EntangleThemPower;
import Absorber.powers.GremlinStabsPower;
import Absorber.powers.LousePower;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.RarePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.DefaultMod;
import Absorber.actions.UncommonPowerAction;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

public class SyringeGun extends SyringeDrainCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("SyringeGun");
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DRAIN_AMOUNT = 1;
    private static final int UPGRADE_PLUS_DRAIN_AMOUNT = 1;

    private static final int TIMES = 4;

    // /STAT DECLARATION/

    public SyringeGun() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DRAIN_AMOUNT;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = TIMES;
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for(int i=0; i<defaultSecondMagicNumber; i++){
            AbstractDungeon.actionManager.addToBottom(new DrainAction(m, magicNumber));
        }
        this.exhaust = true;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DRAIN_AMOUNT);
//            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_TIMES);
            initializeDescription();
        }
    }
}
