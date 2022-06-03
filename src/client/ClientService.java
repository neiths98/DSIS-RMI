package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import interfaces.AppInterface;
import parts.Part;

public class ClientService {

  Scanner sc = new Scanner(System.in);
  AppInterface stub;

  public ClientService(AppInterface stub) {
    this.stub = stub;
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
    System.out.println("addp");       // adiciona uma Pecas ao repositorio
    System.out.println("listp");      // lista pecas do repositorio
    System.out.println("quit");       // encerra a execucao do cliente
  }

  public boolean addp() throws RemoteException {
    Part newPart = createPart();
    return this.stub.addp(newPart);
  }

  public void listp() throws RemoteException {
    System.out.println(this.stub.listp());
  }

}
