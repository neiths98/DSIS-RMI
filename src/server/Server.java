package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

import interfaces.AppInterface;
import parts.Part;
import parts.PartRepository;
import parts.SubPart;
import utils.Utils;

public class Server extends UnicastRemoteObject implements AppInterface {
  private UUID id;
  private String name;
  private PartRepository partRepository;
  private Part currenPart;
  private Vector<SubPart> currentSubParts;
  private Registry registry;

  public Server(String name) throws RemoteException {
    super();
    this.id = UUID.randomUUID();
    this.name = name;
    this.partRepository = new PartRepository(this.id);
    this.currentSubParts = new Vector<SubPart>();
  }

  public static void main(String[] args) throws RemoteException {
    Scanner sc = new Scanner(System.in);

    System.out.println("Digite o nome do servidor:");
    Server server = new Server(Utils.formatServerName(sc.nextLine()));

    sc.close();

    Registry registry;

    try {
      registry = LocateRegistry.getRegistry("127.0.0.1", 1099); // localhost
      Naming.bind(server.name, server);

      server.setRegistry(registry);

      System.out.printf("\nServidor %s levantou...\n", server.name);

    } catch (Exception e) {
      try {
        System.out.println("\nCriando rmiregistry na porta 1099");

        registry = LocateRegistry.createRegistry(1099);
        Naming.bind(server.name, server);

        server.setRegistry(registry);

        System.out.printf("\nServidor %s levantou...\n", server.name);

      } catch (RemoteException | AlreadyBoundException | MalformedURLException error) {
        error.printStackTrace();
      }
    }
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

  public Registry getRegistry() {
    return this.registry;
  }

  public void setRegistry(Registry registry) {
    this.registry = registry;
  }

  // ** Implementação de AppInterface ** 

  @Override
  public String lists() throws RemoteException {
    StringBuilder sBuilder = new StringBuilder();

    String[] servers = this.registry.list();

    for (String s : servers) {
      sBuilder.append(s + "\n");
    }

    return sBuilder.toString();
  }

  @Override
  public boolean addp(Part newPart) throws RemoteException {
    if (this.currentSubParts != null && this.currentSubParts.size() != 0)
      newPart.addSubParts(this.currentSubParts);

    return this.partRepository.addPart(newPart);
  }

  @Override
  public boolean addsubpart(int quant) throws RemoteException {
    SubPart subPart = new SubPart(this.currenPart, quant);
    return this.currentSubParts.add(subPart);
  }

  @Override
  public String listp() throws RemoteException {
    return this.partRepository.listParts();
  }

  @Override
  public Part getp(UUID id) throws RemoteException {
    this.currenPart = this.partRepository.getPartById(id);

    return this.currenPart;
  }

  @Override
  public Part getcp() throws RemoteException {
    return this.currenPart;
  }

  @Override
  public String showp() throws RemoteException {
    StringBuilder sBuilder = new StringBuilder();
    if (this.currenPart == null)
      return "Nenhuma peca foi selecionada";

    sBuilder.append(String.format("ATRIBUTOS DA PECA '%s':\n\n", this.currenPart.getName().toUpperCase()));
    sBuilder.append(String.format("ID:                %s\n", this.currenPart.getId().toString()));
    sBuilder.append(String.format("NOME:              %s\n", this.currenPart.getName()));
    sBuilder.append(String.format("DESCRICAO:         %s\n", this.currenPart.getDescription()));
    sBuilder.append(String.format("QTD DE SUBPECAS:   %d\n", this.currenPart.getSubParts().size()));

    if (this.currenPart.getSubParts().size() == 0)
      return sBuilder.toString();

    sBuilder.append("LISTA DE SUBPECAS:\n");
    int i = 1;
    for (SubPart subPart : this.currenPart.getSubParts()) {
      sBuilder.append(String.format("\nSUBPECA %d:\n\n", i));
      sBuilder.append(String.format("ID:              %s\n", subPart.getPart().getId().toString()));
      sBuilder.append(String.format("NOME:            %s\n", subPart.getPart().getName()));
      sBuilder.append(String.format("DESCRICAO:       %s\n", subPart.getPart().getDescription()));
      sBuilder.append(String.format("QUANTIDADE:      %s\n", subPart.getQuant()));
      i++;
    }

    return sBuilder.toString();
  }

}
