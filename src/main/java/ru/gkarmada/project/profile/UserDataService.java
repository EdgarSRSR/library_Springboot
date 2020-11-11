package ru.gkarmada.project.profile;

import java.security.Principal;
import java.util.Map;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {

  public UserData handleUserInfoRequest(Principal principal) {
    System.out.println("principal "+principal);

    UserData user = new UserData();

    if (principal instanceof KeycloakPrincipal) {

      KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
      AccessToken token = kp.getKeycloakSecurityContext().getToken();
      user.setId(token.getId());
      user.setUserName(token.getName());
      Map<String, Object> otherClaims = token.getOtherClaims();
      user.setCustomAttributes(otherClaims);
    }
    return user;
  }

}
