package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.Stimulated;
import Absorber.cards.AbstractDynamicCard;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import java.util.ArrayList;

import static Absorber.DefaultMod.makeCardPath;
import static Absorber.DefaultMod.makeFinalCardPath;

//@AutoAdd.Ignore
public class FalseAOE extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID("FalseAOE");
    public static final String IMG = makeCardPath("Attack.png");


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 10;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    public FalseAOE() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        ArrayList<AbstractMonster> temp = new ArrayList<AbstractMonster>();
//        for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters){
//            if (!mon.isDeadOrEscaped()){
//                temp.add(mon);
//            }
//        }
        ArrayList<AbstractMonster> temp = AbstractDungeon.getCurrRoom().monsters.monsters;
        int[] damageList = new int[temp.size()];
        for(int i=0;i<temp.size();i++){
            calculateCardDamage(temp.get(i));
            if(damage >= temp.get(i).currentBlock + temp.get(i).currentHealth){
                damage = temp.get(i).currentBlock + temp.get(i).currentHealth - 1;
            }
            damageList[i] = damage;
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,damageList, DamageInfo.DamageType.NORMAL,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
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
}
