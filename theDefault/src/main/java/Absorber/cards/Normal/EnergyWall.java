package Absorber.cards.Normal;

import Absorber.actions.ConsumeAction;
import Absorber.actions.Stimulated;
import Absorber.actions.WallDefendAction;
import Absorber.cards.AbstractDynamicCard;
import Absorber.cards.StimulatedCards;
import basemod.AutoAdd;
import basemod.interfaces.PostEnergyRechargeSubscriber;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class EnergyWall extends StimulatedCards {


    public static final String ID = DefaultMod.makeID("EnergyWall");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
//    private static final int UPGRADE_PLUS_COST = 0;

    private static final int BLOCK = 8;    // DAMAGE = ${DAMAGE}

    private static final int STIM_BLOCK = 5;
    private static final int UPGRADE_STIM_BLOCK = 4;

    public EnergyWall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = STIM_BLOCK;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(is_stimulated){
            this.baseBlock = BLOCK +  STIM_BLOCK;
            super.applyPowers();
        }
        else{
            this.baseBlock = BLOCK;
            super.applyPowers();
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,block));
        this.baseBlock = BLOCK;
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_STIM_BLOCK);
            initializeDescription();
        }
    }
}
