package client;

import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

import interfaces.AppInterface;
import parts.Part;

public class ClientService {

  Scanner sc = new Scanner(System.in);
  AppInterface stub;

  public ClientService(AppInterface stub) {
    this.stub = stub;
  }

  private boolean isUUID(String id) {
    try {
      UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return false;
    }
    return true;
  }
  
  private Part createPart() {
    System.out.println("Digite o nome da peça");
    String name = this.sc.nextLine();

    System.out.println("\nDigite uma descricao da peca");
    String description = this.sc.nextLine();

    return new Part(name, description);
  }

  public void listCommands() {
    System.out.println("Possiveis comandos: ");
    System.out.println("help");       // lista comandos
    System.out.println("lists");      // lista servidores
    System.out.println("addp");       // adiciona uma Pecas ao repositorio
    System.out.println("getp");       // busca peca e vira peca corrente
    System.out.println("listp");      // lista pecas do repositorio
    System.out.println("quit");       // encerra a execucao do cliente
  }

  public void lists() throws RemoteException {
    System.out.println(this.stub.lists());
  }

  public boolean addp() throws RemoteException {
    Part newPart = createPart();
    return this.stub.addp(newPart);
  }

  public void listp() throws RemoteException {
    System.out.println(this.stub.listp());
  }

  public void getp() throws RemoteException {
    System.out.println("Digite o ID da peça que deseja selecionar:");
    String inputId = sc.nextLine();

    System.out.println();
    if (!isUUID(inputId)) {
      System.out.println("ID está em formato inválido");
      return;
    }

    UUID id = UUID.fromString(inputId);

    Part part = this.stub.getp(id);
    boolean hasPart = part != null ? true : false;

    if (hasPart)
      System.out.printf("Peça '%s' selecionada\n", part.getName());
    else
      System.out.println("Peça nao encontrada");
  }
}
