package com.microsoft.hack.buspasswallet.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by prmeno on 7/15/2016.
 */
public class Pass {

    public static final int AC_MONTHLY = 0;
    public static final int NORMAL_MONTHLY = 1;
    public static final int AC_DAILY = 2;
    public static final int NORMAL_DAILY = 3;

    private Date validFrom;
    private Date validTo;
    private User user;
    private int type;

    public Pass(Date validFrom, User user, int type) {
        this.validFrom = validFrom;
        this.user = user;
        this.type = type;
        setValidTo();
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public User getUser() {
        return user;
    }

    public int getType() {
        return type;
    }

    private void setValidTo() {
        Calendar c = Calendar.getInstance();
        c.setTime(validFrom);

        switch (type) {
            case AC_MONTHLY:
            case NORMAL_MONTHLY:
                c.add(Calendar.MONTH, 1);
                break;
            case AC_DAILY:
            case NORMAL_DAILY:
                c.add(Calendar.DATE, 1);
                break;
        }

        validTo = c.getTime();
    }

    public static Pass generatePassFromNow(User user, int passType) {
        Calendar c = Calendar.getInstance();
        return new Pass(c.getTime(), user, passType);
    }
}
