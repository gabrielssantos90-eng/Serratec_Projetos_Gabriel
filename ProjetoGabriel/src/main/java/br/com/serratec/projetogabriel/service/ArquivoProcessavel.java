package br.com.serratec.projetogabriel.service;

import br.com.serratec.projetogabriel.model.Funcionario;
import java.util.List;

/**
 * Interface para processamento de arquivos.
 */
public interface ArquivoProcessavel {
    
    /**
     * Lê funcionários e dependentes de um arquivo CSV.
     * @param caminhoArquivo O caminho do arquivo de entrada
     * @return Lista de funcionários processados
     * @throws Exception Se houver erro na leitura do arquivo
     */
    List<Funcionario> lerArquivo(String caminhoArquivo) throws Exception;
    
    /**
     * Escreve os dados de folha de pagamento em um arquivo CSV.
     * @param funcionarios Lista de funcionários processados
     * @param caminhoArquivo O caminho do arquivo de saída
     * @throws Exception Se houver erro na escrita do arquivo
     */
    void escreverArquivo(List<Funcionario> funcionarios, String caminhoArquivo) throws Exception;
}