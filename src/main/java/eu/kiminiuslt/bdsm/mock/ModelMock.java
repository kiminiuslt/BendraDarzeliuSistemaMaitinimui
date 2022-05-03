package eu.kiminiuslt.bdsm.mock;

import eu.kiminiuslt.bdsm.model.Client;
import eu.kiminiuslt.bdsm.model.enums.JobPostType;

public class ModelMock {

  public Client getMockedClient() {
    return new Client("admin", "admin", JobPostType.DEMO);
  }
}
