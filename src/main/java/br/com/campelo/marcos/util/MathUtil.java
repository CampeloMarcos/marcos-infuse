package br.com.campelo.marcos.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtil {

    public static BigDecimal calculateDiscount(BigDecimal valor, BigDecimal percente) {
        return valor.subtract(valor.multiply(percente.divide(percente)).setScale(2, RoundingMode.HALF_UP));
    }
}
