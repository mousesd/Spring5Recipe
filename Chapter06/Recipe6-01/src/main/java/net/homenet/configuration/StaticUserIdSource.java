package net.homenet.configuration;

import org.springframework.social.UserIdSource;

public class StaticUserIdSource implements UserIdSource {
    private static final String DEFAULT_USERID = "anonymous";
    private String userId = DEFAULT_USERID;

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
