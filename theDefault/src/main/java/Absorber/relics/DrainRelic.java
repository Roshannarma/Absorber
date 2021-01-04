package Absorber.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;

public abstract class DrainRelic extends CustomRelic {
    public DrainRelic(String id, Texture texture, RelicTier tier, LandingSound sfx) {
        super(id, texture, tier, sfx);
    }
    public DrainRelic(String id, Texture texture, Texture outline, RelicTier tier, LandingSound sfx) {
        super(id,texture,outline,tier,sfx);
    }
    public DamageInfo PreDrain(DamageInfo info){
        return info;
    }
    public int PreDrainCheck(int amount){
        return amount;
    }
    public DamageInfo AfterDrain(DamageInfo info){
        return info;
    }
}
