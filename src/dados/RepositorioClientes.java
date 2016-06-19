package dados;

import classesbasicas.Cliente;
import exceptions.*;

public interface RepositorioClientes {

	public void inserir(Cliente cliente);
	public void remover(String CPF) throws ClienteNaoEncontradoException;
	public void atualizar(Cliente cliente) throws ClienteNaoEncontradoException;
	public Cliente procurar(String CPF) throws ClienteNaoEncontradoException;
	public boolean existe(String CPF);
	public void pedido(String pedido, String CPF) throws ClienteNaoEncontradoException, ProdutoNaoCadastradoException;
	public void zerarGastos(String CPF) throws ClienteNaoEncontradoException;
	
}
