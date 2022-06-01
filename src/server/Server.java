package server;

import java.util.UUID;

import parts.PartRepository;

public class Server {
  private UUID id;
  private String name;
  private PartRepository partRepository;

  public Server(String name) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.partRepository = new PartRepository(this.id);
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public PartRepository getPartRepository() {
    return this.partRepository;
  }

  public void listRepositoryParts() {
    this.partRepository.listParts();
  }
}
