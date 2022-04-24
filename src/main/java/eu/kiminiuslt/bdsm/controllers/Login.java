package eu.kiminiuslt.bdsm.controllers;

import eu.kiminiuslt.bdsm.mock.ModelMock;
import eu.kiminiuslt.bdsm.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class Login {
  private final ModelMock modelMock = new ModelMock();

  @GetMapping("/")
  public String login(Model model) {
    model.addAttribute("client", new Client());
    return "login";
  }

  @PostMapping("/")
  public String loggedOrNot(Client client) {
    if (client.getUsername().equals(modelMock.getMockedClient().getUsername())
        && client.getPassword().equals(modelMock.getMockedClient().getPassword())) {
      return "home";
    } else {
      return "login";
    }
  }
}
