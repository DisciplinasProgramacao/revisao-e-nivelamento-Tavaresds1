import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    private static final double DESCONTO = 0.25;
    private static final int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate validade) {
        super(desc, precoCusto, margemLucro);
        dataDeValidade = validade;
    }

    public double valorDeVenda() {
        if (dataDeValidade != null && LocalDate.now().isBefore(dataDeValidade.minusDays(PRAZO_DESCONTO))) {
            return (precoCusto * (1 + margemLucro)) * (1 - DESCONTO);
        } else {
            return precoCusto * (1 + margemLucro);
        }
    }
}
