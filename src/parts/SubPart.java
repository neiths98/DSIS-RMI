package parts;

import java.io.Serializable;

public class SubPart implements Serializable {
  private Part part;
  private int quant;

  public SubPart(Part part, int quant) {
    this.part = part;
    this.quant = quant;
  }

  public Part getPart() {
    return this.part;
  }

  public int getQuant() {
    return this.quant;
  }

}
