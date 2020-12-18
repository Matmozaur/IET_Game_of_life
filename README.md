# Game_of_life

Symulacje uruchamiamy z klasy World znajdującej się w pakiecie agh.cs.lab8.<br>
Możemy zmieniać parametry symulacji ustawiając je w pliku params.json, o nastepującej strukturze (wartości przykładowe):<br>

{<br>
  "width": 20, - szerokość mapy<br>
  "height": 20, - wysokość mapy<br>
  "jungle_ratio": 0.01, - stosunek dżungli do mapy<br>
  "number_of_plants": 10, - początkow liczba roślin<br>
  "plants_energy": 10, - energia dostarczana po zjedzeniu rośliny<br>
  "start_energy": 50, - początkowa energia<br>
  "number_of_animals": 10, - początkowa liczba zwierząt<br>
  "move_energy": 1, - energia potrzebna do ruchu<br>
  "delay": 100, - czas między epokami<br>
  "days_before_snapshot": 100 - ilośc epok zanim ściągniemy statystyki do plku<br>
}<br>
Statystyki ściągnięte po wyznaczonej liczbie dni zapisują się w odpowiednim pliku o nazwie raport[id symulacji].txt
