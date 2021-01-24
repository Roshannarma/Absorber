package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.powers.ProtectedPower;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class UnbreakableWill extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("UnbreakableWill");
    public static final String IMG = makeFinalCardPath("UnbreakableWill");


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int BLOCK = 6;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_BLOCK = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int PROTECTED = 1;
    private static final int UPGRADE_PLUS_PROTECTED = 1;

    public UnbreakableWill() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = PROTECTED;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p,p,block));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p,p,new ProtectedPower(p,magicNumber,false))
        );
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_PROTECTED);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
