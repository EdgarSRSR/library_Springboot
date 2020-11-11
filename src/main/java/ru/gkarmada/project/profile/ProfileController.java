package ru.gkarmada.project.profile;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gkarmada.project.genre.GenreService;

@Controller
public class ProfileController {

  @Autowired
  private UserDataService UserDataService;

  private @Autowired HttpServletRequest request;

  // log out
  @GetMapping(path = "/profile")
  public String logout(ModelMap model,HttpServletRequest request) throws ServletException {
    request.logout();

    UserData user = UserDataService .handleUserInfoRequest();
    model.addAttribute("user", user);
    return "/userprofile/profile";
  }


/* controller to get user data from keycloack
  @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
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
*/


}
