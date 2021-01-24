package Absorber.cards.ConsumeCards;

import Absorber.DefaultMod;
import Absorber.cards.AbstractDynamicCard;
import Absorber.characters.TheDefault;
import Absorber.patches.MonsterRarityEnum;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class ChosenAttack extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID("ChosenAttack");
    public static final String IMG = makeFinalCardPath("Chosen"); // CorrosiveDagger_S.png


    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 10;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int ARTIFACT = 1;
    private static final int UPGRADE_PLUS_ARTIFACT = 1;


    public ChosenAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = ARTIFACT;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && !(m.getIntentBaseDmg() >= 0)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ArtifactPower(p,magicNumber),magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_ARTIFACT);
            initializeDescription();
        }
    }
}
