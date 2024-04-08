package br.com.campelo.marcos.dto.request.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderRequestQueryParamEnum {

	ASC("Crescent"), DESC("Descending");

    private String description;
}