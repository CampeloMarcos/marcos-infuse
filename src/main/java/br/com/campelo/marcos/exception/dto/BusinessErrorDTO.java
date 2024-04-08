package br.com.campelo.marcos.exception.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessErrorDTO<K extends ErrorDTO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<K> errors;
    
    public BusinessErrorDTO<K> withNewErrorList() {
        this.errors = new ArrayList<>();
        return this;
      }

      public BusinessErrorDTO<K> withErrorAdd(K error) {
        this.errors.add(error);
        return this;
      }

      public BusinessErrorDTO<K> build() {
        return new BusinessErrorDTO<>(errors);
      }
}
