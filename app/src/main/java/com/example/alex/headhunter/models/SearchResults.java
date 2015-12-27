package com.example.alex.headhunter.models;

import java.util.List;

public class SearchResults {
    private int per_page;
    private int page;
    private int pages;
    private List<VacancyShort> items;

    public int getPer_page() {
        return per_page;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<VacancyShort> getItems() {
        return items;
    }
}
