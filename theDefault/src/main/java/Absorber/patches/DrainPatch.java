package Absorber.patches;

import Absorber.DefaultMod;
import Absorber.actions.DrainAction;
import Absorber.actions.WallDefendAction;
import Absorber.cards.ConsumeCards.BloodyFeather;
import Absorber.cards.ConsumeCards.GiantHeadSlam;
import Absorber.cards.ConsumeCards.OrbSpray;
import Absorber.cards.starter.LivingDagger;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static Absorber.DefaultMod.temporaryCard;


@SpirePatch(    // "Use the @SpirePatch annotation on the patch class."
        clz = GameActionManager.class, // This is the class where the method we will be patching is. In our case - Abstract Dungeon
        method = "clear" // This is the name of the method we will be patching.
)
public class DrainPatch {// Don't worry about the "never used" warning - *You* usually don't use/call them anywhere. Mod The Spire does.


    private static final Logger logger = LogManager.getLogger(DrainPatch.class.getName()); // This is our logger! It prints stuff out in the console.
    // It's like a very fancy System.out.println();

    @SpireInsertPatch( // This annotation of our patch method specifies the type of patch we will be using. In our case - a Spire Insert Patch

            locator = Locator.class // Spire insert patches require a locator - this isn't something you import - this is something we write.


    )
    //"A patch method must be a public static method."
    public static void thisIsOurActualPatchMethod(
            // 1. "Patch methods are passed all the arguments of the original method,
            // 2. as well as the instance if original method is not static (instance first, then parameters).
            // 3. localvars are passed as arguments, appearing the in parameter list after the original method's parameters."

            // Example: if returnEndRandomRelicKey(RelicTier tier) were NOT static we would write our method parameters as such:
            // thisIsOurActualPatchMethod(AbstractDungeon __instance, AbstractRelic.RelicTier tier, String retVal)
            // As it stands, that method is static so it's not tied to a specific instance of AbstractDungeon. (Read up on what "static" means in java
            // if you don't understand this part).
            // As such we write our method parameters like this instead:
            ) {

        // Wow time to actually put stuff in the basegame code!!! Everything here will be executed exactly as written, at the line which we specified.
        // You can change retVal (using @byRef) to always return the same relic, or return a specific relic if it passes some check.
        // You can execute any other static method you have, you can save retVal to your personal public static variable to always be able to
        // reference the last relic you grabbed - etc. etc. The possibilities are endless. We're gonna do the following:
        logger.info("We are Clearing Everything now");
        DrainAction.clear();
        WallDefendAction.clear();
        for(AbstractCard c :AbstractDungeon.player.masterDeck.group){
            if(c instanceof GiantHeadSlam){
                ((GiantHeadSlam) c).turn_counter =0;
            }
        }
        BloodyFeather.first_turn = true;
        LivingDagger.first_turn = true;
        OrbSpray.first_turn = true;
        if(temporaryCard != null){
            AbstractDungeon.actionManager.addToBottom(new AddCardToDeckAction(temporaryCard));
            temporaryCard = null;
        }
//        DefaultMod.DidConsume = false;
//        DefaultMod.consumed = null;
        // Incredible.

        // Let's talk about @byRef for a bit.
        // https://github.com/kiooeht/ModTheSpire/wiki/@ByRef - Read the documentation it is really helpful!
        // If you grabbed retVal right now and did:

        // retVal = Anchor().relicID
        // logger.info("Hey our patch triggered. The relic we're about to get is " + retVal);

        // The logger would correctly print out saying "we're about to get Anchor".
        // However you wouldn't get anchor. You would get the standard relic roll.
        // The reason behind that is because by default, in patches, all variables are passed by Value, not by Reference.
        // (You can google what this means in java if you don't understand).
        // This means that while we can change retVal within our own method, it won't change in the actual, original basegame method.
        // If you want to do that, you'll need to use @byRef on the variable you want to change - this makes it get passed by reference.
        // In our case that would be retVal - so we can annotate it with @byRef in our parameters. Another thing to note is that non-array
        // objects must be converted to arrays.

        // So if our current method parameters are:
        // (AbstractRelic.RelicTier tier, String retVal)
        // We would instead have:
        // (AbstractRelic.RelicTier tier, @ByRef String[] retVal)

        // Then, when we want to use it, can just access the (for us) one and only value in that array of Strings - which would be placed at index 0.
        // retVal[0] = Anchor().relicID
        // Then the retVal would actually be changed outside of this method - inside returnRandomRelicKey();
    }

