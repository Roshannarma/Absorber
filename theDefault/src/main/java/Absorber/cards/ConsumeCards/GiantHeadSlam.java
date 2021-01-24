package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.cards.starter.LivingDagger;
import Absorber.patches.MonsterRarityEnum;
import basemod.AutoAdd;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class GiantHeadSlam extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("GiantHeadSlam");
    public static final String IMG = makeCardPath("Attack.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final Logger logger = LogManager.getLogger(GiantHeadSlam.class.getName());

    private static final int COST = 2;

    private static final int DAMAGE = 35;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DAMAGE = 10;

    public int turn_counter = 0;


    public GiantHeadSlam() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
    }
    @Override
    public void atTurnStart(){
        turn_counter++;
        logger.info(turn_counter);
        if(turn_counter>=5){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
//            this.baseDamage = 40;
        }
        initializeDescription();
    }
    @Override
    public boolean canPlay(AbstractCard card){
        if(card instanceof GiantHeadSlam){
            return turn_counter>=5;
        }
        else{
            return true;
        }

    }
    @Override
    public void initializeDescription(){
        if(turn_counter<5){
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + (5 - turn_counter) + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        else{
            rawDescription = cardStrings.DESCRIPTION;
        }
        super.initializeDescription();

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(turn_counter>=5){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p,damage,damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
//            upgradeMagicNumber(UPGRADE_PLUS_GAIN);
            initializeDescription();
        }
    }
}
