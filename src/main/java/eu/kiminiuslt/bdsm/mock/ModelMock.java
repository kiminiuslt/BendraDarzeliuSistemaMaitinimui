package eu.kiminiuslt.bdsm.mock;

import eu.kiminiuslt.bdsm.model.Client;
import eu.kiminiuslt.bdsm.model.enums.JobPost;

public class ModelMock {

  public Client getMockedClient() {
    return new Client("admin", "admin", JobPost.DEMO);
  }
}