    private static class Locator extends SpireInsertLocator { // Hey welcome to our SpireInsertLocator class!
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {// All the locator has and needs is an override of the Locate method
            // In simple terms, the locator works like this:
            // We give is something to match with, and it returns the line number that it finds the ting on,
            // inside the method which we specified wayyyy early on in our @SpirePatch annotation.
            // The Locate method is of type int[] - it returns an array of ints. These ints are actually the matching line numbers.

            // This is where we open up the https://github.com/kiooeht/ModTheSpire/wiki/Matcher documentation.

            // The line in the original method, "return !RelicLibrary.getRelic(retVal).canSpawn() ? returnEndRandomRelicKey(tier) : retVal;"
            // is just a simple ternary operator, check out http://www.cafeaulait.org/course/week2/43.html
            // or https://stackoverflow.com/questions/8898590/short-form-for-java-if-statement or simply ask google.
            // if you can't spawn the relic(note the "!"), grab a new relic from the end of the list instead
            // (call the returnEndRandomRelicKey() method) - otherwise return the relic.

            // We want to insert our code immediately above it so we'll need to use a matcher against something in that line.
            // We have a few of options for this 1 particular line. Before you proceed, read the docs and see how many you can personally spot.

            // 1. RelicLibrary.getRelic - is calling the the getRelic() method of the RelicLibrary class.
            // (You can also see that by Ctrl+clicking on getRelic)
            // 2. getRelic(retVal).canSpawn() - is calling the canSpawn() method of the AbstractRelic class.
            // (get relic() returns an AbstractRelic so we just use canSpawn() directly from it to check if we can spawn it)
            // 3. returnEndRandomRelicKey(tier) - is calling the returnEndRandomRelicKey method of the AbstractDungeon class.
            // At the end of the day, all three of these are MethodCallMatchers.

            /*
             * BUT WAIT!
             * Did you know that the third option is *actually* on a completely different line than the first one?
             * Decompiling the code with a different decompiler shows that the last line is actually a lot more like this:
             *
             * if (!RelicLibrary.getRelic(retVal).canSpawn()) {
             *      return returnEndRandomRelicKey(tier);
             * }
             *  return retVal;
             *
             *  Which means that if we use the third matcher we will insert code inside the if statement, while if we use 1. or 2. - just outside of it.
             *
             *  Follow this guide to learn how to decompile your game.
             *  https://github.com/daviscook477/BaseMod/wiki/Decompiling-Your-Game
             *  Essentially - you would want to use JD-GUI and Luyten *both* to get a precise look.
             *  (You can 100% still totally use the IntelliJ for quick-referencing code, it is still very fast and convenient)
             *
             *  On a sidenote, you should enable debug lines in intelliJ both for bugfixing and seeing what thing is *really* on what line
             *  To do so:
             * 1. Ctrl+Shift+A
             * 2. Registry
             * 3. Scroll down and set decompiler.dump.original.lines to true
             */

            // A good way to choose, usually, would be to pick the matcher least likely to appear elsewhere in the code of the method
            // i.e. the rarest one. In this case, it doesn't really matter as it's 3 of the same matcher, and none of their methods
            // ever appear again anywhere else, so let's just go for the first one:
            // As the documentation says, put the Class type and the method name (as a string) as your parameters:

            Matcher finalMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");

            // Now we just have to return the line number corresponding to that particular method call.
            // We have 2 options:
            // 1. findInOrder - Returns the first line number that matches the description of the matcher
            // (i.e. the very first time it finds RelicLibrary.getRelic() in the method we're patching.)

            // 2. findAllInOrder - Returns an array of ints - all of the line numbers matching the description.
            // (This is, for example, if the method we're patching had used "RelicLibrary.getRelic()" 3 different times,
            // and we want to insert our code right before ALL of the matches, or before a particular one of them.)

            // In our case "RelicLibrary.getRelic()" is called only once, in that particular return statement, so we can just return it.
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);

            // If we wanted to use findAllInOrder instead, we would do it like this:
            // return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[0]};
            // The [0] in this case indicates the index of the line number in the array (in the order they were found)
            // The first (and for us, only) instance of "RelicLibrary.getRelic()" would be at index 0. The second at index 1, and so on.

            // Finally, if we wanted to insert our code before *every* line with a match, we would just skip the index and return the whole list of lines:
            // return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)
        }
    }
}