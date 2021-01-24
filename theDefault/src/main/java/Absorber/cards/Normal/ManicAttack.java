package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.DrainAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.cards.SpecialScalingCards;
import basemod.AutoAdd;
import basemod.helpers.dynamicvariables.MagicNumberVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class ManicAttack extends SpecialScalingCards {


    public static final String ID = DefaultMod.makeID("ManicAttack");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final int COST = 2;

    private static final int DAMAGE = 3;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int loops = 4;

    public ManicAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = DAMAGE;
        baseDamage = damage = 3;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.currentHealth<p.maxHealth*.5){
            this.baseDamage = magicNumber * 2;
        }
        else{
            this.baseDamage = magicNumber;
        }
        this.baseDamage += extra_damage;
        calculateCardDamage(m);
        for(int i=0; i<loops;i++){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }
    @Override
    public void applyPowers(){
        AbstractPlayer p = AbstractDungeon.player;
        if(p.currentHealth<p.maxHealth*.5) {
            this.baseDamage = magicNumber * 2;
            this.baseDamage += extra_damage;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
        }
        else {
            this.baseDamage = magicNumber;
            this.baseDamage += extra_damage;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION;
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
        initializeDescription();
    }
    @Override
    public void tookDamage(){
        AbstractPlayer p = AbstractDungeon.player;
        if(p.currentHealth<p.maxHealth*.5) {
            this.baseDamage = magicNumber * 2;
            this.baseDamage += extra_damage;
        }
        else{
            this.baseDamage = magicNumber;
            this.baseDamage += extra_damage;
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
