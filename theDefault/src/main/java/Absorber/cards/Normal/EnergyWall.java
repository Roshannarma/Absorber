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
import com.megacrit.cardcrawl.actions.common.*;
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
public class EnergyWall extends StimulatedCards {


    public static final String ID = DefaultMod.makeID("EnergyWall");
    public static final String IMG = makeCardPath("Skill.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;

    private static final int BLOCK = 7;    // DAMAGE = ${DAMAGE}
    private static final int UPGRADE_PLUS_BLOCK = 3;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}

    private static final int STIMULATED_BLOCK = 7;
    private static final int UPGRADE_PLUS_STIMULATED_BLOCK = 3;

    public int base_secondary_block;
    public int secondary_block;
    public boolean isSecondaryModified;
    public boolean isSecondaryUpgraded;


    public EnergyWall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = block =  BLOCK;
        base_secondary_block = secondary_block = STIMULATED_BLOCK;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(Stimulated.update()){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,block+secondary_block));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,block));
        }
    }
    public void secondaryApplyPowers(){
            this.isSecondaryModified = false;
            float tmp = (float)this.base_secondary_block;

            Iterator var2;
            AbstractPower p;
            for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlock(tmp, this)) {
                p = (AbstractPower)var2.next();
            }

            for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlockLast(tmp)) {
                p = (AbstractPower)var2.next();
            }

            if (this.base_secondary_block != MathUtils.floor(tmp)) {
                this.isSecondaryModified = true;
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }
            this.secondary_block = MathUtils.floor(tmp);
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
            base_secondary_block += UPGRADE_PLUS_STIMULATED_BLOCK;
            isSecondaryUpgraded = true;
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

}