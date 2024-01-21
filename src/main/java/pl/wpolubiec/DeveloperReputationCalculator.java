package pl.wpolubiec;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


public class DeveloperReputationCalculator {


    public static final int FOLLOWERS_MULTIPLIER = 2;
    public static final int HACKTOBER_BONUS = 10;


    public int calculate(Developer dev) {
        final int followersScore = Optional.ofNullable(dev.getFollowers())
                .map(List::size)
                .map(cnt -> cnt * FOLLOWERS_MULTIPLIER)
                .orElse(0);
        final ZonedDateTime now = ZonedDateTime.now();
        final boolean duringHacktober = now.getMonth() == Month.OCTOBER;

        if (duringHacktober) {
            return followersScore + HACKTOBER_BONUS;
        } else {
            return followersScore;
        }
    }

    public int calculate(Developer dev, ZonedDateTime now) {
        final int followersScore = Optional.ofNullable(dev.getFollowers())
                .map(List::size)
                .map(cnt -> cnt * FOLLOWERS_MULTIPLIER)
                .orElse(0);

        final boolean duringHacktober = now.getMonth() == Month.OCTOBER;

        if (duringHacktober) {
            return followersScore + HACKTOBER_BONUS;
        } else {
            return followersScore;
        }
    }
}
