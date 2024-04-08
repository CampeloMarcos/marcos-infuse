package br.com.campelo.marcos.dto.response.abstracts;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractBasePageResponseBodyDTO<T> {

    @XmlElement
    @JsonProperty("page-number")
    private Integer pageNumber;
    @XmlElement
    @JsonProperty("has-next")
    private Boolean hasNext;
    @XmlElement
    @JsonProperty("has-previous")
    private Boolean hasPrevious;
    @XmlElement
    @JsonProperty("total-pages")
    private Integer totalPages;
    @XmlElement
    @JsonProperty("total-elements")
    private Long totalElements;
    @XmlElementWrapper
    @XmlElement
    private List<T> content;

    protected AbstractBasePageResponseBodyDTO() {
        content = new ArrayList<>();
    }
}
