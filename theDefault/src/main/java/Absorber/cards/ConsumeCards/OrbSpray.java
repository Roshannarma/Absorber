package Absorber.cards.ConsumeCards;

import Absorber.actions.ConsumeAction;
import Absorber.actions.DrainAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.patches.MonsterRarityEnum;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class OrbSpray extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("OrbSpray");
    public static final String IMG = makeFinalCardPath("OrbWalker"); // CorrosiveDagger_S.png


    private static final CardRarity RARITY = MonsterRarityEnum.MONSTER;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
//    public static boolean first_turn = true;

    private static final int DAMAGE = 0;    // DAMAGE = ${DAMAGE}

    private static final int GAIN = 6;
    private static final int UPGRADE_PLUS_GAIN = 2;


    public OrbSpray() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
        magicNumber = baseMagicNumber = GAIN;
    }


    @Override
    public void atTurnStart(){
//        if(!first_turn){
        upgradeDamage(this.magicNumber);
//        }
//        first_turn = false;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
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
