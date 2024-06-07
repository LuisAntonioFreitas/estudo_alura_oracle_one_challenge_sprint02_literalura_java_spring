package net.lanet.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDtoBook {
    @JsonAlias("results")
    private List<BookDtoData> results;

    public ResponseDtoBook() {};
    public ResponseDtoBook(
            List<BookDtoData> results) {
        this.results = results;
    }

    public List<BookDtoData> getResults() {
        return results;
    }
}
