package Absorber.cards.ConsumeCards;

import Absorber.powers.EntangleThemPower;
import Absorber.powers.GremlinStabsPower;
import Absorber.powers.LousePower;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.RarePower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
//@AutoAdd.Ignore
public class EntangleThem extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("EntangleThem");
    public static final String IMG = makeCardPath("Slavers/SlaversEntangle.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADE_PLUS_COST = 2;

    // /STAT DECLARATION/

    public EntangleThem() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EntangleThemPower(p, p), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_PLUS_COST);
            initializeDescription();
        }
    }
}
