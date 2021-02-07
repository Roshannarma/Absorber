package Absorber.relics;

import Absorber.actions.SpecialDiscoveryAction;
import Absorber.cards.ConsumeCards.Daggers.ReptoDaggerB;
import Absorber.cards.ConsumeCards.Daggers.ReptoDaggerG;
import Absorber.cards.ConsumeCards.Daggers.ReptoDaggerP;
import Absorber.cards.ConsumeCards.Daggers.ReptoDaggerR;
import Absorber.cards.starter.LivingDagger;
import Absorber.patches.MonsterRarityEnum;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class EvolvingDaggerRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("EvolvingDaggerRelic");
    private static final Texture FRESH = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));
    private static final Logger logger = LogManager.getLogger(EvolvingDaggerRelic.class.getName());

    public EvolvingDaggerRelic() {
        super(ID, FRESH,OUTLINE, RelicTier.STARTER, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    public static void Evolve(AbstractCard Dagger){
//        AbstractCard Dagger = null;
//        for(AbstractCard c: AbstractDungeon.player.masterDeck.group){
//            if(c instanceof LivingDagger){
//                logger.info("-2");
//                Dagger = c.makeStatEquivalentCopy();
//                logger.info("-1.5");
//                AbstractDungeon.player.masterDeck.group.remove(c);
//                logger.info("-1");
//            }
//        }
//        logger.info("-.5");
        if(Dagger != null){
//            logger.info(0);
            ArrayList<AbstractCard> choices = new ArrayList<AbstractCard>();
//            logger.info(1);
            AbstractCard DAMAGE_BUFFED = Dagger.makeStatEquivalentCopy();
            DAMAGE_BUFFED.baseDamage+=6;
            choices.add(DAMAGE_BUFFED);
            logger.info("damage buffed");
            if(Dagger.cost!=0){
                AbstractCard COST_REDUCE = Dagger.makeStatEquivalentCopy();
                ((LivingDagger) COST_REDUCE).changecost(0);
                choices.add(COST_REDUCE);
                logger.info("cost reduce");
            }
            if(!((LivingDagger) Dagger).upgrade_cards){
                AbstractCard UPGRADE_CARDS = Dagger.makeStatEquivalentCopy();
                ((LivingDagger) UPGRADE_CARDS).upgrade_cards = true;
                if(((LivingDagger) Dagger).healing){
                    UPGRADE_CARDS.rawDescription = "Deal !D! damage Fatal: Add an upgraded card to your reward pool. NL Heal 3 HP. NL Exhaust.";
                }
                else{
                    UPGRADE_CARDS.rawDescription = "Deal !D! damage Fatal: Add an upgraded card to your reward pool Exhaust.";
                }
                UPGRADE_CARDS.initializeDescription();
                choices.add(UPGRADE_CARDS);
                logger.info("upgradecards");
            }
            if(!((LivingDagger) Dagger).healing){
                AbstractCard HEALING = Dagger.makeStatEquivalentCopy();
                ((LivingDagger) HEALING).healing = true;
                if(((LivingDagger) HEALING).upgrade_cards){
                    HEALING.rawDescription = "Deal !D! damage Fatal: Add an upgraded card to your reward pool. NL Heal 3 HP. NL Exhaust.";
                }
                else{
                    HEALING.rawDescription = "Deal !D! damage Fatal: Add a card to your reward pool. NL Heal 3 HP. NL Exhaust.";
                }
                choices.add(HEALING);
                logger.info("healing");
            }
            AbstractDungeon.player.masterDeck.removeCard("Absorber:LivingDagger");
//            AbstractDungeon.player.masterDeck.removeCard(Dagger);
            RewardItem customEvolves = new RewardItem();
//            logger.info(choices);
//            logger.info(choices.size());
            if(choices.size() == 3){
                customEvolves.cards = choices;
            }
            else{
            Random rand = new Random();

            // create a temporary list for storing
            // selected element
            ArrayList<AbstractCard> newlist = new ArrayList<AbstractCard>();
//            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < 3; i++) {

                // take a raundom index between 0 to size
                // of given List
                int randomIndex = rand.nextInt(choices.size());

                // add element in temporary list
                newlist.add(choices.get(randomIndex));

                // Remove selected element from orginal list
                choices.remove(randomIndex);
                }
            customEvolves.cards = newlist;
            }
            AbstractDungeon.getCurrRoom().rewards.add(customEvolves);
//            AbstractDungeon.player.masterDeck.removeCard(Dagger);






//            AbstractCard cost_reduce = Dagger.makeStatEquivalentCopy();
//            ((LivingDagger) cost_reduce).changecost(0);
//            AbstractCard Upgrade_cards = Dagger.makeStatEquivalentCopy();
//            ((LivingDagger) Upgrade_cards).upgrade_cards = true;
//            Upgrade_cards.rawDescription = "Deal !D! damage Fatal: Add an upgraded card to your reward pool Exhaust.";
//            Upgrade_cards.initializeDescription();
//            ArrayList<AbstractCard> cards = new ArrayList<>();
//            cards.add(damage_buffed);
//            cards.add(cost_reduce);
//            cards.add(Upgrade_cards);
//            RewardItem customEvolves = new RewardItem();
//            customEvolves.cards = cards;
//            AbstractDungeon.getCurrRoom().rewards.add(customEvolves);

        }
        else{
            logger.info("you removed the dagger, why must you hurt me this way");
        }

    }





    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
