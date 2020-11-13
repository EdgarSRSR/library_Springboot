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

  /*
  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, String> handlePublicRequest() {
    return Collections.singletonMap("message", "public access");
  }*/
/*
  @Autowired
  private HttpServletRequest request;
  Principal user = request.getUserPrincipal();
 mqMessage.setUserName(user.getName());
  Principal user = request.getUserPrincipal();
  mqMessage.setUserName(user.getName());
  KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();
  KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
  KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
  AccessToken accessToken = session.getToken();
  username = accessToken.getPreferredUsername();
  emailID = accessToken.getEmail();
  lastname = accessToken.getFamilyName();
  firstname = accessToken.getGivenName();
  realmName = accessToken.getIssuer();
  Access realmAccess = accessToken.getRealmAccess();
  roles = realmAccess.getRoles();
*/


}
