programa
{
	inclua biblioteca Util
	
	funcao inicio()
	{
		inteiro sequencia
		cadeia continuar = "s"

		enquanto(continuar == "s" ou continuar == "S")
		{
			faca
			{
				escreva("Escreva um número entre 0 e 10 para ver sua tabuada: ")
				leia(sequencia)
				
				se(nao (sequencia >= 0 e sequencia <=10))
				{
					escreva("Número inválido! Digite um número entre 0 e 10.\n")
				}
			}
			enquanto(nao (sequencia >= 0 e sequencia <=10))
			
			escreva("\n--- Tabuada do ", sequencia, " ---\n")
			
			para(inteiro numero = 1; numero <=10; numero = numero + 1)
			{
				escreva(sequencia, " x ", numero, " = ", (sequencia * numero), "\n")
			}
			
			escreva("\nDeseja calcular outra tabuada? (s/n): ")
			leia(continuar)
		}
		
		escreva("Programa finalizado. Obrigado!")
	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta seção do arquivo guarda informações do Portugol Studio.
 * Você pode apagá-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 326; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */