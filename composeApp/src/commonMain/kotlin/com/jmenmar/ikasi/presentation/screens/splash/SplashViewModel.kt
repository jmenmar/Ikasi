package com.jmenmar.ikasi.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.ikasi.data.model.ActivityEntity
import com.jmenmar.ikasi.data.model.SettingsEntity
import com.jmenmar.ikasi.domain.model.ActivityType
import com.jmenmar.ikasi.domain.model.Word
import com.jmenmar.ikasi.domain.repository.IkasiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val ikasiRepository: IkasiRepository
): ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state

    init {
        getOnboarding()
        getTheme()
    }

    private fun getTheme() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect {
                _state.value = _state.value.copy(
                    darkTheme = it?.darkTheme ?: true,
                    isLoading = false
                )
            }
        }
    }
    private fun getOnboarding() {
        viewModelScope.launch {
            ikasiRepository.getSettings().collect {
                _state.value = _state.value.copy(
                    onboarding = it?.onboarding ?: true
                )
            }
        }
    }

    private fun importData(){
        viewModelScope.launch {
            ikasiRepository.newWord(Word(1,"Every now and then","de vez en cuando","sinónimos: ocasionally, from time to time"))
            ikasiRepository.newWord(Word(2,"Work something out","Calcular, resolver", null))
            ikasiRepository.newWord(Word(3,"On top of that","por si fuera poco, encima de eso","para colmo"))
            ikasiRepository.newWord(Word(4,"At this rate","a este paso", null))
            ikasiRepository.newWord(Word(5,"Gather","reunirse, recoger, deducir","1. Reunirse en un lugar\n2. Recoger de diferentes partes\n3. Deducir algo (to deduce)"))
            ikasiRepository.newWord(Word(6,"Watch over","Vigilar","sinónimo: to keep an eye on"))
            ikasiRepository.newWord(Word(7,"As well as","así como","I will invite my friends as well as my family"))
            ikasiRepository.newWord(Word(8,"On my own","por mi cuenta",null))
            ikasiRepository.newWord(Word(9,"Long story short","resumiendo, en resumen","sinónimos: summarizing, in summary"))
            ikasiRepository.newWord(Word(10,"Barely","apenas","1. Parecido a Hardly\n2.Ambos ya incorporan la negación en la frase."))
            ikasiRepository.newWord(Word(11,"Not a chance","ni hablar, ni pensarlo",null))
            ikasiRepository.newWord(Word(12,"Throughout","a lo largo de, durante todo","también puede significar en todo"))
            ikasiRepository.newWord(Word(13,"Frown upon","estar mal visto","desaprobar, ver con malos ojos\nsinónimo: Frown on"))
            ikasiRepository.newWord(Word(14,"Bring it on","vamos, venga, adelante","es una forma de retar a alguien"))
            ikasiRepository.newWord(Word(15,"Way better","mucho mejor","es como much better pero mas informal o coloquial"))
            ikasiRepository.newWord(Word(16,"Eventually","con el tiempo, finalmente, al final","after some time"))
            ikasiRepository.newWord(Word(17,"At first glance","a primera vista","glance: vista, mirada, ojeada, vistazo"))
            ikasiRepository.newWord(Word(18,"At the end of the day","al fin y al cabo, después de todo",null))
            ikasiRepository.newWord(Word(19,"Either one","cualquiera de los dos","alguno de los dos"))
            ikasiRepository.newWord(Word(20,"Regardless","a pesar de todo",null))
            ikasiRepository.newWord(Word(21,"Regardless of","sin tener en cuenta, al margen de","independientemente de"))
            ikasiRepository.newWord(Word(22,"Therefore","por lo tanto",null))
            ikasiRepository.newWord(Word(23,"In short","en resumen","en definitiva, en pocas palabras"))
            ikasiRepository.newWord(Word(24,"Care about","importar (algo)","I care about money\nI don't care about politics"))
            ikasiRepository.newWord(Word(25,"So that","para que, de modo que",null))
            ikasiRepository.newWord(Word(26,"Straightforward","sencillo, claro, directo",null))
            ikasiRepository.newWord(Word(27,"Not quite","no exactamente, no del todo","not at all\nnot entirely"))
            ikasiRepository.newWord(Word(28,"Awareness","conciencia",null))
            ikasiRepository.newWord(Word(29,"Aim","objetivo, aspirar","noun: objetivo\nverb: aspirar, pretender"))
            ikasiRepository.newWord(Word(30,"Have a point","tener razón","I guess you have a point"))
            ikasiRepository.newWord(Word(31,"Nearly","casi","sinónimo: almost"))
            ikasiRepository.newWord(Word(32,"Remarkable","extraordinario","notable, excepcional"))
            ikasiRepository.newWord(Word(33,"In terms of","desde el punto de vista","por lo que respecta a\nen cuanto a"))
            ikasiRepository.newWord(Word(34,"Face","afrontar, enfrentar","We have to face the facts"))
            ikasiRepository.newWord(Word(35,"Starving","muy hambriento, morirse de hambre","extremely hungry\nI'm starving - me muero de hambre"))
            ikasiRepository.newWord(Word(36,"Pull off","quitar / sacar adelante, lograr","1. Quitar, arrancar\n2.lograr, conseguir, sacar adelante"))
            ikasiRepository.newWord(Word(37,"Face off","enfrentamiento, enfrentarse",null))
            ikasiRepository.newWord(Word(38,"Nod","asentir, inclinar la cabeza",null))
            ikasiRepository.newWord(Word(39,"Still","a pesar de todo","se puede usar al comienzo de una frase con este significado. Parecido a after all o despite all"))
            ikasiRepository.newWord(Word(40,"Either way","de cualquier manera, en cualquier caso","es como anyway pero sólo con 2 alternativas.\nI don't know whether to walk or drive to the market, but either way is fine."))
            ikasiRepository.newWord(Word(41,"As per","según, de acuerdo con, conforme a","es como according to pero mas formal"))
            ikasiRepository.newWord(Word(42,"Indeed","de hecho, en efecto","1. de hecho\n2. en efecto, efectivamente"))
            ikasiRepository.newWord(Word(43,"Well spotted","Bien visto","expresión similar a good catch"))
            ikasiRepository.newWord(Word(44,"Along with","junto con",null))
            ikasiRepository.newWord(Word(45,"Before long","en breve, pronto, en poco tiempo",null))
            ikasiRepository.newWord(Word(46,"Eager","ansioso",null))
            ikasiRepository.newWord(Word(47,"Gamble","riesgo",null))
            ikasiRepository.newWord(Word(48,"Forecast","previsión",null))
            ikasiRepository.newWord(Word(49,"Gloomy day","Día triste, día gris","típico día de invierno con el cielo gris, lluvia..."))
            ikasiRepository.newWord(Word(50,"Relief","alivio","relieved = aliviado"))
            ikasiRepository.newWord(Word(51,"As we go","Sobre la marcha, según la marcha","sinónimos: on the fly, as we go along"))
            ikasiRepository.newWord(Word(52,"Speed up","acelerar", null))
            ikasiRepository.newWord(Word(53,"Ring a bell","sonar, recordar a algo","the name rang a bell - me sonaba el nombre\ndoesn't ring any bells - no me sonaba"))


            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20163,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.THEORY,20163,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.WRITING,20163,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20164,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.WRITING,20164,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.WRITING,20165,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.THEORY,20165,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.READING,20165,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20165,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.READING,20166,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20166,60))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.READING,20167,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.WRITING,20168,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20168,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.THEORY,20168,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.READING,20169,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20169,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.LISTENING,20170,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.WRITING,20170,30))
            ikasiRepository.newActivity(ActivityEntity(ActivityType.READING,20171,30))

            ikasiRepository.newSettings(SettingsEntity(0,false, 20163, true))
        }
    }
}