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

  public Part getPartById(UUID partId) {
    for (Part p : this.parts) {
      UUID pId = p.getId();
      if (pId.equals(partId))
        return p;
    }
    return null;
  }

  public boolean addPart(Part newPart) {
    return this.parts.add(newPart);
  }

  public boolean removePart(UUID partId) {
    Part removePart = this.getPartById(partId);
    if (removePart != null)
      return this.parts.remove(removePart);
    else
      return false;
  }

  public String listParts() {
    if (this.parts.isEmpty())
      return "REPOSITORIO VAZIO";

    StringBuilder sBuilder = new StringBuilder();
    
    sBuilder.append(String.format("%-37s| %-37s| %-37s| %-37s\n","ID", "NOME", "DESCRICAO", "QTD SUB PARTES"));
    sBuilder.append(String.format("%-37s+%-38s+%-38s+%-37s\n", "-".repeat(37), "-".repeat(38), "-".repeat(38), "-".repeat(37)));

    for (Part part : this.parts) {
      int maxLength = 30;
      String partId = part.getId().toString();
      String partName = part.getName();
      String partDescription = part.getDescription();
      int subPartsQtd = part.getSubParts().size();

      if (partDescription.length() >= maxLength)
        partDescription = partDescription.substring(0, maxLength - 3) + "...";

      sBuilder.append(String.format("%-37s| %-37s| %-37s| %-37d\n", partId, partName, partDescription, subPartsQtd));
      sBuilder.append(String.format("%-37s+%-38s+%-38s+%-37s\n", "-".repeat(37), "-".repeat(38), "-".repeat(38), "-".repeat(37)));
    }

    return sBuilder.toString();
  }
}
