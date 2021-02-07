package Absorber.patches;

import Absorber.DefaultMod;
import Absorber.actions.DrainAction;
import Absorber.relics.EvolvingDaggerRelic;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


@SpirePatch(    // "Use the @SpirePatch annotation on the patch class."
        clz = RewardItem.class, // This is the class where the method we will be patching is. In our case - Abstract Dungeon
        method = SpirePatch.CONSTRUCTOR, // This is the name of the method we will be patching.
        paramtypez={
}
)
public class MobDropPatch {
//    private static Hitbox hb;
//    private static boolean isDone;
//    private static boolean ignoreReward;
//    private static boolean redText;
//    private static Color reticleColor;
//    private static boolean isBoss;
//    private static ArrayList<AbstractCard> cards;
//    private static String text;
//    private static String[] TEXT;
//    private static RewardItem.RewardType type;
//    private static float flashTimer;// Don't worry about the "never used" warning - *You* usually don't use/call them anywhere. Mod The Spire does.


    private static final Logger logger = LogManager.getLogger(MobDropPatch.class.getName()); // This is our logger! It prints stuff out in the console.
    // It's like a very fancy System.out.println();

    @SpireInsertPatch( // This annotation of our patch method specifies the type of patch we will be using. In our case - a Spire Insert Patch
            rloc = 9,
            localvars = {"cards"}

    )
    //"A patch method must be a public static method."
    public static void thisIsOurActualPatchMethod(RewardItem __instance, @ByRef ArrayList<AbstractCard>[] cards) {

            if(DefaultMod.DidConsume&& DefaultMod.consumed != null) {
//                __instance.hb = new Hitbox(460.0F * Settings.xScale, 90.0F * Settings.yScale);
//                __instance.flashTimer = 0.0F;
//                __instance.isDone = false;
//                __instance.ignoreReward = false;
//                __instance.redText = false;
//                __instance.reticleColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
//                __instance.type = RewardItem.RewardType.CARD;
//                __instance.isBoss = AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss;
//                ArrayList<AbstractCard> temp = new ArrayList<AbstractCard>(1);
//                temp.add(DefaultMod.consumed);
                cards[0].add(DefaultMod.consumed);
                logger.info("Hey our patch triggered. The relic we're about to get is " + cards[0]);
//                for(AbstractRelic a: AbstractDungeon.player.relics){
//                    if(a instanceof EvolvingDaggerRelic){
//                        ((EvolvingDaggerRelic) a).Evolve();
//                        logger.info("done");
//                    }
//                }

                DefaultMod.DidConsume = false;
                DefaultMod.consumed = null;
//                __instance.text = __instance.TEXT[2];
            }



    }

    private static class Locator extends SpireInsertLocator { // Hey welcome to our SpireInsertLocator class!
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {// All the locator has and needs is an override of the Locate method


            Matcher finalMatcher = new Matcher.InstanceOfMatcher(Hitbox.class);

            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);

        }
    }
}