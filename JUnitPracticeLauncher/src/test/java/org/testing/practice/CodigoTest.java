package org.testing.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CodigoTest {

    private Codigo codigo;

    @BeforeEach //Se hace una llamada a este metodo antes de ejecutar todos los tests
    public void init(){
        this.codigo = new Codigo();
    }

    @Test
    public void testSumar(){
        //given
        //Codigo codigo = new Codigo();
        //when
        Integer resultado  = codigo.sumar(4,4);
        //then
        Assertions.assertEquals(8,resultado);
        Assertions.assertInstanceOf(Integer.class, resultado);
    }

    @Test
    public void testSiElNumeroEsPositivo(){
        //given
        //Codigo codigo = new Codigo();
        Integer numero = 4;
        //when
        Boolean resultado = codigo.checkPositivo(numero);
        //then
        Assertions.assertTrue(resultado);
        Assertions.assertInstanceOf(Boolean.class, resultado);
    }

    @Test
    public void testSiElNumeroNoEsPositivo(){
        //given
        //Codigo codigo = new Codigo();
        Integer numero = -4;
       //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            codigo.checkPositivo(numero);
        });
    }


    @Test
    public void testContarLetrasA(){
        //given
        //Codigo codigo = new Codigo();
        String palabra = "Hola Web";

        //when
        Integer cantidad = codigo.contarLetrasA(palabra);

        //then
        Assertions.assertEquals(1,cantidad);
        Assertions.assertNotNull(cantidad);
    }


    @Test
    public void testContieneElemento(){
        //given
        List<String> listString = List.of("Hola", "Gente", "Pais");
        String stringABuscar = "Gente";

        //when
        Boolean resultado = codigo.contieneElemento(listString, stringABuscar);

        //then
        Assertions.assertTrue(resultado);
        Assertions.assertInstanceOf(Boolean.class, resultado);
    }

    @Test
    public void testRevertirCadena(){
        //given
        String string = "test";

        //when
        String resultado = codigo.revertirCadena(string);

        //then
        Assertions.assertEquals("tset", resultado);    }

    @Test
    public void testFactorial(){
        //given
        Integer numero = 3;

        //when
        Long resultado = codigo.factorial(numero);

        //then
        Assertions.assertEquals(6L, resultado);
    }

    @Test
    public void testFactorialError(){
        //given
        Integer numero = -3;

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            this.codigo.factorial(numero);
        });
    }

    @Test
    public void testEsPrimo(){
        //given
        Integer numeroPrimo  = 11;

        //when
        Boolean resultado = codigo.esPrimo(numeroPrimo);

        //then
        Assertions.assertTrue(resultado);
    }

    @Test
    public void testNoEsPrimoPorNegativo(){
        //given
        Integer numero = -1;

        //when
        Boolean resultado = this.codigo.esPrimo(numero);

        //then
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testNoEsPrimo(){
        //given
        Integer numero = 4;

        //when
        Boolean resultado = this.codigo.esPrimo(numero);

        //then
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testMensajeConRetraso() throws InterruptedException {
        //when
        String resultado = this.codigo.mensajeConRetraso();
        //then
        Assertions.assertEquals("Listo después de retraso", resultado);
    }

    @Test
    public void testConvertirAString(){
        //given
        List<Integer> listEnteros = List.of(1,2,3,4);

        //when
        List<String> listStrings = this.codigo.convertirAString(listEnteros);

        //then
        Assertions.assertEquals(List.of("1","2","3","4"), listStrings);
        Assertions.assertEquals(4, listStrings.size());
    }

    @Test
    public void testCalcularMedia(){
        //given
        List<Integer> listEnteros = List.of(7,10,10);

        //when
        Double resultado = this.codigo.calcularMedia(listEnteros);

        //then
        Assertions.assertEquals(9.0, resultado);
    }

    @Test
    public void testCalcularMediaErrorNull(){
        //given
        List<Integer> listEnteros = null;

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            this.codigo.calcularMedia(listEnteros);
        });

    }


    @Test
    public void testCalcularMediaErrorIsEmpty(){
        //given
        List<Integer> listEnteros = List.of();

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            this.codigo.calcularMedia(listEnteros);
        });

    }


    @Test
    public void testConvertirListaAString(){
        //given
        List<String> listaString=  List.of("H","o","l","a");

        //when
        String resultado = this.codigo.convertirListaAString(listaString);

        //then
        Assertions.assertEquals("H,O,L,A", resultado);
    }
}
