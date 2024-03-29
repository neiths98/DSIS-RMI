package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.UUID;

import interfaces.AppInterface;
import parts.Part;
import utils.Utils;

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
    System.out.println("bind");       // seleciona um servidor
    System.out.println("lists");      // lista servidores
    System.out.println("addp");       // adiciona uma Pecas ao repositorio
    System.out.println("getp");       // busca peca e vira peca corrente
    System.out.println("listp");      // lista pecas do repositorio
    System.out.println("quit");       // encerra a execucao do cliente
  }

  public void bind() throws RemoteException {
    System.out.println("Digite o nome do servidor que deseja selecionar:");
    try {
      AppInterface newStub = (AppInterface) Naming.lookup(Utils.formatServerName(sc.nextLine()));
      this.stub = newStub;
      System.out.println("\nServidor selecionado com sucesso");
    } catch (MalformedURLException | NotBoundException e) {
      System.out.println("\nErro ao selecionar servidor");      
    }
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
    if (!Utils.isUUID(inputId)) {
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
