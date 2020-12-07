package Absorber.cards.ConsumeCards;

import Absorber.actions.DrainAction;
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
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.DefaultMod;
import Absorber.actions.UncommonPowerAction;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;
//@AutoAdd.Ignore
public class LagavulinDrain extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("LagavulinDrain");
    public static final String IMG = makeFinalCardPath("Lagavulin");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -1;

    private static final int DRAIN_AMOUNT = 1;

    // /STAT DECLARATION/

    public LagavulinDrain() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DRAIN_AMOUNT;

    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        magicNumber = get_total();
        AbstractDungeon.actionManager.addToBottom(new DrainAction(m, magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p,new StrengthPower(p,magicNumber),magicNumber));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m,p,new StrengthPower(p,-1*magicNumber),-1*magicNumber));
        this.exhaust = true;

    }
    public int get_total(){
        AbstractPlayer p = AbstractDungeon.player;
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1) {
            effect = energyOnUse;
        }
        if (p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            p.getRelic(ChemicalX.ID).flash();
        }
        if (upgraded) {
            ++effect;
        }
        p.energy.use(EnergyPanel.totalCount);
        return effect;
    }
    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}