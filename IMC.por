programa
{
	funcao inicio()
	{
		cadeia nome
		cadeia sobreNome
		inteiro idade
		cadeia sexo
		real altura
		real peso
		real IMC
		
		escreva("\n Digite seu nome: ")
		leia(nome)
	
		escreva("\n Digite seu sobrenome: ")
		leia(sobreNome)

		escreva("\n Digite sua idade: ")
		leia(idade)

		escreva("\n Digite seu sexo: ")
		leia(sexo)

		escreva("\n Digite sua altura (em metros): ")
		leia(altura)

		escreva("\n Digite seu peso (em kg): ")
		leia(peso)

		// O cálculo do IMC deve ser peso / (altura * altura)
		IMC = peso / (altura * altura)


		escreva("\nSeu nome é ", nome, " " + sobreNome )
		escreva( "\nIdade: ", idade)
		escreva( "\nSexo: ", sexo )
		escreva( "\nSeu IMC é: ", IMC )

		se (IMC >=25 e IMC <=30) { 
			escreva("\nSobrepeso - Você está acima do seu peso ideal.")
		} 
		senao se (IMC <18.5) { 
			escreva("\nAbaixo do peso - Você está abaixo do peso ideal.")
		} 
		senao se (IMC >=18.5 e IMC <25) { 
			escreva("\nParábens você esta no peso ideal")
		} 
		senao {
			escreva ("\nObesidade - grau I")
			}
		
	}
}
/* $$$ Portugol Studio $$$ 
 * 
 * Esta seção do arquivo guarda informações do Portugol Studio.
 * Você pode apagá-la se estiver utilizando outro editor.
 * 
 * @POSICAO-CURSOR = 718; 
 * @PONTOS-DE-PARADA = ;
 * @SIMBOLOS-INSPECIONADOS = ;
 * @FILTRO-ARVORE-TIPOS-DE-DADO = inteiro, real, logico, cadeia, caracter, vazio;
 * @FILTRO-ARVORE-TIPOS-DE-SIMBOLO = variavel, vetor, matriz, funcao;
 */