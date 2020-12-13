package org.makeriga.tgbot.features.notifyarrival;

import java.util.Calendar;
import java.util.Date;
import org.makeriga.tgbot.Settings;

public class ArrivalNotification {
    
    public static final int STEP__FINISHED = 0;
    public static final int STEP__AFTER_HOURS_Q = 1;
    public static final int STEP__STAYTIME_Q = 2;
    public static final int STEP__EXTRA_PERSONS_Q = 3;
    public static final int STEP__CONFIRMATION = 4;
    
    public String memberName;
    public Date referenceDate = new Date();
    public int arrivalAfterMinutes;
    public int stayMinutes;
    public int step = STEP__AFTER_HOURS_Q;
    public int extraMembers;
    
    @Override
    public String toString() {
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.add(Calendar.MINUTE, arrivalAfterMinutes);
        Date arrivalDate = c.getTime();
        c.add(Calendar.MINUTE, stayMinutes);
        Date leaveDate = c.getTime();
        return String.format("%s announces their arrival - will arrive at %s; will stay until %s.", memberName, Settings.DF__TEXT.format(arrivalDate), Settings.DF__TEXT.format(leaveDate)) + (extraMembers > 0 ? " Extra " + extraMembers + " pers. will come." : "");
    }
}
