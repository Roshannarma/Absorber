package Absorber.cards.Normal;

import Absorber.actions.DrainAction;
import Absorber.powers.EntangleThemPower;
import Absorber.powers.GremlinStabsPower;
import Absorber.powers.LousePower;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.RarePower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;
//@AutoAdd.Ignore
public class EnergySurge extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID("EnergySurge");
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final Logger logger = LogManager.getLogger(EnergySurge.class.getName());

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = -1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    // /STAT DECLARATION/

    public EnergySurge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DAMAGE;
        this.baseDamage = 0;

    }

    // Actions the card should do.
    public int get_cost(){
        int effect = EnergyPanel.totalCount;
//        if (this.energyOnUse != -1) {
//            effect = this.energyOnUse;
//        }
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            effect += 2;
            AbstractDungeon.player.getRelic("Chemical X").flash();
        }
        return effect;
    }
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int temp = get_cost();
        logger.info(2);
        logger.info(temp);
        if (temp > 0) {
            if(EnergyPanel.totalCount>AbstractDungeon.player.energy.energy){
                this.baseDamage = temp * magicNumber *2;
            }
            else{
                this.baseDamage = temp * magicNumber;
            }
            calculateCardDamage(m);
            EnergyPanel.setEnergy(0);
            addToBot(new DamageAction(m, new DamageInfo(m, damage)));
//            this.exhaust = true;
//            EnergyPanel.setEnergy(0);
        }
        else{
            this.baseDamage = 0;
            calculateCardDamage(m);
            EnergyPanel.setEnergy(0);
            addToBot(new DamageAction(m, new DamageInfo(m, damage)));
//            this.exhaust = true;
        }
    }
    //Upgraded stats.
    @Override
    public void applyPowers(){
        int temp  = get_cost();
        logger.info(temp);
        if(temp > 0){
            if(EnergyPanel.totalCount>AbstractDungeon.player.energy.energy){
                this.baseDamage = temp* magicNumber *2;
            }
            else{
                this.baseDamage = temp * magicNumber;
            }
            super.applyPowers();
//            calculateCardDamage((AbstractMonster)null);
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        else{
            this.baseDamage = 0;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    }
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
             super.calculateCardDamage(mo);
             this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
             initializeDescription();
           }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DAMAGE);
//            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
