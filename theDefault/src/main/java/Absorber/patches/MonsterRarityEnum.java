package Absorber.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class MonsterRarityEnum {
    //    private static final Logger logger = LogManager.getLogger(DefaultInsertPatch.class.getName());
    @SpireEnum
    public static AbstractCard.CardRarity MONSTER;

    public static void check(AbstractCard c) {
        ExampleCard.foo(c.rarity);

    }
}

    class ExampleCard {
        private static final Logger logger = LogManager.getLogger(DefaultInsertPatch.class.getName());

        public static void foo(AbstractCard.CardRarity rarity)
        {
            // Prints "[IRONCLAD, THE_SILENT, CROWBOT, EXAMPLE_CHARACTER]"
            logger.info(Arrays.toString(AbstractCard.CardRarity.values()));
            if(rarity == MonsterRarityEnum.MONSTER) {
                logger.info("detected monster rarity");
            }

        }

}
