package auth;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AppBasicAuthenticator implements Authenticator<BasicCredentials, User> {

    //private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of("user", ImmutableSet.of("USER"));

    private String login;
    private String password;

    public AppBasicAuthenticator(String login, String password) {
        this.login = login;
        this.password = password;
    }
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (login.equals(credentials.getUsername()) && password.equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername()));
        }
        return Optional.empty();
    }
}
