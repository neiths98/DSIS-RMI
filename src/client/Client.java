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

      System.out.printf("\nServidor %s: Conexão estabelecida com sucesso\n\n", serverName);
      sc.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
