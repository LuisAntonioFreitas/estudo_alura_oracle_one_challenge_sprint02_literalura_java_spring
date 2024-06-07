package net.lanet.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageDtoData {
    @JsonAlias("language")
    private String language;

    public LanguageDtoData() {}
    public LanguageDtoData(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

}
