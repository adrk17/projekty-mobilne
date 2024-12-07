# Konwersja architektury MVI na MVVM

## Przegląd
Ten przewodnik pomoże Ci przekształcić aplikację z architektury MVI (Model-View-Intent) na MVVM (Model-View-ViewModel) w projekcie Android.

## Kroki do wykonania

### 1. Przygotowanie projektu
- Stwórz nowy branch w repozytorium
- Skopiuj całą zawartość folderu z kodem źródłowym
- Utwórz nowy pakiet dla kodu MVVM

### 2. Przekształcenie struktury Intent na ViewModel
1. Dla każdego pliku `*Intent.kt`:
   - Utwórz odpowiadający mu `*ViewModel.kt`
   - Zmień nazwę klasy z `*Intent` na `*ViewModel`
   - Dodaj dziedziczenie po `ViewModel()`
   - Usuń interfejs `MviIntent`

2. Przekształć stany:
   ```kotlin
   // Przed (MVI)
   private val _state = MutableStateFlow(State())
   val state = _state.asStateFlow()

   // Po (MVVM)
   private val _state = MutableLiveData<State>()
   val state: LiveData<State> = _state
   ```

### 3. Przekształcenie metod
1. Zamień metody `processIntent`:
   ```kotlin
   // Przed (MVI)
   fun processIntent(intent: *Intent) {
       when (intent) {
           is *Intent.Action -> handleAction()
       }
   }

   // Po (MVVM)
   fun handleAction() {
       viewModelScope.launch {
           // implementacja
       }
   }
   ```

2. Usuń metodę `reduce` i zastąp ją bezpośrednimi aktualizacjami:
   ```kotlin
   // Przed (MVI)
   private fun reduce(state: State, result: Result): State {
       return when (result) {
           is Result.Success -> state.copy(data = result.data)
           is Result.Error -> state.copy(error = result.error)
       }
   }

   // Po (MVVM)
   private fun updateState(data: Data) {
       _state.value = _state.value?.copy(data = data)
   }
   ```

### 4. Aktualizacja widoku (Activity/Fragment)
1. Zmień sposób obserwacji stanu:
   ```kotlin
   // Przed (MVI)
   lifecycleScope.launch {
       intent.state.collect { state ->
           updateUI(state)
       }
   }

   // Po (MVVM)
   viewModel.state.observe(viewLifecycleOwner) { state ->
       updateUI(state)
   }
   ```

2. Zamień wysyłanie intencji na wywołania metod:
   ```kotlin
   // Przed (MVI)
   intent.processIntent(SampleIntent.LoadData)

   // Po (MVVM)
   viewModel.loadData()
   ```

### 5. Aktualizacja testów
1. Zaktualizuj testy jednostkowe:
   - Dodaj `InstantTaskExecutorRule` dla LiveData
   - Użyj `TestCoroutineDispatcher` dla korutyn
   - Zamień asercje związane z Flow na LiveData

2. Przykład testu:
   ```kotlin
   @Test
   fun `test loading data`() = runTest {
       // Arrange
       val viewModel = SampleViewModel()
       
       // Act
       viewModel.loadData()
       
       // Assert
       val state = viewModel.state.getOrAwaitValue()
       assertEquals(expectedData, state.data)
   }
   ```

## Najlepsze praktyki
1. Zachowaj niemutowalność stanu
2. Używaj ViewModelScope dla korutyn
3. Implementuj obsługę błędów
4. Zachowaj jednokierunkowy przepływ danych

## Częste problemy
- Pamiętaj o prawidłowym zarządzaniu cyklem życia
- Unikaj mieszania wzorców MVI i MVVM podczas konwersji
- Sprawdź, czy wszystkie zasoby są prawidłowo zwalniane
- Przetestuj zachowanie przy zmianach konfiguracji

## Przykład pełnej konwersji

### Przed (MVI):
kotlin
data class State(val data: String = "", val isLoading: Boolean = false)

sealed class SampleIntent {
    object LoadData : SampleIntent()
}

class MyIntent @Inject constructor(
    private val repository: Repository
) : MviIntent {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()
    
    fun processIntent(intent: SampleIntent) {
        when (intent) {
            is SampleIntent.LoadData -> loadData()
        }
    }
    
    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val result = repository.getData()
                _state.value = _state.value.copy(
                    data = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}
```

### Po (MVVM):
```kotlin
class MyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableLiveData(State())
    val state: LiveData<State> = _state
    
    fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val result = repository.getData()
                _state.value = _state.value?.copy(
                    data = result,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}
`````` 