package net.homenet.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.util.Assert;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
    private final UserDetailsService userDetailsService;

    public SocialUserDetailsServiceImpl(UserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService, "UserDetailsService cannot be null.");
        this.userDetailsService = userDetailsService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        return new SocialUser(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
