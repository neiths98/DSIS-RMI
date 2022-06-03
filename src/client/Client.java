package client;

import java.rmi.Naming;
import java.util.Scanner;

import interfaces.AppInterface;

public class Client {

  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);

      System.out.println("Digite o nome do servidor que deseja se conectar");
      String serverName = sc.nextLine().trim().replaceAll(" ", "-").toUpperCase();

      AppInterface stub = (AppInterface) Naming.lookup(serverName);
      ClientService clientService = new ClientService(stub);

      System.out.printf("\nServidor %s: Conexão estabelecida com sucesso\n\n", serverName);

      clientService.listCommands();
      while (true) {
        System.out.println("\nDigite um comando:");
        String command = sc.nextLine();
        System.out.println();

        switch (command) {
          case "addp":
            clientService.addp();
            break;
          case "listp":
            clientService.listp();
            break;
          case "help":
            clientService.listCommands();
            break;
          case "quit":
            sc.close();
            System.exit(0);
          case "":
            break;
          default:
            System.err.println("Comando nao encontrado: '" + command + "'.");
            break;
        }
        System.out.println();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
