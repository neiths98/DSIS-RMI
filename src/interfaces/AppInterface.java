package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import parts.Part;

public interface AppInterface extends Remote {

  // adiciona nova peça ao repositorio corrente
  boolean addp(Part newPart) throws RemoteException;

  // lista peças do servidor corrente
  String listp() throws RemoteException;

}