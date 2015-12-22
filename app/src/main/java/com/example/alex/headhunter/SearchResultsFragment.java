package com.example.alex.headhunter;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


public class SearchResultsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        WebView  description = (WebView) view.findViewById(R.id.description);

        description.loadData("<p><strong>Чем нужно будет заниматься: </strong></p> <ul> <li>Разработка нового мобильного приложения под платформу Android</li> <li>Взаимодействие с аналитиками, дизайнерами и тестировщиками.</li> <li>Выявление и исправление ошибок в работе текущего приложения</li> </ul> <p> </p> <p><strong>Требования:</strong></p> <ul> <li>Опыт разработки мобильных приложений - от 1 года;</li> <li>Наличие завершенных проектов, разработанных самостоятельно или в команде разработчиков - приветствуется;</li> <li>Умение работать с Java, модель клиент-сервер;</li> <li>Технический английский язык.</li> </ul> <p> </p> <p><strong>Условия:</strong></p> <ul> <li>Зарплата по результатам собеседования</li> <li>Работа на территории работодателя в команде программистов</li> <li>Полная занятость</li> <li>Оформление по ТК РФ</li> </ul>",
                "text/html; charset=utf-8", null);

    }

}
