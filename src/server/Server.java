package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;

import interfaces.AppInterface;
import parts.Part;
import parts.PartRepository;

public class Server extends UnicastRemoteObject implements AppInterface {
  private UUID id;
  private String name;
  private PartRepository partRepository;

  public Server(String name) throws RemoteException {
    super();
    this.id = UUID.randomUUID();
    this.name = name;
    this.partRepository = new PartRepository(this.id);
  }

  public static void main(String[] args) throws RemoteException {
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o nome do servidor:");
    Server server = new Server(formatServerName(sc.nextLine()));

    sc.close();

    try {
      LocateRegistry.getRegistry("127.0.0.1", 1099); // localhost
      Naming.bind(server.name, server);

      System.out.printf("\nServidor %s levantou...\n", server.name);

    } catch (Exception e) {
      try {
        System.out.println("\nCriando rmiregistry na porta 1099");
        LocateRegistry.createRegistry(1099);
        Naming.bind(server.name, server);

        System.out.printf("\nServidor %s levantou...\n", server.name);

      } catch (RemoteException | AlreadyBoundException | MalformedURLException error) {
        error.printStackTrace();
      }
    }
  }

  private static String formatServerName(String serverName) {
    return serverName.trim().replaceAll(" ", "-").toUpperCase();
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public PartRepository getPartRepository() {
    return this.partRepository;
  }

  // ** Implementação de AppInterface ** 

  @Override
  public boolean addp(Part newPart) throws RemoteException {
    return this.partRepository.addPart(newPart);
  }

  @Override
  public String listp() throws RemoteException {
    return this.partRepository.listParts();
  }
}
