package Absorber.cards.ConsumeCards;

import Absorber.patches.MonsterRarityEnum;
import Absorber.powers.GremlinStabsPower;
import Absorber.powers.LousePower;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.RarePower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
public class GremlinRally extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("GremlinRally");
    public static final String IMG = makeCardPath("Gremlins/GremlinRally.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int NORMAL = 0;

    private static final int UPGRADE_PLUS_NORMAL = 1;

    // /STAT DECLARATION/

    public GremlinRally() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = NORMAL;
        this.cardsToPreview = new GremlinStab();
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if(magicNumber==0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GremlinStabsPower(p, p, 1,false), 1));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new GremlinStabsPower(p, p, 1,true), 1));
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_NORMAL);
            rawDescription = UPGRADE_DESCRIPTION;
            AbstractCard card_to_show = new GremlinStab();
            card_to_show.upgrade();
            this.cardsToPreview = card_to_show;
            initializeDescription();
        }
    }
}