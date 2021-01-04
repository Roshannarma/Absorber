package Absorber.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class DrainPower extends AbstractPower implements CloneablePowerInterface {
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
