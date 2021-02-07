package Absorber.cards.starter;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.patches.DefaultInsertPatch;
import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DamageHooks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.ThroughViolence;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.ThornsPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class LivingDagger extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("LivingDagger");
    public static final String IMG = makeCardPath("ConsumeDagger.png"); // ConsumeDagger.png
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final Logger logger = LogManager.getLogger(LivingDagger.class.getName());

    private static final int COST = 1;

    private static final int DAMAGE = 4;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    public static boolean first_turn;
    public boolean upgrade_cards = false;
    public boolean healing = false;
//    private static final int BUFF = 1;
//    private static final int BUFF_UPGRADE_FROM_RELIC = 2;

    public LivingDagger() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
//        baseDamage = DAMAGE + ConsumeAction.buff_total;
//        logger.info(DAMAGE + ConsumeAction.buff_total);
//        baseMagicNumber = magicNumber = BUFF;
    }
    public LivingDagger(int basedamage, boolean upgrade_buff, boolean healing_buff){
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = basedamage;
        upgrade_cards = upgrade_buff;
        healing = healing_buff;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(
                new ConsumeAction(m,new DamageInfo(p, damage, damageTypeForTurn),this,upgrade_cards,healing));
//        this.buff(magicNumber);
        this.exhaust = true;
    }
    public void previewupdate(){
        this.cardsToPreview = DefaultMod.temporary;
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + DefaultMod.temporary + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
//        logger.info(DefaultMod.temporary);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
//    @Override
//    public void updatedescription(){
//
//    }
    public void changecost(int amount){
        upgradeBaseCost(amount);
    }
    @Override
    public AbstractCard makeCopy(){
        return new LivingDagger(baseDamage,upgrade_cards,healing);
    }
//    public void buff(int amount){
////        this.misc +=1;
////        logger.info(ConsumeAction.buff_total);
////        logger.info(misc);
//        upgradeDamage(amount);
//    }
}