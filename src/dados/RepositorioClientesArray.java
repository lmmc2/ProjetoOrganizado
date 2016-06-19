package dados;

import classesbasicas.Cliente;
import classesbasicas.Produto;
import dados.RepositorioProdutos;
import exceptions.*;


public class RepositorioClientesArray implements RepositorioClientes { // cadastrar, remover, procurar, atualizar
	int maximo;
	private Cliente[] ArrayClientes;
	private int indice;

	public RepositorioClientesArray() {
		this.maximo = 100;
		this.ArrayClientes = new Cliente[maximo];
		this.indice = 0;
	}

	private int getIndice(String CPF) throws ClienteNaoEncontradoException {
		int i = 0;
		boolean achou = false;
		while ( i < indice || !achou) { // procura a posicao do array onde o Cliente procurado esta
			if (ArrayClientes[i].getCPF().equals(CPF)) { 
				achou = true; //se encontrar o indice, sai do laco e muda o valor da variavel para a excessao
			}
			i++;
		}
		if (!achou) {
			throw new ClienteNaoEncontradoException();
		} else {
			return i; //retorna a posicao do cliente no array
		}
	}

	public void inserir(Cliente cliente) {
		if (indice >= maximo) { // testo pra ver se o array ainda tem posicao disponivel
			Cliente[] newArrayClientes = new Cliente[2 * maximo]; // Caso esteja cheio, crio um array auxiliar para aumentar, com o dobro do tamanho
			for (int i = 0; i < maximo; i++) {
				newArrayClientes[i] = ArrayClientes[i]; // insiro os clientes ja cadastrados no array "antigo" no novo array
			}
			ArrayClientes = newArrayClientes; // o ponteiro o array antigo, agora aponta para o novo ponteiro
			maximo = 2 * maximo; // o inteiro auxiliar, que testa o tamanho do array recebe agora o dobro dele mesmo (ou seja, o novo tamanho)
		}

		ArrayClientes[indice] = cliente; // insere o cliente naquele indice
		indice++; // incrementa o indice para proximo cadastro
	}

	public void remover(String CPF)throws ClienteNaoEncontradoException{
		if (getIndice(CPF) != indice) {
			int i = getIndice(CPF);
			// caso encontre aquele cliente, a posicao dele recebe o cliente da
			// posicao segunte e o indice eh decrementado posteriormente
			if (ArrayClientes[i].getCPF().equals(CPF)) {
				for (int j = i; (j + 1) < indice; j++) {
					ArrayClientes[j] = ArrayClientes[j + 1];

				}
				indice--;
			}else{ 
				throw new ClienteNaoEncontradoException();
			}
		} 
	}

	public Cliente procurar(String CPF) throws ClienteNaoEncontradoException { 
		if (getIndice(CPF) != indice) { //
			return ArrayClientes[indice];
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}

	public void atualizar(Cliente clienteAtualizado) throws ClienteNaoEncontradoException {
		if (getIndice(clienteAtualizado.getCPF()) != indice) {
			int i = getIndice(clienteAtualizado.getCPF());
			if (ArrayClientes[i].getCPF() == clienteAtualizado.getCPF()) {
				ArrayClientes[i] = clienteAtualizado;
			}
		} else {
			throw new ClienteNaoEncontradoException();
		}
	}
	
	public boolean existe(String CPF){
		boolean existe = false;
		for(int i = 0; i<indice;i++){
			if(ArrayClientes[i].getCPF().equals(CPF)){
				existe = true;
			}
		}
		return existe;
		
	}	
		public void zerarGastos(String CPF) throws ClienteNaoEncontradoException{
			boolean achou = false;
			for(int i = 0; i<indice; i++){
				if(ArrayClientes[i].getCPF().equals(CPF)){
					achou = true;
					ArrayClientes[i].zerarGastos();
				}
			}
			if(!achou){
				throw new ClienteNaoEncontradoException();
			}
		}

		@Override
		public void pedido(String pedido, String CPF)
				throws ClienteNaoEncontradoException, ProdutoNaoCadastradoException {
			// TODO Auto-generated method stub
			
		}
		

}

