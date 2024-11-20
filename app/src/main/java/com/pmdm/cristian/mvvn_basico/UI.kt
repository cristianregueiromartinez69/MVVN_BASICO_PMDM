package com.pmdm.cristian.mvvn_basico

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * Interfaz de usuario
 * Modificado desde Code
 */

@Composable
fun IU(miViewModel: MyViewModel) {
    // para que sea mas facil la etiqueta del log
    // val TAG_LOG = "miDebug"


    // botones en horizontal
    Column(
        modifier= Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround)
    {
        Column {
            Row {
                // creo un boton rojo
                Boton(miViewModel, Colores.CLASE_ROJO)

                // creo un boton verde
                Boton(miViewModel, Colores.CLASE_VERDE)
            }
            Row {
                // creo un boton azul
                Boton(miViewModel, Colores.CLASE_AZUL)

                // creo un boton amarillo
                Boton(miViewModel, Colores.CLASE_AMARILLO)
            }
            Row {
                ShowCuentaAtras("hola")
            }
        }
        // creao boton Start
        Boton_Start(miViewModel, Colores.CLASE_START)
    }
}

@Composable
fun Boton(miViewModel: MyViewModel, enum_color: Colores) {

    // para que sea mas facil la etiqueta del log
    val TAG_LOG = "miDebug"

    // variable para el estado del boton
    var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.boton_activo) }

    miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        // Log.d(TAG_LOG, "Oserver Estado: ${miViewModel.estadoLiveData.value!!.name}")
        _activo = miViewModel.estadoLiveData.value!!.boton_activo
    }

    // separador entre botones
    Spacer(modifier = Modifier.size(10.dp))

    Button(
        enabled = _activo,
        // utilizamos el color del enum
        colors =  ButtonDefaults.buttonColors(enum_color.color),
        onClick = {
            Log.d(TAG_LOG, "Dentro del boton: ${enum_color.ordinal}")
            miViewModel.comprobar(enum_color.ordinal)
        },
        modifier = Modifier
            .size((80).dp, (40).dp)
    ) {
        // utilizamos el texto del enum
        Text(text = enum_color.txt, fontSize = 10.sp)
    }
}

@Composable
fun ShowCuentaAtras(cuenta:String){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 46.dp, start = 10.dp)
    ) {

        Text(text = "Cuenta Atras: $cuenta" ,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black)

    }

}

@Composable
fun Boton_Start(miViewModel: MyViewModel, enum_color: Colores) {

    // para que sea mas facil la etiqueta del log
    val TAG_LOG = "miDebug"

    // variable para el estado del boton
    var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.start_activo) }

    // variable para el color del boton usado en el LaunchedEffect
    var _color by remember { mutableStateOf(enum_color.color) }


    miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        // Log.d(TAG_LOG, "Oserver Estado: ${miViewModel.estadoLiveData.value!!.name}")
        _activo = miViewModel.estadoLiveData.value!!.start_activo
    }

    // cremos el efecto de parpadear con Launchedffect
    // mientras el estado es INICIO el boton start parpadea
    // si cambia _activo, el LaunchedEffect se inicia o se para
    // https://developer.android.com/develop/ui/compose/side-effects?hl=es-419#launchedeffect

    LaunchedEffect(_activo) {
        Log.d(TAG_LOG, "LaunchedEffect - Estado: ${_activo}")
        // solo si el boton está activo parpadea
        while (_activo) {
            _color = enum_color.color_suave
            delay(100)
            _color = enum_color.color
            delay(500)
        }
    }
    // separador entre botones
    Spacer(modifier = Modifier.size(40.dp))
    Button(
        enabled = _activo,
        // utilizamos el color del enum
        // colors =  ButtonDefaults.buttonColors(enum_color.color),
        colors = ButtonDefaults.buttonColors(_color),
        onClick = {
            Log.d(TAG_LOG, "Dentro del Start - Estado: ${miViewModel.estadoLiveData.value!!.name}")
            miViewModel.crearRandom()
        },
        modifier = Modifier
            .size((100).dp, (40).dp)
    ) {
        // utilizamos el texto del enum
        Text(text = enum_color.txt, fontSize = 10.sp)
    }
}

/**
 * Preview de la interfaz de usuario
 */

@Preview(showBackground = true)
@Composable
fun IUPreview() {
    IU(MyViewModel())
}