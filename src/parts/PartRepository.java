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
    this.parts = new Vector<Part>();
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

  public boolean addPart(Part newPart) {
    return this.parts.add(newPart);
  }

  public void listParts() {
    if (this.parts.isEmpty()) {
      System.out.println("REPOSITORIO VAZIO");
      return;
    }

    System.out.printf("%-37s| %-37s| %-37s| %-37s\n","ID", "NOME", "DESCRICAO", "QTD SUB PARTES");
    System.out.printf("%-37s+%-38s+%-38s+%-37s\n", "-".repeat(37), "-".repeat(38), "-".repeat(38), "-".repeat(37));
    for (Part part : this.parts) {
      int maxLength = 30;
      String partId = part.getId().toString();
      String partName = part.getName();
      String partDescription = part.getDescription();
      int subPartsQtd = part.getSubParts().size();

      if (partDescription.length() >= maxLength)
        partDescription = partDescription.substring(0, maxLength - 3) + "...";

      System.out.printf("%-37s| %-37s| %-37s| %-37d\n", partId, partName, partDescription, subPartsQtd);
      System.out.printf("%-37s+%-38s+%-38s+%-37s\n", "-".repeat(37), "-".repeat(38), "-".repeat(38), "-".repeat(37));
    }
  }

}
