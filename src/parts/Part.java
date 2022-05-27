package parts;

import java.util.UUID;
import java.util.Vector;

public class Part {
  UUID id;
  String name;
  String description;
  Vector<SubPart> subParts;

  Part(String name, String description, Vector<SubPart> subParts) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.subParts = subParts;
  }
}
