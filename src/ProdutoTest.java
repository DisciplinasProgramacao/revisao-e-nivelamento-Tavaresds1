import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    static Produto produto;
    static Produto produtoPerecivelVencido;
    static Produto produtoPerecivelNaoVencido;
        
    
    @BeforeAll
    static public void prepare(){
        produto = new ProdutoNaoPerecivel("Produto teste", 100, 0.1);
        produtoPerecivelVencido = new ProdutoPerecivel("Produto Teste", 100, 0.1, LocalDate.now().plusDays(10));
        produtoPerecivelNaoVencido = new ProdutoPerecivel("Produto Teste", 100, 0.1, LocalDate.now());
    }
    
    @Test
    public void calculaPrecoCorretamente(){
        assertEquals(110.0, produto.valorDeVenda(), 0.01);
    }

    @Test
    public void calculaPrecoCorretamentePerecivelVencido(){
        assertEquals(82.5, produtoPerecivelVencido.valorDeVenda(), 0.01);
    }

    @Test
    public void calculaPrecoCorretamentePerecivelNaoVencido(){
        assertEquals(110, produtoPerecivelNaoVencido.valorDeVenda(), 0.01);
    }

    @Test
    public void stringComDescricaoEValor(){
        String desc = produto.toString();
        assertTrue(desc.contains("Produto teste") && desc.contains("R$ 110,00"));
    }

    @Test
    public void naoCriaProdutoComPrecoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> new ProdutoNaoPerecivel("teste", -5, 0.5));
    }
    
    @Test
    public void naoCriaProdutoComMargemNegativa(){
        assertThrows(IllegalArgumentException.class, () -> new ProdutoNaoPerecivel("teste", 5, -1));
    }
}
