package parts;

import java.util.UUID;
import java.util.Vector;

public class Part {
  private UUID id;
  private String name;
  private String description;
  private Vector<SubPart> subParts;

  public Part(String name, String description) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.subParts = new Vector<SubPart>();
  }

  public Part(String name, String description, Vector<SubPart> subParts) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.subParts = subParts;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public Vector<SubPart> getSubParts() {
    return this.subParts;
  }
}
