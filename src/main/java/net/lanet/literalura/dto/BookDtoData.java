package net.lanet.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

import static net.lanet.literalura.main.Main.DISPLAY_LIVROS;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDtoData {
    @JsonAlias("title")
    private String title;
    @JsonAlias("authors")
    private List<AuthorDtoData> authors;
    @JsonAlias("languages")
    private List<LanguageDtoData> languages;
    @JsonAlias("download_count")
    private Integer downloadCount;

    public BookDtoData() {};
    public BookDtoData(String title,
                       List<AuthorDtoData> authors,
                       List<LanguageDtoData> languages,
                       Integer downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return DISPLAY_LIVROS.formatted(
                this.title,
                this.authors.stream().map(e -> e.getName()).collect(Collectors.joining(" | ")),
                this.languages.stream().map(e -> e.getLanguage()).collect(Collectors.joining(" | ")),
                this.downloadCount
        );
    }

    public String getTitle() {
        return title;
    }

    public List<AuthorDtoData> getAuthors() {
        return authors;
    }

    public List<LanguageDtoData> getLanguages() {
        return languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }
}
