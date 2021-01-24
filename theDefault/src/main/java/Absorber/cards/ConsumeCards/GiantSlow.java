package Absorber.cards.ConsumeCards;

import Absorber.actions.DrainAction;
import Absorber.patches.MonsterRarityEnum;
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
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import Absorber.DefaultMod;
import Absorber.actions.UncommonPowerAction;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;
@AutoAdd.Ignore
public class GiantSlow extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("GiantSlow");
    public static final String IMG = makeFinalCardPath("GiantHead");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int UPGRADE_PlUS_COST = 1;



    // /STAT DECLARATION/

    public GiantSlow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m,p,new SlowPower(m,1),1));
        this.exhaust = true;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_PlUS_COST);
            initializeDescription();
        }
    }
}
