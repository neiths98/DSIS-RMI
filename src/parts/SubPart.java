package parts;

public class SubPart {
  private Part part;
  private int quant;

  SubPart(Part part, int quant) {
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
