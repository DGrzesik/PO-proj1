# PO-proj1
Projekt nie uwzględnia wykresów, rysowania dwóch map i zbierania parametrów.
Wszystkie parametry są w klasie App w metodzie start().
Projekt powinien spełniać wszystkie funkcjonalności dnia, to jest:
  - zwierzęta o energii równej 0 są usuwane
  - zwierzęta wykonują ruchy
  - zwierzęta jedzą i rozmnażają się
  - trawa rośnie - na sawannie i w dżungli

Dane szczegółowe na temat dnia, typu - która trawa jest jedzona przez jakie zwierzę, są wypisywane w konsoli razem z mapą - ostatnie przy użyciu MapVisualiser.\
Wyświetla się jedna mapa, w związku z tym uznałem, że aplikacja będzie jednowątkowa, mapę można wybrać podmieniając wywołanie konstruktora w App:
  - ConstantMap - stała,
  - WalllessMap - zawijająca się.
