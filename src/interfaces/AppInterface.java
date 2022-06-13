package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import parts.Part;

public interface AppInterface extends Remote {

  // lista servidores ativos
  String lists() throws RemoteException;

  // adiciona nova peça ao repositorio corrente
  boolean addp(Part newPart) throws RemoteException;

  // adiciona a peca corrente a lista de subpecas corrente (a lista de subpecas corrente eh referente a ultima peca adicionada)
  boolean addsubpart(int quant) throws RemoteException;

  // esvazia lista de sub-pecas
  void clearlist() throws RemoteException;

  // lista peças do servidor corrente
  String listp() throws RemoteException;

  // busca peca e vira peca corrente
  Part getp(UUID id) throws RemoteException;

  // retorna peca corrente
  Part getcp() throws RemoteException;

  // lista atributos da peca selecionada
  String showp() throws RemoteException;

}