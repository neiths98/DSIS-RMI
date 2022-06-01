package server;

import java.util.UUID;

import parts.Part;
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

  public boolean addPartToRepository(Part newPart) {
    return this.partRepository.addPart(newPart);
  }

  public boolean removePartFromRepository(UUID partId) {
    return this.partRepository.removePart(partId);
  }

  public void listRepositoryParts() {
    this.partRepository.listParts();
  }
}
