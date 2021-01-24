//package Absorber.cards.Normal;
//
//import Absorber.actions.DrainAction;
//import Absorber.powers.EntangleThemPower;
//import Absorber.powers.GremlinStabsPower;
//import Absorber.powers.LousePower;
//import Absorber.cards.AbstractDynamicCard;
//import Absorber.powers.RarePower;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
//import Absorber.DefaultMod;
//import Absorber.actions.UncommonPowerAction;
//import Absorber.characters.TheDefault;
//
//import static Absorber.DefaultMod.makeCardPath;
//import static Absorber.DefaultMod.makeFinalCardPath;
//
//public class Leeches extends AbstractDynamicCard {
//
//
//    // TEXT DECLARATION
//
//    public static final String ID = DefaultMod.makeID("Leeches");
//    public static final String IMG = makeFinalCardPath("Leeches");
//
//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//
//    // /TEXT DECLARATION/
//
//    // STAT DECLARATION
//
//    private static final CardRarity RARITY = CardRarity.COMMON;
//    private static final CardTarget TARGET = CardTarget.ENEMY;
//    private static final CardType TYPE = CardType.ATTACK;
//    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
//
//    private static final int COST = 1;
//    private static final int DAMAGE = 7;
//    private static final int UPGRADE_PLUS_DAMAGE = 2;
//
//    private static final int DRAIN_AMOUNT = 2;
//    private static final int UPGRADE_PLUS_DRAIN_AMOUNT = 1;
//
//    // /STAT DECLARATION/
//
//    public Leeches() {
//        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
//        baseDamage = damage = DAMAGE;
//        baseMagicNumber = magicNumber = DRAIN_AMOUNT;
//
//    }
//
//    // Actions the card should do.
//    @Override
//    public void use(final AbstractPlayer p, final AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
//        AbstractDungeon.actionManager.addToBottom(new DrainAction(m, magicNumber));
//    }
//
//    //Upgraded stats.
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            upgradeMagicNumber(UPGRADE_PLUS_DRAIN_AMOUNT);
//            upgradeDamage(UPGRADE_PLUS_DAMAGE);
////            rawDescription = UPGRADE_DESCRIPTION;
//            initializeDescription();
//        }
//    }
//}
