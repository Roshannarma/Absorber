package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.Stimulated;
import Absorber.cards.AbstractDynamicCard;
import Absorber.cards.StimulatedCards;
import basemod.AutoAdd;
import basemod.interfaces.PostEnergyRechargeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class StimAOE extends StimulatedCards {


    public static final String ID = DefaultMod.makeID("StimAOE");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int DAMAGE = 7;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int STIMULATED_DAMAGE = 5;
    private static final int UPGRADE_PLUS_STIMULATED_DAMAGE = 2;

    public int base_secondary_damage;
    public int secondary_damage;
    public boolean isSecondaryModified;
    public boolean isSecondaryUpgraded;


    public StimAOE() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        base_secondary_damage = secondary_damage = STIMULATED_DAMAGE;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int temp = 0;
        for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!mon.isDeadOrEscaped()){
                temp++;
            }
        }
        int[] damageList = new int[temp];
        if(Stimulated.update()){
            for(int i=0;i<temp;i++){
                damageList[i] = damage + secondary_damage;
            }
        }
        else{
            for(int i=0;i<temp;i++){
                damageList[i] = damage;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(m,damageList,damageTypeForTurn,AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
    public void secondaryApplyPowers(){
        AbstractPlayer pla  = AbstractDungeon.player;
        float tmp = (float)this.base_secondary_damage;
        Iterator var3 = pla.relics.iterator();

        while(var3.hasNext()) {
            AbstractRelic r = (AbstractRelic)var3.next();
            tmp = r.atDamageModify(tmp, this);
            if (this.base_secondary_damage != (int)tmp) {
                this.isSecondaryModified = true;
            }
        }

        AbstractPower p;
        for(var3 = pla.powers.iterator(); var3.hasNext(); tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this)) {
            p = (AbstractPower)var3.next();
        }

        tmp = pla.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
        if (this.base_secondary_damage != (int)tmp) {
            this.isSecondaryModified = true;
        }

        for(var3 = pla.powers.iterator(); var3.hasNext(); tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this)) {
            p = (AbstractPower)var3.next();
        }

        if (tmp < 0.0F) {
            tmp = 0.0F;
        }

        if (this.base_secondary_damage != MathUtils.floor(tmp)) {
            this.isSecondaryModified = true;
        }
        this.secondary_damage = MathUtils.floor(tmp);
    }
    @Override
    public void applyPowers(){
        super.applyPowers();
        secondaryApplyPowers();
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            base_secondary_damage += UPGRADE_PLUS_STIMULATED_DAMAGE;
            isSecondaryUpgraded = true;
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }

}

