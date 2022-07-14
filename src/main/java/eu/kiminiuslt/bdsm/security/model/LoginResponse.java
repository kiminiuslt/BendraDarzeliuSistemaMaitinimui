package eu.kiminiuslt.bdsm.security.model;

import lombok.Value;

import java.util.Set;

@Value(staticConstructor = "of")
public class LoginResponse {
  String username;
  String jwtToken;
  Set<String> roles;
}
