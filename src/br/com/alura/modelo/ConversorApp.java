package br.com.alura.modelo;

import br.com.alura.servico.ConsultaAPI;

import java.util.Scanner;

public class ConversorApp {
    Scanner leitura = new Scanner(System.in);
    ConsultaAPI consultaAPI = new ConsultaAPI();

    public void mostraMenu() {
        int opcao = 0;

        while (opcao != 7) {
            System.out.println("""
                    *******************************************************
                    Bem vindo ao Conversor de Moeda!
                    *******************************************************

                            1) Dólar(USD) =>> Peso Argentino(ARS)
                            2) Peso Argentino(ARS) =>> Dólar(USD)
                            3) Dólar(USD) =>> Real Brasileiro(BRL)
                            4) Real Brasileiro(BRL) =>> Dólar(USD)
                            5) Dólar(USD) =>> Peso Colombiano(COP)
                            6) Peso Colombiano(COP) =>> Dólar(USD)
                            7) Sair
                            
                    *******************************************************
                    """);
            System.out.println("Digite a opção desejada: ");
            opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    realizarConversao("USD", "ARS", "Dólar", "Peso Argentino");
                    break;
                case 2:
                    realizarConversao("ARS", "USD", "Peso Argentino", "Dólar");
                    break;
                case 3:
                    realizarConversao("USD", "BRL", "Dólar", "Real Brasileiro");
                    break;
                case 4:
                    realizarConversao("BRL", "USD", "Real Brasileiro", "Dólar");
                    break;
                case 5:
                    realizarConversao("USD", "COP", "Dólar", "Peso Colombiano");
                    break;
                case 6:
                    realizarConversao("COP", "USD", "Peso Colombiano", "Dólar");
                    break;
                case 7:
                    System.out.println("Programa encerrado!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Selecione uma opção válida");
                    break;
            }
        }
    }

    private void realizarConversao(String moedaInicial, String moedaFinal, String nomeMoedaInicial, String nomeMoedaFinal) {
        System.out.println(nomeMoedaInicial + " => " + nomeMoedaFinal);
        System.out.println("Digite o valor que deseja converter: ");
        double valor = leitura.nextDouble();

        var moeda = consultaAPI.consultaMoeda(moedaInicial, moedaFinal);
        double moedaConvertida = Double.parseDouble(moeda.conversion_rate());

        double valorConvertido = valor * moedaConvertida;

        System.out.printf("O valor de %.2f %s convertido para %s ficou em %.5f %s%n", valor, nomeMoedaInicial, nomeMoedaFinal, valorConvertido, nomeMoedaFinal);
    }
}