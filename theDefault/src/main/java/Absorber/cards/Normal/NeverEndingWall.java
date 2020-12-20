package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.WallDefendAction;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class NeverEndingWall extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("NeverEndingWall");
    public static final String IMG = makeCardPath("Skill.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int BLOCK = 5;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_BLOCK = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int DEFENSE_INCREASE = 2;
//    private static final int UPGRADE_PLUS_DEFENSE_INCREASE = 1;

    public NeverEndingWall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = DEFENSE_INCREASE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int temp = block + WallDefendAction.returnTotal();
        AbstractDungeon.actionManager.addToBottom(
                new GainBlockAction(p,p,temp));
        AbstractDungeon.actionManager.addToBottom(new WallDefendAction(magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
//            upgradeMagicNumber(UPGRADE_PLUS_DEFENSE_INCREASE);
            initializeDescription();
        }
    }
}
