package eu.kiminiuslt.bdsm.security.controller;

import eu.kiminiuslt.bdsm.jpa.entity.Client;
import eu.kiminiuslt.bdsm.security.model.LoginResponse;
import eu.kiminiuslt.bdsm.security.model.UserDto;
import eu.kiminiuslt.bdsm.security.service.JwtProvider;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/login")
@Api(tags = "Login Controller")
@RequiredArgsConstructor
public class LoginController {

  private final AuthenticationManager authenticationManager;
  private final JwtProvider jwtProvider;

  @PostMapping
  public ResponseEntity<LoginResponse> login(@RequestBody UserDto userDto) {
    return Optional.of(authenticate(userDto.getUsername(), userDto.getPassword()))
        .map(auth -> (Client) auth.getPrincipal())
        .map(
            principal ->
                ok(
                    LoginResponse.of(
                        userDto.getUsername(),
                        jwtProvider.getToken(principal),
                        getRoles(principal))))
        .orElseThrow(() -> new BadCredentialsException("Authentication failed"));
  }

  private Set<String> getRoles(Client client) {
    return client.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet());
  }

  private Authentication authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);
    return authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(username, password));
  }
}
