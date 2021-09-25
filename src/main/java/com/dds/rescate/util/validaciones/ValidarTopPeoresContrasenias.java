package com.dds.rescate.util.validaciones;


import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.dds.rescate.helper.LectorArchivos;

import static com.google.common.io.Resources.getResource;

public class ValidarTopPeoresContrasenias extends ValidacionContrasenia {

    private List<String> contrasenias;

    public ValidarTopPeoresContrasenias() {
        super("La Password es debil (presente en top 10k)");

        URL urlArchivo = getResource("10k-most-common.txt");
        LectorArchivos lectorArchivos = new LectorArchivos(urlArchivo);

        try {
			contrasenias = lectorArchivos.devolverContenidoComoListaDeStrings();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Verifica mediante un archivo txt de 10k peores contrasenias
    @Override
    public boolean condicion(String username, String password) {
        return contrasenias.stream().anyMatch(contrasenia -> contrasenia.equals(password));
    }

}