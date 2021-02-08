package ru.gkarmada.project.profile;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.gkarmada.project.genre.GenreService;
import ru.gkarmada.project.user.User;

@Controller
public class ProfileController {

  //@Autowired
  //private UserDataService UserDataService;

  //private @Autowired HttpServletRequest request;

  // log out
  /*
  @GetMapping(path = "/profile")
  public String logout(ModelMap model,HttpServletRequest request) throws ServletException {
    request.logout();

    //UserData user = UserDataService .handleUserInfoRequest();
    //  model.addAttribute("user", user);
    return "/userprofile/profile";
  }*/

  // how to create custom attributes for users and retrieve them from keycloak https://www.baeldung.com/keycloak-custom-user-attributes
 // https://stackoverflow.com/questions/32678883/keycloak-retrieve-custom-attributes-to-keycloakprincipal
  /*@GetMapping(path = "/profile")
  public String getUserInfo(Model model) {
    KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken)
        SecurityContextHolder.getContext().getAuthentication();

    Principal principal = (Principal) authentication.getPrincipal();
    String dob = "";
    String email = "";
    String name = "";
    String username = "";
    String lastname = "";
    String id= "";
    String telephone = "";
    String secondname = "";
    String givenusername = "";
    String role = "";
    String clientId = "project";


    if (principal instanceof KeycloakPrincipal) {
      KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
      IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();

      Map<String, Object> customClaims = token.getOtherClaims();

      email = String.valueOf(token.getEmail());
      name = String.valueOf(token.getGivenName());
      givenusername = String.valueOf(token.getPreferredUsername());
      username = String.valueOf(token.getName());
      lastname = String.valueOf(token.getFamilyName());
      id = String.valueOf(token.getId());
      telephone = String.valueOf(token.getPhoneNumber());
      secondname = String.valueOf(token.getMiddleName());
      role = String.valueOf(kPrincipal.getKeycloakSecurityContext().getToken().getResourceAccess(clientId).getRoles());

      if (customClaims.containsKey("DOB")) {
        dob = String.valueOf(customClaims.get("DOB"));
      }
    }else {
      return "/index";
    }

    model.addAttribute("email", email);
    model.addAttribute("username", principal.getName());
    model.addAttribute("name", name);
    model.addAttribute("givenusername", givenusername);
    model.addAttribute("lastname", lastname);
    model.addAttribute("id", id);
    model.addAttribute("telephone", telephone);
    model.addAttribute("secondname", secondname);
    model.addAttribute("username", username);
    model.addAttribute("role", role);
    model.addAttribute("dob", dob);
    return "/userprofile/profile";
  }*/

  /*
  @ModelAttribute("loggedinuser")
  public User globalUserObject(Model model) {
    // Add all null check and authentication check before using. Because this is global
    KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken)
        SecurityContextHolder.getContext().getAuthentication();

    Principal principal = (Principal) authentication.getPrincipal();
    model.addAttribute("loggedinuser", authentication.getName());
    // Create User pojo class
    User user = new User(authentication.getName());
    return user;
  }


 controller to get user data from keycloack
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
