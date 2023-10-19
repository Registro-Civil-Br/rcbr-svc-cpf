package br.com.rcbr.svc.cpf.services;

import br.com.rcbr.svc.cpf.models.enums.EstadoEnum;
import br.com.rcbr.svc.cpf.models.responses.CpfResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class CpfService {

    private static final String DIGITO_ITERADO_ATUAL_OBTIDO = "Dígito iterado atual obtido: {}";

    public CpfResponse realizaCriacaoDeCpf(EstadoEnum estadoEnum) {
        log.info("Método responsável pela criação de CPF acessado");

        log.info("Iniciando obtenção de dígitos...");
        Integer digitosAleatorios = realizaCriacaoDosDigitosAleatoriosDoCpf();
        Integer digitoEstado = estadoEnum.getDigitoVerificadorCpf();
        Integer primeiroDigitoVerificador = realizaCalculoDoPrimeiroDigitoVerificador(digitosAleatorios);
        Integer segundoDigitoVerificador = realizaCalculoDoSegundoDigitoVerificador(digitosAleatorios, primeiroDigitoVerificador);
        log.info("Dígitos obtidos com sucesso");

        log.info("Iniciando acesso ao método de construção do com dígitos obtidos...");
        String cpf = constroiEstruturaCpf(
                digitosAleatorios, digitoEstado, primeiroDigitoVerificador, segundoDigitoVerificador);
        log.info("CPF construído com sucesso: {}", cpf);

        log.info("Iniciando instância de objeto para retorno do tipo CpfResponse...");
        CpfResponse cpfResponse = new CpfResponse(cpf);
        log.info("Objeto construído com sucesso: {}", cpfResponse.toString());

        log.info("Retornando valores...");
        return cpfResponse;
    }

    private Integer realizaCriacaoDosDigitosAleatoriosDoCpf() {
        int minimo = 11111111;
        int maximo = 99999999;

        int limiteRandomizacao = maximo - minimo + 1;
        return new Random().nextInt(limiteRandomizacao) + minimo;
    }

    private Integer realizaCalculoDoPrimeiroDigitoVerificador(Integer digitosAleatorios) {

        log.info("Método de cálculo do primeiro dígito verificador do CPF acessado");

        log.info("Convertendo digitos aleatórios gerados para string...");
        String digitosAleatoriosConvertidosParaString = String.valueOf(digitosAleatorios);
        log.info("Dígitos aleatórios convertidos para String com sucesso");

        log.info("Criando variáveis de iteração... (contador, somaMultiplicacaoDigitos)");
        int contador = 10;
        int somaMultiplicacaoDigitos = 0;
        int quantidadeDigitosAleatorios = digitosAleatoriosConvertidosParaString.length();
        log.info("Variáveis inicializadas com sucesso");

        log.info("Iniciando iteração pelos dígitos aleatórios gerados para o CPF...");
        for (int indiceDigitoAtual = 0;
             indiceDigitoAtual < quantidadeDigitosAleatorios;
             indiceDigitoAtual++) {

            log.info("Realizando obtenção do dígito iterado atual...");
            int digitoAtual = Integer.parseInt(
                    String.valueOf(
                            digitosAleatoriosConvertidosParaString.charAt(indiceDigitoAtual)
                    )
            );
            log.info(DIGITO_ITERADO_ATUAL_OBTIDO, digitoAtual);

            log.info("Realizando multiplicação {} (digitoAtual) * {} (contador) e incrementando resultado " +
                    "à variável somaMultiplicacaoDigitos ({})", digitoAtual, contador, somaMultiplicacaoDigitos);
            somaMultiplicacaoDigitos += (digitoAtual * contador);
            log.info("Incremento realizado com sucesso. Resultado atual: {}", somaMultiplicacaoDigitos);

            log.info("Decrementando contador...");
            contador--;
            log.info("Contador decrementado com sucesso. Valor atual: {}", contador);
        }
        log.info("Iteração finalizada com sucesso");

        log.info("Realizando divisão do total da multiplicação dos dígitos por 11 e obtendo resto...");
        int restoDaDivisaoPorOnze = somaMultiplicacaoDigitos % 11;
        log.info("Resto da divisão obtido com sucesso: {}", restoDaDivisaoPorOnze);

        log.info("Realizando operação de subtração (11 - restoDaDivisaoPorOnze) do dígito verificador...");
        int primeiroDigitoVerificador = ((restoDaDivisaoPorOnze == 0 || restoDaDivisaoPorOnze == 1)
                ? 0
                : 11 - restoDaDivisaoPorOnze);
        log.info("Operação realizada com sucesso. Primeiro dígito verificador: {}", primeiroDigitoVerificador);

        log.info("Retornando primeiro dígito verificador...");
        return primeiroDigitoVerificador;
    }

    private Integer realizaCalculoDoSegundoDigitoVerificador(Integer digitosAleatorios, Integer primeiroDigitoVerificador) {

        log.info("Método de cálculo do segundo dígito verificador do CPF acessado");

        log.info("Convertendo digitos aleatórios gerados para string e removendo seu primeiro dígito...");
        String digitosAleatoriosSemOPrimeiroDigito = String.valueOf(digitosAleatorios).substring(1, 9);

        log.info("Convertendo primeiro dígito verificador para string...");
        String primeiroDigitoVerificadorString = String.valueOf(primeiroDigitoVerificador);

        log.info("Concatenando digitos aleatórios sem o primeiro dígito com primeiro dígito verificador...");
        String digitosOperacao = digitosAleatoriosSemOPrimeiroDigito + primeiroDigitoVerificadorString;

        log.info("Criando variáveis de iteração... (contador, somaMultiplicacaoDigitos)");
        int contador = 10;
        int somaMultiplicacaoDigitos = 0;
        int quantidadeDigitosOperacao = digitosOperacao.length();
        log.info("Variáveis inicializadas com sucesso");

        log.info("Iniciando iteração pelos dígitos gerados para o CPF...");
        for (int indiceDigitoAtual = 0;
             indiceDigitoAtual < quantidadeDigitosOperacao;
             indiceDigitoAtual++) {

            log.info("Realizando obtenção do dígito iterado atual...");
            int digitoAtual = Integer.parseInt(
                    String.valueOf(
                            digitosOperacao.charAt(indiceDigitoAtual)
                    )
            );
            log.info(DIGITO_ITERADO_ATUAL_OBTIDO, digitoAtual);

            log.info(DIGITO_ITERADO_ATUAL_OBTIDO, digitoAtual);

            log.info("Realizando multiplicação {} (digitoAtual) * {} (contador) e incrementando resultado " +
                    "à variável somaMultiplicacaoDigitos ({})", digitoAtual, contador, somaMultiplicacaoDigitos);
            somaMultiplicacaoDigitos += (digitoAtual * contador);
            log.info("Incremento realizado com sucesso. Resultado atual: {}", somaMultiplicacaoDigitos);

            log.info("Decrementando contador...");
            contador--;
            log.info("Contador decrementado com sucesso. Valor atual: {}", contador);
        }

        log.info("Realizando divisão do total da multiplicação dos dígitos por 11 e obtendo resto...");
        int restoDaDivisaoPorOnze = somaMultiplicacaoDigitos % 11;
        log.info("Resto da divisão obtido com sucesso: {}", restoDaDivisaoPorOnze);

        log.info("Realizando operação de subtração (11 - restoDaDivisaoPorOnze) do dígito verificador...");
        int segundoDigitoVerificador = ((restoDaDivisaoPorOnze == 0 || restoDaDivisaoPorOnze == 1)
                ? 0
                : 11 - restoDaDivisaoPorOnze);
        log.info("Operação realizada com sucesso. Segundo dígito verificador: {}", segundoDigitoVerificador);

        log.info("Retornando segundo dígito verificador...");
        return segundoDigitoVerificador;
    }

    private String constroiEstruturaCpf(Integer digitosAleatorios,
                                        Integer digitoEstado,
                                        Integer primeiroDigitoVerificador,
                                        Integer segundoDigitoVerificador) {

        log.info("Iniciando construção do CPF...");
        String digitosAleatoriosString = String.valueOf(digitosAleatorios);

        String cpf = digitosAleatoriosString.substring(0, 3)
                + "."
                + digitosAleatoriosString.substring(3, 6)
                + "."
                + digitosAleatoriosString.substring(6, 8)
                + digitoEstado
                + "-"
                + primeiroDigitoVerificador
                + segundoDigitoVerificador;
        log.info("CPF construído com sucesso");

        log.info("Retornando valor do CPF...");
        return cpf;
    }

}
