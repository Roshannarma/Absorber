package Absorber.powers;

import Absorber.DefaultMod;
import Absorber.patches.DrainPatch;
import Absorber.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static Absorber.DefaultMod.makePowerPath;

public class EnergyIfNotAttack extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("EnergyIfNotAttack");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(DrainLashPower.class.getName());

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));
    private boolean EnergyGain = true;
    public EnergyIfNotAttack(final AbstractCreature owner, final AbstractCreature source, final int amount){
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        name = NAME;
        ID = POWER_ID;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractPlayer p = AbstractDungeon.player;
        if(EnergyGain){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new EnergizedPower(p,amount),amount));
        }
        EnergyGain = true;
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK){
            EnergyGain = false;
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    @Override
    public AbstractPower makeCopy() {
        return new EnergyIfNotAttack(owner, source, amount);
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
