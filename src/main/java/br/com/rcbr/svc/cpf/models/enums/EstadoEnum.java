package br.com.rcbr.svc.cpf.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoEnum {

    ACRE("AC", "Acre", 2),
    ALAGOAS("AL", "Alagoas", 4),
    AMAPA("AP", "Amapá", 2),
    AMAZONAS("AM", "Amazonas", 2),
    BAHIA("BA", "Bahia", 5),
    CEARA("CE", "Ceará", 3),
    ESPIRITO_SANTO("ES", "Espírito Santo", 7),
    GOIAS("GO", "Goiás", 1),
    MARANHAO("MA", "Maranhão", 3),
    MATO_GROSSO("MT", "Mato Grosso", 1),
    MATO_GROSSO_DO_SUL("MS", "Mato Grosso do Sul", 1),
    MINAS_GERAIS("MG", "Minas Gerais", 6),
    PARA("PA", "Pará", 2),
    PARAIBA("PB", "Paraíba", 4),
    PARANA("PR", "Paraná", 9),
    PERNAMBUCO("PE", "Pernambuco", 4),
    PIAUI("PI", "Piauí", 3),
    RIO_DE_JANEIRO("RJ", "Rio de Janeiro", 7),
    RIO_GRANDE_DO_NORTE("RN", "Rio Grande do Norte", 4),
    RIO_GRANDE_DO_SUL("RS", "Rio Grande do Sul", 0),
    RONDONIA("RO", "Rondônia", 2),
    RORAIMA("RR", "Roraima", 2),
    SANTA_CATARINA("SC", "Santa Catarina", 9),
    SAO_PAULO("SP", "São Paulo", 8),
    SERJIPE("SE", "Serjipe", 5),
    TOCANTINS("TO", "Tocantins", 1),
    DISTRITO_FEDERAL("DF", "Distrito Federal", 1);

    private final String prefixo;
    private final String desc;
    private final int digitoVerificadorCpf;

}
