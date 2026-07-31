package com.app.dualshare.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {
    @Value("${FIREBASE_CONFIG}")
    private final  String firebaseConfig;

    @PostConstruct
    public void initializeFirebase() {
        try {

            InputStream serviceAccount =
                    new ByteArrayInputStream(
                            firebaseConfig.getBytes(StandardCharsets.UTF_8)
                    );

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(serviceAccount)
                            )
                            .build();

            if (FirebaseApp.getApps().isEmpty()) {

                FirebaseApp.initializeApp(options);

                System.out.println(
                        "====== [DUALSHARE] Firebase Admin SDK inicializado con éxito ======"
                );

            }
        } catch (
                IOException e) {
            System.err.println(
                    "Error al inicializar Firebase Admin SDK: "
                            + e.getMessage()
            );
        }
    }
}