package eu.kiminiuslt.bdsm.security.service;

import eu.kiminiuslt.bdsm.jpa.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {
  private final ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return clientRepository
        .findUserByEmailWithAuthorities(username)
        .orElseThrow(() -> new UsernameNotFoundException("'" + username + "' not found!"));
  }
}
