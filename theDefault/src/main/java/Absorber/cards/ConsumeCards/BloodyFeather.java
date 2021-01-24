package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.patches.DrainPatch;
import Absorber.patches.MonsterRarityEnum;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
public class BloodyFeather extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("BloodyFeather");
    public static final String IMG = makeFinalCardPath("Cultist"); // CorrosiveDagger_S.png


    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final Logger logger = LogManager.getLogger(BloodyFeather.class.getName());

    private static final int COST = 1;

    private static int DAMAGE = 0;    // DAMAGE = ${DAMAGE}

//    public static boolean first_turn = true;

    private static final int GAIN = 4;
    private static final int UPGRADE_PLUS_GAIN = 1;


    public BloodyFeather() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = GAIN;
    }
    @Override
    public void atTurnStart(){
//        logger.info("at turn start");
//        logger.info(first_turn);
//        logger.info(magicNumber);
//        if(!first_turn){
            upgradeDamage(this.magicNumber);
//        }
//        logger.info(first_turn);
//        first_turn = false;
//        logger.info(first_turn);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_GAIN);
            initializeDescription();
        }
    }
}
