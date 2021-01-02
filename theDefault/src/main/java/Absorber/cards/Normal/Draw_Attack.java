package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.Stimulated;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class Draw_Attack extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("Draw_Attack");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final Logger logger = LogManager.getLogger(Draw_Attack.class.getName());

    private static boolean is_stimulated;

    private static final int COST = 1;

    private static final int DAMAGE = 9;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 4;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int DRAW  = 1;

    private static final int STIM_DRAW = 2;
    private static final int UPGRADE_PLUS_STIM_DRAW = 1;

    public Draw_Attack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = DRAW;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = STIM_DRAW;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(!Stimulated.update()){
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,magicNumber));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p,defaultBaseSecondMagicNumber));
        }
    }
//    @Override
//    public void triggerOnOtherCardPlayed(AbstractCard c) {
//        logger.info("other card played");
//        update_glow();
//    }
    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        logger.info("other card played");
        is_stimulated = EnergyPanel.totalCount-cardPlayed.costForTurn>AbstractDungeon.player.energy.energy;
        if(is_stimulated){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        logger.info("energy gained");
        update_glow();
    }
    @Override
    public void atTurnStart() {
        logger.info("turn started");
        update_glow();
    }
    @Override
    public void triggerWhenDrawn() {
        logger.info("turn started");
        update_glow();
    }
    public void update_glow(){
        logger.info("update");
        is_stimulated = Stimulated.update();
        if(is_stimulated){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }



    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_STIM_DRAW);
            initializeDescription();
        }
    }
}
