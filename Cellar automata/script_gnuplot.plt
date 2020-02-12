set style data lines
# Po³o¿enie ca³oœci:
set xlabel "Iteracja"
set ylabel "Liczba komorek (%)"
set origin 0,0
set size 1,1
set multiplot
# parametry rysunku nr 1:
set origin 0,0.5
set size 1,0.45
plot 'wyniki.txt' u 1:2 ti "Gracze wspolpracujacy",\
'wyniki.txt' u 1:4 ti "komorkiCA",\
'wyniki.txt' u 1:5 ti "komorkiLA",\
'wyniki.txt' u 1:9 ti "komorkiAllC",\
'wyniki.txt' u 1:10 ti "komorkiPC",\
'wyniki.txt' u 1:11 ti "komorkiAllD",\
'wyniki.txt' u 1:9 ti "komorkik-D"
# parametry rysunku nr 2:
set origin 0,0
set size 1,0.45
plot 'odchylenie_standardowe.txt' u 1:3 ti "Odchylenie stanow C",\
'odchylenie_standardowe.txt' u 1:4 ti "odchylenie komorek CA",\
'odchylenie_standardowe.txt' u 1:5 ti "odchylenie komorek LA",\
'odchylenie_standardowe.txt' u 1:7 ti "odchylenie strategii ALLC",\
'odchylenie_standardowe.txt' u 1:8 ti "odchylenie strategii PC",\
'odchylenie_standardowe.txt' u 1:9 ti "odchylenie strategii ALLD",\
'odchylenie_standardowe.txt' u 1:10 ti "Srednia strategii k-D"
unset multiplot
