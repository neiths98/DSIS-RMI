package parts;

import java.util.UUID;
import java.util.Vector;

public class PartRepository {
  private UUID id;
  private UUID serverId;
  private Vector<Part> parts;

  public PartRepository(UUID serverId) {
    this.id = UUID.randomUUID();
    this.serverId = serverId;
  }

  public UUID getId() {
    return this.id;
  }

  public UUID getServerId() {
    return this.serverId;
  }

  public Vector<Part> getParts() {
    return this.parts;
  }

}
