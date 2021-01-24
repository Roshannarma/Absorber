package Absorber.powers;

import Absorber.DefaultMod;
import Absorber.patches.DrainPatch;
import Absorber.patches.MonsterRarityEnum;
import Absorber.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static Absorber.DefaultMod.makePowerPath;

public class MonsterDouble extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("MonsterDouble");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(DrainLashPower.class.getName());

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("DoubleTrouble84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("DoubleTrouble32.png"));
    private static int double_amount = 0;
//    private boolean EnergyGain = true;
    public MonsterDouble(final AbstractCreature owner, final AbstractCreature source, final int amount){
        this.owner = owner;
        this.amount = amount;
        double_amount = amount;
        this.source = source;
        type = PowerType.BUFF;
        name = NAME;
        ID = POWER_ID;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }
    @Override
    public void atStartOfTurn() {
        double_amount = amount;
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.rarity == MonsterRarityEnum.MONSTER && double_amount > 0){
            flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            double_amount--;
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }
    @Override
    public AbstractPower makeCopy() {
        return new MonsterDouble(owner, source, amount);
    }
    @Override
    public void updateDescription() {
        if(amount>1){
            description = DESCRIPTIONS[0];
        }
        else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }

    }
}