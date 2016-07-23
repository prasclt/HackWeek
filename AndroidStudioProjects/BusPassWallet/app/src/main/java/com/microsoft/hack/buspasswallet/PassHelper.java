package com.microsoft.hack.buspasswallet;

import com.microsoft.hack.buspasswallet.database.Pass;
import com.microsoft.hack.buspasswallet.database.User;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by prmeno on 7/23/2016.
 */
public class PassHelper {

    public static final int AC_MONTHLY = 0;
    public static final int NORMAL_MONTHLY = 1;
    public static final int AC_DAILY = 2;
    public static final int NORMAL_DAILY = 3;

    private static Date computeValidTo(Date validFrom, int type) {
        Calendar c = Calendar.getInstance();
        c.setTime(validFrom);

        switch (type) {
            case AC_MONTHLY:
            case NORMAL_MONTHLY:
                c.add(Calendar.MONTH, 1);
                c.set(Calendar.DAY_OF_MONTH, 1);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                break;
            case AC_DAILY:
            case NORMAL_DAILY:
                c.add(Calendar.DATE, 1);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                break;
        }

        return c.getTime();
    }

    public static String getTypeString(int type) {
        switch (type) {
            case AC_MONTHLY:
                return "AC Monthly";
            case NORMAL_MONTHLY:
                return "Normal Monthly";
            case AC_DAILY:
                return "AC Daily";
            case NORMAL_DAILY:
                return "Normal Daily";
        }

        return "Invalid Pass";
    }

    public static boolean expired(Pass pass) {
        Calendar c = Calendar.getInstance();

        return c.getTime().after(pass.getValidTo()) ? true : false;
    }

    public static Pass generatePassFromNow(User user, int passType) {
        Calendar c = Calendar.getInstance();
        Date validFrom = c.getTime();
        return new Pass(null, validFrom, computeValidTo(validFrom, passType), passType, user.getId());
    }
}
